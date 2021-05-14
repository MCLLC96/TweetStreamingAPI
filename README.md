# TweetAPI-REST
Microservicio de consumo de Tweets basado en unos criterios de configuración y persistencia en base de datos para gestionar a través de una API REST.

## Configuración inicial

1. Instale JDK Java 11 y Maven para poder lanzar la aplicación Spring Boot.
2. Diríjase al directorio src/main/resources.
3. Agregue al fichero **twitter4j.properties** las claves de API y Token de Twitter.
 ```
oauth.consumerKey=*************************
oauth.consumerSecret=**************************************************
oauth.accessToken=**************************************************
oauth.accessTokenSecret=*********************************************
```
3. Diríjase al fichero **application.properties** y elija los parámetros por defecto que usará la aplicación para gestionar los tweets. Por defecto se guardarán aquellos tweets cuyo usuario tenga al menos 1500 seguidores, el idioma sea español, italiano o francés y finalmente de mostrará un top de hashtags con tamaño máximo 10.
```
number.followers=1500
languages=es,it,fr
top.hashtags=10
```

## Ejecución de TweetAPI-REST
1. Aplicación Java:
```
$ mvnw package
$ java -jar target/TweetsAPI-REST-0.0.1-SNAPSHOT.jar
```
2. Plugin spring-boot de Maven:
```
$ mvnw spring-boot:run
```
La aplicación se arranca por defecto en el puerto local 8080.

## Acceso a la base de datos (H2)
Una vez la aplicación haya sido desplegada, introduzca en el navegador ``/h2-console`` y acceda con las siguientes credenciales (por defecto):
* Usuario: sa
* Contraseña:


## INFO TweetAPI-REST
``
GET /tweets
`` Devuelve todos los tweets almacenados en la base de datos.

``
GET /tweets/{id}/valid
`` Cambia la validación del tweet con el id pasado como parámetro al valor true.

``
GET /tweets/validTweets/{user}
`` Devuelve todos los tweets validados de un usuario (nombre) pasado como parámetro.

``
GET /tweets/hashtags
`` Devuelve una lista de los hashtags mas usados (por defecto 10).
