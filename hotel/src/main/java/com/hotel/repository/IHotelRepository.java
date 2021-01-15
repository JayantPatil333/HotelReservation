package com.hotel.repository;

import com.hotel.dto.HotelDTO;

import com.hotel.model.IHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHotelRepository extends JpaRepository<HotelDTO, Long> {

    /*@Query("FROM HOTEL WHERE address_id in (SELECT ADDRESS_ID FROM ADDRESS WHERE CITY = ?1)")
    List<IHotel> findByCity(String city);*/
}
