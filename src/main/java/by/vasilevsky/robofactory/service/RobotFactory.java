package by.vasilevsky.robofactory.service;

import by.vasilevsky.robofactory.domain.Task;

public interface RobotFactory {
	
	void init();

	Task takeTask();
	
	void addTask(Task task);
	
}
