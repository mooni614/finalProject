package com.kh.myproject.store.rentcar.service;


import com.kh.myproject.store.rentcar.model.RentcarInfoDTO;
import com.kh.myproject.store.rentcar.model.RentcarInfoEntity;
import com.kh.myproject.store.rentcar.repository.RentcarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentcarService {

    @Autowired
    private RentcarRepository rentcarRepository;

    public List<RentcarInfoDTO> searchKeyword(String searchKeyword){

        System.out.println("searchKeyword 서비스 메서드 실행");
        System.out.println(searchKeyword);

        List<RentcarInfoEntity> entities = rentcarRepository.FindByCar_name(searchKeyword);


        return entities.stream()
                .map(RentcarInfoDTO::fromEntity)
                .collect(Collectors.toList());
    }




}
