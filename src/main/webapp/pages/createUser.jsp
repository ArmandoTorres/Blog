<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="fragments/cssPageFragment.jspf"%>
        <title>Create User</title>
    </head>
    <body>
        <%@include file="fragments/menuPageFragment.jspf"%>
            <div class="col-xs-12 col-sm-9" style="padding-left: 20%;">
                <div class="well">
                    <form action="CreateUser.htm" method="POST">
                        <table>
                           <tr>
                              <td colspan="2"> <h2>Create User</h2> </td> 
                           </tr>   
                           <tr>
                               <td>
                                   <label for="userNameTB" class="col-sm-2 control-label">Name</label>
                               </td>
                               <td style="width: 550px;"> 
                                   <input class="form-control" type="text" name="userNameTB" placeholder="Enter the name" required>
                               </td>
                           </tr>    
                           <tr>
                               <td>
                                   <label for="passwordTB" class="col-sm-2 control-label">Password</label>
                               </td>
                               <td> 
                                   <input class="form-control" type="password" name="passwordTB" placeholder="Enter the pass" required>
                               </td>
                           </tr>
                           <tr>
                              <td>
                              </td>
                              <td>
                                  <input type="submit" value="Create" class="btn btn-default"/>
                              </td>    
                           </tr>
                           <tr>
                              <td colspan="2">
                                  <c:if test="${not empty message}">
                                      <div class="alert alert-success">
                                          <a href="#" class="close" data-dismiss="alert">
                                              &times;
                                          </a>
                                          <p>${message}</p>
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
            </div>    
        <%@include file="fragments/scriptPageFragment.jspf"%>
    </body>
</html>