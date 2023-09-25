package com.xbfinal.controller;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @Title: 实现文件分片下载
 * @Author 笑霸fianl
 * @Package com.xbfinal.controller
 * @Date 2023/9/24 16:38
 * @描述: 实现文件分片下载   /fileService/down.do?url=https://pic35.photophoto.cn/20150511/0034034892281415_b.jpg
 */
@WebServlet("/fileService/down.do")
public class FileDownloadServlet extends HttpServlet {
    private static final int NUM_THREADS = 4; // 设置线程数
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String url = request.getParameter("url");
        if (url == null || url.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            // 从URL中提取文件名
            String fileName = url.substring(url.lastIndexOf('/') + 1);

            String tempDir = System.getProperty("java.io.tmpdir");
            String downloadFolder = tempDir + File.separator + "downloads" + File.separator;


            System.out.println("文件目录："+downloadFolder);
            File downloadDir = new File(downloadFolder);

            if (!downloadDir.exists()) {
                downloadDir.mkdirs();
            }

            // 下载文件到服务器并切片
            String localFilePath = downloadFolder + fileName;
            downloadFileInThreads(url, localFilePath);

            String downloadLink = request
                    .getRequestURL()
                    .toString()
                    .replace("/fileService/down.do", "/downloadFromTemp") + "?name=" + fileName;

            response.getWriter().write(downloadLink);

            try (InputStream inputStream = new FileInputStream(localFilePath);
                 OutputStream outputStream = response.getOutputStream()) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void downloadFileInThreads(String fileUrl, String destinationPath) throws IOException {
        URL url = new URL(fileUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        int fileSize = connection.getContentLength();

        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);

        // 计算每个线程下载的字节数
        int sliceSize = fileSize / NUM_THREADS;

        for (int i = 0; i < NUM_THREADS; i++) {
            int startByte = i * sliceSize;
            int endByte = (i == NUM_THREADS - 1) ? fileSize - 1 : (i + 1) * sliceSize - 1;

            executorService.execute(() -> {
                try {
                    downloadSlice(fileUrl, destinationPath, startByte, endByte);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
        while (!executorService.isTerminated()) {
            // 等待所有线程下载完成
        }
    }

    private void downloadSlice(String fileUrl, String destinationPath, int startByte, int endByte) throws IOException {
        URL url = new URL(fileUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Range", "bytes=" + startByte + "-" + endByte);

        try (InputStream inputStream = connection.getInputStream();
             RandomAccessFile file = new RandomAccessFile(destinationPath, "rw")) {

            file.seek(startByte);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                file.write(buffer, 0, bytesRead);
            }
        }
    }
}