package com.kh.myproject.store.rentcar.cotroller;

import com.kh.myproject.store.rentcar.model.RentcarComDTO;
import com.kh.myproject.store.rentcar.model.RentcarInfoDTO;
import com.kh.myproject.store.rentcar.model.RentcarInfoEntity;
import com.kh.myproject.store.rentcar.repository.RentcarRepository;
import com.kh.myproject.store.rentcar.service.RentcarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
@Slf4j
@SessionAttributes("user")
public class RentcarController {

    @Autowired
    private RentcarService rentcarService;

    @Autowired
    private RentcarRepository rentcarRepository;


    @GetMapping("/store/rentcar/rentcarMain")
    public String rentcarMain() {

        return "store/rentcar/rentcarMain";

    }




    /*
        @GetMapping("/store/rentcar/rentcarReserve")
        public String rentcarReserve() {

            return "store/rentcar/rentcarReserve";

        }



        @GetMapping("/store/rentcar/rentcarChoice")
        public String rentcarChoice() {

            return "store/rentcar/rentcarChoice";

        }
 */
    @RequestMapping("/MainSearch") // http://localhost:8080/store/rentcar/MainSearch
    public String sample(@RequestParam String input_location,
                         @RequestParam String depart_date,
                         @RequestParam String arrive_date,
                         @RequestParam String input_birth,
                         HttpSession session) {

        System.out.println(input_location);
        System.out.println(depart_date);
        System.out.println(arrive_date);
        System.out.println(input_birth);

        session.setAttribute("input_location", input_location);
        session.setAttribute("depart_date", depart_date);
        session.setAttribute("arrive_date", arrive_date);
        session.setAttribute("input_birth", input_birth);

        return "store/rentcar/rentcarReserve";

    }


/*

    @GetMapping("/store/rentcar/thTest")
    public String thttt(){

        return "store/rentcar/thTest";
    }

*/


    //키워드 검색결

    @GetMapping("/rentcarReserve")
    public ResponseEntity<List<RentcarInfoDTO>> reserveSearch(@RequestParam(value = "searchKeyword") String searchKeyword) {

        System.out.println("reserveSearch 컨트롤러 메서드 실행");
        System.out.println(searchKeyword);

        List<RentcarInfoDTO> rentcarList = rentcarService.searchKeyword(searchKeyword);

        return ResponseEntity.ok(rentcarList);


    }

    @GetMapping("/highPriceList")
    public ResponseEntity<List<RentcarInfoDTO>> highPriceList() {


        List<RentcarInfoDTO> rentcarList = rentcarService.FindDiscountDesc(rentcarService.findAll());

        return ResponseEntity.ok(rentcarList);
    }


    @GetMapping("/lowPriceList")
    public ResponseEntity<List<RentcarInfoDTO>> lowPriceList() {


        List<RentcarInfoDTO> rentcarList = rentcarService.FindDiscountAsc(rentcarService.findAll());

        return ResponseEntity.ok(rentcarList);
    }

    @GetMapping("/carTypeList")
    public ResponseEntity<List<RentcarInfoDTO>> carTypeList() {


        List<RentcarInfoDTO> rentcarList = rentcarService.FindTypeAsc(rentcarService.findAll());

        return ResponseEntity.ok(rentcarList);
    }




    //렌트카 업체 리스트 가져오기
    @GetMapping("/store/rentcar/rentcarChoice")
    public String rentcarChoice(@RequestParam("car_name") String car_name,
                                @RequestParam("cartype") String cartype,
                                @RequestParam("caryear") String caryear,
                                @RequestParam("carprice") String carprice,

            Model model, HttpSession session) {

        List<RentcarInfoEntity> rentcarComList = rentcarService.FindCombycarname(car_name);


        session.setAttribute("cartype", cartype);
        session.setAttribute("caryear", caryear);
        session.setAttribute("carprice", carprice);
        session.setAttribute("car_name", car_name);

        model.addAttribute("rentcarComList",rentcarComList);


        return "store/rentcar/rentcarChoice";

    }


}
