<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty users}">
    <ul>
        <c:forEach items="${users}" var="u">
            <li>
                ${u.name} --- ${u.age} -- ${u.createDate}<br/>

                    <c:forEach items="${u.friends}" var="friend"> FRIEND ===> ${friend.name}</c:forEach>
            </li>
        </c:forEach>
    </ul>
</c:if>
