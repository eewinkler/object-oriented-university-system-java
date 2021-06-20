package org.university.hardware;

import java.util.ArrayList;  
import org.university.software.CampusCourse;
import java.io.Serializable;

public class Classroom implements Serializable{

	private String room;//room number
	private ArrayList<CampusCourse> classes;//class list
	
	public Classroom() {//initialization
		room = "empty";
		classes = new ArrayList<CampusCourse>();
	}

	public String getRoomNumber() {//room number getter
		return room;
	}

	public void setRoomNumber(String room) {//classroom room number setter
		this.room = room;
	}

	public ArrayList<CampusCourse> getClasses() {//classroom class list getter
		return classes;
	}

	public void setClasses(ArrayList<CampusCourse> classes) {//classroom class list setter
		this.classes = classes;
	}
	
	public void addCourse(CampusCourse newCourse) {//function to add course to classroom class list
		if (detectRoomConflict(newCourse) == false) {//check for scheduling conflict
			classes.add(newCourse);//add course to list
			newCourse.setREALRoomAssigned(this);//assign this classroom to course object
		}
		else {//schedule conflict
			return;
		}
	}
	
	public boolean detectRoomConflict(CampusCourse c) {//function to detect scheduling conflict when trying to add courses to classroom
		
		int sch, oldsch, check = 0;//temp variables
		ArrayList<Integer> course = c.getSchedule();//gets room schedule for passed in course (c)
		
		for (int i = 0; i < c.getSchedule().size(); i++) {//for loop for each scheduled time in course
			
			sch = course.get(i);//get course 1
			
			for (int j = 0; j < classes.size(); j++) {//for loop for each course scheduled in this classroom
				
				for (int k = 0; k < classes.get(j).getSchedule().size(); k++) {//for loop for each scheduled time in courses found in this classroom
					
					oldsch = classes.get(j).getSchedule().get(k);//get course 2
				
				if (sch == oldsch) {//if courses are scheduled at the same time
					
					if ((classes.get(j).getRoom().getRoomNumber() != "empty")) {//if old course does not have an empty room assignment
						
						String[] now = makeScheduleReal(oldsch);
						
						System.out.println(c.getDepartment().getDepartmentName() + c.getCourseNumber() + " conflicts with " + classes.get(j).getDepartment().getDepartmentName() + classes.get(j).getCourseNumber() + ". Conflicting time slot " + now[0] + ". " + c.getDepartment().getDepartmentName() + c.getCourseNumber() + " course cannot be added to " + getRoomNumber() + "'s Schedule.");//display conflict message	
						
						check = check + 1;//make checker return true, meaning there is a conflict
						}
					}
				}
			}
		}
		if (check != 0) {
			return true;//conflict
		}
		
		return false;//no conflict
	}
	
	public void printSchedule() {//function to print schedule for classroom class list
		for(int n = 100; n <= 500; n = n + 100) {
			for (int m = 1; m <= 7; m++) {
				for (int i = 0; i < classes.size(); i++) {
					for (int j = 0; j < classes.get(i).getSchedule().size(); j++) {
						if (classes.get(i).getSchedule().get(j) == (n + m))
							System.out.println(classes.get(i).getScheduleReal().get(j) + " " + classes.get(i).getDepartment().getDepartmentName() + classes.get(i).getCourseNumber() + " " + classes.get(i).getName());//schedule, department name, course number, course name
					}
				}
			}
		}
	}
	
	public String[] makeScheduleReal(int sch) {//function assigns numerical value for schedule to a string with cooresponding day and time slot
		int first, second, third;//temp variables
		String[] lastSch = {"none"};//string array

		ArrayList<String> Week = new ArrayList<String>();//string ArrayList for days of the week
		Week.add("Mon "); Week.add("Tue "); Week.add("Wed "); Week.add("Thu "); Week.add("Fri ");//load array with Mon-Fri
		ArrayList<String> Slot = new ArrayList<String>();//string ArrayList for class time slots
		Slot.add("8:00am to 9:15am");
		Slot.add("9:30am to 10:45am");
		Slot.add("11:00am to 12:15pm");
		Slot.add("12:30pm to 1:45pm");
		Slot.add("2:00pm to 3:15pm");
		Slot.add("3:30pm to 4:45pm");   
		
		//Integer passed in (sch) is a three digit integer. The first integer corresponds to a time slot (1-6) and the third digit corresponds to the day of the week (1-5).
		//Second digit contributes to the third digit calculation
		
		first = sch % 10;//first digit
		second = ((sch % 100) - first);//second digit
		third = ((sch) - (second + first))/100;//third digit
		
		lastSch[0] = Week.get(third - 1) + Slot.get(first - 1);//Finds complete schedule and puts the string into the final array
		
		return lastSch;
	}

}
