package com.xbfinal.utils;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.InputStream;

/**
 * @Title: MybayisUtuil
 * @Author 笑霸fianl
 * @Package com.xbfinal.utils
 * @Date 2023/9/23 19:37
 * @描述:
 */
public class MybatisUtil {

    private static SqlSessionFactory build ;
    //静态代码块，初始就加载配置文件
    static {
        String resource="mybatis-config.xml";
        try {

            InputStream is = null;
           is = Resources.getResourceAsStream(resource);

            build = new SqlSessionFactoryBuilder().build(is);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * SqlSession 提供了在数据库执行 SQL 命令所需的所有方法。
     */
    public static SqlSession getSqlSession(){
        //获取操作sql对象(既然有了 SqlSessionFactory，顾名思义，我们可以从中获得 SqlSession 的实例。)
        return build.openSession();
    }

}
