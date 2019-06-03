package com.cafe24.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.GuestBookVo;

@Repository
public class GuestBookDao {
	@Autowired
	private SqlSession sqlSession;
	
	public void delete(GuestBookVo vo) {
		sqlSession.delete("guestbook.delete", vo);
	}
	
	public void insert(GuestBookVo vo) {
		sqlSession.insert("guestbook.insert", vo);
	}
	
	public List<GuestBookVo> getList(){
		List<GuestBookVo> result = sqlSession.selectList("guestbook.getList");
		return result;
	}
	
//	private Connection getConnection() throws SQLException {
//		Connection conn = null;
//		try {
//			Class.forName("org.mariadb.jdbc.Driver");
//			String url = "jdbc:mariadb://192.168.1.37:3307/webdb";
//			conn = DriverManager.getConnection(url, "webdb", "webdb");
//
//		} catch (ClassNotFoundException e) {
//			System.out.println("드라이버 로딩 실패:" + e);
//		}
//		return conn;
//	}
}
