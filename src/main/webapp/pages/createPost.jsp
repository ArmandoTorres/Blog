<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="fragments/cssPageFragment.jspf"%>
        <title>Create Post Page</title>
    </head>
    <body>
        <%@include file="fragments/menuPageFragment.jspf"%>
            <div class="col-xs-12 col-sm-9" style="padding-left: 20%;">
                <div class="well">
                    <form action="CreatePost.htm" method="POST" enctype="multipart/form-data">
                        <table>
                           <tr>
                              <td colspan="2"> <h2>Create Post</h2> </td> 
                           </tr>   
                           <tr>
                               <td>
                                   <label for="titleTB" class="col-sm-2 control-label">Title</label>
                               </td>
                               <td style="width: 550px;"> 
                                   <input class="form-control" type="text" name="titleTB" placeholder="Enter the title" required>
                               </td>
                           </tr>    
                           <tr>
                               <td>
                                   <label for="categoryTB" class="col-sm-2 control-label">Category</label>
                               </td>    
                               <td>
                                   <select name="selectCat">
                                       <c:forEach var="catList" items="${categoryList}">
                                            <option value="${catList.categoryId}">${catList.name}</option>
                                       </c:forEach>
                                   </select>
                               </td>    
                           </tr>    
                           <tr>
                               <td>
                                   <label for="contentTB" class="col-sm-2 control-label">Content</label>
                               </td>
                               <td> 
                                   <textarea rows="15" cols="40" class="form-control" id="contentTB" name="contentTB" required style="resize: none"></textarea>
                               </td>
                           </tr>
                           <tr>
                               <td>
                                    <label for="uploadFile" class="col-sm-2 control-label">File</label>
                               </td>
                               <td>
                                   <input type="file" name="uploadFile" 
                                          accept="image/*,application/vnd.openxmlformats-officedocument.wordprocessingml.document,
                                                  application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,
                                                  application/vnd.openxmlformats-officedocument.presentationml.presentation,
                                                  application/pdf,
                                                  text/html"/>
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