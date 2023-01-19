package com.example.demo.model.repository;

import com.example.demo.model.entity.House;
import com.example.demo.model.enums.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {

    List<House> findAllByMaterial(MaterialType material);

    @Query("select house from House house where house.material = :material")
    List<House> getHouses(@Param("material") MaterialType material);

    @Query(value = "select * from houses where houses.material = :material", nativeQuery = true)
    List<House> getHousesNative(@Param("material") MaterialType material);

}
