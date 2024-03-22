
<%@page import="back.Models.Course"%>
<%@page import="back.Models.Student"%>
<%@page import="back.Models.Semester"%>
<%@page import="back.Models.Academicunit"%>
<%@page import="back.Models.Coursedefinition"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
	
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Edumin - Bootstrap Admin Dashboard </title>


</head>

<body>

    <!--*******************
        Preloader start
    ********************-->
    <div id="preloader">
        <div class="sk-three-bounce">
            <div class="sk-child sk-bounce1"></div>
            <div class="sk-child sk-bounce2"></div>
            <div class="sk-child sk-bounce3"></div>
        </div>
    </div>
    <!--*******************
        Preloader end
    ********************-->

    <!--**********************************
        Main wrapper start
    ***********************************-->
    <div id="main-wrapper">

        <!--**********************************
            Nav header start
        ***********************************-->
        <div class="nav-header">
            <a href="index.html" class="brand-logo">
                <img class="logo-abbr" src="images/logo-white.png" alt="">
                <p>DASHBOARD</p>
            </a>

            <div class="nav-control">
                <div class="hamburger">
                    <span class="line"></span><span class="line"></span><span class="line"></span>
                </div>
            </div>
        </div>
        <!--**********************************
            Nav header end
        ***********************************-->

        <!--**********************************
            Header start
        ***********************************-->
        <div class="header">
            <div class="header-content">
                <nav class="navbar navbar-expand">
                    <div class="collapse navbar-collapse justify-content-between">
                        <div class="header-left">
                            <div class="search_bar dropdown">
                                <span class="search_icon p-3 c-pointer" data-toggle="dropdown">
                                    <i class="mdi mdi-magnify"></i>
                                </span>
                                <div class="dropdown-menu p-0 m-0">
                                    <form>
                                        <input class="form-control" type="search" placeholder="Search" aria-label="Search">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
        <!--**********************************
            Header end ti-comment-alt
        ***********************************-->

        <!--**********************************
            Sidebar start
        ***********************************-->
        <div class="dlabnav">
            <div class="dlabnav-scroll">
                <ul class="metismenu" id="menu">
                    <li class="nav-label first">Main Menu</li>
                    <li><a class="ai-icon" href=admin.jsp aria-expanded="false">
							<i class="la la-home"></i>
							<span class="nav-text">Dashboard</span>
						</a>
                    </li>
					<li><a class="has-arrow" href="javascript:void()" aria-expanded="false">
							<i class="la la-user"></i>
							<span class="nav-text">Teacher</span>
						</a>
                        <ul aria-expanded="false">
                            <li><a href="allteachers">All Teachers</a></li>
                            <li><a href="addteacher">Add Teacher</a></li>
                            <li><a href="about">About Teacher</a></li>
                        </ul>
                    </li>
					<li><a class="has-arrow" href="javascript:void()" aria-expanded="false">
							<i class="la la-users"></i>
							<span class="nav-text">Students</span>
						</a>
                        <ul aria-expanded="false">
                            <li><a href="allstudents">All Students</a></li>
                            <li><a href="addstudent">Add Student</a></li>
                            <li><a href="abou">About Student</a></li>
                        </ul>
                    </li>
					<li><a class="has-arrow" href="javascript:void()" aria-expanded="false">
							<i class="la la-graduation-cap"></i>
							<span class="nav-text">Courses</span>
						</a>
                        <ul aria-expanded="false">
                            <li><a href="allcourses">All Courses</a></li>
                            <li><a href="addcourse">Add Course</a></li>
                            <li><a href="ab">About Courses</a></li>
                        </ul>
                    </li>
					<li><a class="has-arrow" href="javascript:void()" aria-expanded="false">
							<i class="la la-building"></i>
							<span class="nav-text">Academic Unit</span>
						</a>
                        <ul aria-expanded="false">
                            <li><a href="allacademicunit">All Academic Unit</a></li>
                            <li><a href="addacademicunit">Add Academic Unit</a></li>
                        </ul>
                    </li>
					<li><a class="has-arrow" href="javascript:void()" aria-expanded="false">
							<i class="la la-users"></i>
							<span class="nav-text">Semester</span>
						</a>
                        <ul aria-expanded="false">
                            <li><a href="allsemesters">All Semesters</a></li>
                            <li><a href="addsemester">Add Semester</a></li>
                        </ul>
                    </li>					
				</ul>
            </div>
        </div>
        <!--**********************************
            Sidebar end
        ***********************************-->

		
		
        <!--**********************************
            Content body start
        ***********************************-->
        <div class="content-body">
            <!-- row -->
            <div class="container-fluid">
				    
				<div class="row page-titles mx-0">
                    <div class="col-sm-6 p-md-0">
                        <div class="welcome-text">
                            <h4>All Course</h4>
                        </div>
                    </div>
                    <div class="col-sm-6 p-md-0 justify-content-sm-end mt-2 mt-sm-0 d-flex">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                            <li class="breadcrumb-item active"><a href="javascript:void(0);">Courses</a></li>
                            <li class="breadcrumb-item active"><a href="javascript:void(0);">All Course</a></li>
                        </ol>
                    </div>
                </div>
				<form action="DataFilterServlet" method="post">
				<div class="row">
				<div class="col-lg-2 col-md-2 col-sm-12" id="programs">
						<div class="form-group">
							<select id="department" class="form-control" name="department">	
										<option value="">Select Department</option>																			
							<%
																											List<Academicunit> programs = (List<Academicunit>) request.getAttribute("departments");
																																if (programs != null){
																																	for(Academicunit acd: programs){
																										%>
										<option value="<%=acd.getCode()%>"><%=acd.getName() %></option>
							<%
									}
								}
							%>
							</select>
							</div>											
					</div>
					<div class="col-lg-2 col-md-2 col-sm-12" id="programs">
						<div class="form-group">
							<select id="semester" class="form-control" name="semester">
										<option value="">Select Semester</option>																	
							<%
								List<Semester> semesters = (List<Semester>) request.getAttribute("semesters");
								if (semesters != null){
									for(Semester sem: semesters){
												
							%>
										<option value="<%=sem.getId()%>"><%=sem.getName() %></option>
							<%
									}
								}
							%>
							</select>
							</div>											
					</div>
					<div class="col-lg-2 col-md-2 col-sm-12" id="programs">
						<div class="form-group">
							<select id="course" class="form-control" name="student">
										<option value="">Select Student</option>																	
							<%
								List<Student> students = (List<Student>) request.getAttribute("students");
								if (students != null){
									for(Student std: students){
												
							%>
										<option value="<%=std.getRegNo()%>"><%=std.getFirstName()+" "+std.getLastName() %></option>
							<%
									}
								}
							%>
							 </select>
						</div>											
					</div>
					<div class="col-lg-2 col-md-2 col-sm-12">
						<button id="filterbtn" type="submit" class="btn btn-primary">Apply Filter</button>
					</div>	
				</div>				
				</form> 				
				<div class="row">
					<div class="col-lg-12">
						<ul class="nav nav-pills mb-3">
							<li class="nav-item"><a href="#list-view" data-toggle="tab" class="nav-link btn-primary mr-1 show active">List View</a></li>
						</ul>
					</div>
					<div class="col-lg-12">
						<div class="row tab-content">
							<div id="list-view" class="tab-pane fade active show col-lg-12">
								<div class="card">
									<div class="card-header">
										<h4 class="card-title">All Students List  </h4>
										<a href="addcourse" class="btn btn-primary">+ Add new</a>
									</div>
									<div class="card-body">
										<div class="table-responsive">
											<table id="example3" class="display" style="min-width: 845px">
												<thead>
													<tr>
														<th>Course Code</th>
														<th>Course Name</th>
														<th>Course Description</th>
														<th>Course Teacher</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>
												<%
													List<Course> courses = (List<Course>) request.getAttribute("courseList");
													
													if (courses != null){
														for(Course crs: courses){
													
												%>
													<tr>
														<td><strong><%=crs.getCoursedefinition().getCode()%></strong></td>
														<td><%=crs.getCoursedefinition().getName()%></td>
														<td><%=crs.getCoursedefinition().getDescription()%></td>
														<td><%=crs.getTeacher().getNames()%></td>
														<td>
															<a href="editcourse?id=<%=crs.getCoursedefinition().getCode()%>" class="btn btn-sm btn-primary"><i class="la la-pencil"></i></a>
															<a href="deletecourse?id=<%=crs.getCoursedefinition().getCode()%>" class="btn btn-sm btn-danger"><i class="la la-trash-o"></i></a>
															<a href="deletecourse?id=<%=crs.getCoursedefinition().getCode()%>" class="btn btn-sm btn-danger"><i class="la la-trash-o"></i></a>
														</td>												
													</tr>
												<%
														}
													}
												%>
												</tbody>
											</table>
										</div>
									</div>
                                </div>
                            </div>
						</div>
					</div>
				</div> 
            </div>
        </div>
        <div class="footer">
            <div class="copyright">
                <p>Copyright  2023</p>
            </div>
        </div>
    </div>
    <!-- Required vendors -->
    <script src="vendor/global/global.min.js"></script>
	<script src="vendor/bootstrap-select/dist/js/bootstrap-select.min.js"></script>
    <script src="js/custom.min.js"></script>
    <script src="js/dlabnav-init.js"></script>	
	
	<!-- Datatable -->
    <script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="js/plugins-init/datatables.init.js"></script>
	
    <!-- Svganimation scripts -->
    <script src="vendor/svganimation/vivus.min.js"></script>
    <script src="vendor/svganimation/svg.animation.js"></script>
    <script src="js/styleSwitcher.js"></script>
    <script type="text/javascript">
    	function getCourse(id){
    		console.log(id);
    	}
    </script>
	
</body>
</html>