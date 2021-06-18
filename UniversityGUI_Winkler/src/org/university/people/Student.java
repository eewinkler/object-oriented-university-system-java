package org.university.people;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.university.hardware.Department;
import org.university.software.CampusCourse;
import org.university.software.OnlineCourse;

public class Student extends Person{

	private Department department;
	private int unitsDone;
	private int unitsNeeded;
	private int unitsEnrolled;
	private int CampUnits;
	private int OnUnits;
	private int tuitionFee;
	
	public Student () {
		department = new Department();
		unitsDone = 0;
		unitsNeeded = 20;
		unitsEnrolled = 0;
		tuitionFee = 0;
		CampUnits = 0;
		OnUnits = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public Department getDepartment() {
		return department;
	}
	
	public void setDepartment(Department newDep) {
		department = newDep;
	}
	
	public ArrayList<Integer> getSchedule() {
		return schedule;
	}

	public void setSchedule(ArrayList<Integer> schedule) {
		this.schedule = schedule;
	}
	
	public int getCompletedUnits() {
		return unitsDone;
	}

	public void setCompletedUnits(int unitsDone) {
		this.unitsDone = unitsDone;
	}
	
	public int getRequiredCredits() {
		return unitsNeeded;
	}

	public void setRequiredCredits(int unitsNeeded) {
		this.unitsNeeded = unitsNeeded;
	}
	
	public int requiredToGraduate() {
		return unitsNeeded - (unitsDone + unitsEnrolled);
	}

	public int getUnitsEnrolled() {
		return unitsEnrolled;
	}

	public void setUnitsEnrolled(int units) {
		unitsEnrolled = units;
	}
	
	public void setCampusUnitsEnrolled(int unitsEnrolled) {
		CampUnits = unitsEnrolled;
	}
	
	public void addUnitsEnrolled(int units) {
		unitsEnrolled = unitsEnrolled + units;
	}
	
	public void addCampusUnitsEnrolled(int unitsEnrolled) {
		CampUnits = CampUnits + unitsEnrolled;
	}
	
	public int getCampusUnitsEnrolled() {
		return CampUnits;
	}
	
	public void setOnlineUnitsEnrolled(int unitsEnrolled) {
		OnUnits =  unitsEnrolled;
	}
	
	public void addOnlineUnitsEnrolled(int unitsEnrolled) {
		OnUnits = OnUnits + unitsEnrolled;
	}
	
	public int getOnlineUnitsEnrolled() {
		return OnUnits;
	}
	
	public boolean checkUni(CampusCourse c) {
		for (int i = 0; i < CampusCourseList.size(); i++) {
			if (CampusCourseList.get(i) == c) {
				if ((getCampusUnitsEnrolled() - c.getCreditUnits() < 6) && (OnlineCourseList.size() >= 1)) {
					return false;
				}
			}
		}	
		return true;
	}
	
	public void dropCourse(CampusCourse c) {
		for (int i = 0; i < CampusCourseList.size(); i++) {
			if (CampusCourseList.get(i) == c) {
				if ((getCampusUnitsEnrolled() - c.getCreditUnits() < 6) && (OnlineCourseList.size() >= 1)) {
					//System.out.println(getName() + " can't drop this CampusCourse, because this student doesn't have enough campus course credit to hold the online course.");
					JOptionPane.showMessageDialog(null, getName() + " can't drop this CampusCourse, because this student doesn't have enough campus course credit to hold the online course.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if ((getCampusUnitsEnrolled() - c.getCreditUnits() < 6) && (OnlineCourseList.size() < 1)) {
					CampusCourseList.remove(c);
					c.removeStudentfromRoster(this);
					setCampusUnitsEnrolled(getCampusUnitsEnrolled() - c.getCreditUnits());
					setUnitsEnrolled(getUnitsEnrolled() - c.getCreditUnits());
					JOptionPane.showMessageDialog(null, getName() + " has dropped the course " + c.getName(), "Course Dropped", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if ((getCampusUnitsEnrolled() - c.getCreditUnits() >= 6)) {
					CampusCourseList.remove(c);
					c.removeStudentfromRoster(this);
					setCampusUnitsEnrolled(getCampusUnitsEnrolled() - c.getCreditUnits());
					setUnitsEnrolled(getUnitsEnrolled() - c.getCreditUnits());
					JOptionPane.showMessageDialog(null, getName() + " has dropped the course " + c.getName(), "Course Dropped", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}
		}
				//System.out.println("The course " + c.getDepartment().getDepartmentName() + c.getCourseNumber() + " could not be dropped because " + getName() + " is not enrolled in " + c.getDepartment().getDepartmentName() + c.getCourseNumber() + ".");
				JOptionPane.showMessageDialog(null, "The course " + c.getDepartment().getDepartmentName() + c.getCourseNumber() + " could not be dropped because " + getName() + " is not enrolled in " + c.getDepartment().getDepartmentName() + c.getCourseNumber() + ".", "Error", JOptionPane.ERROR_MESSAGE);
				return;
	}
	
	public void dropCourse(OnlineCourse c) {
		if (!OnlineCourseList.remove(c)) {
			//System.out.println("The course " + c.getDepartment().getDepartmentName() + c.getCourseNumber() + " could not be dropped because " + getName() + " is not enrolled in " + c.getDepartment().getDepartmentName() + c.getCourseNumber() + ".");
			JOptionPane.showMessageDialog(null, "The course " + c.getDepartment().getDepartmentName() + c.getCourseNumber() + " could not be dropped because " + getName() + " is not enrolled in " + c.getDepartment().getDepartmentName() + c.getCourseNumber() + ".", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		setOnlineUnitsEnrolled(getOnlineUnitsEnrolled() - c.getCreditUnits());
		setUnitsEnrolled(getUnitsEnrolled() - c.getCreditUnits());
		c.removeStudentfromRoster(this);
		JOptionPane.showMessageDialog(null, getName() + " has dropped the course " + c.getName(), "Course Dropped", JOptionPane.INFORMATION_MESSAGE);
	}

	public void addCourse(CampusCourse newCourse) {
		if (detectConflict(newCourse) == false) {
			if (newCourse.availableTo(this) == true) {
			CampusCourseList.add(newCourse);
			newCourse.addStudenttoRoster(this);
			addCampusUnitsEnrolled(newCourse.getCreditUnits());
			addUnitsEnrolled(newCourse.getCreditUnits());
			}
			else {
			//System.out.println(this.getName() + " can't add Campus Course " + newCourse.getDepartment().getDepartmentName() + newCourse.getCourseNumber() + " " + newCourse.getName() + ". Because this Campus course has enough students.");
			}
		}
		else {
			return;
		}
	}

	public void addCourse(OnlineCourse newCourse) {
		if (newCourse.availableTo(this) == true) {
			OnlineCourseList.add(newCourse);
			newCourse.addStudenttoRoster(this);
			addOnlineUnitsEnrolled(newCourse.getCreditUnits());
			addUnitsEnrolled(newCourse.getCreditUnits());
		}
		else {
			JOptionPane.showMessageDialog(null, this.getName() + " can't add online Course " + newCourse.getDepartment().getDepartmentName() + newCourse.getCourseNumber() + " " + newCourse.getName() + ". Because this student doesn't have enough Campus course credit.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public int getTuitionFee() {
		int mon1 = 0, mon2 = 0, monall = 0;
		
		mon1 = CampUnits * 300;
		
		for (int i = 0; i < OnlineCourseList.size(); i++) {
			if (OnlineCourseList.get(i).getCreditUnits() == 3) {
				mon2 = mon2 + 2000;
			}
			if (OnlineCourseList.get(i).getCreditUnits() == 4) {
				mon2 = mon2 + 3000;
			}
		}
		
		monall = mon1 + mon2;
		
		tuitionFee = monall;
		
		return tuitionFee;
		
	}
	
}