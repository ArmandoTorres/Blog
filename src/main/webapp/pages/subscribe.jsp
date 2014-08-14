<%-- 
    Document   : subscribe
    Created on : May 23, 2014, 7:27:39 PM
    Author     : Armando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="fragments/cssPageFragment.jspf" %>
        <title>Subscribe Page</title>
    </head>
    <body>
         <%@ include file="fragments/menuPageFragment.jspf" %>
         <div class="row row-offcanvas row-offcanvas-right" style="padding-left: 30%">
             <form method="POST" action="Subscribe.htm">
                <table>
                       <tr>
                          <td> <h2>Subscribe</h2> </td> 
                       </tr>   
                       <tr>
                           <td>
                               <label for="nameTB" class="col-sm-2 control-label">Name</label>
                           </td>
                           <td style="width: 350px;"> 
                               <input class="form-control" type="text" name="nameTB" placeholder="Enter your name" required>
                           </td>
                       </tr>    
                       <tr>
                       </tr>    
                       <tr>
                           <td>
                               <label for="emailTB" class="col-sm-2 control-label">Email</label>
                           </td>
                           <td> 
                               <input class="form-control" type="text" id="emailTB" name="emailTB" placeholder="Email Address" onsubmit="return validateEmail('emailTB');" required>
                           </td>
                       </tr>
                       <tr>
                           <td>
                               <label for="nameTB" class="col-sm-2 control-label">Categories</label>
                           </td>
                       </tr>
                       <!-- checkbox con categorias -->
                       <c:forEach var="categories" items="${categoryList}">
                              <tr>
                                  <td>
                                  </td>
                                  <td> 
                                      <label><input type="checkbox" name="optionsCat" value="${categories.categoryId}">${categories.name}</label>
                                  </td>
                              </tr>
                       </c:forEach>       
                      <tr>
                          <td>
                          </td>
                          <td>
                              <input type="submit" value="Join" class="btn btn-default"/>
                          </td>    
                      </tr>
                      <tr>
                        <td colspan="2">
                            <c:if test="${not empty success}">
                                <div class="alert alert-success">
                                    <a href="#" class="close" data-dismiss="alert">
                                              &times;
                                    </a>
                                    <p>${success}</p>
                                </div>
                            </c:if> 
                            <c:if test="${not empty error}">
                                <div class="alert alert-danger">
                                    <a href="#" class="close" data-dismiss="alert">
                                              &times;
                                    </a>
                                    <p>${error}</p>
                                </div>
                            </c:if> 
                        </td>    
                      </tr>
                   </table>
             </form>    
         </div>
         <%@ include file="fragments/scriptPageFragment.jspf" %>
    </body>
</html>