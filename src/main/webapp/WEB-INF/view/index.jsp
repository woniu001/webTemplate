<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://xiaochangwei.com/tags" prefix="permission" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网站首页</title>
</head>
<body>

<permission:hasPermission name="viewInfo">有权限<br/><br/></permission:hasPermission>

<br/><br/>
<br/><br/>
<br/><br/>
<br/><br/>
<br/><br/>
<br/><br/>
<br/><br/>
<br/><br/>
<br/><br/>
<br/><br/>

	<c:forEach items="${users}" var="item" varStatus="status">
		<tr>
			<td>${status.index + 1}</td>
			<td>${item.nickName }</td>
			<td>${item.age }</td>
			<td><img alt="头像" src="${item.photoPath }"/></td>
		</tr>
		<tr>
			<c:forEach items="${item.photos}" var="item2" varStatus="status">
				<td>
					<img alt="相册" src="${item2.path }">
				</td>
			</c:forEach>
		</tr>
	</c:forEach>
</body>
</html>