# ATM-BackEnd

## API-REST de prueba para la empresa High-Impact

#### ATM-BackEnd es una API REST desarrollada en Java, basada en el estandar Servlet que Implementa Spring-Boot y securizada con Spring-Security utilizando un token JWT.

Este proyecto debe ser utilizado en conjunto con el proyecto ATM-FrontEnd que encontrará en el repositorio: https://github.com/julianmacagno/ATM-Frontend

Para ejecutarlo: 

- Debe poseer una versión de *Tomcat >=7.0.41* pues las versiones anteriores no soportan CORS.

- Descargue el código fuente desde el repositorio de GitHub: https://github.com/julianmacagno/ATM-Backend

- Instale *Java 8* y *Maven* si no los tiene instalados.

- Posiciónese sobre la raíz del proyecto y ejecute ``mvn install`` y luego ``mvn clean package`` que generará un archivo .war dentro del directorio *target*.

- Mueva ese archivo .war en la carpeta *webapps* de su instalación de *Tomcat*.

- Si está usando *Tomcat 7* reemplace el archivo *el-api.jar* de la carpeta *lib* de su instalación de *Tomcat 7* por el archivo *el-api.jar* de la carpeta *libs* de la raíz del proyecto.

- Ejecute su servidor *Tomcat*

Para documentación sobre los recursos de la API, lea el documento *API-DOC.md*

---
- Autor: Julian I. Macagno
- Fecha: Diciembre del 2020
---