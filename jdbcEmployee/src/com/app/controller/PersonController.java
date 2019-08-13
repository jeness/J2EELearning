package com.app.controller;

import java.sql.SQLException;
import java.util.*;

import com.app.dao.PersonDao;
import com.app.model.Person;

public class PersonController {

	public void add(Person person) throws SQLException {
		PersonDao dao = new PersonDao();
		dao.addPerson(person);
	}

	public Person getById(Integer id) throws SQLException {
		PersonDao dao = new PersonDao();
		return dao.queryById(id);
	}

	public void edit(Person person) throws SQLException {
		PersonDao dao = new PersonDao();
		dao.updatePerson(person);
	}
	public void del(Integer id) throws SQLException {
		PersonDao dao = new PersonDao();
		dao.deletePerson(id);
	}

	public List<Person> getAllPersonInfo() throws Exception {
		PersonDao dao = new PersonDao();
		return dao.getBasicInfo();
	}

	public List<Person> query(List<Map<String, Object>> params) throws SQLException {
		PersonDao dao = new PersonDao();
		return dao.query(params);
	}

	public static void main(String[] args) throws Exception {
		List<Person> people = new ArrayList<>();
		PersonDao pd = new PersonDao();


		Person p1 = new Person();
		p1.setUsername("Randal");
		p1.setAge(19);
		p1.setGender(1);
		p1.setBirthday(new Date());
		p1.setEmail("ssrrrs@gmail.com");
		p1.setMobile("18766605522");
		p1.setCreateUser("ADMIN");
		p1.setUpdateUser("ADMIN");
		p1.setIsDel(1);
//		p1.setId(1);

//		Person p2 = pd.queryById(3);
//		System.out.println(p2.toString());

//		List<Person> p3 = pd.queryByUsername("Randal");
//		for(Person p : p3){
//			System.out.println(p.toString());
//		}


//		pd.deletePerson(1);

//		pd.updatePerson(p1);

//		pd.addPerson(p1);

//		people = pd.getBasicInfo();
//		for(Person p : people) {
//			System.out.println(p.getUsername() + ", " + p.getAge());
//		}

		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
		Map<String, Object> param = new HashMap<>();
		param.put("name", "user_name");
		param.put("rela", "=");
		param.put("value", "'Randal'");
		params.add(param);
		param = new HashMap<>();
		param.put("name", "mobile");
		param.put("rela", "like");
		param.put("value", "'%666%'");
		params.add(param);
		List<Person> result = pd.query(params);
		for(Person p : result){
			System.out.println(p.toString());
		}

	}
}
