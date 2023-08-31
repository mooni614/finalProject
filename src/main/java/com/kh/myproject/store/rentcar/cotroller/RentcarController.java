package com.kh.myproject.store.rentcar.cotroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Slf4j
@SessionAttributes("user")
public class RentcarController {




/* @GetMapping("/store/rentcar/rentcarMain")
    public String rentcarMain(){

    return "store/rentcar/rentcarMain";

}*/


    @GetMapping("/store/rentcar/rentcarMain")
    public ModelAndView rentcarMain(ModelAndView mav) {

        mav.setViewName("store/rentcar/rentcarMain");

        return mav;
    }


    @GetMapping("/store/rentcar/rentcarReserve")
    public ModelAndView rentcarReserve(ModelAndView mav) {

        mav.setViewName("store/rentcar/rentcarReserve");

        return mav;
    }

    @GetMapping("/store/rentcar/rentcarChoice")
    public ModelAndView rentcarChoice(ModelAndView mav) {

        mav.setViewName("store/rentcar/rentcarChoice");

        return mav;
    }






    //main에서 검색버튼 누른 후 reserve로 넘어가는 메서드
    @GetMapping("/store/rentcar/rentcarMainSearch")
    public ModelAndView mainSearch(        @RequestParam("location") String location,
                                           @RequestParam("departDate") String departDate,
                                           @RequestParam("arriveDate") String arriveDate,
                                           @RequestParam("birthDate") String birthDate,
                                           ModelAndView mav){

        log.info("location", location);
        log.info("departDate",departDate);
        log.info("arriveDate",arriveDate);
        log.info("birthDate",birthDate);



        mav.addObject("location", location);
        mav.addObject("departDate", departDate);
        mav.addObject("arriveDate", arriveDate);
        mav.addObject("birthDate", birthDate);


        mav.setViewName("store/rentcar/thTest");

        return mav;

    }


}
