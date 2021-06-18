package org.university.software;

import java.util.ArrayList;
import org.university.hardware.Classroom;
import org.university.people.Student;

public class CampusCourse extends Course{

	private int MaxStudents;
	private ArrayList<Integer> schedule;
	private ArrayList<String> scheduleReal;
	private Classroom room;
	
	public CampusCourse () {
		MaxStudents = 1000;
		this.schedule = new ArrayList<Integer>();
		this.scheduleReal = new ArrayList<String>();
		room = new Classroom();
	}
	
	public void printSchedule() {
		for (int i = 0; i < getScheduleReal().size(); i++) {
			System.out.println(getScheduleReal().get(i) + " " + getRoom().getRoomNumber());
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
	
	public ArrayList<Integer> getSchedule() {
		return schedule;
	}
	
	public ArrayList<String> getScheduleReal() {
		return scheduleReal;
	}
	
	public void setScheduleReal(String[] s) {
		scheduleReal.add(s[0]);
	}
	
	public void setSchedule(int newSch) {
		schedule.add(newSch);
		
		String[] set = makeScheduleReal(newSch);
		setScheduleReal(set);
	}
	
	public Classroom getRoom() {
		return room;
	}
	
	public void setRoomAssigned(Classroom room) {
		room.addCourse(this);
	}
	
	public void setREALRoomAssigned(Classroom room1) {
		room = room1;
	}
	
	public int getMaxStudents () {
		return MaxStudents;
	}
	
	public void setMaxCourseLimit (int stu) {
		MaxStudents = stu;
	}
	
	public boolean availableTo(Student s) {
		if (this.getStudentRoster().size() == getMaxStudents()) {			
			return false;
		}
		else {
			return true;
		}
	}
	
}
