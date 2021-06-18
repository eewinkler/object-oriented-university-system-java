package org.university.software;

import org.university.people.Student;

public class OnlineCourse extends Course{

	public boolean availableTo(Student s) {
		if (s.getCampusUnitsEnrolled() < 6) {
			System.out.println("Student " + s.getName() + " has only " + s.getCampusUnitsEnrolled() + " on campus credits enrolled. Should have at least 6 credits registered before registering for online courses.");
			return false;
		}
		else {
			return true;
		}
	}
	
}
