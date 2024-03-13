var userObj;

function openYesOrNoDLG(){
	$('.zhezhao').css('display', 'block');
	$('#removeUse').fadeIn();
}

function cancleBtn(){
	$('.zhezhao').css('display', 'none');
	$('#removeUse').fadeOut();
}
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}

$(function(){
	//通过jquery的class选择器（数组）
	//对每个class为viewUser的元素进行动作绑定（click）
	/**
	 * bind、live、delegate
	 * on
	 */
	$(".viewUser").on("click",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		window.location.href="/SMBMS_YX/ProviderServlet/getProviderById?id="+ obj.attr("providerid");
	});

	$(".modifyUser").on("click",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		window.location.href="/SMBMS_YX/ProviderServlet/modify?id="+ obj.attr("providerid");
	});

	$('#no').click(function () {
		cancleBtn();
	});
	
	$('#yes').click(function () {
		deleteUser(userObj);
	});

	$(".deleteUser").on("click",function(){
		var obj = $(this);
		if(confirm("你确定要删除用户【"+obj.attr("providername")+"】吗？")){
			$.ajax({
				type:"GET",
				url:"/SMBMS_YX/ProviderServlet/delProvider",
				data:{id:obj.attr("providerid")},
				dataType:"json",
				success:function(data){
					if(data === "true"){//删除成功：移除删除行
						alert("删除成功");
						window.location.href="/SMBMS_YX/ProviderServlet/query";
					}else if(data === "false"){//删除失败
						alert("对不起，删除供货商【"+obj.attr("providername")+"】失败");
					}else if(data === "notexist"){
						alert("对不起，用户【"+obj.attr("providername")+"】不存在");
					}else{
						alert("对不起，删除供货商【"+obj.attr("providername")+"】失败，该供货商包含【"+data+"】个订单");
					}
				},
				error:function(data){
					alert("对不起，删除失败");
				}
			});
		}
	});
});