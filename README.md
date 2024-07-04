# microservicio_usuario_deportes
Se trata de una aplicación la cual se encarga de registrar usuarios en una base de datos mysql y también un microservicio encargado de generar los tipos de deporte, todo esto está conectado a través del 
microservicio de usuarios que es el encargado de llamar el microservicio de tipos de deportes. En la base de datos de usuarios se guardan como son los datos personales y también el tipo de deporte al que está inscrito.
Como he mencionado anteriormente, son bases de datos totalmente independientes.
Es una estructura como he comentado en base a microservicios, ya que no tiene front aún aplicado, pero se irá mejorando la aplicación seguramente con Angular, para que tenga una buena experiencia de usuario.

## Tipo de tecnologías usadas
- Java
- Spring boot
- Spring Cloud (Api Gateway)
- Eureka server
- Mysql
