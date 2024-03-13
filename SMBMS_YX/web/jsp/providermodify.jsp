<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>
<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong>
		<span>供应商管理页面 >> 供应商修改页面</span>
	</div>
	<div class="providerAdd">
		<form id="providerForm" name="providerForm" method="post" action="${pageContext.request.contextPath }/ProviderServlet/modify">
			<input type="hidden" name="id" value="${id}"/>
			<div>
				<label for="proCode">供应商编号：</label>
				<input type="text" name="proCode" id="proCode" value="${providerList.proCode}">
				<font color="red"></font>
			</div>
			<div>
				<label for="proName">供应商名称：</label>
				<input type="text" name="proName" id="proName" value="${providerList.proName}">
				<font color="red"></font>
			</div>
			<div>
				<label for="proDesc">供应商详细描述：</label>
				<input type="text" name="proDesc" id="proDesc" value="${providerList.proDesc}">
				<font color="red"></font>
			</div>
			<div>
				<label for="proAddress">地址：</label>
				<input type="text" name="proAddress" id="proAddress" value="${providerList.proAddress}">
				<font color="red"></font>
			</div>
			<div>
				<label for="proFax">传真：</label>
				<input type="text" name="proFax" id="proFax" value="${providerList.proFax}">
				<font color="red"></font>
			</div>
			<div>
				<label for="proContact">供应商联系人：</label>
				<input type="text" name="proContact" id="proContact" value="${providerList.proContact}">
				<font color="red"></font>
			</div>
			<div>
				<label for="proPhone">电话：</label>
				<input type="text" name="proPhone" id="proPhone" value="${providerList.proPhone}">
				<font color="red"></font>
			</div>
			<div class="providerAddBtn">
				<input type="button" name="save" id="save" value="保存" />
				<input type="button" id="back" name="back" value="返回"/>
			</div>
		</form>
	</div>
</div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/providermodify.js"></script>
