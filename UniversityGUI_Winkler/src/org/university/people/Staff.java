package org.university.people;

import org.university.hardware.Department;
import org.university.software.CampusCourse;
import org.university.software.OnlineCourse;

public class Staff extends Employee{

	private Department department;
	private double payRate;
	private double hours;
	private int tuitionFee;
	private int CampUnits;
	private int OnUnits;
	public int num = 0;
	
	public Staff () {
		department = new Department();
		payRate = 0;
		hours = 0;	
		CampUnits = 0;
		OnUnits = 0;
		tuitionFee = 0;
	}
	
	public void setPayRate(double pay) {
		payRate = pay;
	}
	
	public double getPayRate() {
		return payRate;
	}
	
	public void setMonthlyHours(double in) {
		hours = in;
	}
	
	public double getMonthlyHours() {
		return hours;
	}
	
	public int getCampusUnits() {
		return CampUnits;
	}
	
	public void setCampusUnits(int one) {
		CampUnits = one;
	}
	
	public int getOnlineUnits() {
		return OnUnits;
	}
	
	public void setOnlineUnits(int one) {
		OnUnits = one;
	}
	
	public Department getDepartment() {
		return department;
	}
	
	public void setDepartment(Department newDep) {
		department = newDep;
	}
	
	public double earns() {
		double earn = payRate*hours;
		return earn;
	}
	
	public void raise(double percent) {
		double per = percent / 100;
		double newSal = (1 + per) * payRate;
		payRate = newSal;
	}
	
	
	public int getTuitionFee() {
		int monall = 0;
		
		if (CampusCourseList.size() != 0) {
			monall = CampusCourseList.get(0).getCreditUnits() * 300;
		}
		
		if (OnlineCourseList.size() != 0) {
			if (OnlineCourseList.get(0).getCreditUnits() == 3) {
				monall = 2000;
			}
			if (OnlineCourseList.get(0).getCreditUnits() == 4) {
				monall = 3000;
			}
		}
		
		tuitionFee = monall;
		
		return tuitionFee;
	}
	
	public void addCourse(CampusCourse newCourse) {
		if (num == 0) {
			CampusCourseList.add(newCourse);
			newCourse.addStudenttoRoster(this);
			num = num + 1;
			return;
		}
		if (CampusCourseList.size() == 1) {
			String old = CampusCourseList.get(0).getDepartment().getDepartmentName();
			int oldNum = CampusCourseList.get(0).getCourseNumber();
			CampusCourseList.get(0).removeStudentfromRoster(this);
			CampusCourseList.remove(0);
			CampusCourseList.add(newCourse);
			newCourse.addStudenttoRoster(this);
			setCampusUnits(newCourse.getCreditUnits());
			System.out.println(old + oldNum + " is removed from " + getName() + "'s schedule(Staff can only take one class at a time). " + CampusCourseList.get(0).getDepartment().getDepartmentName() + CampusCourseList.get(0).getCourseNumber() + " has been added to " + getName() + "'s Schedule.");
			return;
		}
		if (OnlineCourseList.size() == 1) {
			String old = OnlineCourseList.get(0).getDepartment().getDepartmentName();
			int oldNum = OnlineCourseList.get(0).getCourseNumber();
			OnlineCourseList.get(0).removeStudentfromRoster(this);
			OnlineCourseList.remove(0);
			CampusCourseList.add(newCourse);
			newCourse.addStudenttoRoster(this);
			setCampusUnits(newCourse.getCreditUnits());
			setOnlineUnits(0);
			System.out.println(old + oldNum + " is removed from " + getName() + "'s schedule(Staff can only take one class at a time). " + CampusCourseList.get(0).getDepartment().getDepartmentName() + CampusCourseList.get(0).getCourseNumber() + " has been added to " + getName() + "'s Schedule.");
			return;
		}
	}

	
	public void addCourse(OnlineCourse newCourse) {
		if (num ==0) {
			OnlineCourseList.add(newCourse);
			newCourse.addStudenttoRoster(this);
			num = num + 1;
			return;
		}
		if (CampusCourseList.size() == 1) {
			String old = CampusCourseList.get(0).getDepartment().getDepartmentName();
			int oldNum = CampusCourseList.get(0).getCourseNumber();
			CampusCourseList.get(0).removeStudentfromRoster(this);
			CampusCourseList.remove(0);
			OnlineCourseList.add(newCourse);
			newCourse.addStudenttoRoster(this);
			setOnlineUnits(newCourse.getCreditUnits());
			setCampusUnits(0);
			System.out.println(old + oldNum + " is removed from " + getName() + "'s schedule(Staff can only take one class at a time). " + OnlineCourseList.get(0).getDepartment().getDepartmentName() + OnlineCourseList.get(0).getCourseNumber() + " has been added to " + getName() + "'s Schedule.");
			return;
		}
		if (OnlineCourseList.size() == 1) {
			String old = OnlineCourseList.get(0).getDepartment().getDepartmentName();
			int oldNum = OnlineCourseList.get(0).getCourseNumber();
			OnlineCourseList.get(0).removeStudentfromRoster(this);
			OnlineCourseList.remove(0);
			OnlineCourseList.add(newCourse);
			newCourse.addStudenttoRoster(this);
			setOnlineUnits(newCourse.getCreditUnits());
			setCampusUnits(0);
			System.out.println(old + oldNum + " is removed from " + getName() + "'s schedule(Staff can only take one class at a time). " + OnlineCourseList.get(0).getDepartment().getDepartmentName() + OnlineCourseList.get(0).getCourseNumber() + " has been added to " + getName() + "'s Schedule.");
			return;
		}
	}
	
	
}
