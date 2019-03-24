package com.vlocity.scheduler.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.vlocity.scheduler.domain.Project;
import com.vlocity.scheduler.domain.Task;

public class Scheduler {

	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	//Will Print the schedule assuming the working hours is 24/7 
	//since it was not stated by the user story
	//I've mainly used topological Sort 
	
	public static void printSchedule(Project project) {
		//Printing Logic
		System.out.println("PROJECT : "+project.getProjectName());
		LocalDateTime scheduledStartDate = project.getProjectStartDate();
		System.out.println("START DATE :"+scheduledStartDate.format(formatter));
		System.out.println("\n");
		List<Task> sortedTask = new ArrayList<Task>();
		
		//Launch the sorting according to dependency
		taskTopoSort(project.getTaskList()[0], sortedTask);
		
		//Pardon the representation due to being a console app.
		for(Task task : sortedTask) {
			System.out.print("TASK : " +task.getTaskName() + " DURATION : "+task.getTaskDuration() +" START : "+scheduledStartDate.format(formatter));
			scheduledStartDate = scheduledStartDate.plusHours(task.getTaskDuration());
			System.out.println(" END : "+scheduledStartDate.format(formatter));
		}
	}
	
	//Sort the task Topologically according to their dependencies
	private static void taskTopoSort(Task currentTask, List<Task> sortedTask) {
		currentTask.setChecked();
		for(Task n : currentTask) {
			if(!n.isChecked()) {
				taskTopoSort(n, sortedTask);
			}
		}
		sortedTask.add(currentTask);
	}
	
	//Sample project with tasks
	public static void main(String[] args) throws Exception {
		String now = "2019-03-24 10:30";

		//Creation for Project
		LocalDateTime formatDateTime = LocalDateTime.parse(now, formatter);
		Project project = new Project();
		project.setProjectName("VLOCITY PROJECT 1");
		project.setProjectStartDate(formatDateTime);
		
		//Creation for task
		Task[] taskArray = new Task[5];
		taskArray[0] = new Task("A", 8);
		taskArray[1] = new Task("B", 4);
		taskArray[2] = new Task("C", 4);
		taskArray[3] = new Task("D", 14);
		taskArray[4] = new Task("E", 2);
		
		//Creation for Dependencies
		
		//Add Task A  dependency To Task B
		taskArray[0].addDependency(taskArray[1]);
		//Add Task B dependency To Task C
		taskArray[1].addDependency(taskArray[2]);
		//Add Task C dependency to Task D
		taskArray[2].addDependency(taskArray[3]);
		//Add Task C dependency to Task E
		taskArray[2].addDependency(taskArray[4]);
		
		//On this sample Task D,E has no dependency thus started first
		project.setTaskList(taskArray);
		
		printSchedule(project);
	}
}
