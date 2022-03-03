# Farmatodo Challenge

### Ejecutar el proyecto en local

Para ejecutar este proyecto en local tenga las siguientes consideraciones:

+ Tener instalada la base de datos PostgreSQL en su equipo.
+ Ejecutar el script que se encuentra ubicado en la ruta **/static/sql/scripts.sql**
+ Configurar las variables de propiedad que se encuentran ubicadas en la ruta **src/main/resources/application.properties**:
    + spring.datasource.url= jdbc:postgresql://localhost:\<add your port>/rickandmorty_farmatodo
    + spring.datasource.username= \<add your user name>
    + spring.datasource.password= \<add your password>

### Consumir las APIs

Esta API se puede ejecutar y consumir de forma local, y también se puede consumir de forma global, está desplegada sobre la plataforma de servicio en la nube Heroku.

Base url local: http://localhost:9090/api  
Base url despliegue: https://farmatodo-prueba.herokuapp.com

+ Episode
    + Puede obtener la información de un episodio de rick and morty utilizando el endpoint /episodio/{id}, donde el **id** es el episodio a consultar.   
      Ejemplo de consumo local:  http://localhost:9090/api/episode/30  
      Ejemplo de consumo global:  https://farmatodo-prueba.herokuapp.com/api/episode/30

+ Happy Number
    + Puede consultar si un número es feliz o no usando el endpoint /happynumber/{numbers}, donde **numbers** es una lista de números a consultar separados por coma.   
      Ejemplo de consumo local:  http://localhost:9090/api/happynumber/33,331,123  
      Ejemplo de consumo global:  https://farmatodo-prueba.herokuapp.com/api/happynumber/33,331,123

+ Summation
    + Puede calcular el resultado de la suma de números naturales hasta N usando el endpoint /summation/{number}, donde **number** es el número a calcular.   
      Ejemplo de consumo local:  http://localhost:9090/api/summation/5  
      Ejemplo de consumo global:  https://farmatodo-prueba.herokuapp.com/api/summation/5

### Documentación

Se utilizo la herramienta Swagger para documentar las API's, puede consultar su información en el siguiente link:  
  
Url local: http://localhost:9090/swagger-ui.html  
Url global: https://farmatodo-prueba.herokuapp.com/swagger-ui.html