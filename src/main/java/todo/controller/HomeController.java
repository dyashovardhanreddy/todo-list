package todo.controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.MediaType;


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
			@RequestParam("priorityTask") boolean priorityTask, Model model) {
		
		Task task = new Task(taskName, taskDescription, priorityTask, taskDeadLine, false);
		taskService.saveTask(task);
		model.addAttribute("message", "Task addedd successfully");
		return "add-task";
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
		List<Task> completedTasks = new ArrayList<>();
		
		for(Task task :tasks) {
			if(!task.isCompleted()) {
				if(task.getTaskDeadLine().isBefore(LocalDate.now())) {
					overdueTasks.add(task);
				} else {
					upcomingTasks.add(task);
				}
			} else {
				completedTasks.add(task);
			}
		}
		
		model.addAttribute("overdueTasks", overdueTasks);
		model.addAttribute("upcomingTasks", upcomingTasks);
		model.addAttribute("completedTasks", completedTasks);
		return "view-tasks";
	}
	
	@GetMapping(path="/tasks/{taskId}")
	public String viewTaskDetails(@PathVariable("taskId") int taskId, Model model ) {
		model.addAttribute("task", taskService.findTaskByID(taskId));
		return "view-task";
	}
	
	@PostMapping(path="/updatetask",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateString(@RequestBody Task updatedTask){
		
		System.out.println("existing task: " + updatedTask);
		Task existingTask = taskService.findTaskByID(updatedTask.getTaskID());
		
		if(existingTask != null) {
			
			
			existingTask.setCompleted(updatedTask.isCompleted());
			existingTask.setTaskDescription(updatedTask.getTaskDescription());
			existingTask.setTaskDeadLine(updatedTask.getTaskDeadLine());
			existingTask.setTaskName(updatedTask.getTaskName());
			existingTask.setPriorityTask(updatedTask.isPriorityTask());
			
			taskService.updateTask(existingTask);
			
			return ResponseEntity.ok("Task updated successfully!");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found.");
		}
	}
	
	@DeleteMapping(path="/deletetask/{taskID}")
	public ResponseEntity<String> deleteTask(@PathVariable("taskID") int taskId){
		
		if(taskService.deleteTask(taskId)) {
			return ResponseEntity.ok("Task deleted successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Task not deleted");
		}
		
	}
	
	@GetMapping("/edittask/{taskID}")
	public String editTaskDetails(@PathVariable("taskId") int taskId, Model model) {
		model.addAttribute("task", taskService.findTaskByID(taskId));
		return "edit-task-details";
	}
	
}
