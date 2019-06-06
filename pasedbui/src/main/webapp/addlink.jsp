<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%!
//--static
%>
<%

%>







<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta content="en-us" http-equiv="Content-Language" />
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<title>Untitled 1</title>
<style type="text/css">
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




<form>
<div align="center">
<div>
<table style="width: 800px" class="customTable">
        <tr>
                <td>Add Link</td>
        </tr>
        <tr>
                <td>
                <input name="pr" type="checkbox" value="1"/>Parental Rights
                <input name="itm" type="checkbox" value="1"/>In the Media
                <input name="co" type="checkbox" value="1"/>Court Opinions
                <input name="sg" type="checkbox" value="1"/>Support Groups
                </td>
        </tr>
        <tr>
                <td>
                <table style="width: 100%">
                        <tr style="vertical-align: top">
                                <td style="width: 125px" class="auto-style1">URL</td>
                                <td><input name="Text1" style="width: 99%;" type="text" />&nbsp;</td>
                        </tr>
                        <tr style="vertical-align: top">
                                <td style="width: 125px" class="auto-style1">Comments</td>
                                <td><textarea name="TextArea1" rows="3" style="width: 99%; "></textarea>&nbsp;</td>
                        </tr>
                </table>
                </td>
        </tr>
        <tr>
                <td class="auto-style1">
                <input name="Submit1" style="width: 148px; height: 28px" type="submit" value="submit" />&nbsp;</td>
        </tr>
</table>
</div>
</div>
</form>
</body>

</html>
