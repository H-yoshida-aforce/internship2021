package jp.co.aforce.dao;

import jp.co.aforce.beans.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LoginDAO {
	// データソースを保存する変数ds。
	// データソースは複数のDAOクラスで共有するので、staticフィールドに格納する。
	static DataSource ds;

	// データベースへの接続のためのConnectionメソッド
	// DAOクラスのサブクラスもこのメソッドを使う
	public Connection getConnection() throws Exception {

		// データソースがnullの場合のみ、InitialContextクラスとDataSourceインターフェイスを使ってデータソースを取得、dsに保存。
		if (ds == null) {
			InitialContext ic = new InitialContext();
			// リソースの名前を書く
			ds = (DataSource) ic.lookup("java:/comp/env/jdbc/SampleLogin");

		}
		// 接続の取得。呼び出し元に返す。
		return ds.getConnection();
	}

	/**検索処理**/
	public Login search(String loginId, String password) throws Exception {
		Login login = null;
		Connection con = getConnection();

		PreparedStatement st;
		st = con.prepareStatement("select * from login where loginId=? and password=?");
		st.setString(1, loginId);
		st.setString(2, password);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			login = new Login();
			login.setLoginId(rs.getString("loginId"));
			login.setName(rs.getString("name"));
			login.setPassword(rs.getString("password"));
		}

		st.close();
		con.close();
		return login;
	}

}
