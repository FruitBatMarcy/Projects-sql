package projects;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import projects.dao.DbConnection;
import projects.entity.Project;
import projects.exception.DbException;
import projects.service.ProjectService;

public class ProjectsApp {
	private ProjectService projectService = new ProjectService();
	private Project curProject;
	
	//@formatter:off
	private List<String> operations = List.of( //List of operations user can perform
			"1) Add a project",
			"2) List Projects",
			"3) Select a project"
			);
	//@formatter:on
	
	private Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		 new ProjectsApp().processUserSelections();
		 
		 
		 System.out.println("\nExiting, goodbye!");
	}
	
	/*
	 * Prints out options for user, 
	 * user selects using integer inputs
	 */
	private void processUserSelections() {
		boolean done = false;
		
		while(!done) {
			try {
				int selection = getUserSelection();
				switch(selection) {
				case 1:
					addProject();
					break;
				case 2:
					listProjects();
					break;
				case 3:
					selectProject();
					break;
				case -1:
					done = quitMenu();
					break;
				default:
					throw new DbException(String.valueOf(selection + " is not a valid selection"));
				}
			}
			catch(Exception e) {
				System.out.println("\nError: " + e.getMessage() + " try again");
			}
		}
	}

	private void selectProject() {
		listProjects();
		Integer projId = getIntInput("Enter the project ID to select a project");
		
		curProject = null;
		
		curProject = projectService.fetchProjectById(projId);
		
	}

	private void listProjects() {
		List<Project> projects = projectService.fetchAllProjects();
		
		System.out.println("\nProjects:");
		projects.forEach(project -> System.out.println("   " + project.getProjectId() + ": " + project.getProjectName()));
		
	}
	
	/*
	 * prompts user for all data of a  project
	 */
	private void addProject() {
		String projectName = getStringInput("Enter the project name");
		BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours");
		BigDecimal actualHours = getDecimalInput("Enter the actual hours");
		Integer difficulty = getIntInput("Enter the project difficulty (1-5)");
		String notes = getStringInput("Enter the project notes");
		
		Project project = new Project();
		
		project.setProjectName(projectName);
		project.setEstimatedHours(estimatedHours);
		project.setActualHours(actualHours);
		project.setDifficulty(difficulty);
		project.setNotes(notes);
		
		Project sucsess = projectService.addProjcet(project);
		System.out.println("You have succesfully created project: " + sucsess.toString());
		
	}
	
	/*
	 * gets decimal input from user
	 */
	private BigDecimal getDecimalInput(String message) {
		String input = getStringInput(message);
		if(Objects.isNull(input)) {
			return null;
		}
		try {
			return new BigDecimal(input).setScale(2);
		}
		catch(NumberFormatException e) {
			throw new DbException(input + " is not a valid number");
		}
	}

	private boolean quitMenu() {
		return true;
	}

	/*
	 * gets integer input from user 
	 * @return User input, -1 if null
	 */	
	private int getUserSelection() {
		printOperations();
		
		Integer input = getIntInput("Enter a menu selection");
		
		return Objects.isNull(input) ? -1 : input;
	}
	
	/*
	 * Gets an int from the user
	 * @return User provided int
	 * @throws DbException if user enters non int value
	 */
	private Integer getIntInput(String message) {
		String input = getStringInput(message);
		if(Objects.isNull(input)) {
			return null;
		}
		try {
			return Integer.valueOf(input);
		}
		catch(NumberFormatException e) {
			throw new DbException(input + " is not a valid number");
		}
	}
	
	/*
	 * @return User provided String
	 */
	private String getStringInput(String message) {
		System.out.print(message + ": ");
		String input = scanner.nextLine();
		return input.isBlank() ? null : input.trim();
	}

	private void printOperations() {
		System.out.println("\nThese are the avalilable selections. Press the Enter key to quit");
		operations.forEach(line -> System.out.println("  " + line));
		
		if(Objects.isNull(curProject)) {
			System.out.println("\nYou are not working with a project");
		}
		else {
			System.out.println("\nYou are working with project : " + curProject);
		}
		
	}
}










