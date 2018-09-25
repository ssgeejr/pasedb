<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.Enumeration" %>

<%
  Enumeration e = req.getParameterNames();

  while(e.hasMoreElements()){
    Object obj = e.nextElement();
    String fieldName = (String) obj;
    String fieldValue = req.getParameter(fieldName);
    out.println(fieldName + " : " + fieldValue + "<br>");
  }

%>

<html>
<head>
<title>Counter ... </title>
</head>
<body>
COUNTER ...
</body>
</html>
