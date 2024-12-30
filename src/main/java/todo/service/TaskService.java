package todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import todo.dao.TaskDAO;
import todo.model.Task;

@Service
public class TaskService {
	
	@Autowired
	private TaskDAO taskDAO;
	
	@Transactional
	public void saveTask(Task task) {
		taskDAO.saveTask(task);
	}
	
	@Transactional
	public List<Task> getAllTasks(){
		return taskDAO.getAllTasks();
	}
	
	@Transactional
	public Task findTaskByID(int taskID) {
		return taskDAO.findTaskByID(taskID);
	}
	
	@Transactional
	public boolean deleteTask(int taskID) {
		return taskDAO.deleteTask(taskID);
	}
	
	@Transactional
	public void updateTask(Task task) {
		taskDAO.updateTask(task);
	}
	
}
