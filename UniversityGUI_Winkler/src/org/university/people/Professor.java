package org.university.people;

import java.util.ArrayList; 

import org.university.hardware.Department;
import org.university.software.CampusCourse;
import org.university.software.OnlineCourse;

public class Professor extends Employee{

	private double salary;
	private Department department;
	
	public Professor () {
		salary = 0.0;
		department = (new Department());
	}
	
	public void setSalary(double num) {
		salary = num;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Integer> getSchedule() {
		return schedule;
	}

	public void setSchedule(ArrayList<Integer> schedule) {
		this.schedule = schedule;
	}
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public void addCourse(CampusCourse newCourse) {
		if (detectConflict(newCourse) == false) {
			if (detectProfConflict(newCourse) == false) {
			CampusCourseList.add(newCourse);
			newCourse.setProfessor(this);
			}
		}
		else {
			return;
		}
	}
	
	public void addCourse(OnlineCourse newCourse) {
		if (detectProfConflict(newCourse) == false) {
			OnlineCourseList.add(newCourse);
			newCourse.setProfessor(this);
		}
	}
	
	public boolean detectProfConflict(CampusCourse c) {
		if (c.getProfessor().getName() == "unknown") {
			return false;
		}
		else {
			System.out.println("The professor " + this.getName() + " cannot be assigned to this course because professor " + c.getProfessor().getName() + " is already assigned to the course " + c.getName() + ".");
			return true;
		}
	}
	
	public boolean detectProfConflict(OnlineCourse c) {
		if (c.getProfessor().getName() == "unknown") {
			return false;
		}
		else {
			System.out.println("The professor " + this.getName() + " cannot be assigned to this course because professor " + c.getProfessor().getName() + " is already assigned to the course " + c.getName() + ".");
			return true;
		}
	}
	
	public double earns() {
		double earn = salary/26;
		return earn;
	}
	
	public void raise(double percent) {
		double per = percent / 100;
		double newSal = (1 + per) * salary;
		salary = newSal;
	}
	
}
