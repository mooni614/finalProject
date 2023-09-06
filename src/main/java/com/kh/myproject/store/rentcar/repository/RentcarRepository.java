package com.kh.myproject.store.rentcar.repository;



import com.kh.myproject.store.rentcar.model.RentcarInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RentcarRepository extends JpaRepository<RentcarInfoEntity, Long> {



    //차 이름으로 결과검색
    @Query(value="SELECT r FROM RentcarInfoEntity r WHERE r.car_name LIKE %:searchKeyword%")
    List<RentcarInfoEntity> FindByCar_name(@Param("searchKeyword") String searchKeyword);

    //렌트카리스트 정렬
    @Query("SELECT r FROM RentcarInfoEntity r")
    List<RentcarInfoEntity> FindAll();

    //높은 가격순 정렬
    @Query("SELECT r FROM RentcarInfoEntity r ORDER BY r.car_discount DESC")
    List<RentcarInfoEntity> FindDiscountDesc();


    //낮은 가격순 정렬
    @Query("SELECT r FROM RentcarInfoEntity r ORDER BY r.car_discount ASC")
    List<RentcarInfoEntity> FindDiscountAsc();

    //차종순 정렬
    @Query("SELECT r FROM RentcarInfoEntity r ORDER BY r.car_type ASC")
    List<RentcarInfoEntity> FindTypeAsc();


    /*
   @Query(value="select * from car_info where car_name = :car_name ORDER BY car_discount ASC limit 1", nativeQuery = true)
    RentcarInfoEntity FindDiscountOne(@Param("car_name") String car_name);
*/

    @Query(value = "SELECT * FROM car_info ci INNER JOIN com_info co ON ci.com_id = co.com_id WHERE ci.car_name =:car_name", nativeQuery = true)
    List<RentcarInfoEntity> FindCombycarname(@Param("car_name") String car_name);


    // 결제페이지에 넘길 정보
    @Query(value = "SELECT * FROM car_info ci INNER JOIN com_info co ON ci.com_id = co.com_id WHERE ci.car_info_id=:Car_info_id", nativeQuery = true)
    RentcarInfoEntity FindAllInfo(@Param("Car_info_id") Long Car_info_id);

}