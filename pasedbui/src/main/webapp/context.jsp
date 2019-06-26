<!DOCTYPE html>
<html>

<head>
<meta content="text/html;charset=utf-8" http-equiv="content-type">
<title>Parental Alienation Support & Education</title>
<link href="menu.css" media="all" rel="stylesheet" type="text/css">
<%@ include file="meta.htm"%>
<% 
         new org.pasedb.pasedbui.Counter(request);
%>
<%@ page
                language="java"
                contentType="text/html; charset=UTF-8"
                pageEncoding="UTF-8"
                import="org.pasedb.pasedbui.*"
%>
<%!
        String[] context ={"Parental Alienation","Parental Rights","In the Media","Court/Legal","Support Groups","Comments","Books/Reading"};
%>
<%
        StringBuffer lnks = new StringBuffer();
        int cntx = 0;
        try{cntx = Integer.parseInt(request.getParameter("id").toString());}catch(Exception ex){}
        try{
                lnks.append(new HtmlEngine().generateTable(new ElicitEngine().getLinks(cntx)));
        }catch(Exception ex){
                ex.printStackTrace();
        }
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
.links {
        width: 800px;
        border: 1px solid #0000FF;
        border-collapse: collapse;
        padding: 3px;
}
.links td {
        padding: 3px;
}
.links tbody {
        border: 1px solid #0000FF;
        padding: 3px;
}
img { border-style: none; }
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
                        <td>
                                                                <table class="links">
                                                                        <%=lnks.toString()%>
                                                                </table>
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