A- CONFIGURATION:
  1. creer une page 'index.html' qui sera la page d'accueil par defaut
  2. Creer une repertoire /web pour mettre les vues (pages jsp) et y mettre 'index.html'
  3. Creer une repertoire /src pour mettre les codes (fichier java)
  4. Dans le fichier web.xml de votre projet, ajouter deux servlet:
    (a) -servlet-name: FrontServlet
	-servlet-class: etu1839.framework.servlet.FrontServlet
	-url-pattern: /*
    (b) -servlet-name: jsp
	-url-pattern: /web/*
  5. Charger la librairie 'etu1839.jar' dans le projet
  6. Version Tomcat 8.5

B- UTILISATION:
  1. Toutes les class doivent comporter un constructeur vide
  2. Les class doivent importer les packages: 'etu1839.framework.annotation.AnnotationUrl' et 'etu1839.framework.ModelView'
  3. Ajouter '@AnnotationUrl' (inclus dans le framework) sur chaque methode que l'on veut etre considerer, et specifier le slug pour chaque method (ex: @AnnotationUrl(url = "emp-all"))
  4. Les class ont comme type de retour la class 'ModelView' dont la constructeur demande le nom d'une page de redirection.
  5. Pour passer des donnes vers les vues, on peut utiliser la methode 'addItem' dans 'ModelView' qui a besoin d'une 'cle type <String>' et de 'objet type <Objet>'. On peut recuperer ces valeurs dans la page cible avec 'request.getAttribute(<cle>)'
  6. Le nom des input doit correspondre au nom des attributs (identique)
  7. Le nom des valeurs passer en url et celle des parametres du fonction que l'on veut invoquer doit etre similaire
  4. La compilation doit etre suivie du parametre '-parameters'

