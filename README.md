# Aplicación para Médicos

#### Feature: Usuarios

**Endpoints**

 * GET     /users/       return all users
 * POST    /users/       add a new user
 * GET     /users/{id}   return a specific user
 * PUT     /users/{id}   update a specific user
 * DELETE  /users/{id}   delete a specific user
 
**Todo**

 * Habilitar swagger
 * Configurar perfiles de desarrollo, testing y producción
 * Configurar CommandLineRunner para que solo corra en desarrollo
 * Configurar Liquibase para crear el modelo de base de datos
 * Configurar desarrollo y testing con H2 y en producción usar MySQL
 * Agregar la creación de los indices en el XML de las tablas de liquibase
 
