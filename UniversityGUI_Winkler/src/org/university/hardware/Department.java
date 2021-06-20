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

	private String name;//department name
	private ArrayList<Course> courses;//department courses
	private ArrayList<CampusCourse> coursesC;//department courses on campus in person
	private ArrayList<OnlineCourse> coursesO;//department courses online
	private ArrayList<Student> students;//students in department
	private ArrayList<Professor> professors;//professors in department
	private ArrayList<Staff> staff;//staff in department
	
	public Department() {//initializations
		name = "unknown";
		this.students = new ArrayList<Student>();
		this.courses = new ArrayList<Course>();
		this.professors = new ArrayList<Professor>();
		this.staff = new ArrayList<Staff>();
		this.coursesC = new ArrayList<CampusCourse>();
		this.coursesO = new ArrayList<OnlineCourse>();
	}
	
	public String getDepartmentName() {//name getter
		return name;
	}
	
	public ArrayList<Course> getCourses() {//course list getter
		return courses;
	}
	
	public void setDepartmentName(String newName) {//name setter
		name = newName;
	}
	
	public ArrayList<Course> getCourseList() {//course list getter
		return courses;
	}
	
	public ArrayList<CampusCourse> getCampusCourseList() {//campus course list getter
		return coursesC;
	}
	
	public ArrayList<OnlineCourse> getOnlineCourseList() {//online course list getter
		return coursesO;
	}
	
	public void addCourse(CampusCourse newCourse) {//add campus course to course list
		courses.add(newCourse);//add to big course list
		coursesC.add(newCourse);//add to campus course list
		newCourse.setDepartment(this);//set department for course
	}
	
	public void addCourse(OnlineCourse newCourse) {//add online course to course list
		courses.add(newCourse);//add to big course list
		coursesO.add(newCourse);//add to online course list
		newCourse.setDepartment(this);//set department for course
	}
	
	public ArrayList<Student> getStudentList() {//student list getter
		return students;
	}
	
	public ArrayList<Professor> getProfessorList() {//professor list getter
		return professors;
	}
	
	public ArrayList<Staff> getStaffList() {//staff list getter
		return staff;
	}
	
	public void addStudent(Student newStu) {//add student
		students.add(newStu);
		newStu.setDepartment(this);
	}
	
	public void addProfessor(Professor newPro) {//add professor
		professors.add(newPro);
		newPro.setDepartment(this);
	}
	
	public void addStaff(Staff newS) {//add staff
		staff.add(newS);
		newS.setDepartment(this);
	}
	
	public void get1Student() {//gets one student from department
		for (int i = 0; i < students.size(); i++) {
			System.out.println(students.get(i).getName());
		}
	}
	
	public void get1Course() {//gets one course from department
		for (int i = 0; i < courses.size(); i++) {
			System.out.println(courses.get(i).getDepartment().getDepartmentName() + courses.get(i).getCourseNumber() + " " + courses.get(i).getName());
		}
	}
	
	public void get1Professor() {//gets one professor from department
		for (int i = 0; i < professors.size(); i++) {
			System.out.println(professors.get(i).getName());
		}
	}
	
	public void get1Staff() {//gets one staff from department
		for (int i = 0; i < staff.size(); i++) {
			System.out.println(staff.get(i).getName());
		}
	}
	
	public void printStudentList() {//prints whole student name list
		for (int i = 0; i < students.size(); i++) {
			System.out.println(students.get(i).getName());
		}
	}
	
	public void printCourseList() {//prints whole course list with department, course number, and name
		for (int i = 0; i < courses.size(); i++) {
			System.out.println(courses.get(i).getDepartment().getDepartmentName() + courses.get(i).getCourseNumber() + " " + courses.get(i).getName());
		}
	}

	public void printProfessorList() {//prints whole professor name list
		for (int i = 0; i < professors.size(); i++) {
			System.out.println(professors.get(i).getName());
		}
	}
	
	public void printStaffList() {//prints whole staff name list
		for (int i = 0; i < staff.size(); i++) {
			System.out.println(staff.get(i).getName());
		}
	}
}
