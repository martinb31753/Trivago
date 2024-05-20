# Proyecto de Desarrollo de API para Agencia de Turismo "Trivago"

## Descripción

Este proyecto consiste en el desarrollo de una API para una agencia de turismo que permitirá a los usuarios realizar consultas y reservas de hoteles y vuelos. La agencia desea una solución eficiente y escalable que pueda ser utilizada para futuras mejoras incrementales.

## Requerimientos Técnicos Funcionales

### Hoteles

- *Listado de Hoteles*
- *Búsqueda de Hoteles Disponibles*: 
  - Filtros: Fecha Entrada, Fecha Salida, Destino
- *Reserva de Hotel*: 
  - Campos: Fecha Entrada, Fecha Salida, Destino, Cantidad de Personas, Tipo de Habitación, métodos de pago

### Vuelos

- *Listado de Vuelos*
- *Búsqueda de Vuelos Disponibles*: 
  - Filtros: Fecha Ida, Fecha Vuelta, Origen, Destino
- *Reserva de Vuelo*: 
  - Campos: Fecha Ida, Fecha Vuelta, Origen, Destino, Cantidad de Personas, métodos de pago

### Definición Consensuada con el Equipo

Un usuario cuando reserve un hotel lo hará por el total de días que el mismo esté disponible.

## Tecnologías

- Lenguaje de Programación: Java
- Framework: Spring Boot
- Build Tool: Maven
- Base de Datos: [Json - Se espera implementar base de datos relacional en los siguientes Sprint]

## Colaboradores

Equipo 3:

- Abril Ramos
- Joaquin Fiorio
- Imanol Martinez
- Martin Botta
- Melisa Vande Velde

## Links Útiles

- [Trello](https://trello.com/b/fc2sAitn/trivago)


# Documentación de la API
## Endpoint para hoteles

GET /api/v1/hotels

GET /api/v1/filterHotels

POST /api/v1/booking

## Endpoint para vuelos

GET /api/v1/flights

GET /api/v1/flightsByDate

POST /api/v1/flight-reservation

