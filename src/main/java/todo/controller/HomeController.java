package todo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import todo.service.TaskService;
import todo.configuration.formatter.LocalDateEditor;
import todo.model.Task;

@Controller
public class HomeController {

	@Autowired
	private TaskService taskService;
	
	 @InitBinder
	 public void initBinder(WebDataBinder binder) {
		 binder.registerCustomEditor(LocalDate.class, new LocalDateEditor());
	 }
	 
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@PostMapping("/savetask")
	public String saveTask(
			@RequestParam("taskName") String taskName,
			@RequestParam("taskDeadLine") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate taskDeadLine,
			@RequestParam("taskDescription") String taskDescription,
			@RequestParam("priorityTask") boolean priorityTask) {
		
		Task task = new Task(taskName, taskDescription, priorityTask, taskDeadLine);
		taskService.saveTask(task);
		return "feedback";
	}
	
	@RequestMapping(path="/addtask", method = RequestMethod.GET)
	public String addTask() {
		return "add-task";
	}
	
	@RequestMapping(path="/tasks", method = RequestMethod.GET)
	public String viewTasks(Model model) {
		List<Task> tasks = taskService.getAllTasks();
		List<Task> overdueTasks = new ArrayList<>();
		List<Task> upcomingTasks = new ArrayList<>();
		
		for(Task task :tasks) {
			if(task.getTaskDeadLine().isBefore(LocalDate.now())) {
				overdueTasks.add(task);
			} else {
				upcomingTasks.add(task);
			}
		}
		
		model.addAttribute("overdueTasks", overdueTasks);
		model.addAttribute("upcomingTasks", upcomingTasks);
		return "view-tasks";
	}
	
	@GetMapping(path="/tasks/{taskId}")
	public String viewTaskDetails(@PathVariable("taskId") int taskId, Model model ) {
		model.addAttribute("task", taskService.getTaskDetails(taskId));
		return "view-task";
	}
	
}
