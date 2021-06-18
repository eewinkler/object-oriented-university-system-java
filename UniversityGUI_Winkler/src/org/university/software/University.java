package org.university.software;

import java.util.ArrayList;   
import org.university.hardware.Department;
import org.university.hardware.Classroom;
import org.university.people.Student;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class University implements Serializable {

	public ArrayList<Department> departmentList;
	public ArrayList<Classroom> classroomList;
	public ArrayList<Student> allStudents;
	
	public University() {
		super();
		this.departmentList = new ArrayList<Department>();
		this.classroomList = new ArrayList<Classroom>();
		this.allStudents = new ArrayList<Student>();
	}
	
	public void printDepartmentList() {
		for(int i = 0; i < departmentList.size(); i++) {
			departmentList.get(i).getDepartmentName();
		}
	}
	
	public void makeAllStudents() {
		allStudents = new ArrayList<Student>();
		for (int i = 0; i < getDepartment().size(); i++) {
		for (int j = 0; j < getDepartment().get(i).getStudentList().size(); j++) {
			allStudents.add(getDepartment().get(i).getStudentList().get(j));
		}
	}
	}
	
	public ArrayList<Student> getAllStudents() {
		return allStudents;
	}
	
	public void printClassroomList() {
		for(int i = 0; i < classroomList.size(); i++) {
			classroomList.get(i).getRoomNumber();
		}
	}
	
	public void printStudentList() {
		for(int i = 0; i < departmentList.size(); i++) {
			departmentList.get(i).get1Student();
		}
	}
	
	public void printCourseList() {
		for(int i = 0; i < departmentList.size(); i++) {
			departmentList.get(i).get1Course();
		}
	}

	
	public void printProfessorList() {
		for(int i = 0; i < departmentList.size(); i++) {
			departmentList.get(i).get1Professor();
		}
	}
	
	public void printStaffList() {
		for(int i = 0; i < departmentList.size(); i++) {
			departmentList.get(i).get1Staff();
		}
	}
	
	public ArrayList<Department> getDepartment() {
		return departmentList;
	}
	
	public ArrayList<Classroom> getClassroom() {
		return classroomList;
	}
	
	public static void saveData(University univ){
		
		
		FileOutputStream fileOut = null;
		ObjectOutputStream objOut = null;

		try 
		{
			fileOut = new FileOutputStream("university.ser");		//the Employee object makes its way to serial data in the file Employee.ser
			objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(univ);
			objOut.close();
			fileOut.close();
	     }	
		
		catch(IOException i)
	    {
			i.printStackTrace();
	    }		
		}

	public static University loadData()
	{	
		FileInputStream fileIn = null;
		ObjectInputStream objIn = null;
		University emp=null;
			
		try
		{
			fileIn = new FileInputStream("university.ser");
			objIn = new ObjectInputStream(fileIn);
			emp = (University) objIn.readObject();
			objIn.close();
			fileIn.close();
		}
		catch(IOException i)
		{
			i.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}  
		return emp;
	}	   
	
	public void printAll()
	{	  
	    System.out.println("List of departments:");
		for(int i = 0; i < departmentList.size(); i++) {
			System.out.println(departmentList.get(i).getDepartmentName());
		}
	    System.out.println("\nClassroom list:");
		for(int i = 0; i < classroomList.size(); i++) {
			System.out.println(classroomList.get(i).getRoomNumber());
		}
	    
    	for(int i = 0; i < departmentList.size(); i++) {
    		System.out.println("\nThe professor list for department " + departmentList.get(i).getDepartmentName() + ":");	
    		departmentList.get(i).get1Professor();
    	}
	    
		for(int i = 0; i < departmentList.size(); i++) {
			System.out.println("\nThe course list for department " + departmentList.get(i).getDepartmentName() + ":");
			departmentList.get(i).get1Course();
		}
		
		for(int i = 0; i < classroomList.size(); i++) {
			System.out.println("\nThe schedule for classroom " + classroomList.get(i).getRoomNumber() + ":");
			classroomList.get(i).printSchedule();;
		}
		
		for(int i = 0; i < departmentList.size(); i++) {
			
			System.out.println("\nDepartment " + departmentList.get(i).getDepartmentName());
			
			System.out.println("\nPrinting Professor Schedules:");
			for(int j = 0; j < departmentList.get(i).getProfessorList().size(); j++) {
				System.out.println("\nThe Schedule for Prof. " + departmentList.get(i).getProfessorList().get(j).getName() + ":");
				departmentList.get(i).getProfessorList().get(j).printSchedule();
			}
			
			System.out.println("\nPrinting Student Schedules:");
			for(int j = 0; j < departmentList.get(i).getStudentList().size(); j++) {
				System.out.println("\nThe schedule for Student " + departmentList.get(i).getStudentList().get(j).getName() + ":");
				departmentList.get(i).getStudentList().get(j).printSchedule();
			}
			
			System.out.println("\nPrinting Staff Schedules:");
			for(int j = 0; j < departmentList.get(i).getStaffList().size(); j++) {
				System.out.println("\nThe schedule for Employee " + departmentList.get(i).getStaffList().get(j).getName() + ":");
				departmentList.get(i).getStaffList().get(j).printSchedule();
				System.out.println("\nStaff: " + departmentList.get(i).getStaffList().get(j).getName() + " earns " + (departmentList.get(i).getStaffList().get(j).getMonthlyHours()*departmentList.get(i).getStaffList().get(j).getPayRate()) + " this month.");
			}
			
			System.out.println("\nThe rosters for courses offered by " + departmentList.get(i).getDepartmentName() + ":");
			for(int j = 0; j < departmentList.get(i).getCampusCourseList().size(); j++) {
				System.out.println("\nThe roster for course " + departmentList.get(i).getDepartmentName() + departmentList.get(i).getCampusCourseList().get(j).getCourseNumber() + ":");
				for(int k = 0; k < departmentList.get(i).getCampusCourseList().get(j).getStudentRoster().size(); k++) {
					System.out.println(departmentList.get(i).getCampusCourseList().get(j).getStudentRoster().get(k).getName());
				}				
			}	
		}
	    	  
	}
	
}