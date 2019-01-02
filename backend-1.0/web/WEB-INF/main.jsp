<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>:: Danamas Backend Mobile ::</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Danamase" />
<!-- Custom Theme files -->
<link href="<s:url value="/assets/css/style.css"/>" rel="stylesheet" type="text/css" media="all" />
<!-- //Custom Theme files -->
<!-- font-awesome icons -->
<link href="<s:url value="/assets/css/font-awesome.css"/>" rel="stylesheet"> 
<!-- //font-awesome icons -->
<!-- web font -->
<link href="//fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
<!-- //web font -->
<!-- jS --> 
<script src="<s:url value="/assets/js/jquery.min.js"/>"></script> 
<script src="<s:url value="/assets/js/script.js"/>"></script> 
<!-- //js -->
</head>
<body>
	<!-- main -->
	<div class="main-agile">
		<div class="logo">
			<img src="<s:url value="/assets/images/icon.png"/>"/>
		</div>
		<h1>Danamas Backend Mobile</h1>
		<div id="w3ls_form" class="signin-form">
			<div class="content">
				<h1>Version <s:text name="backend.version"/></h1>
				<h4>Last Deployment : <s:text name="backend.deploymentDate"/></h4>
			</div>
		</div>
		<!-- copyright -->
		<div class="copyright">
			<p> © Copyright 2017, DANAMAS. All Right Reserved.</p>
		</div>
		<!-- //copyright -->  
	</div>	
	<!-- //main --> 
</body>
</html>