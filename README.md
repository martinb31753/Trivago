# Proyecto de Desarrollo de API para Agencia de Turismo "Trivago"

[](https://github.com/martinb31753/Trivago#proyecto-de-desarrollo-de-api-para-agencia-de-turismo-trivago)

## Descripción

[](https://github.com/martinb31753/Trivago#descripci%C3%B3n)

Este proyecto consiste en el desarrollo de una API para una agencia de turismo que permitirá a los usuarios realizar consultas y reservas de hoteles y vuelos. La agencia desea una solución eficiente y escalable que pueda ser utilizada para futuras mejoras incrementales.

## Requerimientos Técnicos Funcionales SPRINT I

[](https://github.com/martinb31753/Trivago#requerimientos-t%C3%A9cnicos-funcionales)

### Hoteles

[](https://github.com/martinb31753/Trivago#hoteles)

-   _Listado de Hoteles_
-   _Búsqueda de Hoteles Disponibles_:
  -   Filtros: Fecha Entrada, Fecha Salida, Destino
-   _Reserva de Hotel_:
  -   Campos: Fecha Entrada, Fecha Salida, Destino, Cantidad de Personas, Tipo de Habitación, métodos de pago

### Vuelos

[](https://github.com/martinb31753/Trivago#vuelos)

-   _Listado de Vuelos_
-   _Búsqueda de Vuelos Disponibles_:
  -   Filtros: Fecha Ida, Fecha Vuelta, Origen, Destino
-   _Reserva de Vuelo_:
  -   Campos: Fecha Ida, Fecha Vuelta, Origen, Destino, Cantidad de Personas, métodos de pago

### Definición Consensuada con el Equipo

[](https://github.com/martinb31753/Trivago#definici%C3%B3n-consensuada-con-el-equipo)

Un usuario cuando reserve un hotel lo hará por el total de días que el mismo esté disponible.

# Desarrollo con Spring Boot + Testing y Validaciones

## Especificación de Requerimientos Funcionales SPRINT II

### User Stories

La lista de requerimientos que habían sido solicitados por la Agencia de turismo eran los siguientes:

- **US 0001:** Obtener un listado de todos los hoteles registrados.
- **US 0002:** Obtener un listado de todos los hoteles disponibles en un determinado rango de fechas y según el destino seleccionado.
- **US 0003:** Realizar una reserva de un hotel, indicando cantidad de personas, fecha de entrada, fecha de salida y tipo de habitación. Obtener como respuesta el monto total de la reserva realizada.
- **US 0004:** Obtener un listado de todos los vuelos registrados.
- **US 0005:** Obtener un listado de todos los vuelos disponibles en un determinado rango de fechas y según el destino y el origen seleccionados.
- **US 0006:** Realizar una reserva de un vuelo, indicando cantidad de personas, origen, destino y fecha de ida. Obtener como respuesta el monto total de la reserva realizada.

### Validaciones

#### 1.1 Hoteles

**US 0002:** Obtener un listado de todos los hoteles disponibles en un determinado rango de fechas y según el destino seleccionado.

**Validaciones:**
| Parámetro      | Validación                                      | Mensaje de error                              |
|----------------|-------------------------------------------------|-----------------------------------------------|
| Fecha Entrada  | Formato correcto.                               | Formato de fecha debe ser dd-mm-aaaa          |
|                | Fecha de entrada < a fecha de salida.           | La fecha de entrada debe ser menor a la de salida |
| Fecha Salida   | Formato correcto.                               | Formato de fecha debe ser dd-mm-aaaa          |
|                | Fecha de salida > a fecha de entrada.           | La fecha de salida debe ser mayor a la de entrada |
| Destino        | Que exista.                                     | El destino elegido no existe                  |

**US 0003:** Realizar una reserva de un hotel, indicando cantidad de personas, fecha de entrada, fecha de salida y tipo de habitación. Obtener como respuesta el monto total de la reserva realizada.

**Validaciones:**

| Parámetro         | Validación                                                                  | Mensaje de error / Status Code                                      |
|-------------------|-----------------------------------------------------------------------------|---------------------------------------------------------------------|
| Fecha Entrada     | Formato correcto.                                                           | Formato de fecha debe ser dd-mm-aaaa                                |
|                   | Fecha de entrada < a fecha de salida.                                       | La fecha de entrada debe ser menor a la de salida                   |
| Fecha Salida      | Formato correcto.                                                           | Formato de fecha debe ser dd-mm-aaaa                                |
|                   | Fecha de salida > a fecha de entrada.                                       | La fecha de salida debe ser mayor a la de entrada                   |
| Destino           | Que exista.                                                                 | El destino elegido no existe                                        |
| Cantidad de Personas | Que sea un valor numérico.                                               | La cantidad de personas debe ser un valor numérico.                 |
| Tipo de Habitación | Que coincida con la cantidad de personas.                                  | El tipo de habitación seleccionada no coincide con la cantidad de personas que se alojarán en ella. |
| E-mail            | Que el mail cumpla con el formato de correo electrónico nombre@dominio.com. | Por favor ingrese un e-mail válido                                  |
| Intereses         | En caso de tarjeta de crédito verificar recargo de intereses.               | Tarjeta de crédito: Devolver porcentaje y monto de interés (recargo). |
|                   | En caso de tarjeta de débito verificar que no se incorporen intereses.       | Tarjeta de débito: Informar que se ha ingresado una cantidad de cuotas diferente a 1. |
Nota: Tener en cuenta que el tipo de habitación debe coincidir con la cantidad de personas que se alojarán en ella. Por ejemplo: tres personas necesitarán una habitación triple.

#### 1.2 Vuelos

**US 0005:** Obtener un listado de todos los vuelos disponibles en un determinado rango de fechas y según el destino y el origen seleccionados.

**Validaciones:**

| Parámetro      | Validación                                      | Mensaje de error                              |
|----------------|-------------------------------------------------|-----------------------------------------------|
| Fecha Ida      | Formato correcto.                               | Formato de fecha debe ser dd-mm-aaaa          |
|                | Fecha de ida < a fecha de vuelta.               | La fecha de ida debe ser menor a la de vuelta |
| Fecha Vuelta   | Formato correcto.                               | Formato de fecha debe ser dd-mm-aaaa          |
|                | Fecha de vuelta > a fecha de ida.               | La fecha de vuelta debe ser mayor a la de ida |
| Origen         | Que exista.                                     | El origen elegido no existe                   |
| Destino        | Que exista.                                     | El destino elegido no existe                  |

**US 0006:** Realizar una reserva de un vuelo, indicando cantidad de personas, origen, destino, fecha de ida y fecha de vuelta. Obtener como respuesta el monto total de la reserva realizada.

**Validaciones:**

| Parámetro         | Validación                                                                  | Mensaje de error                               |
|-------------------|-----------------------------------------------------------------------------|-----------------------------------------------|
| Fecha Ida         | Formato correcto.                                                           | Formato de fecha debe ser dd-mm-aaaa          |
|                   | Fecha de ida < a fecha de vuelta.                                           | La fecha de ida debe ser menor a la de vuelta |
| Fecha Vuelta      | Formato correcto.                                                           | Formato de fecha debe ser dd-mm-aaaa          |
|                   | Fecha de vuelta > a fecha de ida.                                           | La fecha de vuelta debe ser mayor a la de ida |
| Destino           | Que exista.                                                                 | El destino elegido no existe                  |
| Cantidad de Personas | Que sea un valor numérico.                                               | La cantidad de personas debe ser un valor numérico.                 |
| E-mail            | Que el mail cumpla con el formato de correo electrónico nombre@dominio.com. | Por favor ingrese un e-mail válido            |
| Intereses         | En caso de tarjeta de crédito verificar recargo de intereses.               | Tarjeta de crédito: Devolver porcentaje y monto de interés (recargo). |
|                   | En caso de tarjeta de débito verificar que no se incorporen intereses.       | Tarjeta de débito: Informar que se h

### Tests Unitarios

A continuación se sugiere una serie de test unitarios a llevar a cabo; sin embargo, en caso de que se considere necesario implementar otros, esto es totalmente viable. Nota: Tener en cuenta que los datos de entrada pueden variar dependiendo del modelado que haya sido realizado en la primera instancia. En caso de corresponder, realizar las modificaciones/adaptaciones correspondientes en los tests unitarios.

| User Story  | Situaciones/Datos de entrada                         | Comportamiento Esperado                                                                                                                                              |
|-------------|------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **US-0001** | Se envía solicitud de listado de todos los hoteles registrados. | Si hay hoteles registrados: Permite continuar con normalidad y muestra listado completo. Si no hay hoteles: Notifica la no existencia mediante una excepción.       |
| **US-0002** | Se envía solicitud de listado de todos los hoteles disponibles en determinado rango de fechas y destinos. Datos de entrada: Fecha Desde, Fecha Hasta, Destino | Si hay registros que cumplan el criterio: Se debe obtener un listado de los hoteles disponibles en ese rango de fechas en esos destinos. No se cumple: Notifica la situación mediante una excepción. |
| **US-0003** | Se envía solicitud de reserva de un hotel. Datos de entrada: Id hotel, Cantidad de personas, Fecha Entrada, Fecha Salida, Tipo de habitación | Se cumplen todos los criterios: Responde un Status code 200 con el monto total de la reserva. Da de alta una nueva reserva. No se cumple: Notifica error/imposibilidad de finalizar la transacción. |
| **US-0004** | Se envía solicitud de listado de todos los vuelos registrados. | Si hay vuelos registrados: Permite continuar con normalidad y muestra listado completo. Si no hay vuelos registrados: Notifica la no existencia mediante una excepción. |
| **US-0005** | Se envía solicitud de listado de todos los vuelos disponibles en determinado rango de fechas y según un origen y destino. Datos de entrada: Fecha Desde, Fecha Hasta, Origen, Destino | Si hay registros que cumplan el criterio: Se debe obtener un listado de los vuelos disponibles en ese rango de fechas en esos destinos. No se cumple: Notifica la situación mediante una excepción. |
| **US-0006** | Se envía solicitud de reserva de un vuelo. Datos de entrada: Id vuelo, Cantidad de personas, Origen, Destino, Fecha de ida | Se cumplen todos los criterios: Responde un Status code 200 con el monto total de la reserva. Da de alta una nueva reserva de vuelo. No se cumple: Notifica error/imposibilidad de finalizar la transacción. |

### Definición Consensuada con el Equipo
Se decide trabajar en conjunto las validaciones y los test unitarios de las US requeridas.

## Colaboradores

[](https://github.com/martinb31753/Trivago#colaboradores)

Equipo 3:

-   Abril Ramos
-   Joaquin Fiorio
-   Imanol Martinez
-   Martin Botta
-   Melisa Vande Velde

## Links Útiles

[](https://github.com/martinb31753/Trivago#links-%C3%BAtiles)

-   [Trello](https://trello.com/b/fc2sAitn/trivago)

## Tecnologías

[](https://github.com/martinb31753/Trivago#tecnolog%C3%ADas)

-   Lenguaje de Programación: Java
-   Framework: Spring Boot
-   Dependencias: Jakarta - Hibernate- JUnit y Mockito
-   Build Tool: Maven
-   Base de Datos: [Json - Se espera implementar base de datos relacional en los siguientes Sprint]

# Documentación de la API

[](https://github.com/martinb31753/Trivago#documentaci%C3%B3n-de-la-api)

## Endpoint para hoteles

[](https://github.com/martinb31753/Trivago#endpoint-para-hoteles)

GET /api/v1/hotels

GET /api/v1/hotels?date_from=dd-mm-aaaa&date_to=dd-mm-aaaa&destination=Puerto Iguazu

POST /api/v1/booking

## Endpoint para vuelos

[](https://github.com/martinb31753/Trivago#endpoint-para-vuelos)

GET /api/v1/flights

GET /api/v1/flights?date_from=dd-mm-aaaa&date_to=dd-mm-aaaa&origin=Buenos Aires&destination=Puerto Iguazú

POST /api/v1/flight-reservation