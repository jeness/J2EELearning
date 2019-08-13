package com.app.view;

import com.app.controller.PersonController;
import com.app.model.Person;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class View {

	public void printMenu() {
		System.out.println("This is the menu list:");
		System.out.println("M: Return main menu");
		System.out.println("Q: Get all people's basic info");
		System.out.println("G: Get one person's detailed info by id");
		System.out.println("A: Add one new person's info");
		System.out.println("D: Delete one new person's info");
		System.out.println("U: Update one person's info");
		System.out.println("S: Search people by name or mobile(fuzzy search)");
		System.out.println("E: Exit");
		System.out.println("Exit: Quit from current function and return to main menu");
	}
	private static final String OPERATION_MAIN = "MAIN";
	private static final String OPERATION_QUERY = "QUERY";
	private static final String OPERATION_GET = "GET";
	private static final String OPERATION_ADD = "ADD";
	private static final String OPERATION_UPDATE = "UPDATE";
	private static final String OPERATION_DELETE = "DELETE";
	private static final String OPERATION_SEARCH = "SEARCH";
	private static final String OPERATION_EXIT = "EXIT";
	private static final String OPERATION_BREAK = "BREAK";

	public static void main(String[] args) throws Exception {
		View sh = new View();
		sh.printMenu();
		Scanner scan = new Scanner(System.in);
		Person person = new Person();
		PersonController controller = new PersonController();
		String previous = null;
		Integer step = 1;
		boolean getById = true;
		Map<String, Object> param = new HashMap<>();
		while(scan.hasNext()){
			String input = scan.next().toString();
			if(OPERATION_MAIN.equals(input.toUpperCase())
					|| OPERATION_MAIN.substring(0, 1).equals(input.toUpperCase())){
				sh.printMenu();
			}
			else if(OPERATION_EXIT.equals(input.toUpperCase())
					|| OPERATION_EXIT.substring(0, 1).equals(input.toUpperCase())){
				System.out.println("You exits system!!!");
				break;
			}
			else if(OPERATION_QUERY.equals(input.toUpperCase())
					|| OPERATION_QUERY.substring(0,1).equals(input.toUpperCase())) {
				List<Person> list = controller.getAllPersonInfo();
				for(Person p : list){
					System.out.println(p.getId() + ", name: " + p.getUsername());
				}
			}
			else if(OPERATION_GET.equals(input.toUpperCase())
					|| OPERATION_GET.substring(0,1).equals(input.toUpperCase())
					|| OPERATION_GET.equals(previous)) {
				previous = OPERATION_GET;
				if(getById == true){
					System.out.println("Please input the id to query: ");
					getById = false;
				}
				else {
					Person p = controller.getById(Integer.valueOf(input));
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					System.out.println("Id: "+p.getId() + "\n" +
							"Name: " + p.getUsername() + "\n" +
							"email: " + p.getEmail() + "\n" +
							"Age: " + p.getAge() + "\n" +
							"Mobile: " + p.getMobile()+ "\n" +
							"Gender: " + p.getGender()+ "\n" +
							"Birthday: " + df.format(p.getBirthday()));
					previous = null;
					getById = true;
				}
			}
			else if(OPERATION_ADD.equals(input.toUpperCase())
			|| OPERATION_ADD.substring(0,1).equals(input.toUpperCase())
			|| OPERATION_ADD.equals(previous)){
				previous = OPERATION_ADD;
				//add new person
				if(1 == step){
					System.out.println("Please input the [name] of person:");
				}
				else if(2 == step){
					person.setUsername(input);
					System.out.println("Please input the [age] of person: ");
				}
				else if(3 == step){
					person.setAge(Integer.valueOf(input));
					System.out.println("Please input the [birthday] of person:(yyyy-MM-dd)");
				}
				else if(4 == step){
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					Date birthday = null;
					try{
						birthday = sf.parse(input);
						person.setBirthday(birthday);
						System.out.println("Please input the [email] of person: ");
					} catch (ParseException e) {
						e.printStackTrace();
						System.out.println("Your input format has error, please try again");
						step = 3;
					}
				}
				else if(5 == step){
					person.setEmail(input);
					System.out.println("Please input [mobile phone number]: ");
				}
				else if(6 == step){
					person.setMobile(input);
					try{
						controller.add(person);
						System.out.println("Successfully add a new person");
						step = 1;
						previous = null;
						person = new Person();
					}catch (Exception e){
						e.printStackTrace();
						System.out.println("Fail to add new person");
					}
				}
				if(OPERATION_ADD.equals(previous)){
					step++;
				}
			}
			else if(OPERATION_DELETE.equals(input.toUpperCase())
					|| OPERATION_DELETE.substring(0,1).equals(input.toUpperCase())
					|| OPERATION_DELETE.equals(previous)) {
				previous = OPERATION_DELETE;
				if(1 == step){
					System.out.println("Please input the id to delete: ");
				}
				else if(2 == step){
					try{
						controller.del(Integer.valueOf(input));
						System.out.println("Successfully delete a person");
						step = 1;
						previous = null;
					}catch (Exception e){
						e.printStackTrace();
						System.out.println("Fail to delete a person");
					}
				}
				if(OPERATION_DELETE.equals(previous)){
					step++;
				}
			}
			else if(OPERATION_UPDATE.equals(input.toUpperCase())
					|| OPERATION_UPDATE.substring(0,1).equals(input.toUpperCase())
					|| OPERATION_UPDATE.equals(previous)) {
				previous = OPERATION_UPDATE;
				if(step == 1){
					System.out.println("Please input the id to update: ");
				}
				else if(step == 2){
					int inputId = Integer.valueOf(input);
					person = controller.getById(inputId);
					if(person == null){
						System.out.println("Your id is not existed, please input a right id again");
						step = 1;
					}
					else {
						person.setId(inputId);
						System.out.println("Please enter [new username] to update: (if not change, enter null)");
					}
				}
				else if(step == 3){
					if(!input.equals("null")){
						person.setUsername(input);
					}
					System.out.println("Please enter [new gender] to update: (if not change, enter null)");
				}
				else if(step == 4){
					if(!input.equals("null")){
						person.setGender(Integer.valueOf(input));
					}
					System.out.println("Please enter [new age] to update: (if not change, enter null)");
				}
				else if(step == 5){
					if(!input.equals("null")){
						person.setAge(Integer.valueOf(input));
					}
					System.out.println("Please enter [new birthday yyyy-MM-dd] to update: " +
							"(if not change, enter null)");
				}
				else if(step == 6){
					if(!input.equals("null")){
						SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
						Date birthday = null;
						try{
							birthday = sf.parse(input);
							person.setBirthday(birthday);
							System.out.println("Please input the [new email] of person: (if not change, enter null)");
						} catch (ParseException e) {
							e.printStackTrace();
							System.out.println("Your input format has error, please try again");
							step = 5;
						}
					}
				}
				else if(step == 7){
					if(!input.equals("null")){
						person.setEmail(input);
					}
					System.out.println("Please enter[new mobile] to update:"
					+"if not change, enter null");
				}
				else if(step == 8){
					if(!input.equals("null")){
						person.setMobile(input);
					}
					try{
						controller.edit(person);
						System.out.println("Successfully edit a person");
						step = 1;
						previous = null;
						person = new Person();
					}catch (Exception e){
						e.printStackTrace();
						System.out.println("Fail to edit a person");
					}
				}
				if(OPERATION_UPDATE.equals(previous)){
					step++;
				}
			}
			else if(OPERATION_SEARCH.equals(input.toUpperCase())
					|| OPERATION_SEARCH.substring(0,1).equals(input.toUpperCase())
					|| OPERATION_SEARCH.equals(previous)) {
				previous = OPERATION_SEARCH;
				if(step == 1){
					System.out.println("Please input the fuzzy key word\n" +
							"[NAME] Name\n" +
							"[MOBILE] Mobile");
				}
				else if(step == 2){
					if(input.toUpperCase().equals("NAME")){
						System.out.println("Please input the fuzzy name to query: ");
						param.put("name", "user_name");
					}
					else if(input.toUpperCase().equals("MOBILE")){
						System.out.println("Please input the fuzzy mobile to query: ");
						param.put("name", "mobile");
					}
				}
				else if(step == 3){
					try{
						param.put("rela", "like");
						param.put("value", "'%"+input+"%'");
						List<Map<String, Object>> list = new ArrayList<>();
						list.add(param);
						List<Person> listPeople = controller.query(list);
						for(Person p : listPeople){
							DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
							System.out.println("Id: "+p.getId() + " | " +
									"Name: " + p.getUsername() + " | " +
									"email: " + p.getEmail() + " | " +
									"Age: " + p.getAge() + " | " +
									"Mobile: " + p.getMobile()+ " | " +
									"Gender: " + p.getGender()+ " | " +
									"Birthday: " + df.format(p.getBirthday()));


						}
						previous = null;
						step = 1;
					}
					catch (Exception e) {
						e.printStackTrace();
						System.out.println("Fuzzy query has error, please try again");
						step = 2;
					}

				}
				if(OPERATION_SEARCH.equals(previous)){
					step++;
				}
			}

			else{
				System.out.println("Your input is: " + input);
			}
		}

	}

}
