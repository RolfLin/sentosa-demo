# sentosa-demo

To launch a local REST server:
```bash
mvn clean install
mvn exec:java
```

To generator a *.war file for a Servlet container such as Tomcat:
```bash
mvn clean package
```
*.war file can be found in ``target`` folder.
