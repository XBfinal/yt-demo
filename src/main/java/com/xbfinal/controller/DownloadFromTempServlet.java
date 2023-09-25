package com.xbfinal.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Title: DownloadFromTempServlet
 * @Author 笑霸fianl
 * @Package com.xbfinal.controller
 * @Date 2023/9/24 19:53
 * @描述:
 */
@WebServlet("/downloadFromTemp")
public class DownloadFromTempServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("name");
        String filePath = "D:/environment/apache-tomcat-8.5.88/temp/downloads/"+name; // 实际文件路径

        response.setContentType("application/octet-stream");
        String fileName = name; // 下载时显示的文件名
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        try (InputStream inputStream = new FileInputStream(filePath);
             OutputStream outputStream = response.getOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

