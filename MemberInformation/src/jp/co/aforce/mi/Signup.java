package jp.co.aforce.mi;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.Calendar;
import java.text.SimpleDateFormat;

@WebServlet(urlPatterns = { "/mi/signup" })
public class Signup extends HttpServlet {
	@SuppressWarnings("null")
	/**
	 * 新規登録処理
	 * 
	 * @author 吉田萌乃香
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup("java:/comp/env/jdbc/MemberInformation");
			Connection con = ds.getConnection();

			if (request.getParameter("shinki") != null) {

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
				if (name == null || name.isEmpty() || nennrei == null || nennrei.isEmpty() || seinenn == null
						|| seinenn.isEmpty() || seigetsu == null || seigetsu.isEmpty() || seijitsu == null
						|| seijitsu.isEmpty()) {
					request.setAttribute("Erms", "入力されていない項目があります");
					request.getRequestDispatcher("signup.jsp").forward(request, response);

				} else {
					Calendar c1 = Calendar.getInstance();
					PreparedStatement st = con.prepareStatement("insert into member values(?,?,?,?,?,?)");
					// 会員番号をセットする
					SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmss");
					String str = sdf.format(c1.getTime());
					st.setString(1, "A" + str);
					st.setString(2, name);
					st.setInt(3, age);
					st.setInt(4, birth_year);
					st.setInt(5, birth_month);
					st.setInt(6, birth_day);
					int line = st.executeUpdate();

					if (line > 0) {
						out.println("登録に成功しました。");

					} else {
						con.rollback();
						out.println("登録に失敗しました。");

					}

					st.close();
					con.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace(out);
		}
	}
}
