<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/head.jsp" %>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>供货商管理页面</span>
    </div>
    <div class="search">
        <form method="get" action="${pageContext.request.contextPath }/BillServlet/query">
            <span>订单编号：</span>
            <input name="billCode" class="input-text" type="text">
            <span>订单名称：</span>
            <input name="billName" class="input-text" type="text">
            <span>是否支付：</span>
            <select name="queryIsPayment">
                <option value="0" >未支付</option>
                <option value="1" >已支付</option>
            </select>
            <input value="查 询" type="submit" id="searchbutton">
            <a href="${pageContext.request.contextPath}/jsp/billadd.jsp">添加供货商</a>
        </form>
    </div>
    <!--供货商-->
    <table class="billTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th><input type="checkbox" id="selectAll"></th>
            <th width="10%">订单名称</th>
            <th width="35%">订单详细描述</th>
            <th width="10%">地址</th>
            <th width="10%">传真</th>
            <th width="10%">订单联系人</th>
            <th width="10%">电话</th>
            <th width="10%">操作</th>
        </tr>
        <c:forEach var="bill" items="${billList}" varStatus="status">
            <tr>
                <th><input type="checkbox" name="userIds" value="${bill.id}"></th>
                <td>
                    <span>${bill.billName }</span>
                </td>
                <td>
                    <span>${bill.billDesc }</span>
                </td>
                <td>
                    <span>${bill.billAddress}</span>
                </td>
                <td>
                    <span>${bill.billFax}</span>
                </td>
                <td>
                    <span>${bill.billContact}</span>
                </td>
                <td>
                    <span>${bill.billPhone}</span>
                </td>
                <td>
                    <span>
                        <a class="viewUser" href="javascript:;" billid=${bill.id } billname=${bill.billName }>
                            <img src="${pageContext.request.contextPath }/images/read.png" alt="查看" title="查看"/>
                        </a>
                    </span>
                    <span><a class="modifyUser" href="javascript:;" billid=${bill.id } billname=${bill.billName }>
                        <img src="${pageContext.request.contextPath }/images/xiugai.png" alt="修改" title="修改"/>
                        </a>
                    </span>
                    <span>
                        <a class="deleteUser" href="javascript:;" billid=${bill.id } billname=${bill.billName }>
                            <img src="${pageContext.request.contextPath }/images/schu.png" alt="删除" title="删除"/>
                        </a>
                    </span>
                </td>
            </tr>
        </c:forEach>
    </table>
    <input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
    <c:import url="rollpage.jsp">
        <c:param name="totalCount" value="${totalCount}"/>
        <c:param name="currentPageNo" value="${currentPageNo}"/>
        <c:param name="totalPageCount" value="${totalPageCount}"/>
    </c:import>
</div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该供货商吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<%@include file="/jsp/common/foot.jsp" %>
<%--删除功能未制作--%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/billlist.js"></script>
