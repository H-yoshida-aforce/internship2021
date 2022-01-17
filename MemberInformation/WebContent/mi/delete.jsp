<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.html"%>
<link rel="stylesheet" type="text/css" href="../css.css">

<%@page import="bean.Member"%>

<div class=delete>
	<p>会員情報削除画面</p>

	<%Member member = (Member) request.getAttribute("member");%>
	<%request.getAttribute("Erms");%>
	<%request.getAttribute("nobody");%>

	<form action="./delete" method="post">
		${Erms } ${nobody }
		<p>
			会員番号 <input type="text" name="keyword" class="bangou"
				value=${member.member_no }> <input type="submit"
				name="hyouji" class="hyouji" value="表示">
		</p>

		<p>
			名前 <input type="text" name="name" class="namae" readonly
				value=${member.name }>
		</p>
		<p>
			年齢 <input type="text" name="age" class="toshi" readonly
				value=${member.age }>
		</p>

		<!-- selectがdisabledのため、値を送信するために、hiddenでinput typeも用意 -->
		<p>
			生年月日 <select name="birth_year" class="nenn" disabled>
				<option value=${member.birth_year }>${member.birth_year}</option>
			</select> <input type="hidden" name="birth_year" value=${member.birth_year }>

			<select name="birth_month" class="getsu" disabled>
				<option value=${member.birth_month }>${member.birth_month}
				</option>
			</select> <input type="hidden" name="birth_month" value=${member.birth_month }>

			<select name="birth_day" class="nichi" disabled>
				<option value=${member.birth_day }>${member.birth_day}</option>
			</select> <input type="hidden" name="birth_day" value=${member.birth_day }>
		</p>

		<button type="button" class="modoru" onclick="history.back(-1)">戻る</button>
		<input type="submit" name="sakujo" class="sakujo" value="削除">

	</form>
</div>

<%@ include file="../footer.html"%>