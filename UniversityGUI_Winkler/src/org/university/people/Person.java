package org.university.people;

import java.io.Serializable; 
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.university.software.CampusCourse;
import org.university.software.OnlineCourse;

public abstract class Person implements Serializable{

	protected String name;
	protected ArrayList<Integer> schedule;
	protected ArrayList<CampusCourse> CampusCourseList;
	protected ArrayList<OnlineCourse> OnlineCourseList;
	
	public Person () {
		name = "unknown";
		this.schedule = new ArrayList<Integer>();
		this.CampusCourseList = new ArrayList<CampusCourse>();
		this.OnlineCourseList = new ArrayList<OnlineCourse>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public ArrayList<CampusCourse> getCCourse() {
		return CampusCourseList;
	}
	
	public ArrayList<OnlineCourse> getOCourse() {
		return OnlineCourseList;
	}
	
	public abstract void addCourse(CampusCourse newCourse);
	public abstract void addCourse(OnlineCourse newCourse);
	
	public boolean detectConflict(CampusCourse c) {
		
		int sch, oldsch, check = 0; 
		ArrayList<Integer> course = c.getSchedule();
		
		for (int i = 0; i < c.getSchedule().size(); i++) {
			
			sch = course.get(i);
			
			for (int j = 0; j < CampusCourseList.size(); j++) {
				
				for (int k = 0; k < CampusCourseList.get(j).getSchedule().size(); k++) {
					
					oldsch = CampusCourseList.get(j).getSchedule().get(k);
				
				if (sch == oldsch) {
					
					String[] now = makeScheduleReal(oldsch);
					
					JOptionPane.showMessageDialog(null, c.getDepartment().getDepartmentName() + c.getCourseNumber() + " cannot be added to " + getName() + "'s Schedule. " + c.getDepartment().getDepartmentName() + c.getCourseNumber() + " conflicts with " + CampusCourseList.get(j).getDepartment().getDepartmentName() + CampusCourseList.get(j).getCourseNumber() + ". Conflicting time slot is " + now[0] + ".", "Error", JOptionPane.ERROR_MESSAGE);
	
					check = 1;
					
					}
				}
			}
		}
		if (check == 1) {
			return true;
		} 
		return false;
	}
	
	public ArrayList<Integer> getSchedule() {
		return schedule;
	}

	public void setSchedule(ArrayList<Integer> schedule) {
		this.schedule = schedule;
	}
	
	public void printSchedule() {
		if (CampusCourseList.size() >= 1) {
		for(int n = 100; n <= 500; n = n + 100) {
			for (int m = 1; m <= 7; m++) {
				for (int i = 0; i < CampusCourseList.size(); i++) {
					for (int j = 0; j < CampusCourseList.get(i).getSchedule().size(); j++) {
						if (CampusCourseList.get(i).getSchedule().get(j) == (n + m))
							System.out.println(CampusCourseList.get(i).getScheduleReal().get(j) + " " + CampusCourseList.get(i).getDepartment().getDepartmentName() + CampusCourseList.get(i).getCourseNumber() + " " + CampusCourseList.get(i).getName());
						}
					}
				}
			}
		}
		if (OnlineCourseList.size() < 1) {
			return;
		}
		for (int l = 0; l < OnlineCourseList.size(); l++) {
			System.out.println(OnlineCourseList.get(l).getCourseNumber() + " " + OnlineCourseList.get(l).getName());
		}
	}
	
	
	public String[] makeScheduleReal(int sch) {
		int first, second, third;
		String[] lastSch = {"none"};

		ArrayList<String> Week = new ArrayList<String>();
		Week.add("Mon "); Week.add("Tue "); Week.add("Wed "); Week.add("Thu "); Week.add("Fri ");
		ArrayList<String> Slot = new ArrayList<String>();
		Slot.add("8:00am to 9:15am");
		Slot.add("9:30am to 10:45am");
		Slot.add("11:00am to 12:15pm");
		Slot.add("12:30pm to 1:45pm");
		Slot.add("2:00pm to 3:15pm");
		Slot.add("3:30pm to 4:45pm");    
		
		first = sch % 10;
		second = ((sch % 100) - first);
		third = ((sch) - (second + first))/100;
		
		lastSch[0] = Week.get(third - 1) + Slot.get(first - 1);
		
		return lastSch;
	}
	
}
