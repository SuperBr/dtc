package com.test;

import com.dtc.zkService.BeanInfo;
import com.google.common.collect.Lists;
import com.suber.model.Person;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Method;
import java.time.Period;
import java.util.List;
import java.util.Map;

public class ListTest {

    public static void main(String[] args) {

        Method[] method = ListTest.class.getDeclaredMethods();

      /*  BeanInfo beanInfo = new BeanInfo();
        beanInfo.buildMethodInfo(method[1]);*/

        Comp.class.getDeclaredFields();

        BeanWrapper beanWrapper = new BeanWrapperImpl(Comp.class);


    }


    public void test(List<Person> people, Map<Person,Map<List<Person>,Map<Object,Person>>> personObjectMap,Person person) {


    }


}

class Comp {
    private List<Person> people;

    private Map<Person, List<Person>> personPersonMap;

    public List<Person> getPeople() {
        return people;
    }

    public Comp setPeople(List<Person> people) {
        this.people = people;
        return this;
    }

    public Map<Person, List<Person>> getPersonPersonMap() {
        return personPersonMap;
    }

    public Comp setPersonPersonMap(Map<Person, List<Person>> personPersonMap) {
        this.personPersonMap = personPersonMap;
        return this;
    }
}