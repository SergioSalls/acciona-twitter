# acciona-twitter

El proyecto acciona-twitter ha sido desarrollado en Java 11 con SpringBoot y permite realizar las siguientes acciones:

- Consultar los tweets.
- Consultat un tweet por id.
- Marcar un tweet como validado.
- Consultar los tweets validados por usuario.
- Consultat una clasificación de todos los hashtags disponibles
- Consultar una clasificación de los N hashtags más usados (default 10)

El almacenamiento de tweets se ha basado en un cron parametrable, el cuál va recuperando datos en un intervalo de tiempo
dónde almacena los tweets en una base de datos según unos critérios de configuración.

Para su almacenamiento se ha utilizado una base de datos no persitente H2 que nos permite tener acceso a ella
mediante la url http://localhost:8080/h2-console. Para entrar en el interfaz será necesário introducir las credenciales
que se encuentran en el fichero application.yml del proyecto.

Todos los criterios a tener en cuenta a la hora de persistir los tweets en la base de datos son obtenidos del fichero
application.yml.
Estos criterios se definen como:
- twitter.cron.seconds --> Nos permite definir el intervalo de tiempo en segundos que queremos que se ejecute la obtención de tweets
- twitter.query-count --> El número de tweets que queremos recibir por cada petición
- twitter.min-followers --> Número mínimo de seguidores para tener en cuenta su almacenamiento en base de datos
- twitter.min-hashtag-count --> Número de veces mínimas que debe aparecer un hashtag para considerarlo dentro de la lista de clasificaciones de hashtags más usados
- twitter.max-tweet-page --> Número máximo de elementos a mostrar por página en la consulta de todos los tweets almacenados
- twitter.languages --> Idiomas a tener en cuenta para su almacenamiento en base de datos

Todo estos enpoints pueden ser probados con swagger mediante la url http://localhost:8080/swagger-ui/index.html