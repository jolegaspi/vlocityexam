package com.vlocity.scheduler.domain;

import java.util.Iterator;
import java.util.LinkedList;

//Domain Class for Task
public class Task implements Iterable<Task> { 
	
	private String taskName;
	private LinkedList<Task> depedencies;
	private long taskDuration;
	private boolean checked;;
	public LinkedList<Task> getDepedencies() {
		return depedencies;
	}

	public void setDepedencies(LinkedList<Task> depedencies) {
		this.depedencies = depedencies;
	}

	public Task(String taskName, long taskDuration) {
		depedencies = new LinkedList<>();
		this.setTaskName(taskName);
		this.setTaskDuration(taskDuration);
		checked = false;
	}

	public void addDependency(Task dependency) {
		depedencies.add(dependency);
	}

	public int dependencyCount() {
		return depedencies.size();
	}
	@Override
	public Iterator<Task> iterator() {
		return depedencies.iterator();
	}
	public void setChecked() {
		this.checked = true;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isChecked() {
		return this.checked;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public long getTaskDuration() {
		return taskDuration;
	}

	public void setTaskDuration(long taskDuration) {
		this.taskDuration = taskDuration;
	}
}
