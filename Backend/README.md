# Sistema de Parqueadero

Este es un sistema de gestión de parqueadero desarrollado con Spring Boot y base de datos H2. El sistema permite gestionar el ingreso y salida de vehículos, calcular tarifas y generar reportes de ganancias.

## Características

- Gestión de dos tipos de vehículos:
  - Motocicletas: Tarifa de 62 pesos por hora
  - Vehículos Ligeros: Tarifa de 120 pesos por hora
- Capacidad limitada:
  - 6 plazas para motocicletas
  - 5 plazas para vehículos ligeros
- Descuento del 25% para vehículos eléctricos o híbridos
- Registro completo de información de vehículos
- Cálculo automático de tarifas
- Generación de reportes de ganancias

## Requisitos

- Java 17
- Maven
- Spring Boot 3.2.3

## Configuración

1. Clonar el repositorio
2. Ejecutar `mvn clean install`
3. Iniciar la aplicación con `mvn spring-boot:run`

La aplicación estará disponible en `http://localhost:8080`

## Acceso a la Base de Datos H2

La consola H2 está disponible en `http://localhost:8080/h2-console`

Credenciales:
- JDBC URL: `jdbc:h2:mem:parkingdb`
- Username: `sa`
- Password: `password`

## API Endpoints

### Registrar Vehículo
```
POST /api/parking/register
```

Cuerpo de la petición:
```json
{
    "licensePlate": "ABC123",
    "type": "MOTORCYCLE",
    "hasDiscount": true
}
```

Respuesta:
```json
{
    "id": 1,
    "licensePlate": "ABC123",
    "type": "MOTORCYCLE",
    "hasDiscount": true,
    "entryTime": "2024-04-25T10:00:00",
    "exitTime": null,
    "assignedSpot": "M1"
}
```

### Actualizar Vehículo
```
PUT /api/parking/{id}
```

Cuerpo de la petición:
```json
{
    "licensePlate": "ABC123",
    "type": "MOTORCYCLE",
    "hasDiscount": true,
    "entryTime": "2024-04-25T10:00:00",
    "exitTime": "2024-04-25T12:00:00"
}
```

### Eliminar Vehículo
```
DELETE /api/parking/{id}
```

### Listar Todos los Vehículos
```
GET /api/parking
```

### Obtener Vehículo por ID
```
GET /api/parking/{id}
```

### Obtener Ganancias Totales
```
GET /api/parking/earnings
```

Respuesta:
```json
1200.50
```

### Forzar Salida de Todos los Vehículos
```
POST /api/parking/force-exit
```

## Manejo de Errores

El sistema maneja los siguientes casos de error:

1. No hay espacios disponibles:
```json
{
    "error": "No spots available for MOTORCYCLE"
}
```

2. Vehículo ya está estacionado:
```json
{
    "error": "Vehicle is already parked"
}
```

3. Vehículo no encontrado:
```json
{
    "error": "Vehicle not found"
}
```

## Ejemplos de Uso

### Registrar una Motocicleta
```bash
curl -X POST http://localhost:8080/api/parking/register \
-H "Content-Type: application/json" \
-d '{
    "licensePlate": "MOT123",
    "type": "MOTORCYCLE",
    "hasDiscount": true
}'
```

### Registrar un Vehículo Ligero
```bash
curl -X POST http://localhost:8080/api/parking/register \
-H "Content-Type: application/json" \
-d '{
    "licensePlate": "CAR456",
    "type": "LIGHT_VEHICLE",
    "hasDiscount": false
}'
```

### Obtener Ganancias
```bash
curl -X GET http://localhost:8080/api/parking/earnings
```

## Cálculo de Tarifas

- Motocicleta normal: 62 pesos/hora
- Motocicleta con descuento: 46.5 pesos/hora (25% descuento)
- Vehículo ligero normal: 120 pesos/hora
- Vehículo ligero con descuento: 90 pesos/hora (25% descuento)

El tiempo se redondea hacia arriba a la hora más cercana.

## Contribución

1. Fork el proyecto
2. Crear una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abrir un Pull Request

## Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles. 