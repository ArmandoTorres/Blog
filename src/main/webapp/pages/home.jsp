<%@ page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Incluyendo los styles css -->
        <%@include  file="fragments/cssPageFragment.jspf" %>
        <title>Home Page</title>
    </head>
    <body>
        <!-- Incluyendo el menu -->
        <%@include  file="fragments/menuPageFragment.jspf" %>
        <div class="row row-offcanvas row-offcanvas-right" style="padding-left:20%">
        <div class="col-xs-12 col-sm-9">
          <div class="jumbotron">
              <h1 style="font-size: 35px;">${initParam['HomeMessage']}</h1>
              <p> ${initParam['HomeMessageDetails']}</p>
          </div>
          <div class="row">
            <c:forEach var="post" items="${postList}">   
                <div class="col-6 col-sm-6 col-lg-4">
                  <h2>
                      ${fn:length(post.title) > 13 ? fn:substring(post.title,0,13).concat('...') : post.title}
                  </h2>
                  <p>
                      ${fn:length(post.content) > 150 ? fn:substring(post.content,0,150).concat('...') : post.content}
                  </p>
                  <p>
                      <a class="btn btn-default" 
                         href="<c:url value="Post.htm">
                                <c:param name="postId" value="${post.postId}"/>
                              </c:url>" 
                         role="button">View details &raquo;</a>
                  </p>
                </div><!--/span-->
            </c:forEach>
          </div><!--/row-->
        </div><!--/span-->
        <!-- Incluyendo los JavaScript -->
        <%@include  file="fragments/scriptPageFragment.jspf" %>
    </body>
</html>