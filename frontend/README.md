# Sistema de Parqueadero - Frontend

Este es el frontend del Sistema de Parqueadero, desarrollado con Angular 17.3.0. Proporciona una interfaz de usuario moderna y responsiva para gestionar el parqueadero.

## Características

- Registro de vehículos (motocicletas y vehículos ligeros)
- Gestión de descuentos para vehículos eléctricos/híbridos
- Visualización de vehículos estacionados
- Cálculo automático de tarifas
- Forzar salida de todos los vehículos
- Visualización de ganancias totales

## Requisitos Previos

- Node.js (versión 18 o superior)
- npm (incluido con Node.js)
- Angular CLI (versión 17.3.0 o superior)

## Instalación

1. Clonar el repositorio:
```bash
git clone [URL_DEL_REPOSITORIO]
cd parking-system/frontend
```

2. Instalar dependencias:
```bash
npm install
```

## Configuración

El frontend está configurado para conectarse con el backend en `http://localhost:8080`. Si necesitas cambiar esta URL, modifica la variable `apiUrl` en el archivo `src/app/services/vehicle.service.ts`.

## Ejecución

Para iniciar el servidor de desarrollo:

```bash
ng serve
```

La aplicación estará disponible en `http://localhost:4200`.

## Estructura del Proyecto

```
frontend/
├── src/
│   ├── app/
│   │   ├── components/
│   │   │   └── vehicle-list/
│   │   │       ├── vehicle-list.component.ts
│   │   │       ├── vehicle-list.component.html
│   │   │       └── vehicle-list.component.scss
│   │   ├── models/
│   │   │   └── vehicle.model.ts
│   │   ├── services/
│   │   │   └── vehicle.service.ts
│   │   ├── app.component.ts
│   │   └── app.module.ts
│   └── styles.scss
├── angular.json
└── package.json
```

## Tecnologías Utilizadas

- Angular 17.3.0
- Bootstrap 5.3.3
- RxJS 7.8.0
- TypeScript 5.3.0

## Funcionalidades Principales

### Registro de Vehículos
- Formulario para ingresar placa, tipo de vehículo y descuento
- Validación de campos requeridos
- Asignación automática de espacio

### Lista de Vehículos
- Tabla con información detallada de cada vehículo
- Visualización de tiempo de entrada y salida
- Indicador de descuento aplicado
- Opción para eliminar vehículos

### Gestión de Parqueadero
- Botón para forzar salida de todos los vehículos
- Visualización de ganancias totales
- Actualización en tiempo real de la información

## Estilos y Diseño

- Interfaz responsiva usando Bootstrap
- Diseño moderno y limpio
- Componentes reutilizables
- Estilos SCSS para personalización

## Próximas Mejoras

- [ ] Implementar autenticación de usuarios
- [ ] Agregar gráficos de estadísticas
- [ ] Implementar sistema de notificaciones
- [ ] Agregar exportación de reportes
- [ ] Implementar búsqueda y filtrado de vehículos

## Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles. 