<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="model.Emp" %>

<%
    Emp employe =  (Emp) request.getAttribute("emp");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <style>
            body {
                font-family: Arial, Helvetica, sans-serif;
            }
            .tab {
                border: none;
            }
            .tab th {
                width: 100px;
            }
            .tab tr {
                text-align: left;
            }
        </style>
    </head>
    <body>
        <h1>Welcome this page</h1>
        
        <table class="tab">
            <tr>
                <th> IdEmp: </th>
                <td><%=employe.getIdEmp()%></td>
            </tr>
            <tr>
                <th> Nom: </th>
                <td><%=employe.getNom()%></td>
            </tr>
            <tr>
                <th> Prenom: </th>
                <td><%=employe.getPrenom()%></td>
            </tr>
            <tr>
                <th> Age: </th>
                <td><%=employe.getAge()%></td>
            </tr>
        </table>

        <div>
        <br>
            <a href="emp-all"><< Retour</a>
        </div>
    </body>
</html>
