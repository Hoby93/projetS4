<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="model.Emp" %>

<%
    Emp[] list = null;

    if(request.getAttribute("lst") != null) {
        list = (Emp[]) request.getAttribute("lst");
    }
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
                padding: 2px;
                background-color: rgb(131, 182, 240);
            }
            .tab tr {
                background-color: rgba(201, 201, 201, 0.753);
                text-align: left;
            }
        </style>
    </head>
    <body>
        <h1>Welcome this page</h1>

        <a href="emp-add">Ajouter</a><br><br>
        <table class="tab">
            <tr>
                <th> IdEmp </th>
                <th> Nom </th>
                <th> Prenom </th>
                <th> Age </th>
            </tr>
            <% for(int i = 0; i < list.length; i++) { %>
            <tr>
                <td> <%=list[i].getIdEmp()%> </td>
                <td> <%=list[i].getNom()%> </td>
                <td> <%=list[i].getPrenom()%> </td>
                <td> <%=list[i].getAge()%> </td>
                <td> <a href="emp-findById?id=<%=list[i].getIdEmp() - 1%>">Detail</a> </td>
            </tr>
            <% } %>
        </table>
    </body>
</html>
