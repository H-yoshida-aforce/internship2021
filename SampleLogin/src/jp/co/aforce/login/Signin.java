package jp.co.aforce.login;

import jp.co.aforce.beans.Login;
import jp.co.aforce.dao.LoginDAO;
import jp.co.aforce.tool.Page;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(urlPatterns = { "/login/signin" })
public class Signin extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Page.header(out);
		
		try {
			String login_id=request.getParameter("login_id");
			String password=request.getParameter("password");
			
			LoginDAO dao=new LoginDAO();
			Login login=dao.search(login_id,password);
			
			if (login != null) {
				request.setAttribute("login", login);
				request.getRequestDispatcher("../jsp/login-success.jsp").forward(request, response);
			} else {
				request.setAttribute("ErrorId", login_id);
//				request.getRequestDispatcher("../jsp/login.jsp").forward(request, response);
				
				request.setAttribute("Errormsg", "IDもしくはパスワードが違います");
				request.getRequestDispatcher("../jsp/login.jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace(out);
		}
		Page.footer(out);
	}
}
