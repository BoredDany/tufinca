package web.rent.tufinca;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
class TufincaApplicationTests {

	/*@Test
	void contextLoads() {
	}*/

}

/*
Diccionario de datos (Base de datos):

- Tabla user

id_user: BIGINT - Identificador único del usuario.
email: VARCHAR - Dirección de correo electrónico del usuario.
money: INT - Cantidad de dinero asociada al usuario.
name: VARCHAR - Nombre completo del usuario.
password: VARCHAR - Contraseña del usuario.
phone: INT - Número de teléfono del usuario.
photo: VARCHAR - URL o referencia a la foto de perfil del usuario.
status: TINYINT - Estado del usuario en el sistema.

- Tabla property
id_property: BIGINT - Identificador único de la propiedad.
area: INT - Área de la propiedad.
city: VARCHAR - Ciudad donde se encuentra la propiedad.
country: VARCHAR - País donde se encuentra la propiedad.
latitude: VARCHAR - Latitud geográfica de la propiedad.
longitude: VARCHAR - Longitud geográfica de la propiedad.
name: VARCHAR - Nombre o título de la propiedad.
price: INT - Precio de alquiler de la propiedad.
detail_id: BIGINT - Identificador de detalles adicionales de la propiedad.
owner_id: BIGINT - Identificador del dueño de la propiedad.

- Tabla property_detail
id_property_detail: BIGINT - Identificador único de los detalles de la propiedad.
description: VARCHAR - Descripción detallada de la propiedad.
num_kitchens: INT - Número de cocinas.
num_level: INT - Número de niveles o pisos.
num_rooms: INT - Número de habitaciones.
num_toilets: INT - Número de baños.

- Tabla photo
id_photo: BIGINT - Identificador único de la foto.
description: VARCHAR - Descripción o título de la foto.
url: VARCHAR - URL de la foto.
property_id: BIGINT - Identificador de la propiedad asociada.

- Tabla rent
id_rent: BIGINT - Identificador único del alquiler.
date_end: VARCHAR - Fecha de finalización del alquiler.
date_start: VARCHAR - Fecha de inicio del alquiler.
num_people: INT - Número de personas incluidas en el alquiler.
payment: INT - Tipo o método de pago.
price: INT - Precio acordado para el alquiler.
rating_owner: INT - Calificación del dueño.
rating_renter: INT - Calificación del inquilino.
owner_id: BIGINT - Identificador del dueño.
renter_id: BIGINT - Identificador del inquilino.

- Tabla rent_request
id_rent_request: BIGINT - Identificador único de la solicitud de alquiler.
approval: INT - Estado de aprobación de la solicitud.
date_end: VARCHAR - Fecha deseada para fin del alquiler.
date_start: VARCHAR - Fecha deseada para inicio del alquiler.
num_people: INT - Número de personas para el alquiler.
price: INT - Precio ofrecido o esperado.
owner_id: BIGINT - Identificador del dueño.
property_id: BIGINT - Identificador de la propiedad.
renter_id: BIGINT - Identificador del solicitante.

 */