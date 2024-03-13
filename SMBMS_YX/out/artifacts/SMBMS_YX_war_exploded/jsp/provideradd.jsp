<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>

<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong>
		<span>供货商管理页面 >> 供货商添加页面</span>
	</div>
	<div class="providerAdd">
		<form id="providerForm" name="providerForm" method="post" action="${pageContext.request.contextPath }/ProviderServlet/add">
			<input type="hidden" name="method" value="add">
			<!--div的class 为error是验证错误，ok是验证成功-->
			<div>
				<label for="proCode">供货商编号：</label>
				<input type="text" name="proCode" id="proCode" value="">
				<!-- 放置提示信息 -->
				<font color="red"></font>
			</div>
			<div>
				<label for="proName">供货商名称：</label>
                <input type="text" name="proName" id="proName" value="">
				<!-- 放置提示信息 -->
				<font color="red"></font>
			</div>
			<div>
			     <label for="proDesc">供货商详细描述：</label>
			     <input type="text" name="proDesc" id="proDesc" value="">
				 <font color="red"></font>
			</div>
			<div>
			    <label for="proAddress">地址：</label>
			    <input type="text" name="proAddress" id="proAddress" value="">
				<font color="red"></font>
            </div>
			<div>
                <label for="proFax">传真：</label>
                <input type="text" name="proFax" id="proFax" value="">
				<font color="red"></font>
            </div>
			<div>
				<label for="proContact">供货商联系人：</label>
				<input type="text" name="proContact" id="proContact" value="">
				<font color="red"></font>
			</div>
			<div>
				<label for="proPhone">供货商联系人电话：</label>
				<input type="text" name="proPhone" id="proPhone" value="">
				<font color="red"></font>
			</div>
            <div class="providerAddBtn">
                <input type="button" name="add" id="add" value="保存" >
				<input type="button" id="back" name="back" value="返回" >
            </div>
		</form>
	</div>
</div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/provideradd.js"></script>
