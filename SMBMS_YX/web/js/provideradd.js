var proCode = null;
var proName = null;
var proDesc = null;
var proAddress = null;
var proFax = null;
var proContact = null;
var proPhone = null;
var addBtn = null;
var backBtn = null;


$(function () {
    proCode = $("#proCode");
    proName = $("#proName");
    proDesc = $("#proDesc");
    proAddress = $("#proAddress");
    proFax = $("#proFax");
    proContact = $("#proContact");
    proPhone = $("#proPhone");
    addBtn = $("#add");
    backBtn = $("#back");
    //初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
    proCode.next().html("*");
    proName.next().html("*");
    proDesc.next().html("*");
    proFax.next().html("*");
    proContact.next().html("*");
    proPhone.next().html("*");
    proAddress.next().html("*");

    /*
     * 失焦\获焦
     * jquery的方法传递
     */
    proCode.bind("focus", function () {
        validateTip(proCode.next(), {"color": "#666666"}, "*", false);
    }).bind("blur", function () {
        if (proCode.val() != null && proCode.val().length > 0) {
            $.ajax({
                type: "GET",
                url: "/SMBMS_YX/ProviderServlet/ProviderCodeExist",
                data: {proCode: proCode.val()},
                dataType: "json",
                success: function (data) {
                    if (data === "true") {
                        validateTip(proCode.next(), {"color": "green"}, imgYes, true);
                    } else {
                        validateTip(proCode.next(), {"color": "red"}, imgNo + "供货商编号已存在", false);
                    }
                },
                error: function (data) {//当访问时候，404，500 等非200的错误状态码
                    validateTip(userCode.next(), {"color": "red"}, imgNo + " 您访问的页面不存在", false);
                }
            })
        } else {
            validateTip(proCode.next(), {"color": "red"}, imgNo + " 请输入供货商名称", false);
        }
    });

    proName.bind("focus", function () {
        validateTip(proName.next(), {"color": "#666666"}, "*", false);
    }).bind("blur", function () {
        if (proName.val() != null && proName.val().length > 0) {
            validateTip(proName.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(proName.next(), {"color": "red"}, imgNo + " 请输入供货商名称", false);
        }
    });

    proDesc.bind("focus", function () {
        validateTip(proDesc.next(), {"color": "#666666"}, "*", false);
    }).bind("blur", function () {
        if (proDesc.val() != null && proDesc.val().length > 0) {
            validateTip(proDesc.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(proDesc.next(), {"color": "red"}, imgNo + " 请输入供货商详细描述", false);
        }
    });

    proContact.bind("focus", function () {
        validateTip(proContact.next(), {"color": "#666666"}, "*", false);
    }).bind("blur", function () {
        if (proContact.val() != null && proContact.val().length > 0) {
            validateTip(proContact.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(proContact.next(), {"color": "red"}, imgNo + " 请输入供货商地址", false);
        }
    });

    proAddress.bind("focus", function () {
        validateTip(proAddress.next(), {"color": "#666666"}, "*", false);
    }).bind("blur", function () {
        if (proAddress.val() != null && proAddress.val().length > 0) {
            validateTip(proAddress.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(proAddress.next(), {"color": "red"}, imgNo + " 请输入供货商传真", false);
        }
    });

    proFax.bind("focus", function () {
        validateTip(proFax.next(), {"color": "#666666"}, "*", false);
    }).bind("blur", function () {
        if (proFax.val() != null && proFax.val().length > 0) {
            validateTip(proFax.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(proFax.next(), {"color": "red"}, imgNo + " 请输入供货商联系人", false);
        }
    });

    proPhone.bind("focus", function () {
        validateTip(proPhone.next(), {"color": "#666666"}, "*", false);
    }).bind("blur", function () {
        if (proPhone.val() != null && proPhone.val().length > 0) {
            validateTip(proPhone.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(proPhone.next(), {"color": "red"}, imgNo + " 请输入联系人手机号", false);
        }
    });

    addBtn.bind("click", function () {
        if (proCode.attr("validateStatus") == "true"
            && proName.attr("validateStatus") == "true"
            && proDesc.attr("validateStatus") == "true"
            && proAddress.attr("validateStatus") == "true"
            && proFax.attr("validateStatus") == "true"
            && proContact.attr("validateStatus") == "true"
            && proPhone.attr("validateStatus") == "true") {
            if (confirm("是否确认提交数据")) {
                $("#providerForm").submit();
            }
        }
    });

    backBtn.on("click", function () {
        if (referer != undefined
            && null != referer
            && "" != referer
            && "null" != referer
            && referer.length > 4) {
            window.location.href = referer;
        } else {
            history.back(-1);
        }
    });
});
