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
            <div class="col-xs-12 col-sm-9" style="padding-left: 20%;">
                <div class="well">
                    <form action="UpdateUser.htm" method="POST">
                        <table>
                           <tr>
                              <td colspan="2"> <h2>Update User</h2> </td> 
                           </tr>   
                           <tr>
                               <td>
                                   <label class="col-sm-2 control-label">Name</label>
                               </td>
                               <td style="width: 550px;"> 
                                   <label class="col-sm-2 control-label">${selectedUser.userName}</label>
                               </td>
                           </tr>    
                           <tr>
                               <td>
                                   <label for="passwordTB1" class="col-sm-2 control-label">New Password</label>
                               </td>
                               <td> 
                                   <input class="form-control" type="password" name="passwordTB1" maxlength="25" required>
                               </td>
                           </tr>
                           <tr>
                               <td>
                                   <label for="passwordTB2" class="col-sm-2 control-label">Repeat Password</label>
                               </td>
                               <td> 
                                   <input class="form-control" type="password" name="passwordTB2" maxlength="25" required>
                               </td>
                           </tr>
                           <tr>
                              <td>
                              </td>
                              <td>
                                  <input type="submit" value="Change Pass" class="btn btn-default"/>
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
                               <input type="hidden" value="${selectedUser.userId}" name="userId"/>    
                    </form>
                </div>
            </div>    
        <%@include file="fragments/scriptPageFragment.jspf"%>        
    </body>
</html>