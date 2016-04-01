package com.luo.demo.lambda.data;

/**
 * Created by luohui on 15/11/6.
 */
public class Person {

    private String firstName, lastName, job, gender;
    private int salary, age;

    public Person(String firstName, String lastName, String job, String gender, int age,
        int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.job = job;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getJob() {
        return job;
    }

    public String getGender() {
        return gender;
    }

    public int getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }
}
