package by.vasilevsky.robofactory.service.impl;

import java.util.concurrent.TimeUnit;

import by.vasilevsky.robofactory.service.Robot;
import by.vasilevsky.robofactory.service.RobotFactory;

public class RobotImpl implements Robot {
	private RobotFactory robotFactory;
	
	public RobotImpl(RobotFactory robotFactory) {
		this.robotFactory = robotFactory;
	}
	
	public RobotImpl() {
		
	}
	

	public void setRobotFactory(RobotFactory robotFactory) {
		this.robotFactory = robotFactory;
	}

	@Override
	public void run() {
		while (true) {
			System.out.println(this + "- is executing: " + robotFactory.takeTask().getCommand().execute());
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
