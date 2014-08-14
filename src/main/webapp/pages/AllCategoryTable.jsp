<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="fragments/cssPageFragment.jspf"%>
        <title>Categories</title>
    </head>
    <body>
        <%@include file="fragments/menuPageFragment.jspf"%>
            <div class='table-responsive' style='margin-left: 20px;margin-right: 20px;'>
                <table class="table table-hover">
                    <caption><h2>Categories Table</h2></caption>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="categoryTable" items="${categoryList}">
                            <tr>
                                <td>${categoryTable.name}</td>
                                <td>${fn:substring(categoryTable.description,0,50).concat('...')}</td>
                                <td>
                                    <a class='btn btn-default' 
                                       href="<c:url value='UpdateCategory.htm'>
                                                <c:param name='catId'  value='${categoryTable.categoryId}'/>
                                                <c:param name='actions' value='Edit'/>
                                                <c:param name='pageInfomation' value='AllCatTable'/>
                                             </c:url>">Edit</a>
                                    <button type="button" class="btn btn-default" onclick="askQuestion('${categoryTable.categoryId}');">
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
                  if (confirm('Do you want to delete this category an all post, comment and attached related?')){  
                    document.location.href="UpdateCategory.htm?catId="+var1+"&actions=Delete&pageInfomation=AllCatTable";
                  }  
                }
        </script>
    </body>
</html>