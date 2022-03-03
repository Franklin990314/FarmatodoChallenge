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

Base url local: http://localhost:9090/api

+ Episode
    + Puede obtener la información de un episodio de rick and morty utilizando el endpoint /episodio/{id}, donde el **id** es el episodio a consultar.   
      Ejemplo de consumo local:  http://localhost:9090/api/episode/30

+ Happy Number
    + Puede consultar si un número es feliz o no usando el endpoint /happynumber/{numbers}, donde **numbers** es una lista de números a consultar separados por coma.   
      Ejemplo de consumo local:  http://localhost:9090/api/happynumber/33,331,123

+ Summation
    + Puede calcular el resultado de la suma de números naturales hasta N usando el endpoint /summation/{number}, donde **number** es el número a calcular.   
      Ejemplo de consumo local:  http://localhost:9090/api/summation/5