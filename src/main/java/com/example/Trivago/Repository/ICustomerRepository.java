//package com.example.Trivago.Repository;
//
//import com.example.Trivago.DTO.TopClientDTO;
//import com.example.Trivago.Entity.People;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
//public interface ICustomerRepository extends JpaRepository<People,Long> {
//
//    @Query(value = "SELECT * FROM people p WHERE p.is_hotel = true AND p.year = :year ORDER BY p.total_amount DESC LIMIT 3", nativeQuery = true)
//    List<TopClientDTO> findTop3ClientsByYearHotels(@Param("year") Integer year);
//
//    @Query(value = "SELECT * FROM people p WHERE p.is_hotel = true AND p.year = :year ORDER BY p.total_amount DESC LIMIT 3", nativeQuery = true)
//    List<TopClientDTO> findTop3ClientsByYearFlights(@Param("year") Integer year);
//}