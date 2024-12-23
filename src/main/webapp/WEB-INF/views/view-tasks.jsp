<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="todo.model.Task" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Tasks</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <a class="navbar-brand" href="/todo-list/">Todo-List</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>

	 <div class="collapse navbar-collapse" id="navbarSupportedContent">
	   <ul class="navbar-nav mr-auto">
	     <li class="nav-item active">
	       <a class="nav-link" href="/todo-list/">Home <span class="sr-only">(current)</span></a>
	     </li>
	     <li class="nav-item">
	       <a class="nav-link" href="/todo-list/addtask">Add Task</a>
	     </li>
	    <li class="nav-item">
	       <a class="nav-link" href="/todo-list/tasks">View Tasks</a>
	     </li>
	   </ul>
	 </div>
	</nav>
	<br>
	
	<div class="container mt-2" style="max-width: 1200px; padding: 0;" id="collapsibleDiv">
    <!-- Header Title -->
    <h5 class="text-left" style="margin: 0; padding: 0.5rem; border-bottom: none;">Upcoming Tasks</h5>
    
    <!-- Table -->
    <div style="border: 3px solid #ccc;">
        <table class="table">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Task</th>
                    <th scope="col">Priority Task</th>
                    <th scope="col">Deadline</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="task" items="${upcomingTasks}">
                    <tr>
                        <td><a href="/todo-list/tasks/${task.getTaskID()}">${task.getTaskName()}</a></td>
                        <td>${task.isPriorityTask()}</td>
                        <td>${task.getTaskDeadLine()}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
	</div>
	<div class="container mt-2" style="max-width: 1200px; padding: 0;">
    <!-- Header Title -->
    <h5 class="text-left" style="margin: 0; padding: 0.5rem; border-bottom: none;">Overdue Tasks</h5>
    
    <!-- Table -->
    <div style="border: 3px solid #ccc;">
        <table class="table">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Task</th>
                    <th scope="col">Priority Task</th>
                    <th scope="col">Deadline</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="task" items="${overdueTasks}">
                    <tr>
                        <td><a href="/todo-list/tasks/${task.getTaskID()}">${task.getTaskName()}</a></td>
                        <td>${task.isPriorityTask()}</td>
                        <td>${task.getTaskDeadLine()}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
	
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	
</body>
</html>