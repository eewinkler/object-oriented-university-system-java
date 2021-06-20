package org.university.people;

import java.util.ArrayList; 

import org.university.hardware.Department;
import org.university.software.CampusCourse;
import org.university.software.OnlineCourse;

public class Professor extends Employee{//subclass of employee object

	private double salary;//salary
	private Department department;//department
	
	public Professor () {//initialization
		salary = 0.0;
		department = (new Department());
	}
	
	public void setSalary(double num) {//salary setter
		salary = num;
	}
	
	public double getSalary() {//salary getter
		return salary;
	}
	
	public String getName() {//name getter
		return name;
	}

	public void setName(String name) {//name setter
		this.name = name;
	}
	
	public ArrayList<Integer> getSchedule() {//schedule getter
		return schedule;
	}

	public void setSchedule(ArrayList<Integer> schedule) {//schedule setter
		this.schedule = schedule;
	}
	
	public Department getDepartment() {//department getter
		return department;
	}

	public void setDepartment(Department department) {//department setter
		this.department = department;
	}
	
	public void addCourse(CampusCourse newCourse) {//add campus course function, abstract in person object
		if (detectConflict(newCourse) == false) {//check for conflict
			if (detectProfConflict(newCourse) == false) {//check for if professor is conflicted (multiple professors)
			CampusCourseList.add(newCourse);//add course to professor
			newCourse.setProfessor(this);//add professor to course
			}
		}
		else {//conflict found, system error message printed in conflict functions
			return;//end
		}
	}
	
	public void addCourse(OnlineCourse newCourse) {//add online course
		if (detectProfConflict(newCourse) == false) {//check for if professor is conflicted (multiple professors)
			OnlineCourseList.add(newCourse);//add course to professor
			newCourse.setProfessor(this);//add professor to course
		}
	}
	
	public boolean detectProfConflict(CampusCourse c) {//method checks if a professor has already been assigned to a campus course
		if (c.getProfessor().getName() == "unknown") {//checks if class professor has initialized name "unknown", meaning it is unset
			return false;//no conflict
		}
		else {
			System.out.println("The professor " + this.getName() + " cannot be assigned to this course because professor " + c.getProfessor().getName() + " is already assigned to the course " + c.getName() + ".");//error message
			return true;//conflict
		}
	}
	
	public boolean detectProfConflict(OnlineCourse c) {//method checks if a professor has already been assigned to a online course
		if (c.getProfessor().getName() == "unknown") {//checks if class professor has initialized name "unknown", meaning it is unset
			return false;//no conflict
		}
		else {
			System.out.println("The professor " + this.getName() + " cannot be assigned to this course because professor " + c.getProfessor().getName() + " is already assigned to the course " + c.getName() + ".");//error message
			return true;//conflict
		}
	}
	
	public double earns() {//method returns what professor earns in one two-week paycheck
		double earn = salary/26;
		return earn;
	}
	
	public void raise(double percent) {//method calculates and applies raise for professor
		double per = percent / 100;
		double newSal = (1 + per) * salary;
		salary = newSal;
	}
	
}
