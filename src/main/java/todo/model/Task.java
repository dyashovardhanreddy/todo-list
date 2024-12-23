package todo.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="tasks")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int taskID;
	
	@Column(name="task_name", length=30)
	private String taskName;
	
	@Column(name="description", length=50)
	private String taskDescription;
	
	@Column(name="is_important")
	private boolean priorityTask;
	
	@Column(name="task_deadline")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate taskDeadLine;

	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Task(String taskName, String taskDescription, boolean priorityTask, LocalDate taskDeadLine) {
		super();
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.priorityTask = priorityTask;
		this.taskDeadLine = taskDeadLine;
	}

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public boolean isPriorityTask() {
		return priorityTask;
	}

	public void setPriorityTask(boolean isImportant) {
		this.priorityTask = isImportant;
	}

	public LocalDate getTaskDeadLine() {
		return taskDeadLine;
	}

	public void setTaskDeadLine(LocalDate taskDeadLine) {
		this.taskDeadLine = taskDeadLine;
	}

	@Override
	public String toString() {
		return "Task [taskID=" + taskID + ", taskName=" + taskName + ", description=" + taskDescription + ", isImportant="
				+ priorityTask + ", deadLine=" + taskDeadLine + "]";
	}
}
