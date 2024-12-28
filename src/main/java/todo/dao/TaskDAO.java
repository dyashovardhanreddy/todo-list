package todo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
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
	
	public Task findTaskByID(int taskID) {
		return sessionFactory.getCurrentSession().get(Task.class, taskID);
	}
	
	public boolean deleteTask(int taskID) {
		NativeQuery<?> query = sessionFactory.getCurrentSession().createNativeQuery("delete from tasks where taskid = :taskID");
		query.setParameter("taskID", taskID);
		
		int rows = query.executeUpdate();
		
		if(rows == 1) {
			return true;
		} else {
			return false;
		}
		
	}
}
