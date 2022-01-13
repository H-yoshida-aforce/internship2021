<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.html"%>
<link rel="stylesheet" type="text/css" href="../login.css">

<%@page import="jp.co.aforce.beans.Login"%>
<%
request.getAttribute("Errormsg");
%>
<%
request.getAttribute("ErrorId");
%>

<div class="form-wrapper">
	<h1>ログイン画面</h1>
	${Errormsg}

	<form action="../login/signin" method="post">
		<dl>
			<dt>ID</dt>
			<dd>
				<input type="text" name="loginId" class="type id" value=${ErrorId }>
			</dd>

			<dt>パスワード</dt>
			<dd>
				<input type="password" name="password" class="type pw">
			</dd>
		</dl>

		<div class="button-panel">
			<p>
				<input type="submit" value="ログイン" class="button">
			</p>
		</div>
	</form>
</div>

<%@ include file="../footer.html"%>