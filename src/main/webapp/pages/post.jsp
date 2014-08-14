<%-- 
    Document   : post
    Created on : May 23, 2014, 10:55:12 AM
    Author     : Armando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="fragments/cssPageFragment.jspf" %>
        <title>Post Page</title>
    </head>
    <body>
        <%@include file="fragments/menuPageFragment.jspf" %>
        
        <div class="col-xs-12 col-sm-9" style="padding-left: 20%;">
            <div class="well">
              <h2 style="font-size: 35px;">${PostData.title}</h2>
              <p>${PostData.content}</p>
              <c:if test="${not empty attach}">
                  <a href="<c:url value="Download.htm">
                                <c:param name="fileName" value="${attach.fileName}"/> 
                                <c:param name="autor" value="${attach.userName}"/> 
                           </c:url>     
                     ">Attachment</a>
                  <br>
              </c:if>    
              <br>
             <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#demo" >
                Comments:
             </button>
              <br>
              <br>
              <div id="demo" class="collapse in">
                    <c:if test="${empty commentList}">
                          <div class="panel panel-default">
                            <div class="panel-heading" style="background-color:#C5BEBE">
                               Not comment!
                            </div>
                            <div class="panel-body">
                               No comment at this time, but you can be the first!.
                            </div>
                         </div>
                    </c:if>
                    <c:forEach var='comments' items="${commentList}">
                          <div class="panel panel-default">
                            <div class="panel-heading" style="background-color:#C5BEBE">
                               ${comments.name}
                            </div>
                            <div class="panel-body">
                               ${comments.content}
                            </div>
                         </div>
                    </c:forEach>  
              </div>    
          </div>  
          <div>    
              <form action="Post.htm" method="POST">
                <div class="form-group">
                   <label for="name" class="col-sm-2 control-label">Name</label>
                   <div class="col-sm-10">
                      <input class="form-control" type="text" name="nameTB" placeholder="Enter your name" required>
                   </div>
                </div>
                <div class="form-group">
                   <label for="email" class="col-sm-2 control-label">Email</label>
                   <div class="col-sm-10">
                      <input class="form-control" type="text" name="emailTB" placeholder="Email Address">
                   </div>
                </div>              
                <div class="form-group">
                   <label for="comment" class="col-sm-2 control-label">Comment</label>
                   <div class="col-sm-10">
                       <textarea class="form-control" rows="4" cols="30" name="commentTB" required></textarea>
                   </div>
                </div> 
                <div class="form-group">
                   <div class="col-sm-offset-2 col-sm-10">
                       <input class="btn btn-default" type="submit" value="Post comment">
                   </div>
                </div>
                  <input type="hidden" name="actualPost" value="${PostData.postId}"/>
              </form>
               <c:if test="${not empty error}">
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <a href="#" class="close" data-dismiss="alert">
                              &times;
                            </a>
                            <p class="alert alert-danger"> ${error}</p>
                        </div>
                    </div>    
               </c:if>    
           </div>      
        </div>      
        <%@include  file="fragments/scriptPageFragment.jspf"%>      
    </body>
</html>
