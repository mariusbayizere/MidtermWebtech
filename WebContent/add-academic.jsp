<%@page import="java.util.List"%>
<%@page import="back.Models.Academicunit"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
	
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Edumin - Bootstrap Admin Dashboard </title>
    <!-- Favicon icon -->


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
                            <h4>Add Academic</h4>
                        </div>
                    </div>
                    <div class="col-sm-6 p-md-0 justify-content-sm-end mt-2 mt-sm-0 d-flex">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                            <li class="breadcrumb-item active"><a href="javascript:void(0);">Academics</a></li>
                            <li class="breadcrumb-item active"><a href="javascript:void(0);">Add Academic</a></li>
                        </ol>
                    </div>
                </div>
				
				<div class="row">
					<div class="col-xl-12 col-xxl-12 col-sm-12">
                        <div class="card">
                            <div class="card-header">
								<h5 class="card-title">Basic Info</h5>
							</div>
							<div class="card-body">
                                <form action="addacademicunit" method="post">
									<div class="row">
										<div class="col-lg-6 col-md-6 col-sm-12">
											<div class="form-group">
												<label class="form-label">Academic Code</label>
												<input type="text" class="form-control" name="code">
											</div>
										</div>
										<div class="col-lg-6 col-md-6 col-sm-12">
											<div class="form-group">
												<label class="form-label">Academic Name</label>
												<input type="text" class="form-control" name="name">
											</div>
										</div>
										<div class="col-lg-6 col-md-6 col-sm-12" id="types">
											<div class="form-group">
												<label class="form-label">Academic Type</label>
												<select id="academictype" class="form-control" name="academicType">
													<option value="" selected="">Select Type</option>
													<option value="PROGRAM" >PROGRAM</option>
													<option value="FACULTY">FACULTY</option>
													<option value="DEPARTMENT">DEPARTMENT</option>
												</select>
											</div>
										</div>
										<div class="col-lg-6 col-md-6 col-sm-12" id="programs">
											<div class="form-group">
												<label class="form-label">Academic parent</label>
												<select class="form-control" name="parentId">										
										<%
																					List<Academicunit> programs = (List<Academicunit>) request.getAttribute("programs");
																												if (programs != null){
																													for(Academicunit acd: programs){
																				%>
												<option value="<%=acd.getCode()%>"><%=acd.getName()%></option>
											<%
												}
																			}
											%>
												</select>
											</div>											
										</div>
										<div class="col-lg-6 col-md-6 col-sm-12" id="faculties">
											<div class="form-group">
												<label class="form-label">Academic parent</label>
												<select class="form-control" name="parentId">										
										<%
																					List<Academicunit> faculties = (List<Academicunit>) request.getAttribute("faculties");
																												if (faculties != null){
																													for(Academicunit fac: faculties){
																				%>
												<option value="<%=fac.getCode()%>"><%=fac.getName() %></option>
											<%
											}
										}
											%>
												</select>
											</div>											
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12">
											<button type="submit" class="btn btn-primary">Submit</button>
										</div>
									</div>
								</form>
                            </div>
                        </div>
                    </div>
				</div>
                
            </div>
        </div>
        <!--**********************************
            Content body end
        ***********************************-->


        <!--**********************************
            Footer start
        ***********************************-->
        <div class="footer">
            <div class="copyright">
                <p>Copyright � Designed &amp; Developed by <a href="../index.htm" target="_blank">DexignLab</a> 2020</p>
            </div>
        </div>
        <!--**********************************
            Footer end
        ***********************************-->

		<!--**********************************
           Support ticket button start
        ***********************************-->

        <!--**********************************
           Support ticket button end
        ***********************************-->


    </div>
    <!-- Required vendors -->
<script src="vendor/global/global.min.js"></script>
	<script src="vendor/bootstrap-select/dist/js/bootstrap-select.min.js"></script>
    <script src="js/custom.min.js"></script>
	<script src="js/dlabnav-init.js"></script>

	<!-- Svganimation scripts -->
    <script src="vendor/svganimation/vivus.min.js"></script>
    <script src="vendor/svganimation/svg.animation.js"></script>
    <script src="js/styleSwitcher.js"></script>
	
	<!-- pickdate -->
    <script src="vendor/pickadate/picker.js"></script>
    <script src="vendor/pickadate/picker.time.js"></script>
    <script src="vendor/pickadate/picker.date.js"></script>
	
	<!-- Pickdate -->
    <script src="js/plugins-init/pickadate-init.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			const faculties = $("#faculties");
			const programs = $("#programs");
			const types = $("#types");
			faculties.remove();
			programs.remove();
			$("#academictype").change(function() {
				if(this.value === "FACULTY"){
					faculties.remove();
					types.after(programs);
				}else if(this.value === "DEPARTMENT"){
					programs.remove();
					types.after(faculties);
				}else{
					faculties.remove();
					programs.remove();	
				}
			});
		});
	</script>
	
</body>
</html>