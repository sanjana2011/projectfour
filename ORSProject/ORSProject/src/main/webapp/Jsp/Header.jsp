<%@page import="in.co.rays.Proj4.controller.ORSView"%>
<%@page import="in.co.rays.Proj4.bean.UserBean"%>
<%@page import="in.co.rays.Proj4.controller.LoginCtl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- Include jQuery -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<!-- Include jQuery UI -->
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.min.js"></script>
<!-- Include jQuery UI CSS -->
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<script src="/ORSProject/js/checkbox.js"></script>
<script src="/ORSProject/js/datepicker.js"></script>
</head>
<body>
	<%
		UserBean user = (UserBean) session.getAttribute("user");
	%>
	<%
		if (user != null) {
	%>
	<h3>
		Hi,
		<%=user.getFirstName()%>
		(<%=session.getAttribute("role")%>)
	</h3>
 <a href="<%=ORSView.MY_PROFILE_CTL%>"><b>My Profile</b></a> <b>|</b>
    <a href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><b>Change Password</b></a> <b>|</b>
    <a href="<%=ORSView.GET_MARKSHEET_CTL%>"><b>Get Marksheet</b></a> <b>|</b>
    <a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><b>Marksheet Merit List</b></a> <b>|</b>
    <a href="<%=ORSView.USER_CTL%>"><b>Add User</b></a> <b>|</b>
    <a href="<%=ORSView.USER_LIST_CTL%>"><b>User List</b></a> <b>|</b>
    <a href="<%=ORSView.ROLE_CTL%>"><b>Add Role</b></a> <b>|</b>
    <a href="<%=ORSView.ROLE_LIST_CTL%>"><b>Role List</b></a> <b>|</b>
    <a href="<%=ORSView.COLLEGE_CTL%>"><b>Add College</b></a> <b>|</b>
    <a href="<%=ORSView.COLLEGE_LIST_CTL%>"><b>College List</b></a> <b>|</b>
    <a href="<%=ORSView.STUDENT_CTL%>"><b>Add Student</b></a> <b>|</b>
    <a href="<%=ORSView.STUDENT_LIST_CTL%>"><b>Student List</b></a> <b>|</b>
    <a href="<%=ORSView.MARKSHEET_CTL%>"><b>Add Marksheet</b></a> <b>|</b>
    <a href="<%=ORSView.MARKSHEET_LIST_CTL%>"><b>Marksheet List</b></a> <b>|</b>
    <a href="<%=ORSView.COURSE_CTL%>"><b>Add Course</b></a> <b>|</b>
    <a href="<%=ORSView.COURSE_LIST_CTL%>"><b>Course List</b></a> <b>|</b>
    <a href="<%=ORSView.SUBJECT_CTL%>"><b>Add Subject</b></a> <b>|</b>
    <a href="<%=ORSView.SUBJECT_LIST_CTL%>"><b>Subject List</b></a> <b>|</b>
    <a href="<%=ORSView.TIMETABLE_CTL%>"><b>Add Timetable</b></a> <b>|</b>
    <a href="<%=ORSView.TIMETABLE_LIST_CTL%>"><b>Timetable List</b></a> <b>|</b>
    <a href="<%=ORSView.FACULTY_CTL%>"><b>Add Faculty</b></a> <b>|</b>
    <a href="<%=ORSView.FACULTY_LIST_CTL%>"><b>Faculty List</b></a> <b>|</b>
    <a href="<%=ORSView.CART_CTL%>"><b>Add Cart</b></a> <b>|</b>
    <a href="<%=ORSView.CART_LIST_CTL%>"><b>Cart List</b></a> <b>|</b>
	<a href="<%=ORSView.JAVADOC_VIEW%>"target="blank"><b>javadoc</b></a>
	
	
	<b>|</b>
	<a href="LoginCtl?operation=<%=LoginCtl.OP_LOG_OUT%>"><b>Logout</b></a>
	<%
		} else {
	%>
	<h3>Hi, Guest</h3>
	<a href="WelcomeCtl"><b>Welcome</b></a> |
	<a href="LoginCtl"><b>Login</b></a>
	<%
		}
	%>
	<hr>
</body>
</html>