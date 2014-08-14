<%-- 
    Document   : categories
    Created on : May 23, 2014, 7:02:31 PM
    Author     : Armando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="fragments/cssPageFragment.jspf" %>
        <title>Categories Page</title>
    </head>
    <body>
        <%@ include file="fragments/menuPageFragment.jspf" %>
        
        <div class="row row-offcanvas row-offcanvas-right" style="padding-left:20%">
            <div class="col-xs-12 col-sm-9">
               <div class="row">
                   
                   <c:if test="${empty PostListByCategory}">
                        <div class="panel panel-default">
                          <div class="panel-heading" style="background-color:#C5BEBE">
                             Post's not found!
                          </div>
                          <div class="panel-body">
                             There is not any post for this category!
                          </div>
                       </div>
                   </c:if>
                   <c:forEach var="post" items="${PostListByCategory}">   
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
        </div>
        <%@ include file="fragments/scriptPageFragment.jspf" %>
    </body>
</html>
