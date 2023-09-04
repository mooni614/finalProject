package com.kh.myproject.store.rentcar.repository;



import com.kh.myproject.store.rentcar.model.RentcarInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RentcarRepository extends JpaRepository<RentcarInfoEntity, Long> {


    @Query(value="SELECT r FROM RentcarInfoEntity r WHERE r.car_name = :searchKeyword")
    List<RentcarInfoEntity> FindByCar_name(@Param("searchKeyword") String searchKeyword);
}
