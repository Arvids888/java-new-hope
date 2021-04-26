package model;

import model.enums.CourseName;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private CourseName name;
    private int lessonCount;
    private List<Student> students = new ArrayList<>();

    public Course(CourseName name, int lessonCount) {
        this.name = name;
        this.lessonCount = lessonCount;
    }

    public CourseName getName() {
        return name;
    }

    public int getLessonCount() {
        return lessonCount;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);

    }
}
