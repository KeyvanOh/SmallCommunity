package main2;

import java.io.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;


class Main {
    public static void main(String[] args) {
        System.out.println("Here is Main in main2.");
        
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "{\"name\":\"JK\", \"age\":30}";
        
        try {
            Student student = mapper.readValue(jsonString, Student.class);
            
            System.out.println(student);
            
            jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);
            
            System.out.println(jsonString);
        } catch(Exception e) {
            e.printStackTrace();
        };
    }
}
class Student {
    private String name;
    private int age;
    public Student() {
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "Student [ name: " + name + ", age: " + age + " ]";
    }
}