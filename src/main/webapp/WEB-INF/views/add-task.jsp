<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Todo-List</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body onload="showMessage('${message}');">
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <a class="navbar-brand" href="/todo-list/">Todo-List</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>

	 <div class="collapse navbar-collapse" id="navbarSupportedContent">
	   <ul class="navbar-nav mr-auto">
	     <li class="nav-item">
	       <a class="nav-link" href="/todo-list/addtask">Add Task <span class="sr-only">(current)</span></a>
	     </li>
	    <li class="nav-item">
	       <a class="nav-link" href="/todo-list/tasks">View Tasks</a>
	     </li>
	   </ul>
	 </div>
	</nav>
	
  <div class="container mt-5 p-4" style="max-width: 600px; border: 1px solid #ccc; border-radius: 8px;">
  <form action="/todo-list/savetask" method="POST">
    <div class="form-group">
      <label for="taskName">Task Name</label>
      <input type="text" class="form-control" id="taskName" name="taskName" placeholder="Enter task name">
    </div>
    <div class="form-group">
      <label for="taskDescription">Task Description</label>
      <input type="text" class="form-control" id="taskDescription" name="taskDescription" placeholder="Enter task description">
    </div>
    <div class="form-group">
      <p>Priority Task</p>
      <label for="yesPriorityTask">
        <input type="radio" id="yesPriorityTask" name="priorityTask" value="yes" required> Yes
      </label>
      <label for="notPriorityTask">
        <input type="radio" id="notPriorityTask" name="priorityTask" value="no" required> No
      </label>
    </div>
    <div class="form-group">
      <label for="taskDeadLine">Deadline</label>
      <input type="date" class="form-control" id="taskDeadLine" name="taskDeadLine" required>
    </div>
    <button type="submit" class="btn btn-primary">Add Task</button>
  </form>
</div>

	<script>
		function showMessage(message){
			if(message != ""){
				alert(message);
			}
		}
	</script>
  
 
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>

</html>