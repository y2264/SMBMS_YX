package cn.smbms.controller;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.pojo.User;
import cn.smbms.service.bill.BillService;
import cn.smbms.service.bill.BillServiceImpl;
import cn.smbms.service.provider.ProviderService;
import cn.smbms.tools.Constants;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Servlet implementation class BillServlet
 */
@Controller
@RequestMapping("/BillServlet")
public class BillController {

    @Resource
    private BillService billService;

    @Resource
    private ProviderService providerService;

    /**
     * 获取订单列表
     *
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/query")
    private String query(@RequestParam(value = "billName", required = false) String queryProductName,
                       @RequestParam(value = "billCode", required = false) String queryBillCode,
                       @RequestParam(value = "queryIsPayment", required = false) String queryIsPayment,
                       String pageNum, Model model) {
        System.out.println("BillServlet的query =====>");

        int pageNumber = 1;
        if(!StringUtils.isNullOrEmpty(pageNum)){
            pageNumber = Integer.parseInt(pageNum);
        }

        List<Provider> providerList = providerService.getProviderList(pageNumber, queryProductName, queryBillCode);
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



        List<Bill> billList = billService.getBillList(null, currentPageNo, 1);
        model.addAttribute("billList", billList);
        model.addAttribute("queryProductName", queryProductName);
        model.addAttribute("queryProviderId", null);
        model.addAttribute("queryIsPayment", queryIsPayment);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("currentPageNo", currentPageNo);
        return "../jsp/billlist.jsp";

    }

    /**
     * 添加订单信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从页面中获取用户输入的订单信息
        String billCode = request.getParameter("billCode");
        String productName = request.getParameter("productName");
        String productDesc = request.getParameter("productDesc");
        String productUnit = request.getParameter("productUnit");
        String productCount = request.getParameter("productCount");
        String totalPrice = request.getParameter("totalPrice");
        String providerId = request.getParameter("providerId");
        String isPayment = request.getParameter("isPayment");
        // 创建订单对象--用于保存从页面中获取的订单信息
        Bill bill = new Bill();
        bill.setBillCode(billCode);
        bill.setProductName(productName);
        bill.setProductDesc(productDesc);
        bill.setProductUnit(productUnit);
        // 将商品数量转换为BigDecimal类型并为bill对象的属性productCount赋值
        bill.setProductCount(new BigDecimal(productCount).setScale(2, BigDecimal.ROUND_DOWN));
        // 将是否支付转换为Integer类型并为bill对象的属性isPayment赋值
        bill.setIsPayment(Integer.parseInt(isPayment));
        bill.setTotalPrice(new BigDecimal(totalPrice).setScale(2, BigDecimal.ROUND_DOWN));
        bill.setProviderId(Integer.parseInt(providerId));
        // 获取登录者的ID信息并为bill对象的属性createdBy赋值
        bill.setCreatedBy(((User) request.getSession().getAttribute(Constants.USER_SESSION)).getId());
        bill.setCreationDate(new Date()); // 为bill对象的属性：创建时间，赋值为当前日期

        boolean flag = false; // 创建添加是否成功的变量
        BillService billService = new BillServiceImpl(); // 创建BillService对象
        flag = billService.add(bill); // 调用添加方法并获取结果
        if (flag) { // 如果添加成功
        } else { // 添加失败
            request.getRequestDispatcher("jsp/billadd.jsp").forward(request, response); // 返回添加页面
        }
    }

    /**
     * 获取供应商列表
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void getProviderlist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Provider> providerList = new ArrayList<Provider>(); // 创建List集合用于存放Provider对象
        providerList = providerService.getProviderList(1, "" , null); // 调用查询方法获取供应商信息
        // 把providerList转换成json对象输出
        response.setContentType("application/json");
        PrintWriter outPrintWriter = response.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(providerList));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    /**
     * 根据billId获取Bill信息
     *
     * @param request
     * @param response
     * @param url
     * @throws ServletException
     * @throws IOException
     */
    private void getBillById(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {
        String id = request.getParameter("billid"); // 获取用户传入的订单ID
        if (!StringUtils.isNullOrEmpty(id)) { // 如果ID不为空
            BillService billService = new BillServiceImpl(); // 创建BillService对象
            Bill bill = null; // 创建Bill对象
            bill = billService.getBillById(id); // 调用查询方法并获取查询结果
            request.setAttribute("bill", bill); // 将结果添加到request作用域中
            request.getRequestDispatcher(url).forward(request, response); // 使用转发的方式返回到相应页面
        }
    }

    /**
     * 修改订单信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从页面中获取用户输入的信息
        String id = request.getParameter("id");
        String productName = request.getParameter("productName");
        String productDesc = request.getParameter("productDesc");
        String productUnit = request.getParameter("productUnit");
        String productCount = request.getParameter("productCount");
        String totalPrice = request.getParameter("totalPrice");
        String providerId = request.getParameter("providerId");
        String isPayment = request.getParameter("isPayment");
        // 创建订单对象--用于保存从页面中获取的订单信息
        Bill bill = new Bill();
        bill.setId(Integer.valueOf(id));
        bill.setProductName(productName);
        bill.setProductDesc(productDesc);
        bill.setProductUnit(productUnit);
        bill.setProductCount(new BigDecimal(productCount).setScale(2, BigDecimal.ROUND_DOWN));
        bill.setIsPayment(Integer.parseInt(isPayment));
        bill.setTotalPrice(new BigDecimal(totalPrice).setScale(2, BigDecimal.ROUND_DOWN));
        bill.setProviderId(Integer.parseInt(providerId));
        bill.setModifyBy(((User) request.getSession().getAttribute(Constants.USER_SESSION)).getId());
        bill.setModifyDate(new Date());
        boolean flag = false; // 创建修改是否成功的变量
        BillService billService = new BillServiceImpl(); // 创建BillService对象
        flag = billService.modify(bill); // 调用修改方法并获取结果
        if (flag) { // 如果修改成功
        } else { // 修改失败
            request.getRequestDispatcher("jsp/billmodify.jsp").forward(request, response); // 返回修改页面
        }
    }

    /**
     * 通过billId删除Bill
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void delBill(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 从页面获取用户传入的订单ID
        String id = request.getParameter("billid");
        // 创建HashMap集合--以键值对方式存储数据
        HashMap<String, String> resultMap = new HashMap<String, String>();
        // 如果获取的ID不为空
        if (!StringUtils.isNullOrEmpty(id)) {
            BillService billService = new BillServiceImpl(); // 创建BillService对象
            boolean flag = billService.deleteBillById(id); // 调用删除方法并获取结果
            if (flag) {// 删除成功
                resultMap.put("delResult", "true");
            } else {// 删除失败
                resultMap.put("delResult", "false");
            }
        } else { // 如果ID值为空
            resultMap.put("delResult", "notexit");
        }
        // 把resultMap转换成JSON对象输出
        response.setContentType("application/json");
        PrintWriter outPrintWriter = response.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(resultMap));
        outPrintWriter.flush();
        outPrintWriter.close();
    }
}
