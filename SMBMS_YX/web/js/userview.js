var backBtn = null;

$(function(){
	backBtn = $("#back");
	backBtn.on("click",function(){
		//alert("view : "+referer);
		if(referer != undefined 
			&& null != referer 
			&& "" != referer
			&& "null" != referer
			&& referer.length > 4){
		 window.location.href = "../../SMBMS_YX/UserServlet/query";
		}else{
			history.back(-1);
		}
	});
});