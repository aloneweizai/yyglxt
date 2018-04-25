<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>ABC财税平台运营管理系统</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
</head>

<body class="ht_login_m">
<div class="row container-fluid">
    <div class="ht_login_t">
        <form action="${ctx}/login.php" method="post">
            <div class="ht_login_b">
                <img src="${ctx}/images/cms/logo.png">
                <div class="username-con p_rel"><i class="glyphicon glyphicon-user p_abs"></i>
                    <input class="login_username" name="username" maxlength="100" placeholder="用户名" type="text"
                           required>
                </div>
                <div class="password-con p_rel"><i class="glyphicon glyphicon-check p_abs"></i>
                    <input class="login_password" name="password" maxlength="32" placeholder="密码" type="password"
                           required>
                </div>
                <div style="color: #ff493d">${erro!}</div>
                <div class="">
                    <input type="submit" class="layui-btn login_btn" value="登录">
                </div>
            </div>
        </form>
    </div>
</div>

</body>
</html>