<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.html"%>
<link rel="stylesheet" type="text/css" href="../css.css">

<div class="menu">
	<p class="moji">メニュー画面</p>

	<form action="signup.jsp" method="post">
		<input type="submit" value="会員情報新規登録" class="btns signup">
	</form>

	<br>

	<form action="update.jsp" method="post">
		<input type="submit" value="会員情報変更" class="btns update">
	</form>

	<br>

	<form action="delete.jsp" method="post">
		<input type="submit" value="会員情報削除" class="btns delete">
	</form>
</div>

<%@ include file="../footer.html"%>