package com.hotel.repository;

import com.hotel.entity.HotelEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHotelRepository extends JpaRepository<HotelEntity, Long> {

    /*@Query("FROM HOTEL WHERE address_id in (SELECT ADDRESS_ID FROM ADDRESS WHERE CITY = ?1)")
    List<IHotel> findByCity(String city);*/
}
