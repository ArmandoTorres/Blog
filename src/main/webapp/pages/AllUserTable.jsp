<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="fragments/cssPageFragment.jspf"%>
        <title>Users</title>
    </head>
    <body>
        <%@include file="fragments/menuPageFragment.jspf"%>
            <div class='table-responsive' style='margin-left: 30%;margin-right: 30%;'>
                <table class="table table-hover">
                    <caption><h2>Users Table ${prb}</h2></caption>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="userTable" items="${userList}">
                            <tr>
                                <td>${userTable.userName}</td>
                                <td>${userTable.status eq 'A' ? 'Active' : 'Inactive'}</td>
                                <td>
                                    <a class='btn btn-default' style="width: 125px" 
                                       href="<c:url value='UpdateUser.htm'>
                                                <c:param name='userId'  value='${userTable.userId}'/>
                                                <c:param name='option'  value='Change'/>
                                             </c:url>">Change Pass
                                    </a>
                                    <a class='btn btn-default' style="width: 125px" 
                                       href="<c:url value='UpdateUser.htm'>
                                                <c:param name='userId'  value='${userTable.userId}'/>
                                                <c:param name='option'  value="${userTable.status eq 'A' ? 'Disable' : 'Enabled'}"/>
                                             </c:url>">${userTable.status eq 'A' ? 'Disable' : 'Enable'}
                                    </a>
                                </td>
                            </tr>    
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        <%@include file="fragments/scriptPageFragment.jspf"%>
    </body>
</html>