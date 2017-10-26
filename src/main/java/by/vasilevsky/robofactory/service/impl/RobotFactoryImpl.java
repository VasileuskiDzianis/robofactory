package by.vasilevsky.robofactory.service.impl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import by.vasilevsky.robofactory.domain.Task;
import by.vasilevsky.robofactory.service.Robot;
import by.vasilevsky.robofactory.service.RobotFactory;

public class RobotFactoryImpl implements RobotFactory {
	private static final int ROBOTS_CREATING_DELAY = 250;
	private static final int ROBOTS_INITIAL_NUMBER = 5;

	private BlockingQueue<Task> taskQueue;

	@Override
	public void init() {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < ROBOTS_INITIAL_NUMBER; i++) {
			Robot robot = new RobotImpl(this);

			exec.execute(robot);
			try {
				TimeUnit.MILLISECONDS.sleep(ROBOTS_CREATING_DELAY);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public Task takeTask() {
		Task task = null;
		try {
			task = taskQueue.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
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
}
