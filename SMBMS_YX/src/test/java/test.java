import cn.smbms.dao.UserDao;
import cn.smbms.pojo.Provider;
import cn.smbms.pojo.User;
import cn.smbms.service.user.UserService;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class test extends HttpServlet {

    public SqlSession sqlSession;

    @Test
    public void testSpring(){
        Provider provider = new Provider();
        provider.setModifyDate(new Date());
        System.out.println(provider.getModifyDate());
    }

    @Test
    public void test2() throws IOException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-service.xml");
        UserService userService =applicationContext.getBean(UserService.class);
        User user = userService.login("admin" , "1234567");
        System.out.println(user);
    }

    @Test
    public void deleteByIds(){
        String[] strings = new String[20];
        strings[0] = "8";
        strings[1] = "10";
        System.out.println(sqlSession.getMapper(UserDao.class).deleteByIds(strings));
    }

    @Test
    public void test() throws Exception {
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<Date> time = (List<Date>) mapper;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(time.get(0));
        Date date1 = simpleDateFormat.parse(date);
        System.out.println(date);
        System.out.println(date1);
        // 先截取到字符串中的年、月、日
        for (int i = 0; i < time.size(); i++) {
            String strs[] = simpleDateFormat.format(time.get(i)).trim().split("-");
            int selectYear = Integer.parseInt(strs[0]);
//            int selectMonth = Integer.parseInt(strs[1]);
//            int selectDay = Integer.parseInt(strs[2]);
            // 得到当前时间的年、月、日
            Calendar cal = Calendar.getInstance();
            int yearNow = cal.get(Calendar.YEAR);
//            int monthNow = cal.get(Calendar.MONTH) + 1;
//            int dayNow = cal.get(Calendar.DATE);

            // 用当前年月日减去生日年月日
            int yearMinus = yearNow - selectYear;
//            int monthMinus = monthNow - selectMonth;
//            int dayMinus = dayNow - selectDay;

            int age = yearMinus;// 先大致赋值
            System.out.println(age);

            if (1 == 1 && 1 != 2) {

            }
        }
    }
}
