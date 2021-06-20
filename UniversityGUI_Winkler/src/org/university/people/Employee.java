package org.university.people;

public abstract class Employee extends Person {//employee is a child of person class
	
	public abstract double earns();//abstract salary method, used in professor and staff objects
	public abstract void raise(double percent);//abstract raise method, used in professor and staff objects
	
}
