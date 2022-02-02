# Product Catalog

Readme md que contiene la información necesaria para la construcción y despliegue 

# Prefacio
Este repositorio contiene ambos artefactos solicitados: backend utilizando Spring Boot con java11 y frontend utilizando angular 13 que es por defecto a la versión de npm que está instalado en mi equipo. 

### BACKEND
Respecto al backend se está utilizando paquetes que ofrece la misma tecnología, por lo que el archivo pom.xml contiene spring-boot-starter-web, spring-boot-starter-data-jpa y spring-boot-starter-test. Lo único que es fuera de estas librerías es commons-lang3 de org.apache.commons para validar el contenido de variables String. 

Para persistencia decidí por una base de datos en memoria h2, más que nada por la rapidez en la implementación y la facilidad que da Spring Boot para integrarla. 

Decidí también responder con un objeto definido y estándar para cada una de las respuesta de los endpoint, que es parte de la manera que he estado trabajando los proyectos en java, este objeto que es de la clase "Respuesta" contiene una variable genérica llamada "data" que contendría los devuelto (el o los productos, por ejemplo) por el sistema, esta también se utiliza al momento de haber excepciones y devolver errores 4xx o 5xx. 

### FRONTEND

Respecto al frontend se decidió por Angular como está solicitado en el documento, versión 13 que se desarrolló utilizando node v14 y npm 6.14, En este caso decidí hacer el sistema un poco más distribuido en diferentes componentes que solo uno grande con todas las operaciones, aunque estas no son demasiadas, se comunican a través de un servicio que contiene un Subject, para que los componentes se enteren de los cambios en la grilla de los productos.

Para la validación de formularios se optó por trabajar con ReactiveForms 

### BASE DE DATOS

Solo contiene dos tablas Producto e Imagen, Imagen teniendo como llave foránea el producto_id, en un principio pensé en que producto tuviese una url para su imagen principal además de el listado de imágenes pero ya más avanzado el desarrollo decidí que es mejor tener todas las imágenes en esa relación pero con un boolean que indique cuál de todas estas es la principal.

## CONSTRUCCIÓN

Cada sub-carpeta en el repositorio, para catalog-product y catalog-product-front, contiene su propio Dockerfile para la construcción y posterior ejecución.

### BACK

1. Se debe construir una imagen con el siguiente comando
 `docker build -t <<NOMBRE_IMAGEN>> .`
donde <<NOMBRE_IMAGEN>> se refiere al nombre que se le dará al contenedor para su posterior ejecución con:
`docker run -p 8080:8080 <<NOMBRE_IMAGEN>>`

>El puerto no está limitado a sólo ser 8080 pero sí el proyecto en front se espera que esté la API corriendo en http://localhost:8080

### FRONT 
1. Se debe construir una imagen con el siguiente comando
`docker build -t <<NOMBRE_IMAGEN>> .`
donde <<NOMBRE_IMAGEN>> se refiere al nombre que se le dará al contenedor para su posterior ejecución con:
`docker run -p 8080:8080 <<NOMBRE_IMAGEN>>`

> esta artefacto está esperando que la API esté sobre http://localhost:8080, en caso de esto cambiar, se debe actualizar desde el código fuente en los archivos environment del proyecto. 

## EXTRAS
Si revisan el Dockerfile del proyecto Spring, se darán cuenta que está la bandera `-DskipsTests` la razón fue que a pesar de que las pruebas estuviesen funcionando correctamente al ejecutarlas de manera unitaria durante el desarrollo no logré hacerlas funcionar en el entorno de ejecución de maven. Investigué y tiene que ver con H2 pero en pro del tiempo no pude darle mayor investigación.

Situación similar es las pruebas unitarias para Angular que a base de mi desconocimiento pude investigar y entender un poco como las implementan pero por temas de tiempo preferí enfocarme a que el sistema funcione bien y en su base y prescindir por las pruebas por el momento. No quiere decir que no lo vaya a seguir estudiando. 
