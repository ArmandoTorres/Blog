<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="fragments/cssPageFragment.jspf"%>
        <title>Post Table</title>
    </head>
    <body>
        <%@include file="fragments/menuPageFragment.jspf"%>
            <div class='table-responsive' style='margin-left: 20px;margin-right: 20px;'>
                <table class="table table-hover">
                    <caption><h2>Post Table</h2></caption>
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Content</th>
                            <th>User</th>
                            <th>Category</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="postTable" items="${postList}">
                            <tr>
                                <td>${postTable.title}</td>
                                <td>${fn:substring(postTable.content,0,50).concat('...')}</td>
                                <td>${postTable.user}</td>
                                <td>${postTable.category}</td>
                                <td>
                                    <a class='btn btn-default' 
                                       href="<c:url value='UpdatePost.htm'>
                                                <c:param name='postId'  value='${postTable.postId}'/>
                                                <c:param name='actions' value='Edit'/>
                                                <c:param name='pageInfomation' value='AllPostTable'/>
                                             </c:url>">Edit</a>
                                    <button type="button" class="btn btn-default" onclick="askQuestion('${postTable.postId}');">
                                        Delete
                                    </button>  
                                </td>
                            </tr>    
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        <%@include file="fragments/scriptPageFragment.jspf"%>
        <script type="text/javascript" language="javascript">
                function askQuestion(var1){
                  if (confirm('Do you want to delete this post an all comment, attached related?')){  
                    document.location.href="UpdatePost.htm?postId="+var1+"&actions=Delete&pageInfomation=AllPostTable";
                  }  
                }
        </script>
    </body>
</html>