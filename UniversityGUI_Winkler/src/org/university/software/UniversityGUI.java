package org.university.software;

import java.util.ArrayList;  
import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.swing.*;

public class UniversityGUI extends JFrame{

	private University univ2;
	private JMenuBar menuBar;		
	private JMenu fileMenu;
	private JMenu studentsMenu;
	private JMenu adminMenu;		
	private ArrayList<University> univList;
	
	private JMenuItem adminPrintAll; 		
	private JMenuItem studentsAddCourse; 
	private JMenuItem studentsDropCourse; 
	private JMenuItem studentsPrintSchedule;
	private JMenuItem fileSaveItem;
	private JMenuItem fileLoadItem;
	private JMenuItem fileExit;
	
	public UniversityGUI(String windowTitle, University univ1) 
	{
		super(windowTitle);
		univ2 = univ1;

		setSize(300, 200);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(new JLabel("<HTML><center>Welcome to the university." +
				"<BR>Choose an action from the above menus.</center></HTML>"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildUnivGUI();	
		setVisible(true);
	}
	
	public void buildUnivGUI() 
	{
		menuBar = new JMenuBar();     	
		
		adminMenu = new JMenu("Administrator");
		studentsMenu = new JMenu("Students");		
		fileMenu = new JMenu("File");
		
		fileSaveItem = new JMenuItem("Save");
		fileLoadItem = new JMenuItem("Load");
		fileExit = new JMenuItem("Exit");
		adminPrintAll = new JMenuItem("Print All Info");
		studentsAddCourse = new JMenuItem ("Add Course");
		studentsDropCourse = new JMenuItem ("Drop Course");
		studentsPrintSchedule = new JMenuItem ("Print Schedule");

		fileSaveItem.addActionListener(new MenuListener());
		fileLoadItem.addActionListener(new MenuListener());
		fileExit.addActionListener(new MenuListener());	
		adminPrintAll.addActionListener(new MenuListener());		
		studentsAddCourse.addActionListener(new MenuListener());
		studentsDropCourse.addActionListener(new MenuListener());
		studentsPrintSchedule.addActionListener(new MenuListener());
	    
		adminMenu.add(adminPrintAll);
		fileMenu.add(fileSaveItem);
		fileMenu.add(fileLoadItem);
		fileMenu.add(fileExit);
		studentsMenu.add(studentsAddCourse);
		studentsMenu.add(studentsDropCourse);
		studentsMenu.add(studentsPrintSchedule);
		
	    menuBar.add(fileMenu);
	    menuBar.add(studentsMenu);
		menuBar.add(adminMenu);
	
		setJMenuBar(menuBar);
	}
	
	private class MenuListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) //this is the method MenuListener must implement, as it comes from the ActionListener interface.
		{
			JMenuItem source = (JMenuItem)(e.getSource());
			
			if(source.equals(adminPrintAll))
			{
				handleAdminPrint();
			}
			else if(source.equals(fileSaveItem)) {
				handleFileSave();
			}
			else if(source.equals(fileLoadItem)) {
				handleFileLoad();
			}
			else if(source.equals(fileExit))
			{
				handleFileExit();	
			}
			else if(source.equals(studentsAddCourse)) {
				handleAddCourse();
			}
			else if(source.equals(studentsDropCourse)) {
				handleDropCourse();
			}
			else if(source.equals(studentsPrintSchedule))
			{
				handlePrintSchedule();	
			}
		}
		
		private void handleAdminPrint() 
		{
			if(univ2 != null)
			{				
				
				ByteArrayOutputStream printAll = new ByteArrayOutputStream();
				PrintStream pAll = new PrintStream(printAll);
				System.setOut(pAll);
				univ2.printAll();
				//String prant = new String(printAll.toString());
				JTextArea print = new JTextArea(printAll.toString());
				System.out.flush();
				System.setOut(System.out);				
				
				JFrame window = new JFrame("UniversityGUI - Print All Info");
				JScrollPane pop = new JScrollPane(print, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				window.add(pop);
				window.setSize(500, 500);
				window.setVisible(true);
				
			}
			else
			{
				JOptionPane.showMessageDialog(null, 
						"No University save file", 
						"Error", 
						JOptionPane.PLAIN_MESSAGE);
			}
		}
		
		//save and load
		public void handleFileSave() {
			University.saveData(univ2);
		}
		
		public void handleFileLoad() {
			univ2 = University.loadData();
		}
		
		public void handleFileExit() {
			setVisible(false);
			dispose();
		}
		
		public void handleAddCourse() {
			JTextField empName = new JTextField();
			JTextField dep = new JTextField();
			JTextField num = new JTextField();
			int counter1 = 0, counter2 = 0, counter3 = 0, big1 = 0, big2 = 0, big3 = 0;
			int done1 = 0, done2 = 0;
			Object[] opt = {
					"Student Name: \n", empName, 
					"Department: \n", dep, 
					"Course #:", num
			};
			int choice = JOptionPane.showConfirmDialog(null, opt, "Add Course", JOptionPane.OK_CANCEL_OPTION);
			if (choice == JOptionPane.OK_OPTION) {
				for (int l = 0; l < univ2.getDepartment().size(); l++) {
					if (dep.getText().compareTo(univ2.getDepartment().get(l).getDepartmentName()) == 0) {
						for (int h = 0; h < univ2.getDepartment().get(l).getCourseList().size(); h++) {
							if (Integer.parseInt(num.getText()) == univ2.getDepartment().get(l).getCourseList().get(h).getCourseNumber()) {
								univ2.makeAllStudents();
								for (int k = 0; k < univ2.getAllStudents().size(); k++) {
									if (empName.getText().compareTo(univ2.getAllStudents().get(k).getName()) == 0) {
										for (int c = 0; c < univ2.getDepartment().get(l).getCampusCourseList().size(); c++) {
											if (univ2.getDepartment().get(l).getCampusCourseList().get(c) == univ2.getDepartment().get(l).getCourseList().get(h)) {
												if (univ2.getAllStudents().get(k).detectConflict(univ2.getDepartment().get(l).getCampusCourseList().get(c)) == false) {
													if (univ2.getDepartment().get(l).getCampusCourseList().get(c).availableTo(univ2.getAllStudents().get(k)) == true) {
														if (univ2.getAllStudents().get(k).checkUni(univ2.getDepartment().get(l).getCampusCourseList().get(c)) == true) {
															if (done1 == 0) {
																univ2.getAllStudents().get(k).addCourse(univ2.getDepartment().get(l).getCampusCourseList().get(c));
																JOptionPane.showMessageDialog(null, "Success! You have added " + univ2.getDepartment().get(l).getCampusCourseList().get(c).getName() + ".", "Success", JOptionPane.INFORMATION_MESSAGE);
															}
														}
														else {
															JOptionPane.showMessageDialog(null, getName() + " can't drop this CampusCourse, because this student doesn't have enough campus course credit to hold the online course.", "Error", JOptionPane.ERROR_MESSAGE);
														}
														done1 = 1;
													}
													else {
														JOptionPane.showMessageDialog(null, univ2.getAllStudents().get(k).getName() + " can't add Campus Course " + univ2.getDepartment().get(l).getDepartmentName() + univ2.getDepartment().get(l).getCampusCourseList().get(c).getCourseNumber() + " " + univ2.getDepartment().get(l).getCampusCourseList().get(c).getName() + ". Because this Campus course has enough students.", "Error", JOptionPane.ERROR_MESSAGE);
													}
												}
											}
											else {
												for (int o = 0; o < univ2.getDepartment().get(l).getOnlineCourseList().size(); o++) {
													if (univ2.getDepartment().get(l).getOnlineCourseList().get(o) == univ2.getDepartment().get(l).getCourseList().get(h)) {	
														if (univ2.getDepartment().get(l).getOnlineCourseList().get(o).availableTo(univ2.getAllStudents().get(k)) == true) {
															if (done1 == 0) {
																univ2.getAllStudents().get(k).addCourse(univ2.getDepartment().get(l).getOnlineCourseList().get(o));
																JOptionPane.showMessageDialog(null, "Success! You have added " + univ2.getDepartment().get(l).getOnlineCourseList().get(o).getName() + ".", "Success", JOptionPane.INFORMATION_MESSAGE);
															}
															done1 = 1;
														}
														else {
															if (done2 == o) {
																JOptionPane.showMessageDialog(null, "Student " + univ2.getAllStudents().get(k).getName() + " has only " + univ2.getAllStudents().get(k).getCampusUnitsEnrolled() + " on campus credits enrolled. Should have at least 6 credits registered before registering for online courses.\n" + univ2.getAllStudents().get(k).getName() + " can't add online Course " + univ2.getDepartment().get(l).getDepartmentName() + univ2.getDepartment().get(l).getOnlineCourseList().get(o).getCourseNumber() + " " + univ2.getDepartment().get(l).getOnlineCourseList().get(o).getName() + ". Because this student doesn't have enough Campus course credit.", "Error", JOptionPane.ERROR_MESSAGE);
															}
															done2 = done2 + 1;
														}
													}
												}
											}
								
										}
												
									}
									else {
										counter1 = counter1 + 1;
									}
									big1 = big1 + 1;
								}
							}
							else {
								counter2 = counter2 + 1;		
							}
							big2 = big2 + 1;
						}
					}
					else {
						counter3 = counter3 + 1;
					}
					big3 = big3 + 1;
				}
				if (counter3 == big3) {
					JOptionPane.showMessageDialog(null, "The entered department does not exist in this University.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (counter2 == big2) {
					JOptionPane.showMessageDialog(null, "The entered course does not exist in this department.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}				
				if (counter1 == big1) {
					JOptionPane.showMessageDialog(null, "Student \"" + empName.getText() + "\" doesn't exist.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			} 
		}
		
		public void handleDropCourse() {
			JTextField empName = new JTextField();
			JTextField dep = new JTextField();
			JTextField num = new JTextField();
			int counter1 = 0, counter2 = 0, counter3 = 0, big1 = 0, big2 = 0, big3 = 0;
			int done1 = 0, done2 = 0;
			Object[] opt = {
					"Student Name: \n", empName, 
					"Department: \n", dep, 
					"Course #:", num
			};
			int choice = JOptionPane.showConfirmDialog(null, opt, "Drop Course", JOptionPane.OK_CANCEL_OPTION);
			if (choice == JOptionPane.OK_OPTION) {
				for (int l = 0; l < univ2.getDepartment().size(); l++) {
					if (dep.getText().compareTo(univ2.getDepartment().get(l).getDepartmentName()) == 0) {
						for (int h = 0; h < univ2.getDepartment().get(l).getCourseList().size(); h++) {
							if (Integer.parseInt(num.getText()) == univ2.getDepartment().get(l).getCourseList().get(h).getCourseNumber()) {
								univ2.makeAllStudents();
								for (int k = 0; k < univ2.getAllStudents().size(); k++) {
									if (empName.getText().compareTo(univ2.getAllStudents().get(k).getName()) == 0) {
										for (int c = 0; c < univ2.getDepartment().get(l).getCampusCourseList().size(); c++) {
											if (univ2.getDepartment().get(l).getCampusCourseList().get(c) == univ2.getDepartment().get(l).getCourseList().get(h)) {
												univ2.getAllStudents().get(k).dropCourse(univ2.getDepartment().get(l).getCampusCourseList().get(c));
											}
											else {
												for (int o = 0; o < univ2.getDepartment().get(l).getOnlineCourseList().size(); o++) {
													if (univ2.getDepartment().get(l).getOnlineCourseList().get(o) == univ2.getDepartment().get(l).getCourseList().get(h)) {	
														if (done2 == o) {
															univ2.getAllStudents().get(k).dropCourse(univ2.getDepartment().get(l).getOnlineCourseList().get(o));
														}
														done2 = done2 + 1;
													}
												}
											}
								
										}
												
									}
									else {
										counter1 = counter1 + 1;
									}
									big1 = big1 + 1;
								}
							}
							else {
								counter2 = counter2 + 1;		
							}
							big2 = big2 + 1;
						}
					}
					else {
						counter3 = counter3 + 1;
					}
					big3 = big3 + 1;
				}
				if (counter3 == big3) {
					JOptionPane.showMessageDialog(null, "The entered department does not exist in this University.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (counter2 == big2) {
					JOptionPane.showMessageDialog(null, "The entered course does not exist in this department.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}				
				if (counter1 == big1) {
					JOptionPane.showMessageDialog(null, "Student \"" + empName.getText() + "\" doesn't exist.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			} 
		}
		
		public void handlePrintSchedule() {
			JTextField stu = new JTextField();
			int counter = 0;
			int counter2 = 0;
			Object[] filee = {
					"Student Name:", stu
			};
			int pick = JOptionPane.showConfirmDialog(null, filee, "Print Student Schedule", JOptionPane.OK_CANCEL_OPTION);
			if (pick == JOptionPane.OK_OPTION) {
				for (int i = 0; i < univ2.getDepartment().size(); i++) {
					for (int j = 0; j < univ2.getDepartment().get(i).getStudentList().size(); j++) {
						if (stu.getText().compareTo(univ2.getDepartment().get(i).getStudentList().get(j).getName()) == 0) {
							ByteArrayOutputStream printAll = new ByteArrayOutputStream();
							PrintStream pAll = new PrintStream(printAll);
							System.setOut(pAll);
							univ2.getDepartment().get(i).getStudentList().get(j).printSchedule();
							String prant = new String(printAll.toString());
							//JTextArea print = new JTextArea(printAll.toString());
							System.out.flush();
							System.setOut(System.out);	
							
							JOptionPane.showMessageDialog(null, prant, "Student " + univ2.getDepartment().get(i).getStudentList().get(j).getName() + "'s Schedule", JOptionPane.INFORMATION_MESSAGE);
							
//							JFrame window = new JFrame("UniversityGUI - Print All Info");
//							JScrollPane pop = new JScrollPane(print, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//							window.add(pop);
//							window.setSize(500, 500);
//							window.setVisible(true);
						}
						else {
							counter = counter + 1;
						}
						counter2 = counter2 + 1;
					}
				}
				if (counter == counter2) {
					JOptionPane.showMessageDialog(null, "Student \"" + stu.getText() + "\" doesn't exist.", "Error", JOptionPane.ERROR_MESSAGE);
				}				
			}

		}
		
	}
	
}
