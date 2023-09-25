import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.xbfinal.entity.UirViews;
import com.xbfinal.entity.User;
import com.xbfinal.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * @Title: UserTest
 * @Author 笑霸fianl
 * @Package PACKAGE_NAME
 * @Date 2023/9/23 19:35
 * @描述: 测试类
 */
public class UserTest {


    @Test
    public void testGetBtId() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        User getUserById = sqlSession.selectOne("getUserById", 1);
        System.out.println(getUserById);

    }
    @Test
    public void getUserByName() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        User getUserById = sqlSession.selectOne("getUserByName", "test");
        System.out.println(getUserById);

    }

    @Test
    public void insertUser(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        User user = new User();
        user.setName("11");
        user.setPassword("11");
        int insertUser = sqlSession.insert("insertUser", user);
        sqlSession.commit();
        sqlSession.close();
        System.out.println(insertUser);
    }

    @Test
    public void testMD5(){
        /**
         * e10adc3949ba59abbe56e057f20f883e
         */
        System.out.println(SecureUtil.md5("123456"));
    }

    //图片验证码
    @Test
    public void testyzm(){
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        System.out.println(lineCaptcha.getCode());
    }


    @Test
    public void findAllNum(){
//        try(SqlSession sqlSession = MybatisUtil.getSqlSession()) {
//            List<UirViews> findAllNum = sqlSession.selectList("findAllNum");
//
//            String jsonString = JSON.toJSONString(findAllNum);
//            System.out.println(jsonString);
//        }

        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            sqlSession.update("addOneApi", 3);
            sqlSession.commit();
        }
    }

    public static void main(String[] args) {
        String currentProjectPath = System.getProperty("user.dir");
        System.out.println("当前项目路径：" + currentProjectPath);



    }



}
