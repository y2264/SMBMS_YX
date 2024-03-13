package cn.smbms.controller;

import cn.smbms.pojo.Provider;
import cn.smbms.pojo.User;
import cn.smbms.service.provider.ProviderService;
import cn.smbms.tools.Constants;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.util.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Servlet implementation class ProviderServlet
 */
@Controller
@RequestMapping("/ProviderServlet")
public class ProviderController {

    @Resource
    private ProviderService providerService;

    /**
     * 添加供应商
     *
     * @param request
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/add")
    private String add(HttpServletRequest request, Model model) {
        System.out.println("add =====>");
        Provider provider = new Provider();
        //将数据传入pojo类
        try {
            BeanUtils.populate(provider, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        provider.setCreatedBy(((User) request.getSession().getAttribute(Constants.USER_SESSION)).getId());
        provider.setCreationDate(new Date());
        if (providerService.add(provider)) {
            return query(null, null, null, model);
        } else {
            return "../jsp/provideradd.jsp";
        }
    }

    /**
     * 根据ID删除供应商信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/delProvider")
    @ResponseBody
    private String delProvider(String id , HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("delProvider =====>");
        String result = null;
        HashMap<String, String> resultMap = new HashMap<String, String>();
        if (!StringUtils.isNullOrEmpty(id)) {
            int flag = providerService.deleteProviderById(id);
            if (flag == 0) {// 删除成功
                result = "true";
            } else if (flag == -1) {// 删除失败
                result = "false";
            } else if (flag > 0) {// 该供应商下有订单，不能删除，返回订单数
                result = String.valueOf(flag);
            }
        } else {
            result = "notexit";
        }
        // 把resultMap转换成json对象输出
        return JSONArray.toJSONString(result);
    }

    /**
     * 修改供应商信息
     *
     * @param request
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/modify")
    private String modify(String id, HttpServletRequest request, Model model) {
        // 从页面中获取用户输入的值
        System.out.println("modify =====>");
        String result = "../jsp/providermodify.jsp";
        Provider provider = new Provider();

        try {
            BeanUtils.populate(provider, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        provider.setModifyBy(((User) request.getSession().getAttribute(Constants.USER_SESSION)).getId());
        provider.setModifyDate(new Date());
        //将id值存入
        model.addAttribute("id", id);
        System.out.println(provider.getModifyDate());
        if (!StringUtils.isNullOrEmpty(provider.getProCode())) {//判断是否第一次进入此方法。编号不能为空，所以必定有值
            if (providerService.modify(provider)) {
                result = query(null, null, null, model);
            }
        }
        return result;
    }

    /**
     * 根据条件查询供应商信息
     */
    @RequestMapping("/query")
    private String query(@RequestParam(value = "proName", required = false) String queryProName,
                         @RequestParam(value = "proCode", required = false) String queryProCode,
                         String pageNum, Model model) {
        // 从页面中获取用户输入的条件信息
        if (StringUtils.isNullOrEmpty(queryProName)) { // 如果供应商名称为null
            queryProName = ""; // 将queryProName的值设置为""
        }
        if (StringUtils.isNullOrEmpty(queryProCode)) {
            queryProCode = "";
        }
        int pageNumber = 1;
        if (!StringUtils.isNullOrEmpty(pageNum)) {
            pageNumber = Integer.parseInt(pageNum);
        }
        List<Provider> providerList = providerService.getProviderList(pageNumber, queryProName, queryProCode);
        //使用page助手
        PageInfo<Provider> pageInfo = new PageInfo<>(providerList);
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

        model.addAttribute("providerList", providerList);
        model.addAttribute("queryProName", queryProName);
        model.addAttribute("queryProCode", queryProCode);
        //将页数存值
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("currentPageNo", currentPageNo);
        //根据此值进行页面页数的跳转
        model.addAttribute("url", "ProviderServlet");
        return "../jsp/providerlist.jsp";
    }


    /**
     * 根据ID获取供应商
     *
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/getProviderById")
    private String getProviderById(String id, Model model) {
        if (!StringUtils.isNullOrEmpty(id)) {
            System.out.println("getProviderById =====>");
            Provider provider = providerService.getProviderById(id);
            model.addAttribute("provider", provider);
        }
        return "../jsp/providerview.jsp";
    }


    /**
     * 查询供货商名称
     */
    @RequestMapping("/getProviderName")
    public void getProviderName(Model model) {

    }

    /**
     * 查询供货商编号是否存在
     */
    @RequestMapping("/ProviderCodeExist")
    @ResponseBody
    public String providerCodeExist(String proCode) {
        String result = null;
        if (providerService.providerCodeExist(proCode)) {
            //供货商编号不存在
            result = "true";
        } else {
            //供货商编号存在
            result = "false";
        }
        return JSONArray.toJSONString(result);
    }
}
