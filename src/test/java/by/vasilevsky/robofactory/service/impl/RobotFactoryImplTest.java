package by.vasilevsky.robofactory.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

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

	@Test
	public void test() throws InterruptedException {
		RobotFactoryImpl robotFactory = new RobotFactoryImpl();
		robotFactory.init();
		List<Task> additionalTasks = Arrays.asList(new Task(9, new DrillCommand()),
				new Task(10, new MoveUpCommand()),
				new Task(11, new MoveUpCommand()),
				new Task(12, new MoveUpCommand()),
				new Task(13, new MoveUpCommand()),
				new Task(14, new MoveUpCommand()),
				new Task(15, new MoveUpCommand())
				);
		for (Task task : additionalTasks) {
			try {
				robotFactory.addTask(task);
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		TimeUnit.SECONDS.sleep(10);
		System.out.println("Test finished");
	}

}
