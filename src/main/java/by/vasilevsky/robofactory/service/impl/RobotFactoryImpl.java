package by.vasilevsky.robofactory.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import by.vasilevsky.robofactory.command.impl.DrillCommand;
import by.vasilevsky.robofactory.command.impl.MoveDownCommand;
import by.vasilevsky.robofactory.command.impl.MoveLeftCommand;
import by.vasilevsky.robofactory.command.impl.MoveRightCommand;
import by.vasilevsky.robofactory.command.impl.MoveUpCommand;
import by.vasilevsky.robofactory.command.impl.PutCommand;
import by.vasilevsky.robofactory.command.impl.SelfDestroyCommand;
import by.vasilevsky.robofactory.command.impl.TakeCommand;
import by.vasilevsky.robofactory.command.impl.ThrowCommand;
import by.vasilevsky.robofactory.domain.Task;
import by.vasilevsky.robofactory.service.Robot;
import by.vasilevsky.robofactory.service.RobotFactory;

public class RobotFactoryImpl implements RobotFactory {
	private static final int INITIAL_QUEUE_CAPACITY = 40;
	private static final int ROBOTS_CREATING_DELAY = 250;
	private static final int ROBOTS_INITIAL_NUMBER = 5;
	private static final double LOAD_FACTOR = 0.75;
	
	private static final List<Task> TASKS_LIST = Arrays.asList(new Task(9, new DrillCommand()),
			new Task(1, new DrillCommand()),
			new Task(2, new MoveUpCommand()),
			new Task(3, new MoveDownCommand()),
			new Task(4, new MoveRightCommand()),
			new Task(5, new MoveLeftCommand()),
			new Task(7, new TakeCommand()),
			new Task(8, new ThrowCommand()),
			new Task(9, new SelfDestroyCommand()),
			new Task(10, new PutCommand())
			);

	private BlockingQueue<Task> taskQueue;

	@Override
	public void init() {
		initTaskQueue();
		
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < ROBOTS_INITIAL_NUMBER; i++) {
			Robot robot = new RobotImpl(this);
			exec.execute(robot);
			try {
				TimeUnit.MILLISECONDS.sleep(ROBOTS_CREATING_DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void initTaskQueue() {
		taskQueue = new ArrayBlockingQueue<>(INITIAL_QUEUE_CAPACITY);
		for (int i = 0; i < (int) (INITIAL_QUEUE_CAPACITY * LOAD_FACTOR); i ++) {
			taskQueue.add(generateRandomTask());
		}
	}
	
	@Override
	public Task takeTask() {
		Task task = null;
		try {
			task = taskQueue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return task;
	}

	public void setTaskQueue(BlockingQueue<Task> commands) {
		this.taskQueue = commands;
	}

	public static void main(String[] args) {
		new RobotFactoryImpl().init();
	}
	@Override
	public void addTask(Task task) {
		
		taskQueue.offer(task);
	}
	
	private Task generateRandomTask() {
		
		return TASKS_LIST.get((int) (Math.random() * (TASKS_LIST.size() - 1)));
	}
}
