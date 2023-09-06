package com.kh.myproject.store.flight.service;

import com.kh.myproject.store.flight.model.entity.FlightTicketInfo;
import com.kh.myproject.store.flight.repository.FlightTicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
@Slf4j
public class FlightService {

    @Autowired
    private FlightTicketRepository flightRepository;

    public void saveFlight(FlightTicketInfo TicketInfo){

        flightRepository.save(TicketInfo);
    }

    public List<FlightTicketInfo> getTicketList(Long userNum){
        return flightRepository.findByUser_UserNumber(userNum);
    }

    public void removeTicket(Long ticketId){
        flightRepository.deleteById(ticketId);
    }
    public String getFlightList(String url){

        StringBuilder result = new StringBuilder();

        try{
            URL resUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) resUrl.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while((line = br.readLine()) != null){
                result.append(line);
            }
            br.close();
            conn.disconnect();
            System.out.println(result);

        } catch(Exception e){
            e.printStackTrace();
        }

        return result.toString();
    }
}
