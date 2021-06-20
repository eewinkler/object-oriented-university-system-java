package org.university.people;

import java.io.Serializable; 
import java.util.ArrayList;

import javax.swing.JOptionPane;//swing importation

import org.university.software.CampusCourse;
import org.university.software.OnlineCourse;

public abstract class Person implements Serializable{

	protected String name;//person name
	protected ArrayList<Integer> schedule;//person schedule
	protected ArrayList<CampusCourse> CampusCourseList;//person campus course list
	protected ArrayList<OnlineCourse> OnlineCourseList;//person online course list
	
	public Person () {//initialization
		name = "unknown";
		this.schedule = new ArrayList<Integer>();
		this.CampusCourseList = new ArrayList<CampusCourse>();
		this.OnlineCourseList = new ArrayList<OnlineCourse>();
	}
	
	public String getName() {//name getter
		return name;
	}
	
	public void setName(String newName) {//name setter
		name = newName;
	}
	
	public ArrayList<CampusCourse> getCCourse() {//campus course list getter
		return CampusCourseList;
	}
	
	public ArrayList<OnlineCourse> getOCourse() {//online course list getter
		return OnlineCourseList;
	}
	
	public abstract void addCourse(CampusCourse newCourse);//abstract add campus course method, used in student and professor
	public abstract void addCourse(OnlineCourse newCourse);//abstract add online course method, used in student and professor
	
	public boolean detectConflict(CampusCourse c) {//detects scheduling conflicts for any person adding new course
		
		int sch, oldsch, check = 0;//temp variables
		ArrayList<Integer> course = c.getSchedule();//passed in courses schedule
		
		for (int i = 0; i < c.getSchedule().size(); i++) {//loop through passed course schedule
			
			sch = course.get(i);//get course 1
			
			for (int j = 0; j < CampusCourseList.size(); j++) {//loop for person's campus course list
				
				for (int k = 0; k < CampusCourseList.get(j).getSchedule().size(); k++) {//loop for course schedule
					
					oldsch = CampusCourseList.get(j).getSchedule().get(k);//get course 2
				
				if (sch == oldsch) {//conflict found
					
					String[] now = makeScheduleReal(oldsch);//stringify schedule
					
					JOptionPane.showMessageDialog(null, c.getDepartment().getDepartmentName() + c.getCourseNumber() + " cannot be added to " + getName() + "'s Schedule. " + c.getDepartment().getDepartmentName() + c.getCourseNumber() + " conflicts with " + CampusCourseList.get(j).getDepartment().getDepartmentName() + CampusCourseList.get(j).getCourseNumber() + ". Conflicting time slot is " + now[0] + ".", "Error", JOptionPane.ERROR_MESSAGE);//Error message prints
	
					check = 1;//check returns true
					
					}
				}
			}
		}
		if (check == 1) {
			return true;//conflict
		} 
		return false;//no conflict
	}
	
	public ArrayList<Integer> getSchedule() {//schedule getter
		return schedule;
	}

	public void setSchedule(ArrayList<Integer> schedule) {//schedule setter
		this.schedule = schedule;
	}
	
	public void printSchedule() {//method that prints the person's schedule list
		if (CampusCourseList.size() >= 1) {
		for(int n = 100; n <= 500; n = n + 100) {//loop with three digit course system described in method line 107, first digit described time slot (1-7) and third digit described day of the week (1-5, Mon-Fri)
			for (int m = 1; m <= 7; m++) {//first digit
				for (int i = 0; i < CampusCourseList.size(); i++) {
					for (int j = 0; j < CampusCourseList.get(i).getSchedule().size(); j++) {
						if (CampusCourseList.get(i).getSchedule().get(j) == (n + m))//combines three digits into number and checks within schedule
							System.out.println(CampusCourseList.get(i).getScheduleReal().get(j) + " " + CampusCourseList.get(i).getDepartment().getDepartmentName() + CampusCourseList.get(i).getCourseNumber() + " " + CampusCourseList.get(i).getName());//prints if found
						}
					}
				}
			}
		}
		if (OnlineCourseList.size() < 1) {//end if no online courses
			return;
		}
		for (int l = 0; l < OnlineCourseList.size(); l++) {//print online courses
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
