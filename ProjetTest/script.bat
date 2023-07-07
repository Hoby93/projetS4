cd "C:\Program Files\Apache Software Foundation\Apache Tomcat 8.0.27\webapps\FrameWork"
javac -cp .\WEB-INF\lib\*.jar -d .\WEB-INF\classes src\*.java

cd "C:\Program Files\Apache Software Foundation\Apache Tomcat 8.0.27\webapps\FrameWork\WEB-INF\classes"
jar cvfm etu1839.jar META-INF/MANIFEST.MF ./

copy etu1839.jar "C:\Program Files\Apache Software Foundation\Apache Tomcat 8.0.27\webapps\ProjetTest\WEB-INF\lib"

cd "C:\Program Files\Apache Software Foundation\Apache Tomcat 8.0.27\webapps\ProjetTest"
javac -parameters -cp .\WEB-INF\lib\*.jar -d .\WEB-INF\classes src\*.java

cd "C:\Program Files\Apache Software Foundation\Apache Tomcat 8.0.27\webapps\ProjetTest"
jar -cvf ProjetdeTest.war .\

copy ProjetdeTest.war "C:\Program Files\Apache Software Foundation\Apache Tomcat 8.0.27\webapps"



