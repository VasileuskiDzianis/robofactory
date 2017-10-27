package by.vasilevsky.robofactory.command.impl;

import by.vasilevsky.robofactory.command.Command;

public class SelfDestroyCommand implements Command {

	@Override
	public String execute() {

		return "Throw command";
	}

}
