<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="model.Emp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <style>
            .form-login {
                width: 200px;
                padding: 5px;
                margin-top: 5px;
            }
        </style>
    </head>
    <body>
        <h1>Welcome this page</h1>
        
        <form action="emp-add" method="post"> 
            <input type="text" placeholder="Nom" name="nom" class="form-login"/> <br>
            <input type="text" placeholder="Prenom" name="prenom" class="form-login"/> <br>
            <select name="age" class="form-login" style="width: 212px;">
                <option value="">Age</option>
                <option value="18">18</option>
                <option value="19">19</option>
                <option value="20">20</option>
            </select> <br>

            <input type="submit"  value="Confirmer" class="form-login" style="width: 212px;"/> <br>
        </form>
            
    </body>
</html>
