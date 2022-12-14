<%--
  Created by IntelliJ IDEA.
  User: sora
  Date: 2022/09/26
  Time: 11:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>List Page</h1>

<h1>${appName}</h1>
<h2>${loginInfo}</h2>
<h3>${loginInfo.mname}</h3>

    <ul>
        <c:forEach var="dto" items="${dtoList}">
            <li>
                <span><a href="/todo/read?tno=${dto.tno}">${dto.tno}</a></span>
                <span>${dto.title}</span>
                <span>${dto.dueDate}</span>
                <span>${dto.finished? "DONE": "NOT YET"}</span>
            </li>
        </c:forEach>
    </ul>

<form action="/logout" method="post">
    <button>LOGOUT</button>
</form>

</body>
</html>
