package org.university.hardware;

import java.util.ArrayList;  
import org.university.software.CampusCourse;
import java.io.Serializable;

public class Classroom implements Serializable{

	private String room;
	private ArrayList<CampusCourse> classes;
	
	public Classroom() {
		room = "empty";
		classes = new ArrayList<CampusCourse>();
	}

	public String getRoomNumber() {
		return room;
	}

	public void setRoomNumber(String room) {
		this.room = room;
	}

	public ArrayList<CampusCourse> getClasses() {
		return classes;
	}

	public void setClasses(ArrayList<CampusCourse> classes) {
		this.classes = classes;
	}
	
	public void addCourse(CampusCourse newCourse) {
		if (detectRoomConflict(newCourse) == false) {
			classes.add(newCourse);
			newCourse.setREALRoomAssigned(this);
		}
		else {
			return;
		}
	}
	
	public boolean detectRoomConflict(CampusCourse c) {
		
		int sch, oldsch, check = 0;
		ArrayList<Integer> course = c.getSchedule();
		
		for (int i = 0; i < c.getSchedule().size(); i++) {
			
			sch = course.get(i);
			
			for (int j = 0; j < classes.size(); j++) {
				
				for (int k = 0; k < classes.get(j).getSchedule().size(); k++) {
					
					oldsch = classes.get(j).getSchedule().get(k);
				
				if (sch == oldsch) {
					
					if ((classes.get(j).getRoom().getRoomNumber() != "empty")) {
						
						String[] now = makeScheduleReal(oldsch);
						
						System.out.println(c.getDepartment().getDepartmentName() + c.getCourseNumber() + " conflicts with " + classes.get(j).getDepartment().getDepartmentName() + classes.get(j).getCourseNumber() + ". Conflicting time slot " + now[0] + ". " + c.getDepartment().getDepartmentName() + c.getCourseNumber() + " course cannot be added to " + getRoomNumber() + "'s Schedule.");	
						
						check = check + 1;
						}
					}
				}
			}
		}
		if (check != 0) {
			return true;
		}
		
		return false;
	}
	
	public void printSchedule() {
		for(int n = 100; n <= 500; n = n + 100) {
			for (int m = 1; m <= 7; m++) {
				for (int i = 0; i < classes.size(); i++) {
					for (int j = 0; j < classes.get(i).getSchedule().size(); j++) {
						if (classes.get(i).getSchedule().get(j) == (n + m))
							System.out.println(classes.get(i).getScheduleReal().get(j) + " " + classes.get(i).getDepartment().getDepartmentName() + classes.get(i).getCourseNumber() + " " + classes.get(i).getName());
					}
				}
			}
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
