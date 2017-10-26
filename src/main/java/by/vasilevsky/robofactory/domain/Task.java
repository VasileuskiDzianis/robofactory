package by.vasilevsky.robofactory.domain;

import by.vasilevsky.robofactory.command.Command;

public class Task {
	private int id;
	private Command command;

	public Task(int id, Command command) {
		this.id = id;
		this.command = command;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}
}
