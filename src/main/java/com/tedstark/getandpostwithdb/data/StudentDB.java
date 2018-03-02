package com.tedstark.getandpostwithdb.data;

import com.tedstark.getandpostwithdb.models.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDB {
    private List<Student> studentList = new ArrayList<>();

    public List<Student> getAllStudents() {
        return studentList;
    }
    public void addStudent (Student student) {
        studentList.add(student);
    }
    public Student findStudentID(String id) {
        for (Student student:studentList){
            if (student.getIdNum().equalsIgnoreCase(id)) {
                return student;
            }
        }
        return null;
    }
}
