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
    </head>
    <body>
        <h1>Welcome this page</h1>
        
        <table>
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
    </body>
</html>
