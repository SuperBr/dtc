package com.test;


import com.alibaba.fastjson.JSON;
import com.suber.model.Person;

public class JsonTest {

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("wyc");
        String json =JSON.toJSON(person).toString();
        System.out.println(json);
    }
}
