package com.app.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.app.db.DBUtils;
import com.app.model.Person;

public class PersonDao {

	public void addPerson(Person person) throws SQLException {
		Connection con = DBUtils.getConnection();
		String sql = "insert into personaldata"+
				"(user_name, gender, age, birthday, email, mobile, " +
				"create_user, create_date, update_user, update_date, isdel)"+
				"values" +
				"(?,?,?,?,?,?,?,current_date(),?,current_date(),?)";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, person.getUsername());
		ptmt.setInt(2, 0);
		ptmt.setInt(3, person.getAge());
		ptmt.setDate(4, new Date(person.getBirthday().getTime()));
		ptmt.setString(5, person.getEmail());
		ptmt.setString(6, person.getMobile());
		ptmt.setString(7, "Admin");
		ptmt.setString(8,"Admin");
		ptmt.setInt(9, 0);
		ptmt.execute();
	}
	
	public void updatePerson(Person person) throws SQLException {
		Connection con = DBUtils.getConnection();
		String sql = " update personaldata"+
				" set user_name=?, gender=?, age=?, birthday=?, email=?, mobile=?," +
				" update_user=?, update_date=current_date, isdel=?"+
				" where id=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, person.getUsername());
		ptmt.setInt(2, person.getGender());
		ptmt.setInt(3, person.getAge());
		ptmt.setDate(4, new Date(person.getBirthday().getTime()));
		ptmt.setString(5, person.getEmail());
		ptmt.setString(6, person.getMobile());
		ptmt.setString(7,"Admin");
		ptmt.setInt(8, 1);
		ptmt.setInt(9, person.getId());
		ptmt.execute();
	}
	
	public void deletePerson(Integer id) throws SQLException {
		Connection con = DBUtils.getConnection();
		String sql = "delete from personaldata"+
				" where id=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setInt(1, id);
		ptmt.execute();
	}
	
	public List<Person> getBasicInfo() throws Exception{
		Connection con = DBUtils.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select id, user_name, age from personaldata");
		List<Person> people = new ArrayList<>();
		while(rs.next()) {
			Person person = new Person();
			person.setId(rs.getInt("id"));
			person.setUsername(rs.getString("user_name"));
			person.setAge(rs.getInt("age"));
			people.add(person);
		}
		return people;
	}
	
	public Person queryById(int id) throws SQLException {
		Person p = null;
		Connection con = DBUtils.getConnection();
		String sql = "select * from personaldata"+
				" where id=?";
		PreparedStatement ptmt = con.prepareStatement(sql);

		ptmt.setInt(1, id);
		ResultSet rs = ptmt.executeQuery();
		while(rs.next()){
			p=new Person();
			p.setId(rs.getInt("id"));
			p.setUsername(rs.getString("user_name"));
			p.setAge(rs.getInt("age"));
			p.setGender(rs.getInt("gender"));
			p.setBirthday(rs.getDate("birthday"));
			p.setEmail(rs.getString("email"));
			p.setMobile(rs.getString("mobile"));
			p.setCreateDate(rs.getDate("create_date"));
			p.setCreateUser(rs.getString("create_user"));
			p.setUpdateUser(rs.getString("update_user"));
			p.setUpdateDate(rs.getDate("update_date"));
		}
		return p;
		
	}

	public List<Person> queryByUsername(String name) throws SQLException {
		Person p = null;
		List<Person> people = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		String sql = "select * from personaldata"+
				" where user_name=?";
		PreparedStatement ptmt = con.prepareStatement(sql);

		ptmt.setString(1, name);
		ResultSet rs = ptmt.executeQuery();
		while(rs.next()){
			p=new Person();
			p.setId(rs.getInt("id"));
			p.setUsername(rs.getString("user_name"));
			p.setAge(rs.getInt("age"));
			p.setGender(rs.getInt("gender"));
			p.setBirthday(rs.getDate("birthday"));
			p.setEmail(rs.getString("email"));
			p.setMobile(rs.getString("mobile"));
			p.setCreateDate(rs.getDate("create_date"));
			p.setCreateUser(rs.getString("create_user"));
			p.setUpdateUser(rs.getString("update_user"));
			p.setUpdateDate(rs.getDate("update_date"));
			people.add(p);
		}
		return people;
	}

	public List<Person> query(List<Map<String, Object>> params) throws SQLException {

		List<Person> people = new ArrayList<>();
		Connection con = DBUtils.getConnection();
//		String sql = "select * from personaldata"+
//				" where user_name=?";

		StringBuilder sb = new StringBuilder();
		sb.append("select * from personaldata where 1=1");
//		sb.append(" where user_name like ? and mobile like ? and email")
		if(params != null && params.size() > 0){
			for(int i = 0; i < params.size(); i++){
				Map<String, Object> map  = params.get(i);
				sb.append(" and " + map.get("name") + " "+ map.get("rela") + " " +map.get("value"));
			}
		}
		PreparedStatement ptmt = con.prepareStatement(sb.toString());

		ResultSet rs = ptmt.executeQuery();
		Person p = null;
		while(rs.next()){
			p=new Person();
			p.setId(rs.getInt("id"));
			p.setUsername(rs.getString("user_name"));
			p.setAge(rs.getInt("age"));
			p.setGender(rs.getInt("gender"));
			p.setBirthday(rs.getDate("birthday"));
			p.setEmail(rs.getString("email"));
			p.setMobile(rs.getString("mobile"));
			p.setCreateDate(rs.getDate("create_date"));
			p.setCreateUser(rs.getString("create_user"));
			p.setUpdateUser(rs.getString("update_user"));
			p.setUpdateDate(rs.getDate("update_date"));
			people.add(p);
		}
		return people;
	}
}
