package com.guest.repository;


import com.guest.dto.GuestDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGuestRepository extends CrudRepository<GuestDTO, Long> {

}
