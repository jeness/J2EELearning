package com.app.test;

import com.app.controller.PersonController;
import com.app.model.Person;

import java.lang.reflect.Array;
import java.util.*;

public class testController {
    public static void main(String[] args) throws Exception {
        PersonController controller = new PersonController();
        /** get all user info
        List<Person> result = controller.getAllPersonInfo();
        for(int i = 0; i < result.size(); i++){
            System.out.println(result.get(i).getId() + ":"+
                    result.get(i).getUsername());
        }
        */

        /**  create a new person and add into db*/
        Person p = new Person();
        p.setUsername("Dave");
        p.setAge(30);
        p.setGender(0);
        p.setBirthday(new Date());
        p.setEmail("888xxx@vmail.com");
        p.setMobile("18812341234");
        p.setIsDel(0);
        p.setId(7);
//        controller.add(p);

//        controller.edit(p);

//        controller.del(7);

        List<Map<String, Object>> params = new ArrayList<>();
        Map<String, Object> param = new HashMap<>();
        param.put("name", "user_name");
        param.put("rela", "=");
        param.put("value", "'Randal'");
        params.add(param);

        List<Person> result = controller.query(params);

        for(int i = 0; i < result.size(); i++){
            System.out.println(result.get(i).getId() + ", " + result.get(i).getUsername());
        }
    }
}
