package cn.smbms.tools;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class MyBatisUtil {

    private static InputStream inputStream;
    private static SqlSessionFactory sqlSessionFactory;
    private static SqlSession sqlSession;

    static {
        try {
            inputStream = Resources.getResourceAsStream("MyBatis-Configuration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession(boolean bo){
        if(sqlSession == null){
            //调用时会报错
            // Error opening session.  Cause: java.lang.NullPointerException
            // Cause: java.lang.NullPointerException
            sqlSession = sqlSessionFactory.openSession(bo);
        }
        return sqlSession;
    }

    //关闭连接
    public static void close(){
        if(sqlSession != null){
            sqlSession.close();
        }
    }
}
