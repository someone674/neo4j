<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty world}">
    <ul>
        <c:forEach items="${world}" var="w">
            <li>
                ${w.name} --- ${w.moons}<br/>
            </li>
        </c:forEach>
    </ul>
</c:if>
