# Test Api Restful Client

Requisitos: 
- Java 22


Descargar el proyecto desde el Repositorio Publico:
```
https://github.com/moracool/SpringBoot-test
```

# 1) Hacer el Deplay desde el Jar

El archivo Jar ejecutable se encuentra disponible en la carpeta Target dentro del proyecto, ejecutar desde la consola:

```
java -jar springboot-client-0.0.1-SNAPSHOT
```

# 2) Otra forma es abrirlo desde el Visual Studio Code u otro IDE que tengamos configurado para manejar Java

**Importante:** 

Configurar variable de entorno para que apunte a la versión de Java:

```
export JAVA_HOME=/directorio/jdk
```


# PRUEBAS

Para Realizar las pruebas del Api RestFul,  debe hacerse: 
1) Importar al Postamn el collection del proyecto que se encuentra en /DOCS
   
2) Iniciar el Deploy/Run del proyecto (ApiResful)
   
3) Realizar las pruebas GET, POST, PUT, DEL incluidad en el collection

# DOCUMENTACIÓN 

La documentación solicitada se encuentra dentro de la carpeta /DOCS

1) Modelo Entidad Relación del Test: Model databases ER.pdf

2) Postman Collectión usado para todo el desarrollo : API_CLIENT_JAVA.postman_collection

3) Data Base Script: Pequeño script con la creación de la tabla
database_script.sql

# SWAGGER

Para ver la Documentación de la Api con Swagger, una vez esta corriendo el test:

```
http://localhost:8080/swagger-ui/index.html
```
