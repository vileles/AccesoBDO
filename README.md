# Acceso a Base de Datos Oracle - Aplicación en Java

Este proyecto consiste en una aplicación en Java que accede a una base de datos Oracle de una aerolínea. La base de datos incluye las tablas `VUELOS` y `PASAJEROS`, cuya implementación se encuentra en el archivo adjunto.

## Funcionalidades

La aplicación consta de las siguientes clases principales:

1. **Mostrar y pedir información general de la base de datos:**
   - Esta funcionalidad permite obtener y mostrar información general sobre la base de datos.

2. **Mostrar la información de la tabla `PASAJEROS`:**
   - Permite visualizar la información almacenada en la tabla `PASAJEROS`.

3. **Ver la información de los pasajeros de un vuelo:**
   - Se puede consultar la información de los pasajeros de un vuelo específico, pasando el código de vuelo como parámetro.

4. **Insertar un vuelo:**
   - Permite añadir un nuevo vuelo a la base de datos, pasando los valores necesarios como parámetros.

5. **Borrar un vuelo:**
   - Permite eliminar un vuelo específico de la base de datos, utilizando el número de vuelo como parámetro.

6. **Modificar vuelos de fumadores a no fumadores:**
   - Esta funcionalidad modifica los vuelos que originalmente permitían fumar, convirtiéndolos en vuelos no fumadores.

## Implementación

El proyecto utiliza Java para la lógica de la aplicación y se conecta a la base de datos Oracle utilizando JDBC (Java Database Connectivity).

---

Este proyecto proporciona una interfaz de línea de comandos para interactuar con la base de datos de una aerolínea, facilitando operaciones como consulta, inserción, eliminación y modificación de datos relacionados con vuelos y pasajeros. Si tienes alguna pregunta o necesitas ayuda, no dudes en contactarnos.

