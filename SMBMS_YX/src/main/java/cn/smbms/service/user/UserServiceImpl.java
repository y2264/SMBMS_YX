package cn.smbms.service.user;

import cn.smbms.dao.UserDao;
import cn.smbms.pojo.User;
import cn.smbms.tools.ApplicationUtil;
import cn.smbms.tools.MyBatisUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    public UserServiceImpl() {
    }

    /**
     * 通过构造方法实例化userDao对象
     */

    @Override
    public User login(String userCode, String userPassword) {
        User user = null; // 创建User对象
        try {
            user = ApplicationUtil.getContext().getBean(UserDao.class).login(userCode); // 调用登录方法并获取返回值
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 匹配密码
        if (null != user) { // 如果用户不为空
            if (!user.getUserPassword().equals(userPassword)) // 如果用户输入的密码与数据库中的密码不一致
                user = null; // 清空user对象
        }
        return user; // 返回user对象
    }

    @Override
    public boolean updatePwd(int id, String pwd) {
        boolean flag = false;
        try {
            if (ApplicationUtil.getContext().getBean(UserDao.class).updatePwd(id, pwd) > 0)
                flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<User> getUserList(int pageNum , String queryUserName, int queryUserRole) {

        List<User> userList = null;
        try {
            // 设置分页相关参数 当前页 ， 每页显示数据条数
            PageHelper.startPage(pageNum, 8);
            userList = ApplicationUtil.getContext().getBean(UserDao.class).getUserList(queryUserName, queryUserRole);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public int getUserCount(String queryUserName, int queryUserRole) {
        int count = 0;
        System.out.println("queryUserName ---- > " + queryUserName);
        System.out.println("queryUserRole ---- > " + queryUserRole);
        try {
            count = ApplicationUtil.getContext().getBean(UserDao.class).getUserCount(queryUserName, queryUserRole);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public User selectUserCodeExist(String userCode) {
        User user = null;
        try {
            user = ApplicationUtil.getContext().getBean(UserDao.class).login(userCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean add(User user) {
        boolean flag = false;
        try {
            int updateRows = ApplicationUtil.getContext().getBean(UserDao.class).add(user);
            if (updateRows > 0) {
                flag = true;
                System.out.println("add success!");
            } else {
                System.out.println("add failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("rollback==================");
        }
        return flag;
    }

    @Override
    public User getUserById(String id) {
        User user = null;
        try {
            user = ApplicationUtil.getContext().getBean(UserDao.class).getUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
            user = null;
        }
        return user;
    }

    @Override
    public boolean deleteUserById(Integer delId) {
        boolean flag = false;
        try {
            if (ApplicationUtil.getContext().getBean(UserDao.class).deleteUserById(delId) > 0)
                flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean modify(User user) {
        boolean flag = false;
        try {
            if (ApplicationUtil.getContext().getBean(UserDao.class).modify(user) > 0) {flag = true;}
        } catch (Exception e) {
            //这里会报空指针，但程序可以运行（待解决）
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean deleteByIds(String[] ids) {
        boolean flag = false;
        //创建myBatis对象
        MyBatisUtil myBatisUtil = ApplicationUtil.getContext().getBean(MyBatisUtil.class);
        if (myBatisUtil.getSqlSession(false).getMapper(UserDao.class).deleteByIds(ids) > 0) {
            flag = true;
        }
        return flag;
    }
}
