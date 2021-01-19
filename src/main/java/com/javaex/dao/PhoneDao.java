package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.PersonVo;

public class PhoneDao {
	ResultSet rs = null;
	Connection conn = null;
	PreparedStatement pstmt = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb";
	private String pw = "phonedb";

	private void getConnection() {
		try {
			Class.forName(driver);


			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("여기서 문제 1 :" + e);
		} catch (SQLException e) {
			System.out.println("여기서 문제 2 :" + e);
		}
	}


	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

//insert========================================================	
	public int personInsert(PersonVo PersonVo) {

		getConnection();
		int count = 0;
		try {

			
			String query = "";
			query += " insert into person ";
			query += " values(seq_person_id.nextval,?,?,?) ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, PersonVo.getName());
			pstmt.setString(2, PersonVo.getHp());
			pstmt.setString(3, PersonVo.getCompany());

			count = pstmt.executeUpdate();

			// ���ó��
			System.out.println("[" + count + "�� ��ϵǾ����ϴ�.]");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		// �ڿ�����
		close();
		return count;
	}

//list========================================================	
	public List<PersonVo> getPersonList() {

		List<PersonVo> personList = new ArrayList<PersonVo>();

		getConnection();
		try {

			// SQL�� �غ� / ���ε� / ����
			String query = "";

			query += " SELECT person_id, ";
			query += " 		  name, ";
			query += "        hp, ";
			query += "        company ";
			query += " FROM person ";
			query += " order by person_id asc";

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// ���ó��
			while (rs.next()) {
				int personId = rs.getInt("person_id");
				String personName = rs.getNString("name");
				String personHp = rs.getNString("hp");
				String personcompany = rs.getNString("company");
				PersonVo vo = new PersonVo(personId, personName, personHp, personcompany);
				personList.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		System.out.println("문제없음.");
		close();
		return personList;
	}
	
//update========================================================
	public int personUpdate(PersonVo personVo) {
		getConnection();
		int count = 0;
		try {

			//SQL�� �غ� / ���ε� / ����
			String query = "";
			query += " update person ";
			query += " set name = ?, ";
			query += "     hp = ?, ";
			query += "     company = ? ";
			query += " where person_id = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());
			pstmt.setInt(4, personVo.getPersonId());

			count = pstmt.executeUpdate();// �� ����Ǹ� 1��ȯ �ȵǸ�0

			// ���ó��
			System.out.println("[" + count + "�� �����Ǿ����ϴ�.]");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		// �ڿ�����
		close();

		return count;
	}
	
//delete=======================================================
	public int parsonDelete(int personId) {
		getConnection();
		int count = 0;
		try {

			// 3. SQL�� �غ� / ���ε� / ����

			String query = "";
			query += " delete from person ";
			query += " where person_id = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, personId);

			count = pstmt.executeUpdate();

			// 4.���ó��
			System.out.println("[" + count + "�� �����Ǿ����ϴ�.]");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		close();
		return count;
	}
	
//search=======================================================
	public List<PersonVo> getsearchList(String search) {
		// 0. import java.sql.*;

		List<PersonVo> personsearchList = new ArrayList<PersonVo>();

		getConnection();
		try {
			String query = "";
			
			query += " select person_id, ";
			query += " 		  name, ";
			query += "        hp, ";
			query += "  	  company ";
			query += " from person ";
			query += " where name LIKE '%" + search + "%' ";
			query += " or hp LIKE '%" + search + "%' ";
			query += " or company LIKE '%" + search + "%' ";
			query += " order by person_id asc";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			// 4.���ó��
			while (rs.next()) {
				int personId = rs.getInt("person_id");
				String personName = rs.getNString("name");
				String personHp = rs.getNString("hp");
				String personcompany = rs.getNString("company");
				PersonVo vo = new PersonVo(personId, personName, personHp, personcompany);
				personsearchList.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		// �ڿ�����
		close();
		return personsearchList;
	}
	
	//1명 가져오기 
		public PersonVo getPerson(int personId) {
			// 0. import java.sql.*;
			PersonVo personVo = null;
		
			getConnection();
			try {
				String query = "";
				
				query += " select person_id, ";
				query += " 		  name, ";
				query += "        hp, ";
				query += "  	  company ";
				query += " from person ";
				query += " where person_id = ?";
				query += " order by person_id asc";

				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1,personId);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					int personID = rs.getInt("person_id");
					String name = rs.getNString("name");
					String hp = rs.getNString("hp");
					String company = rs.getNString("company");
					
					personVo = new PersonVo(personID,name,hp,company);
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
			
			close();
			return personVo;
		}
}
