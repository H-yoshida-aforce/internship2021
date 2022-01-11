<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.html" %>
<link rel="stylesheet" type="text/css" href="../login.css">

<%@page import="jp.co.aforce.beans.Login" %>

<% Login login=(Login)request.getAttribute("login"); %>

<div class="hello">
ようこそ、${login.name}さん！
</div>

<%@ include file="../footer.html" %>