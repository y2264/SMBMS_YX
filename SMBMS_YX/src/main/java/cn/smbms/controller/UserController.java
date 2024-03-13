package cn.smbms.controller;

import cn.smbms.dao.UserDao;
import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.pojo.WorkBean;
import cn.smbms.service.role.RoleService;
import cn.smbms.service.user.UserService;
import cn.smbms.tools.ApplicationUtil;
import cn.smbms.tools.Constants;
import cn.smbms.tools.MyBatisUtil;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.util.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class UserServlet
 */
@Controller
@RequestMapping("/UserServlet")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 分页查询用户信息
     *
     * @param request
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/query")
    private String query(String pageNum, String queryname, String queryUserRole, Model model,
                         HttpServletRequest request) {
        System.out.println("query ======>");
        //请求的页面数
        int pageNumber = 1;
        if (pageNum != null) {
            pageNumber = Integer.parseInt(pageNum);
        }
        // 查询用户列表
        String queryUserName1 = queryname; // 从页面获取用户输入的用户名
        int queryUserRole1 = 0; // 定义角色变量，初始值为0
        if (request.getParameter("queryUserRole") != null) {
            queryUserRole1 = Integer.parseInt(queryUserRole);
            System.out.println("查询的用户角色：" + queryUserRole);
        }
        System.out.println("查询的用户名" + queryUserName1);
        // 调用UserService中的方法，传入参数并获取结果 将请求的页面数传入
        // 创建存放用户信息的集合
        List<User> userList = userService.getUserList(pageNumber, queryUserName1, queryUserRole1);
        //使用分页助手
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        // 总数量（表）
        int totalCount = Math.toIntExact(pageInfo.getTotal());
        // 当前页码
        int currentPageNo = pageInfo.getPageNum();
        //总页数
        int totalPageCount = pageInfo.getPages();
        // 控制首页和尾页
        if (currentPageNo < 1) {
            currentPageNo = 1;
        } else if (currentPageNo > totalPageCount) {
            currentPageNo = totalPageCount;
        }

        //存值
        model.addAttribute("userList", userList);// 向request作用域中存值
        // 调用RoleService中的方法并获取结果
        // 创建存放角色信息的集合
        List<Role> roleList = roleService.getRoleList();
        // 向request作用域中以键值对的方式存值
        model.addAttribute("queryUserName", queryname);
        model.addAttribute("queryUserRole", queryUserRole);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("currentPageNo", currentPageNo);
        //根据此值进行页面页数的跳转
        model.addAttribute("url" , "UserServlet");
        // 带着数据转发到userlist.jsp页面
        return "../jsp/userlist.jsp";
    }

    /**
     * 获取旧密码
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("getPwdByUserId")
    @ResponseBody
    private String getPwdByUserId(String oldpassword, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("getPwdByUserId =====>");
        String result = null;
        System.out.println("旧密码为：" + oldpassword);
        // 从session作用域中取出登录者的信息
        Object obj = request.getSession().getAttribute(Constants.USER_SESSION);
        String oldPassWrod = oldpassword;// 从页面中获取用户输入的旧密码
        Map<String, String> resultMap = new HashMap<String, String>();// 创建Map集合对象
        if (null == obj) {// session过期
            result = "sessionerror";// 向结果集中以键值对方式赋值
        } else if (StringUtils.isNullOrEmpty(oldPassWrod)) {// 旧密码输入为空
            result = "error";
        } else {
            String sessionPwd = ((User) obj).getUserPassword();// 获取登录者的密码
            if (oldPassWrod.equals(sessionPwd)) {// 如果用户输入的旧密码与登录者的密码一致
                result = "true";
            } else {// 旧密码输入不正确
                result = "false";
            }
        }
        System.out.println("result：" + result);
        return JSONArray.toJSONString(result);
    }

    /**
     * 修改密码
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("updatePwd")
    private String updatePwd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("updatePwd =====>");
        String result = null;
        Object obj = request.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = request.getParameter("newpassword");
        int flag = 0;
        if (obj != null && !StringUtils.isNullOrEmpty(newpassword)) {
            UserDao userDao = ApplicationUtil.getContext().getBean(UserDao.class);

            try {
                flag = userDao.updatePwd(((User) obj).getId(), newpassword);
                MyBatisUtil myBatisUtil = ApplicationUtil.getContext().getBean(MyBatisUtil.class);
                myBatisUtil.getSqlSession(false).commit();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (flag != 0) {
                request.setAttribute(Constants.SYS_MESSAGE, "修改密码成功,请退出并使用新密码重新登录！");
                request.getSession().removeAttribute(Constants.USER_SESSION);// session注销
                result = "../login.jsp";
            } else {
                request.setAttribute(Constants.SYS_MESSAGE, "修改密码失败！");
                MyBatisUtil myBatisUtil = ApplicationUtil.getContext().getBean(MyBatisUtil.class);
                myBatisUtil.getSqlSession(false).rollback();
                result = "../jsp/pwdmodify.jsp";
            }
        } else {
            request.setAttribute(Constants.SYS_MESSAGE, "修改密码失败！");
            result = "../jsp/pwdmodify.jsp";
        }
        return result;
    }

    /**
     * 获取用户角色信息
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("getRoleList")
    @ResponseBody
    private List<Role> getRoleList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("getRoleList =====>");
        List<Role> roleList = null; // 创建存放角色信息的集合
        roleList = roleService.getRoleList(); // 调用RoleService中的方法并获取结果

        return roleList;
    }

    /**
     * 判断用户编码是否存在
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("userCodeExist")
    @ResponseBody
    private String userCodeExist(@Param("userCode") String userCode,
                                 HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("userCodeExist =====>");
        //判断用户账号是否可用
        String result = null;
        if (StringUtils.isNullOrEmpty(userCode)) {
            result = "notempty";
        } else {
            User user = userService.selectUserCodeExist(userCode);
            System.out.println(user);
            if (null != user) {
                result = "exist";
            } else {
                result = "notexist";
            }
        }
        return JSONArray.toJSONString(result);
    }

    /**
     * 添加用户信息
     *
     * @param request
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("add")
    private String add(HttpServletRequest request , Model model)
            throws ServletException, IOException {
        System.out.println("add()================");
        // 从页面中获取用户输入的数据--获取的数据都是String类型
        User user = new User();//创建User对象|

        try {
            BeanUtils.populate(user, request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        String gender = request.getParameter("gender");
        String userRole = request.getParameter("userRole");

        user.setGender(Integer.valueOf(gender));
        user.setUserRole(Integer.valueOf(userRole));
        user.setCreationDate(new Date());
        user.setCreatedBy(((User) request.getSession().getAttribute(Constants.USER_SESSION)).getId());

        System.out.println(user);
        String result = null;
        if (userService.add(user)) {
            result = query("1", "", "0", model, request);
        } else {
            result = "../jsp/useradd.jsp";
        }
        return result;
    }

    /**
     * 根据主键ID查询用户信息
     *
     * @param request
     * @param response
     * @param url
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("getUserById")
    private void getUserById(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {
        String id = request.getParameter("uid");
        if (!StringUtils.isNullOrEmpty(id)) {
            //调用后台方法得到user对象
            User user = userService.getUserById(id);
            request.setAttribute("user", user);
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    /**
     * 修改用户信息
     *
     * @param request
     * @throws ServletException
     * @throws IOException
     */


    @RequestMapping("modify")
    private String modify(String uid, HttpServletRequest request, Model model)
            throws ServletException, IOException {
        System.out.println("modify =====>");
        String result = null;
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        user.setId(Integer.valueOf(uid));
        user.setModifyBy(((User) request.getSession().getAttribute(Constants.USER_SESSION)).getId());
        user.setModifyDate(new Date());

        //将uid存入域中，
        User userById = ApplicationUtil.getContext().getBean(UserService.class).getUserById(uid);
        model.addAttribute("userList" , userById);
        if (!userService.modify(user)) {//当从userlist.jsp进入此方法时，会报空指针错误，但功能可以正常运行
            return "../jsp/usermodify.jsp";
        }
        return query("1", "", "0", model, request);
    }

    /**
     * 根据ID删除用户信息
     *
     * @param request
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("deleteUserById")
    @ResponseBody
    private String deleteUserById(String uid, HttpServletRequest request , Model model) {
        System.out.println("deleteUser =====>");
        String result = null;

        Integer delId = Integer.valueOf(uid);
        if (delId <= 0) {
            result = "notexist";
        } else {
            if (userService.deleteUserById(delId)) {
                result = "true";
            } else {
                result = "false";
            }
        }
        return JSONArray.toJSONString(result);
    }

    /**
     * 根据ID删除多个用户信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void deleteByIds(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] strings = request.getParameterValues("uid");

        HashMap<String, String> resultMap = new HashMap<String, String>();
        if (strings.length <= 0) {
            resultMap.put("delResult", "notexist");
        } else {
            if (userService.deleteByIds(strings)) {
                resultMap.put("delResult", "true");
            } else {
                resultMap.put("delResult", "false");
            }
        }

        //把resultMap转换成json对象输出
        response.setContentType("application/json");
        PrintWriter outPrintWriter = response.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(resultMap));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    /**
     * 根据id获取用户信息
     */
    @RequestMapping("userview")
    public String view(String uid, Model model) {
        System.out.println("uid为:" + uid);

        User user = ApplicationUtil.getContext().getBean(UserService.class).getUserById(uid);

        model.addAttribute("user", user);
        return "../jsp/userview.jsp";
    }

    @RequestMapping("/quick12")
    public String test(List<WorkBean> beanList , Model model){
        System.out.println("test =====>");
        for (WorkBean workBean : beanList) {
            System.out.println(workBean);
        }
        model.addAttribute("beanList" , beanList);
        return "../view.jsp";
    }
}
