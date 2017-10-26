package by.vasilevsky.robofactory.service.impl;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.junit.Before;
import org.junit.Test;

import by.vasilevsky.robofactory.command.impl.*;
import by.vasilevsky.robofactory.domain.Task;
public class RobotFactoryImplTest extends RobotFactoryImpl {

	private BlockingQueue<Task> commands;
	
	@Before
	public void setUp() throws Exception {
		commands = new ArrayBlockingQueue<>(20);
		
		
		commands.offer(new Task(1, new DrillCommand()));
		commands.offer(new Task(2, new MoveUpCommand()));
		commands.offer(new Task(3, new MoveDownCommand()));
		commands.offer(new Task(4, new MoveRightCommand()));
		commands.offer(new Task(5, new MoveLeftCommand()));
		commands.offer(new Task(6, new PutCommand()));
		commands.offer(new Task(7, new TakeCommand()));
		commands.offer(new Task(8, new ThrowCommand()));
	}

	@Test(timeout = 100000)
	public void test() {
		RobotFactoryImpl robotFactory = new RobotFactoryImpl();
		robotFactory.setTaskQueue(commands);
		robotFactory.init();
	}

}
