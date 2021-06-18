package org.university.hardware;

import java.util.ArrayList;   
import org.university.software.Course;
import org.university.software.CampusCourse;
import org.university.software.OnlineCourse;
import org.university.people.Student;
import org.university.people.Professor;
import org.university.people.Staff;
import java.io.Serializable;

public class Department implements Serializable{

	private String name;
	private ArrayList<Course> courses;
	private ArrayList<CampusCourse> coursesC;
	private ArrayList<OnlineCourse> coursesO;
	private ArrayList<Student> students;
	private ArrayList<Professor> professors;
	private ArrayList<Staff> staff;
	
	public Department() {
		name = "unknown";
		this.students = new ArrayList<Student>();
		this.courses = new ArrayList<Course>();
		this.professors = new ArrayList<Professor>();
		this.staff = new ArrayList<Staff>();
		this.coursesC = new ArrayList<CampusCourse>();
		this.coursesO = new ArrayList<OnlineCourse>();
	}
	
	public String getDepartmentName() {
		return name;
	}
	
	public ArrayList<Course> getCourses() {
		return courses;
	}
	
	public void setDepartmentName(String newName) {
		name = newName;
	}
	
	public ArrayList<Course> getCourseList() {
		return courses;
	}
	
	public ArrayList<CampusCourse> getCampusCourseList() {
		return coursesC;
	}
	
	public ArrayList<OnlineCourse> getOnlineCourseList() {
		return coursesO;
	}
	
	public void addCourse(CampusCourse newCourse) {
		courses.add(newCourse);
		coursesC.add(newCourse);
		newCourse.setDepartment(this);
	}
	
	public void addCourse(OnlineCourse newCourse) {
		courses.add(newCourse);
		coursesO.add(newCourse);
		newCourse.setDepartment(this);
	}
	
	public ArrayList<Student> getStudentList() {
		return students;
	}
	
	public ArrayList<Professor> getProfessorList() {
		return professors;
	}
	
	public ArrayList<Staff> getStaffList() {
		return staff;
	}
	
	public void addStudent(Student newStu) {
		students.add(newStu);
		newStu.setDepartment(this);
	}
	
	public void addProfessor(Professor newPro) {
		professors.add(newPro);
		newPro.setDepartment(this);
	}
	
	public void addStaff(Staff newS) {
		staff.add(newS);
		newS.setDepartment(this);
	}
	
	public void get1Student() {
		for (int i = 0; i < students.size(); i++) {
			System.out.println(students.get(i).getName());
		}
	}
	
	public void get1Course() {
		for (int i = 0; i < courses.size(); i++) {
			System.out.println(courses.get(i).getDepartment().getDepartmentName() + courses.get(i).getCourseNumber() + " " + courses.get(i).getName());
		}
	}
	
	public void get1Professor() {
		for (int i = 0; i < professors.size(); i++) {
			System.out.println(professors.get(i).getName());
		}
	}
	
	public void get1Staff() {
		for (int i = 0; i < staff.size(); i++) {
			System.out.println(staff.get(i).getName());
		}
	}
	
	public void printStudentList() {
		for (int i = 0; i < students.size(); i++) {
			System.out.println(students.get(i).getName());
		}
	}
	
	public void printCourseList() {
		for (int i = 0; i < courses.size(); i++) {
			System.out.println(courses.get(i).getDepartment().getDepartmentName() + courses.get(i).getCourseNumber() + " " + courses.get(i).getName());
		}
	}

	public void printProfessorList() {
		for (int i = 0; i < professors.size(); i++) {
			System.out.println(professors.get(i).getName());
		}
	}
	
	public void printStaffList() {
		for (int i = 0; i < staff.size(); i++) {
			System.out.println(staff.get(i).getName());
		}
	}
}
