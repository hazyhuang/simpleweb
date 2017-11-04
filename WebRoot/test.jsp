
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Tomcat+memcached共享session测试</title>
</head>
<body>
	SessionID:<%=session.getId()%>
	<BR> SessionIP:<%=request.getServerName()%>
	<BR> SessionPort:<%=request.getServerPort()%>
	<BR>
	<%
		out.println("This is Tomcat Server.");
	%>
</body>
</html>