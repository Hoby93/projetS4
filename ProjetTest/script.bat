cd "C:\Program Files\Apache Software Foundation\Tomcat 8.5\webapps\MyFrameWork"
javac -cp .\WEB-INF\lib\*.jar -d .\WEB-INF\classes src\*.java

cd "C:\Program Files\Apache Software Foundation\Tomcat 8.5\webapps\MyFrameWork\WEB-INF\classes"
jar cvfm etu1839.jar META-INF/MANIFEST.MF ./

copy etu1839.jar "C:\Program Files\Apache Software Foundation\Tomcat 8.5\webapps\ProjetTest\WEB-INF\lib"

cd "C:\Program Files\Apache Software Foundation\Tomcat 8.5\webapps\ProjetTest"
javac -cp .\WEB-INF\lib\*.jar -d .\WEB-INF\classes src\*.java

cd "C:\Program Files\Apache Software Foundation\Tomcat 8.5\webapps\ProjetTest"
jar -cvf ProjetdeTest.war .\

copy ProjetdeTest.war "C:\Program Files\Apache Software Foundation\Tomcat 8.5\webapps"



