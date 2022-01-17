package dao;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import bean.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAO {
//データソースを保存する変数ds。
//データソースは複数のDAOクラスで共有するので、staticフィールドに格納する。
	static DataSource ds;

	// データベースへの接続のためのConnectionメソッド
	// DAOクラスのサブクラスもこのメソッドを使う
	public Connection getConnection() throws Exception {

		// データソースがnullの場合のみ、InitialContextクラスとDataSourceインターフェイスを使ってデータソースを取得、dsに保存。
		if (ds == null) {
			InitialContext ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:/comp/env/jdbc/MemberInformation");

		}
		// 接続の取得。呼び出し元に返す。
		return ds.getConnection();
	}

	// DAOのサブクラスを参考に作ったもの。ここからデータを引き出せる？
	public Member Search(String keyword) throws Exception {

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("select * from member where member_no = ?");
		st.setString(1, keyword);
		ResultSet rs = st.executeQuery();

		// 判定文?
		Member pm = new Member();
		if (rs.next()) {
			pm.setMember_no(rs.getString("member_no"));
			pm.setName(rs.getString("name"));
			pm.setAge(rs.getInt("age"));
			pm.setBirth_year(rs.getInt("birth_year"));
			pm.setBirth_month(rs.getInt("birth_month"));
			pm.setBirth_day(rs.getInt("birth_day"));
		} else {
			return null;
		}

		st.close();
		con.close();

		return pm;

	}

	public int Update(Member member) throws Exception {
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
				"update member set name=?, age=?,birth_year=?,birth_month=?,birth_day=? where member_no =?");
		st.setString(1, member.getName());
		st.setInt(2, member.getAge());
		st.setInt(3, member.getBirth_year());
		st.setInt(4, member.getBirth_month());
		st.setInt(5, member.getBirth_day());
		st.setString(6, member.getMember_no());
		int line = st.executeUpdate();

		st.close();
		con.close();
		return line;
	}

	public int Delete(Member member) throws Exception {
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("delete from member where member_no= ?");
		st.setString(1, member.getMember_no());
		int line = st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
}
