package org.university.software;

import java.util.ArrayList; 
import org.university.hardware.Department;
import org.university.people.Student;
import org.university.people.Professor;
import org.university.people.Staff;
import org.university.people.Person;
import java.io.Serializable;

public abstract class Course implements Serializable{
	
	private ArrayList<Person> roster;
	private int number;
	private String name;
	private Department department;
	private Professor professor;
	private int CreditUnits;
	
	public Course () {
		name = "unknown";
		number = 10;
		department = new Department();
		this.roster = new ArrayList<Person>();
		professor = new Professor();
		setCreditUnits(0);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public int getCourseNumber() {
		return number;
	}
	
	public void setCourseNumber(int newNum) {
		number = newNum;
	}
	
	public Department getDepartment() {
		return department;
	}
	
	public void setDepartment(Department newDep) {
		department = newDep;
	}
	
	public ArrayList<Person> getStudentRoster() {
		return roster;
	}
	
	public void addStudenttoRoster(Student newStu) {
		roster.add(newStu);
	}
	
	public void removeStudentfromRoster(Student newStu) {
		roster.remove(newStu);
	}
	
	public void addStudenttoRoster(Staff newSt) {
		roster.add(newSt);
	}
	
	public void removeStudentfromRoster(Staff newSt) {
		roster.remove(newSt);
	}
	
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public int getCreditUnits() {
		return CreditUnits;
	}

	public void setCreditUnits(int creditUnits) {
		CreditUnits = creditUnits;
	}
	
	public abstract boolean availableTo (Student a);

}