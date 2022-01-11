package jp.co.aforce.dao;

import jp.co.aforce.beans.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LoginDAO {
	//データソースを保存する変数ds。
	//データソースは複数のDAOクラスで共有するので、staticフィールドに格納する。
		static DataSource ds;

		// データベースへの接続のためのConnectionメソッド
		// DAOクラスのサブクラスもこのメソッドを使う
		public Connection getConnection() throws Exception {

			// データソースがnullの場合のみ、InitialContextクラスとDataSourceインターフェイスを使ってデータソースを取得、dsに保存。
			if (ds == null) {
				InitialContext ic = new InitialContext();
				//リソースの名前を書く
				ds = (DataSource) ic.lookup("java:/comp/env/jdbc/SampleLogin");

			}
			// 接続の取得。呼び出し元に返す。
			return ds.getConnection();
		}
	public Login search(String login_id, String password) throws Exception {
		Login login=null;
		Connection con=getConnection();
		
		PreparedStatement st;
		st=con.prepareStatement("select * from login where login_id=? and password=?");
		st.setString(1, login_id);
		st.setString(2, password);
		ResultSet rs=st.executeQuery();
		
		while(rs.next()) {
			login=new Login();
			login.setLogin_id(rs.getString("login_id"));
			login.setName(rs.getString("name"));
			login.setPassword(rs.getString("password"));
		}
		//elseでnullを返す？
		
		st.close();
		con.close();
		return login;
	}

}
