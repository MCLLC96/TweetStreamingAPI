# TweetAPI-REST
Microservicio de consumo de Tweets basado en unos criterios de configuración y persistencia en base de datos para gestionar a través de una API REST.

## Configuración inicial

1. Instale JDK Java 11 y Maven para poder lanzar la aplicación Spring Boot.
2. Agregue al fichero **twitter4j.properties** las claves de API y Token de Twitter, situado en el directorio src/main/resources.
 ```
oauth.consumerKey=*************************
oauth.consumerSecret=**************************************************
oauth.accessToken=**************************************************
oauth.accessTokenSecret=*********************************************
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
