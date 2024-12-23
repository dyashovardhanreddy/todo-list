package todo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import todo.model.Task;

@Repository
public class TaskDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveTask(Task task) {
		sessionFactory.getCurrentSession().save(task);
	}
	
	public List<Task> getAllTasks(){
		return sessionFactory.getCurrentSession().createQuery("from Task", Task.class).list();
	}
	
	public Task getTaskDetails(int taskID) {
		return sessionFactory.getCurrentSession().get(Task.class, taskID);
	}
	
}
