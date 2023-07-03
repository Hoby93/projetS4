<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
        <h1>Se connecter</h1>
        
        <form action="login-check" method="post" enctype="multipart/form-data">
            <input type="text" placeholder="Nom utilisateur" name="nom" class="form-login"/> <br>
            <input type="text" placeholder="Mot de passde" name="mdp" class="form-login"/> <br>

            <input type="submit"  value="Valider" class="form-login" style="width: 212px;"/> <br>
        </form>
    </body>
</html>
