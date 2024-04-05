# Sistema de Arrendamiento de Fincas

El Sistema de Arrendamiento de Fincas es una aplicación web diseñada para facilitar el proceso de alquiler de propiedades rurales. Ofrece una plataforma donde los propietarios pueden listar sus fincas para alquiler y los inquilinos pueden buscar y reservar propiedades adecuadas.

## Características

- Gestión de propiedades: Los propietarios pueden añadir, editar y eliminar propiedades.
- Búsqueda de fincas: Los inquilinos pueden buscar fincas disponibles basándose en varios criterios como ubicación, tamaño y precio.
- Reservas: Los usuarios pueden reservar fincas para fechas específicas.
- Administración de usuarios: Gestión de cuentas de usuario con diferentes roles y permisos.

## Tecnologías Utilizadas

- **Spring Boot**: Framework principal para la creación de la aplicación.
- **Spring MVC**: Modelo de arquitectura y patrones para implementar la interfaz de usuario.
- **Thymeleaf**: Motor de plantillas para generar vistas HTML.
- **JPA/Hibernate**: Para el mapeo objeto-relacional y la gestión de la base de datos.
- **MySQL**: Sistema de gestión de base de datos.
- **JUnit y Mockito**: Para pruebas unitarias y de integración.
- **ModelMapper**: Herramienta para mapear entre modelos de datos.
- **Maven**: Para la gestión de dependencias y la construcción del proyecto.
- **Docker**: Para contenerizar y desplegar la aplicación.
- **Jenkins**: Para la integración continua y la entrega continua del código.

## Requisitos

- Java 11 o superior.
- Maven 3.6 o superior.
- MySQL 8.0 o superior.
- Docker 19.03.0 o superior.
- Jenkins 2.249.1 o superior.

## Diccionario de Datos
### Tablas del Sistema de Gestión de Alquileres de Propiedades

#### `user`
- **id_user** (BIGINT): Identificador único del usuario.
- **email** (VARCHAR): Dirección de correo electrónico del usuario.
- **money** (INT): Cantidad de dinero asociada al usuario para transacciones.
- **name** (VARCHAR): Nombre completo del usuario.
- **password** (VARCHAR): Contraseña para acceso del usuario.
- **phone** (INT): Número de teléfono del usuario.
- **photo** (VARCHAR): Enlace a la foto de perfil del usuario.
- **status** (TINYINT): Estado del usuario en el sistema (activo, inactivo, etc.).

#### `property`
- **id_property** (BIGINT): Identificador único de la propiedad.
- **area** (INT): Área de la propiedad en metros cuadrados.
- **city** (VARCHAR): Ciudad donde se encuentra la propiedad.
- **country** (VARCHAR): País de ubicación de la propiedad.
- **latitude** (VARCHAR): Latitud geográfica de la propiedad.
- **longitude** (VARCHAR): Longitud geográfica de la propiedad.
- **name** (VARCHAR): Nombre o título de la propiedad.
- **price** (INT): Precio de alquiler de la propiedad.
- **detail_id** (BIGINT): Referencia a los detalles adicionales de la propiedad.
- **owner_id** (BIGINT): Referencia al usuario propietario de la propiedad.

#### `property_detail`
- **id_property_detail** (BIGINT): Identificador único de los detalles de la propiedad.
- **description** (VARCHAR): Descripción textual de la propiedad.
- **num_kitchens** (INT): Número de cocinas en la propiedad.
- **num_level** (INT): Número de niveles o pisos de la propiedad.
- **num_rooms** (INT): Número de habitaciones en la propiedad.
- **num_toilets** (INT): Número de baños en la propiedad.

#### `photo`
- **id_photo** (BIGINT): Identificador único de la foto.
- **description** (VARCHAR): Descripción de la foto.
- **url** (VARCHAR): Dirección URL de la foto.
- **property_id** (BIGINT): Identificador de la propiedad a la que pertenece la foto.

#### `rent`
- **id_rent** (BIGINT): Identificador único del alquiler.
- **date_end** (VARCHAR): Fecha de finalización del alquiler.
- **date_start** (VARCHAR): Fecha de inicio del alquiler.
- **num_people** (INT): Número de personas incluidas en el alquiler.
- **payment** (INT): Tipo o método de pago utilizado.
- **price** (INT): Precio total acordado para el alquiler.
- **rating_owner** (INT): Calificación otorgada al propietario por el inquilino.
- **rating_renter** (INT): Calificación otorgada al inquilino por el propietario.
- **owner_id** (BIGINT): Identificador del dueño de la propiedad.
- **renter_id** (BIGINT): Identificador del inquilino.

#### `rent_request`
- **id_rent_request** (BIGINT): Identificador único de la solicitud de alquiler.
- **approval** (INT): Estado de la aprobación de la solicitud.
- **date_end** (VARCHAR): Fecha deseada para el final del alquiler.
- **date_start** (VARCHAR): Fecha deseada para el inicio del alquiler.
- **num_people** (INT): Número de personas que participarán en el alquiler.
- **price** (INT): Precio propuesto para el alquiler.
- **owner_id** (BIGINT): Identificador del propietario de la propiedad.
- **property_id** (BIGINT): Identificador de la propiedad objeto de la solicitud.
- **renter_id** (BIGINT): Identificador del usuario que realiza la solicitud.
