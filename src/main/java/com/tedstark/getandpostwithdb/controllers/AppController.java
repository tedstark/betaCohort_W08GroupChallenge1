package com.tedstark.getandpostwithdb.controllers;

import com.tedstark.getandpostwithdb.data.StudentDB;
import com.tedstark.getandpostwithdb.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class AppController {

    @Autowired
    private StudentDB studentDB;

    @RequestMapping("/getallstudents")
    public @ResponseBody List<Student> getAllStudents() {
        return studentDB.getAllStudents();
    }
    @RequestMapping(value = "/add/student", method = RequestMethod.POST)
    public @ResponseBody Student postStudent (@RequestBody Map<String, Object> payload) {
        String newIdNum=String.valueOf(payload.get("idNum"));
        String newFName=String.valueOf(payload.get("firstName"));
        String newLName=String.valueOf(payload.get("lastName"));
        String newZipCode=String.valueOf(payload.get("zipCode"));
        Student newStudent = new Student(newIdNum, newFName, newLName, newZipCode);
        studentDB.addStudent(newStudent);
        return newStudent;

    }
    @RequestMapping("/get/{idNum}")
    public @ResponseBody Student findStudentID(@PathVariable String idNum){
        Student foundStudent=studentDB.findStudentID(idNum);
        return foundStudent;
    }
    @RequestMapping("/")
    public String indexRte (){
        return "index";
    }
}
