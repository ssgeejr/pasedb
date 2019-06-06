<!DOCTYPE html>
<html>

<head>
<meta content="text/html;charset=utf-8" http-equiv="content-type">
<title>Parental Alienation Support & Education</title>
<link href="menu.css" media="all" rel="stylesheet" type="text/css">

<!--
<% 
	// new org.pasedb.pasedbui.Counter(request);
%>
-->
<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List"
%>
<%!
//--static
%>
<%
	try{
		String addurl = request.getParameter("addurl");
		if(addurl == null)
			System.out.println("do note save ...");
		else{
			System.out.println("... SAVE FORM VALUE ...");
			System.out.println(": " + request.getParameter("url"));
			System.out.println(": " + request.getParameter("comment"));		
			
			System.out.println("_cbpr: " + request.getParameter("_cbpr"));
			System.out.println("_cbim: " + request.getParameter("_cbim"));
			System.out.println("_cbco: " + request.getParameter("_cbco"));
			System.out.println("_cbsg: " + request.getParameter("_cbsg"));
		}
	}catch(Exception ex){}
%>


<style type="text/css">
.auto-style5 {
	font-size: 8pt;
	color: #FF0000;
}
.auto-style7 {
	color: #FF0000;
	font-size: large;
	text-align: center;
}

.auto-style1 {
        text-align: right;
}

table.customTable {
  width: 100%;
  background-color: #FFFFFF;
  border-collapse: collapse;
  border-width: 2px;
  border-color: #FF0000;
  border-style: solid;
  color: #000000;
}

table.customTable td, table.customTable th {
        padding: 1px;
}

table.customTable thead {
  background-color: #7EA8F8;
}

input[type=text], textarea, input[type=submit] {
  background-color : #ffffcc;
}
</style>
</head>
<body>
	<div align="center">
	<table style="border: 1px solid #AAAAAA;width: 900px">
		<tr>
			<td>
				<%@ include file="banner.htm"%>
			</td>
		</tr>
		<tr>
		<td>
		<%@ include file="menu.htm"%></td>
		</tr>
		<tr>
		<td class="auto-style7">
		<strong>OFFICIAL RELEASE JUNE 22, 2019</strong>
		<br>
		LOGIN
		</td>
		</tr>
		<tr>
		<td>
<div align="center">
		<strong><span class="auto-style5">VALIDATION TESTING underway - site 
		may (or MAY NOT) load correctly</span></strong></div>
</td>
		</tr>
		<tr>
			<td>


<form action="addlink.jsp" method="post">
<div align="center">
<div>
<table style="width: 800px" class="customTable">
        <tr>
                <td>Add Link</td>
        </tr>
        <tr>
                <td>
	                <input name="_cbpr" type="checkbox" value="1"/>Parental Rights
	                <input name="_cbim" type="checkbox" value="1"/>In the Media
	                <input name="_cbco" type="checkbox" value="1"/>Court Opinions
	                <input name="_cbsg" type="checkbox" value="1"/>Support Groups
                </td>
        </tr>
        <tr>
                <td>
                <table style="width: 100%">
                        <tr style="vertical-align: top">
                                <td style="width: 125px" class="auto-style1">URL</td>
                                <td><input name="url" style="width: 99%;" type="text" />&nbsp;</td>
                        </tr>
                        <tr style="vertical-align: top">
                                <td style="width: 125px" class="auto-style1">Comments</td>
                                <td><textarea name="comment" rows="3" style="width: 99%; "></textarea>&nbsp;</td>
                        </tr>
                </table>
                </td>
        </tr>
        <tr>
                <td class="auto-style1">
                <input name="addurl" style="width: 148px; height: 28px" type="submit" value="Add Link" />&nbsp;</td>
        </tr>
</table>
</div>
</div>
</form>



			</td>
		</tr>
		<tr>
			<td>
			<!--
				<center>
					<%@ include file="footer.htm"%>
					</center>
					-->
			</td>
		</tr>
	</table>
	</div>
</body>
</html>
