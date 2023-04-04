<% 
    String message = "Empty";
     if(request.getAttribute("message") != null) {
        message = request.getAttribute("message").toString();
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Bonjour, <%=message%></h1>
        <h3>Page d'accueil par defaut</h3>
    </body>
</html>
