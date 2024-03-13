<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>
 <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>供应商管理页面 >> 供应商信息查看页面</span>
        </div>
        <div class="providerView">
            <p><strong>供应商编号：</strong><span>${provider.proCode }</span></p>
            <p><strong>供应商名称：</strong><span>${provider.proName }</span></p>
            <p><strong>供应商详细描述：</strong><span>${provider.proDesc }</span></p>
            <p><strong>地址：</strong><span>${provider.proAddress }</span></p>
            <p><strong>传真：</strong><span>${provider.proFax }</span></p>
            <p><strong>供应商联系人：</strong><span>${provider.proContact }</span></p>
            <p><strong>电话：</strong><span>${provider.proPhone }</span></p>
			<div class="providerAddBtn">
            	<input type="button" id="back" name="back" value="返回" >
            </div>
        </div>
    </div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/providerview.js"></script>