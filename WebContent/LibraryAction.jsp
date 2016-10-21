<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>

<title>result</title>
</head>
<body>
<table align = center border="4" cellspacing="20">
<tr>
	<th>书名</th>
	<th>删除</th>
</tr>
<s:iterator value = "list" status = "st">
<tr><td>
<a href="
<s:url action="detail.action">
    <s:param name="title"><s:property /></s:param>
</s:url>
"><s:property /></a></td>
<td>
<a href="
<s:url action="delete.action" >
    <s:param name="title"><s:property /></s:param>
</s:url>
">  删除</a>
</td>
</tr>
</s:iterator>

</table>
</body>
</html>