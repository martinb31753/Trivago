package com.example.Trivago.Service;


import com.example.Trivago.DTO.*;
import com.example.Trivago.DTO.Request.BookingRequestDTO;
import com.example.Trivago.DTO.Response.*;
import com.example.Trivago.Entity.Customer;
import com.example.Trivago.Entity.HotelBooking;
import com.example.Trivago.Entity.People;
import com.example.Trivago.Exception.HotelNotFound;
import com.example.Trivago.Exception.InvalidBookingHotel;
import com.example.Trivago.Exception.InvalidDate;
import com.example.Trivago.Exception.InvalidDestination;
import com.example.Trivago.Entity.Hotel;
import com.example.Trivago.Repository.ICustomerRepository;
import com.example.Trivago.Repository.IHotelBookingRepository;
import com.example.Trivago.Repository.IHotelRepository;
import com.example.Trivago.Repository.IPeopleRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service

public class HotelBookingServiceImpl implements IHotelBookingService {

    @Autowired
    private IHotelRepository hotelRepository;

    @Autowired
    private IHotelBookingRepository hotelBookingRepository;

    @Autowired
    private IPeopleRepository peopleRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<AllHotelReservationsDTO> findAllHotelBooking() {
        return hotelBookingRepository.findAll().stream()
                .map(hotelBooking -> modelMapper.map(hotelBooking, AllHotelReservationsDTO.class))
                .collect(Collectors.toList());
    }
//        List<HotelBooking> hotelBookings = hotelBookingRepository.findAll();
//
//        List<HotelReservationDTO> hotelReservationDTOs = hotelBookings.stream()
//                .map(this::convertToHotelReservationDTO)
//                .collect(Collectors.toList());
//
//        return new AllHotelReservationsDTO(hotelReservationDTOs);
//    }
//
//    private HotelReservationDTO convertToHotelReservationDTO(HotelBooking hotelBooking) {
//        HotelReservationDTO dto = new HotelReservationDTO();
//        dto.setReservationId(hotelBooking.getId());
//        dto.setDateFrom(hotelBooking.getHotel().getDateFrom());
//        dto.setDateTo(hotelBooking.getHotel().getDateTo());
//        dto.setDestination(hotelBooking.getHotel().getDestination());
//        dto.setHotelCode(hotelBooking.getHotel().getHotelCode());
//        dto.setPeopleAmount(hotelBooking.getPeopleAmount());
//        dto.setRoomType(hotelBooking.getHotel().getRoomType());
//
//        Set<PersonDTO> peopleDTOs = hotelBooking.getPeople().stream()
//                .map(person -> {
//                    PersonDTO personDTO = new PersonDTO();
//                    personDTO.setDni(person.getDni());
//                    personDTO.setName(person.getName());
//                    personDTO.setLastName(person.getLastName());
//                    personDTO.setBirthDate(person.getBirthDate());
//                    personDTO.setEmail(person.getEmail());
//                    return personDTO;
//                })
//                .collect(Collectors.toSet());
//        dto.setPeople(peopleDTOs);
//
//        PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO();
//        paymentMethodDTO.setPaymentMethod(hotelBooking.getPaymentMethod());
//        paymentMethodDTO.setNumberCard(hotelBooking.getNumberCard());
//        paymentMethodDTO.setDues(hotelBooking.getDues());
//        dto.setPaymentMethodDto(paymentMethodDTO);
//
//        return dto;
//    }

    @Override
    public BookingResponseDTO bookHotelresponse(BookingRequestDTO request) {
        // Encontrar el hotel por código

        Optional<Hotel> hotelOpt = hotelRepository.getByHotelCode(request.getBooking().getHotelCode());

        Hotel hotel = hotelOpt.orElseThrow(() -> new HotelNotFound("El hotel con el codigo " + request.getBooking().getHotelCode() + " no existe"));

        LocalDate dateFrom = request.getBooking().getDateFrom();//request.getDateFrom();
        LocalDate dateTo = request.getBooking().getDateTo();//getDateTo();


        if (hotel.getIsReserved()) {
            throw new InvalidBookingHotel(hotel.getHotelCode()+ " el hotel ya fue reservado");
        }


        // noche de la doble  $6300")
        double pricePerNight = Double.parseDouble(hotel.getPricePerNight().replace("$", ""));
        long numberOfNights = dateFrom.until(dateTo).getDays();
        double amount = pricePerNight * numberOfNights;

        // Interes
        double interest = 0.0;
        double total = amount;

        if (request.getBooking().getPaymentMethod().getPaymentMethod().equalsIgnoreCase("CREDIT")) {
            if (request.getBooking().getPaymentMethod().getDues() <= 3) {
                interest = 5;
                total = amount + (amount * interest / 100);
            } else if (request.getBooking().getPaymentMethod().getDues() <= 6) {
                interest = 10;
                total = amount + (amount * interest / 100);
            } else if (request.getBooking().getPaymentMethod().getDues() <= 12) {
                interest = 15;
                total = amount + (amount * interest / 100);
            }
        }

        if (request.getBooking().getPaymentMethod().getPaymentMethod().equalsIgnoreCase("DEBIT") &&
                request.getBooking().getPaymentMethod().getDues() != 1) {
            throw new InvalidBookingHotel("La tarjeta de crédito solo acepta una cuota");
        }

        //  respuesta
        BookingResponseDetailDTO bookingDetail = new BookingResponseDetailDTO();
        if(dateFrom.isAfter(dateTo) ||
                !dateTo.isEqual(hotel.getDateTo()) ||
                !dateFrom.isEqual(hotel.getDateFrom())) {
            throw new InvalidDate("La fecha de llegada debe ser posterior a la fecha de salida " +
                    "o viceversa y además debe coincidir con las fechas disponibles del hotel");
        }

        bookingDetail.setDateFrom(dateFrom);
        bookingDetail.setDateTo(dateTo);

        if(!hotel.getDestination().equalsIgnoreCase(request.getBooking().getDestination())  ){
            throw new InvalidDestination(request.getBooking().getDestination() + " como destino es incorrecto");
        }

        bookingDetail.setDestination(request.getBooking().getDestination());

        bookingDetail.setHotelCode(request.getBooking().getHotelCode());
        if(request.getBooking().getPeopleAmount() > 5 ){
            throw new InvalidBookingHotel(hotel.getRoomType() + " No admite más de 5 personas ");
        }


        int maxCapacity = 0;
        switch (hotel.getRoomType().toLowerCase()) {
            case "single":
                maxCapacity = 1;
                break;
            case "double":
                maxCapacity = 2;
                break;
            case "triple":
                maxCapacity = 3;
                break;
            case "multiple":
                maxCapacity = 4;
                break;
            default:
                throw new InvalidBookingHotel("Tipo de habitación desconocido: " + hotel.getRoomType());
        }

        if (request.getBooking().getPeopleAmount() > maxCapacity) {
            throw new InvalidBookingHotel(hotel.getRoomType() + " no admite más de " + maxCapacity + " personas.");
        }


        bookingDetail.setPeopleAmount(request.getBooking().getPeopleAmount());

        switch (request.getBooking().getRoomType().toLowerCase()) {
            case "single":
                bookingDetail.setRoomType("Single");
                break;
            case "double":
                bookingDetail.setRoomType("Double");
                break;
            case "triple":
                bookingDetail.setRoomType("Triple");
                break;
            case "multiple":
                bookingDetail.setRoomType("Multiple");
                break;
            default:
                throw new InvalidBookingHotel("Tipo de habitación desconocido: " + request.getBooking().getRoomType());
        }


        bookingDetail.setRoomType(request.getBooking().getRoomType());
        bookingDetail.setPeople(request.getBooking().getPeople());

        // Convertir PersonDTO a People
        Set<People> people = request.getBooking().getPeople().stream()
                .map(personDTO -> modelMapper.map(personDTO, People.class))
                .collect(Collectors.toSet());

        // Buscar el Customer por userName
        Customer customer = customerRepository.FindByUserName(request.getUserName())
                .orElseThrow(() -> new InvalidBookingHotel("El usuario " + request.getUserName() + " no existe"));

        // Crear y guardar la instancia de HotelBooking
        HotelBooking hotelBooking = new HotelBooking();
        hotelBooking.setPeopleAmount(request.getBooking().getPeopleAmount());
        hotelBooking.setCustomerId(customer);
        hotelBooking.setPeople(people);
        hotelBooking.setPaymentMethod(request.getBooking().getPaymentMethod().getPaymentMethod());
        hotelBooking.setNumberCard(request.getBooking().getPaymentMethod().getNumberCard());
        hotelBooking.setDues(request.getBooking().getPaymentMethod().getDues());
        hotelBooking.setAmount(total);
        hotelBooking.setHotel(hotel);
        hotelBooking.setIsActive(true);

        hotelBooking = hotelBookingRepository.save(hotelBooking);

        // Marcar el hotel como reservado
        hotel.setIsReserved(true);
        hotelRepository.save(hotel);

        ResponseStatusDTO responseStatusDTO  = new ResponseStatusDTO();
        responseStatusDTO.setCode(201);
        responseStatusDTO.setMessage("El proceso termino satisfactoriamente");


        BookingResponseDTO response = new BookingResponseDTO();
        response.setUserName(request.getUserName());
        response.setAmount(amount);
        response.setInterest(interest);
        response.setTotal(total);
        response.setBooking(bookingDetail);
        response.setStatus(responseStatusDTO);


        return response;
    }

    @Override
    public RespuestaDTO updateHotelReservation(BookingRequestDTO editReservation, Long id) {
        HotelBooking hotelBookingEdit = hotelBookingRepository.findById(id)
        .orElseThrow(() -> new InvalidBookingHotel("No se encontró la Reserva de hotel"));
        // Actualizar manualmente las propiedades del HotelBooking
        hotelBookingEdit.setPeopleAmount(editReservation.getBooking().getPeopleAmount());

        // Actualizar Customer
        Customer customer = customerRepository.FindByUserName(editReservation.getUserName())
                .orElseThrow(() -> new InvalidBookingHotel("El usuario " + editReservation.getUserName() + " no existe"));
        hotelBookingEdit.setCustomerId(customer);

        // Convertir PersonDTO a People y actualizar la relación
        Set<People> people = editReservation.getBooking().getPeople().stream()
                .map(personDTO -> modelMapper.map(personDTO, People.class))
                .collect(Collectors.toSet());
        hotelBookingEdit.setPeople(people);

        // Actualizar PaymentMethod
        // Calcular y actualizar el monto total
        LocalDate dateFrom = editReservation.getBooking().getDateFrom();
        LocalDate dateTo = editReservation.getBooking().getDateTo();
        hotelBookingEdit.setPaymentMethod(editReservation.getBooking().getPaymentMethod().getPaymentMethod());
        hotelBookingEdit.setNumberCard(editReservation.getBooking().getPaymentMethod().getNumberCard());
        hotelBookingEdit.setDues(editReservation.getBooking().getPaymentMethod().getDues());
        double pricePerNight = Double.parseDouble(hotelBookingEdit.getHotel().getPricePerNight().replace("$", ""));
        long numberOfNights = dateFrom.until(dateTo).getDays();
        double amount = pricePerNight * numberOfNights;

        double interest = 0.0;
        double total = amount;
        if (editReservation.getBooking().getPaymentMethod().getPaymentMethod().equalsIgnoreCase("CREDIT")) {
            int dues = editReservation.getBooking().getPaymentMethod().getDues();
            if (dues <= 3) {
                interest = 5;
            } else if (dues <= 6) {
                interest = 10;
            } else if (dues <= 12) {
                interest = 15;
            }
            total = amount + (amount * interest / 100);
        }
        hotelBookingEdit.setAmount(total);

        // Guardar los cambios en la base de datos
        hotelBookingRepository.save(hotelBookingEdit);

            return new RespuestaDTO("Reserva de Hotel modificada correctamente");
    }

    public RespuestaDTO cancelBooking(Long id) {

        Optional<HotelBooking> optionalHotelBooking = hotelBookingRepository.findById(id);
        if (optionalHotelBooking.isPresent()) {
            HotelBooking hotelBooking = optionalHotelBooking.get();
            hotelBooking.setIsActive(false);
            hotelBookingRepository.save(hotelBooking);

            return new RespuestaDTO("La reserva se canceló con exito");
        }

        return new RespuestaDTO("No se encontro la reserva");
    }
}