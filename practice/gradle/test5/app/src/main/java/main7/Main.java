package main7;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class Main {
    public static void main(String[] args) {
        System.out.println("Here is Main in main7.");
        
        Main tester = new Main();
        try {
            ObjectMapper mapper = new ObjectMapper();
            
            Map<String, Object> studentDataMap = new HashMap<String, Object>();
            int[] marks = {1, 2, 3};
            
            Student student = new Student();
            student.setAge(10);
            student.setName("Mahesh");
            
            studentDataMap.put("student", student);
            studentDataMap.put("name", "Mahesh Kumar");
            studentDataMap.put("verified", Boolean.FALSE);
            studentDataMap.put("marks", marks);
            
            mapper.writeValue(new File("student.json"), studentDataMap);
            
            studentDataMap = mapper.readValue(new File("student.json"), Map.class);
            
            System.out.println(studentDataMap.get("student"));
            System.out.println(studentDataMap.get("name"));
            System.out.println(studentDataMap.get("verified"));
            System.out.println(studentDataMap.get("marks"));
        } catch(JsonParseException e) {
            e.printStackTrace();
        } catch(JsonMappingException e) {
            e.printStackTrace();
        } catch(IOException e) {
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