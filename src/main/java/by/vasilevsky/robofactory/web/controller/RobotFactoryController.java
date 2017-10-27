package by.vasilevsky.robofactory.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import by.vasilevsky.robofactory.service.RobotFactory;

@RestController
@RequestMapping("/api/v1")
public class RobotFactoryController {
	
	@Autowired
	private RobotFactory robotFactory;
	
	@RequestMapping(path = "/activate", method = RequestMethod.GET)
	public @ResponseBody String activateRobots() {
		
		robotFactory.init();
		
		return "success";
	}
	@RequestMapping(path = "/terminate", method = RequestMethod.GET)
	public @ResponseBody String terminateRobots() {
		
		robotFactory.terminate();;
		
		return "success";
	}

}
