<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="fragments/cssPageFragment.jspf"%>
        <title>Edit Category</title>
    </head>
    <body>
        <%@include file="fragments/menuPageFragment.jspf"%>
          <div class="col-xs-12 col-sm-9" style="padding-left: 20%;">
                <div class="well">
                    <form action="UpdateCategory.htm" method="POST">
                        <table>
                           <tr>
                              <td colspan="2"> <h2>Edit Category</h2> </td> 
                           </tr>   
                           <tr>
                               <td>
                                   <label for="nameTB" class="col-sm-2 control-label">Title</label>
                               </td>
                               <td style="width: 550px;"> 
                                   <input class="form-control" type="text" name="nameTB" value="${category.name}" required>
                               </td>
                           </tr>    
                           <tr>
                               <td>
                                   <label for="descriptionTB" class="col-sm-2 control-label">Content</label>
                               </td>
                               <td> 
                                   <textarea rows="15" cols="40" class="form-control" id="contentTB" name="descriptionTB" required style="resize: none"><c:out value="${category.description}"/></textarea>
                               </td>
                           </tr>
                           <tr>
                              <td>
                              </td>
                              <td>
                                  <input type="submit" value="Update" class="btn btn-default"/>
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
                            <input type="hidden" value="${category.categoryId}" name="hiddenId"/>   
                    </form>
                </div>
            </div>        
        <%@include file="fragments/scriptPageFragment.jspf"%>        
    </body>
</html>
