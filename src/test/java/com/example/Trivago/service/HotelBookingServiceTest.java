package com.example.Trivago.service;



import com.example.Trivago.DTO.HotelDTO;
import com.example.Trivago.DTO.PaymentMethodDTO;
import com.example.Trivago.DTO.PersonDTO;
import com.example.Trivago.DTO.Request.BookingRequestDTO;
import com.example.Trivago.DTO.Request.BookingRequestDetailDTO;
import com.example.Trivago.DTO.Response.BookingResponseDTO;
import com.example.Trivago.DTO.Response.BookingResponseDetailDTO;
import com.example.Trivago.DTO.Response.ResponseStatusDTO;
import com.example.Trivago.Exception.InvalidBookingHotel;
import com.example.Trivago.Model.Hotel;
import com.example.Trivago.Repository.HotelRepositoryImpl;
import com.example.Trivago.Service.HotelBookingServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class HotelBookingServiceTest {

    @Mock
    private HotelRepositoryImpl hotelRepository;

    @InjectMocks
    private HotelBookingServiceImpl hotelBookingService;

    private static final BookingRequestDTO bookinRequest = new BookingRequestDTO(
            "juanperez@gmail.com",
            new BookingRequestDetailDTO(
                    LocalDate.parse("10-02-2025", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                    LocalDate.parse("20-03-2025", DateTimeFormatter.ofPattern("dd-MM-yyyy")),

                    "Puerto Iguazú",
                    "CH-0002",
                    2,
                    "DOUBLE",
                    List.of(
                            new PersonDTO("12345678", "Juan", "Perez", LocalDate.parse("10-11-1982", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "juanperez@gmail.com"),
                            new PersonDTO("87654321", "Maria", "Lopez", LocalDate.parse("01-05-1985", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "marialopez@gmail.com")
                    ),
                    new PaymentMethodDTO("CREDIT", "1234-1234-1234-1234", 6)
            )
    );

    private static final BookingRequestDTO bookinRequestDTO2 = new BookingRequestDTO(
            "juanperez@gmail.com",
            new BookingRequestDetailDTO(
                    LocalDate.parse("10-02-2025", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                    LocalDate.parse("20-03-2025", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                    "Cataratas Hotel",

                    "CH-0002",
                    3,
                    "DOBLE",
                    List.of(
                            new PersonDTO("12345678", "Juan", "Perez", LocalDate.parse("10-11-1982", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "juanperez@gmail.com"),
                            new PersonDTO("87654321", "Maria", "Lopez", LocalDate.parse("01-05-1985", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "marialopez@gmail.com")
                    ),
                    new PaymentMethodDTO("CREDIT", "1234-1234-1234-1234", 6)
            )
    );


    private static final BookingResponseDTO bookingResponseDTO1 = new BookingResponseDTO(
            "juanperez@gmail.com", // userName
            63000.0, // amount
            5.5, // interest
            66465.0, // total
            new BookingResponseDetailDTO(

                    LocalDate.parse("10-02-2025", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                    LocalDate.parse("20-03-2025", DateTimeFormatter.ofPattern("dd-MM-yyyy")),

                    "Puerto Iguazú",

                    "CH-0002", // hotelCode
                    2, // people_amount
                    "DOUBLE", // roomType
                    List.of(
                            new PersonDTO("12345678", "Juan", "Perez", LocalDate.parse("10-11-1982", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "juanperez@gmail.com"),
                            new PersonDTO("87654321", "Maria", "Lopez", LocalDate.parse("01-05-1985", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "marialopez@gmail.com")
                    )

            ),

            new ResponseStatusDTO(201,"El proceso termino satisfactoriamente") // statusDTO
    );

    public static final Hotel hotel1 = new Hotel(
            "CH-0002",
            "Cataratas Hotel",
            "Puerto Iguazú",
            "Double",
            "$6300.00", // Insert the appropriate amount value here
            LocalDate.parse("10-02-2025", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
            LocalDate.parse("20-03-2025", DateTimeFormatter.ofPattern("dd-MM-yyyy")),

            false );

    public static final Hotel hotel2 = new Hotel(
            "HH-0002",
            "Cataratas Hotel",
            "Puerto Iguazú",
            "Double",
            "$6300.00",
            LocalDate.parse("10-02-2025", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
            LocalDate.parse("20-03-2025", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
            false );

    public static final PaymentMethodDTO paymentMethodDTODebitoOK = new PaymentMethodDTO("DEBIT", "1234-1234-1234-1234", 1);
    public static final PaymentMethodDTO paymentMethodDTOCreditoOK = new PaymentMethodDTO("CREDIT", "1234-1234-1234-1234", 6);
    public static final PaymentMethodDTO paymentMethodDTOCreditoOK2 = new PaymentMethodDTO("CREDIT", "1234-1234-1234-1234", 16);
    public static final PaymentMethodDTO paymentMethodDTOTestNOK = new PaymentMethodDTO("CREDITX", "1234-1234-1234-1234", 6);


    @Test
    public void BookHotelResposeOk(){
        when(hotelRepository.getById(hotel1.getHotelCode())).thenReturn(hotel1);
        BookingResponseDTO obtenido = hotelBookingService.bookHotelresponse(bookinRequest);
        assertEquals (bookingResponseDTO1, obtenido);
    }
    @Test
    public void BookHotelResposeFail(){
        when(hotelRepository.getById(hotel2.getHotelCode())).thenReturn(hotel2);
        Assertions.assertThrows(RuntimeException.class, () -> hotelBookingService.bookHotelresponse(bookinRequest));



    }

//    @Test
//    public void bookHotelResponseInvalidPeopleAmount() {
//        // Crear una solicitud con más de 5 personas
//        BookingRequestDTO requestDTO = new BookingRequestDTO(
//                "juanperez@gmail.com",
//                new BookingRequestDetailDTO(
//                        LocalDate.parse("10-02-2025", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
//                        LocalDate.parse("20-03-2025", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
//                        "Puerto Iguazú",
//                        "CH-0002",
//                        6, // Más de 5 personas
//                        "DOUBLE",
//                        List.of(
//                                new PersonDTO("12345678", "Juan", "Perez", LocalDate.parse("10-11-1982", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "juanperez@gmail.com"),
//                                new PersonDTO("87654321", "Maria", "Lopez", LocalDate.parse("01-05-1985", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "marialopez@gmail.com")
//                        ),
//                        new PaymentMethodDTO("CREDIT", "1234-1234-1234-1234", 6)
//                )
//        );
//
//        // Configurar el mock del repositorio para devolver un hotel válido
//        Hotel hotel = new Hotel(
//                "CH-0002",
//                "Cataratas Hotel",
//                "Puerto Iguazú",
//                "Double",
//                "$6300.00",
//                LocalDate.parse("10-02-2025", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
//                LocalDate.parse("20-03-2025", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
//                false
//        );
//        when(hotelRepository.getById(requestDTO.getBooking().getHotelCode())).thenReturn(hotel);
//
//        // Verificar que se lance la excepción InvalidBookingHotel
//        Assertions.assertThrows(InvalidBookingHotel.class, () -> hotelBookingService.bookHotelresponse(requestDTO));
//    }
//
//
}

