<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Success login</title>
    <#include "fragments/bootsrap-links.ftl"/>
</head>
<body>


<#include "fragments/nav-bar.ftl"/>
<h2>Success Login</h2>

<form action="/auth/logout" method="post">
    <button type="submit">Logout</button>
</form>
<br>
<br>
</body>
</html>