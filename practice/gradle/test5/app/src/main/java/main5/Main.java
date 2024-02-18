package main5;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class Main {
    public static void main(String[] args) {
        System.out.println("Here is Main in main5.");
        
        Main tester = new Main();
        try {
            Student student = new Student();
            student.setAge(30);
            student.setName("jk");
            tester.writeJSON(student);
            
            Student student1 = tester.readJSON();
            System.out.println(student1);
        } catch(JsonParseException e) {
            e.printStackTrace();
        } catch(JsonMappingException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        };
    }
    private void writeJSON(Student student) throws JsonGenerationException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("student.json"), student);
    }
    private Student readJSON() throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Student student = mapper.readValue(new File("student.json"), Student.class);
        return student;
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
        return "Student [ name: " + name + ", age " + age + " ]";
    }
}