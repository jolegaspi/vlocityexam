package com.vlocity.scheduler.domain;

import java.time.LocalDateTime;


//Domain Class for Project
public class Project {
	
	private LocalDateTime projectStartDate;
	private String projectName;
	private Task[] taskList;
	
	public LocalDateTime getProjectStartDate() {
		return projectStartDate;
	}
	public void setProjectStartDate(LocalDateTime projectStartDate) {
		this.projectStartDate = projectStartDate;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Task[] getTaskList() {
		return taskList;
	}
	public void setTaskList(Task[] taskList) {
		this.taskList = taskList;
	}
}
