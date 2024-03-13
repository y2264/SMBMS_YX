<%--
  Created by IntelliJ IDEA.
  User: 22641
  Date: 2022/2/16
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
    <script>
        var testBeanList = new Array();
        testBeanList.push({userid: "1", username: "admin", address: "北京市海淀区", mobile: "12345678912"});
        testBeanList.push({userid: "2", username: "梅西", address: "湖北省武汉市", mobile: "12345678912"});
        testBeanList.push({userid: "3", username: "詹姆斯", address: "四川省成都市", mobile: "12345678912"});
        testBeanList.push({userid: "4", username: "梅西", address: "广西省广州市", mobile: "12345678912"});

        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/UserServlet/quick12",
            data: JSON.stringify(testBeanList),
            contentType: "application/json;charset-utf-8",
            success:function (data){
                window.location.href ="${pageContext.request.contextPath}" + data;
                alert(${pageContext.request.contextPath} + data)
            }
        });
    </script>
</head>
<body>

</body>
</html>
