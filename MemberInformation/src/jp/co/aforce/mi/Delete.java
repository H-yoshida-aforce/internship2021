package jp.co.aforce.mi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Member;
import dao.DAO;

@WebServlet(urlPatterns = { "/mi/delete" })
public class Delete extends HttpServlet {
	/**
	 * 削除処理
	 * 
	 * @author 吉田萌乃香
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		// 会員情報を探す処理
		// 検索して情報を取得する。jspからjava
		try {
			// if文 ボタン名："表示"だったら～する
			if (request.getParameter("hyouji") != null) {
				// リクエストパラメータでデータ取得。jspからjava
				String keyword = request.getParameter("keyword");

				// 会員番号の入力チェック
				if (keyword == null || keyword.isEmpty()) {
					request.setAttribute("Erms", "入力されていない項目があります");
					request.getRequestDispatcher("delete.jsp").forward(request, response);
				} else {
					// DAOに検索ワードを入れて探す
					DAO dao = new DAO();
					Member member = dao.Search(keyword);

					// 既存の情報をsetしてjspに表示する。
					if (member != null) {
						request.setAttribute("member", member);
						request.getRequestDispatcher("delete.jsp").forward(request, response);

					} else {
						request.setAttribute("nobody", "該当する会員情報が見つかりません");
						request.getRequestDispatcher("delete.jsp").forward(request, response);
					}
				}
				// else ボタン名：変更だったら～する
			} else if (request.getParameter("sakujo") != null) {
				// リクエストパラメータでデータ取得。jspからjava
				String member_no = request.getParameter("keyword");
				String name = request.getParameter("name");

				// intをnullチェックしてparseIntする
				String nennrei = request.getParameter("age");
				// 変数の宣言
				int age = 0;
				if (nennrei != null && nennrei != "") {
					age = Integer.parseInt(nennrei);
				}

				String seinenn = request.getParameter("birth_year");
				int birth_year = 0;
				if (seinenn != null && seinenn != "") {
					birth_year = Integer.parseInt(seinenn);
				}

				String seigetsu = request.getParameter("birth_month");
				int birth_month = 0;
				if (seigetsu != null && seigetsu != "") {
					birth_month = Integer.parseInt(seigetsu);
				}

				String seijitsu = request.getParameter("birth_day");
				int birth_day = 0;
				if (seijitsu != null && seijitsu != "") {
					birth_day = Integer.parseInt(seijitsu);
				}

				// 入力チェック
				if (member_no == null || member_no.isEmpty() || name == null || name.isEmpty() || nennrei == null
						|| nennrei.isEmpty() || seinenn == null || seinenn.isEmpty() || seigetsu == null
						|| seigetsu.isEmpty() || seijitsu == null || seijitsu.isEmpty()) {
					request.setAttribute("Erms", "入力されていない項目があります");
					request.getRequestDispatcher("delete.jsp").forward(request, response);

				} else {

					// Memberオブジェクトを作成して、セッタで新しい情報を書き込む。
					Member m = new Member();
					m.setMember_no(member_no);
					m.setName(name);
					m.setAge(age);
					m.setBirth_year(birth_year);
					m.setBirth_month(birth_month);
					m.setBirth_day(birth_day);

					// DAOに上のデータを入れる。変更処理をする。
					DAO dao = new DAO();
					int line = dao.Delete(m);

					// 成功か失敗か表示
					if (line > 0) {
						out.println("削除に成功しました");

					} else {
						out.println("削除に失敗しました");
					}

					// if文のとじかっこ
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}