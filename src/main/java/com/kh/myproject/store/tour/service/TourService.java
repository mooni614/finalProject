package com.kh.myproject.store.tour.service;

import com.kh.myproject.store.tour.model.dto.TourismDto;
import com.kh.myproject.store.tour.model.entity.Tourism;
import com.kh.myproject.store.tour.repository.TourismRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TourService {

    @Autowired
    TourismRepository tourismRepository;

    public String getApiData(String url) {

        StringBuilder result = new StringBuilder();

        try {
            URL resUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) resUrl.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            br.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public List<TourismDto> getTourism(String areaName) {
        List<Tourism> list = tourismRepository.findAllByAreaName(areaName);
        List<TourismDto> dtoList = new ArrayList<>();

        for (Tourism item : list) {
            dtoList.add(item.toDto());
        }
        return dtoList;
    }
//    public City getCityInfo(String cityName) {
//        City CityInfo = new City();
//        List<String> placeName = new ArrayList<>();
//    }

}
