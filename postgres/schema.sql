CREATE SCHEMA IF NOT EXISTS notificaciones;

CREATE TABLE IF NOT EXISTS notificaciones.notificacion (
  id SERIAL PRIMARY KEY,
  usuario_id UUID NOT NULL,
  tipo VARCHAR(50) NOT NULL,
  detalle TEXT NOT NULL,
  fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE notificaciones.plantillas (
	id bigserial NOT NULL,
	tipo varchar(255) NULL,
	nombre varchar(255) NULL,
	contenido varchar(255) NULL,
	descripcion varchar(255) NULL,
	creado_en timestamp DEFAULT CURRENT_TIMESTAMP NULL,
	actualizado_en timestamp DEFAULT CURRENT_TIMESTAMP NULL,
	CONSTRAINT plantillas_pkey PRIMARY KEY (id)
);

INSERT INTO notificaciones.plantilla_notificacion (
    canal,
    tipo,
    contenido,
    descripcion,
    fecha_creacion,
    fecha_actualizacion
) VALUES (
    'email',
    'transferencia',
    'Hola {{nombre}}, transferencia de tipo {{tipo}} realizada.',
    'Plantilla para confirmar transferencias por correo.',
    '2025-04-21 20:06:43.763',
    '2025-04-21 20:06:43.763'
);