package com.pacomolina.tipodeportes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pacomolina.tipodeportes.entity.Sport;

import java.util.List;
@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {


    List<Sport> findByType(@Param("type") String type);
    
    List<Sport> findByUserId(int id);


}

