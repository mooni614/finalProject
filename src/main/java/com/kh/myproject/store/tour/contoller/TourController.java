package com.kh.myproject.store.tour.contoller;

import com.kh.myproject.api.naverBlog.BlogSearch;
import com.kh.myproject.store.tour.model.dto.TourismDto;
import com.kh.myproject.store.tour.model.vo.AreaBaseListParam;
import com.kh.myproject.store.tour.service.TourService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@Slf4j
@SessionAttributes("user")
public class TourController {


    @Autowired
    TourService tourService;
    final String TOURAPI_URL = "https://apis.data.go.kr/B551011/KorService1/";

    final String SERVICE_KEY = "serviceKey=ZgRTKBFIJGjeIJ14VHOZrP9UMtis8xSBTJvnPqQIigzUQ4aIL8V03y5XCVZ5B8GAKHaJX%2FOz2UpnX%2FvgKqv38w%3D%3D";
    final String LAST_URL = "&MobileOS=ETC&MobileApp=AppTest&_type=json";
    //    final String DETAILCOMMON_LAST_URL = "&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&numOfRows=10&pageNo=1";
    final String blogSearchUrl = "";


    @GetMapping("store/home")
    public ModelAndView storeHome(ModelAndView mav) {
        mav.setViewName("store/tour/tourMain");
        return mav;
    }

    @GetMapping("/store/tour/tourSearch")
    public ModelAndView tourSearch(ModelAndView mav) {
        mav.setViewName("store/tour/tourSearch");

        return mav;
    }

    @GetMapping("/store/tour/cities")
    public ModelAndView cities(@RequestParam("cityName") String cityName,
                               ModelAndView mav) {
        log.info("cityName={}", cityName);
        mav.addObject("cityName", cityName);
        List<TourismDto> list = tourService.getTourism(cityName);

        log.info("tourismList={}", list);
        mav.addObject("tourismList", list);
        mav.setViewName("store/tour/cities");

        return mav;
    }


    @GetMapping("/store/tour/tourDetail")
    public ModelAndView tourDetail(ModelAndView mav) {
        mav.setViewName("store/tour/tourDetail");

        return mav;
    }

    @GetMapping("/store/tour/getArea")
    public ResponseEntity<?> getArea() {
        StringBuilder result = new StringBuilder();
        String url = TOURAPI_URL + "areaCode1?" + SERVICE_KEY;
        url += "&numOfRows=17" + LAST_URL;

        return ResponseEntity.ok(tourService.getApiData(url));
    }

    @GetMapping("/store/tour/getSigungu")
    public ResponseEntity<?> getSigungu(@RequestParam("areaCode") String areaCode) {
        String url = TOURAPI_URL + "areaCode1?" + SERVICE_KEY;
        url += "&areaCode=" + areaCode;
        url += "&numOfRows=40" + LAST_URL;
        return ResponseEntity.ok(tourService.getApiData(url));
    }

    @GetMapping("/store/tour/getCat1")
    public ResponseEntity<?> getCat1(@RequestParam("contentTypeId") String contentTypeId) {
        String url = TOURAPI_URL + "categoryCode1?" + SERVICE_KEY;
        url += "&contentTypeId=" + contentTypeId + LAST_URL;
        return ResponseEntity.ok(tourService.getApiData(url));
    }

    @GetMapping("/store/tour/getCat2")
    public ResponseEntity<?> getCat2(@RequestParam("contentTypeId") String contentTypeId,
                                     @RequestParam("cat1") String cat1) {
        String url = TOURAPI_URL + "categoryCode1?" + SERVICE_KEY;
        url += "&contentTypeId=" + contentTypeId;
        url += "&cat1=" + cat1 + LAST_URL;

        return ResponseEntity.ok(tourService.getApiData(url));
    }

    @GetMapping("/store/tour/getCat3")
    public ResponseEntity<?> getCat3(@RequestParam("contentTypeId") String contentTypeId,
                                     @RequestParam("cat1") String cat1,
                                     @RequestParam("cat2") String cat2) {
        String url = TOURAPI_URL + "categoryCode1?" + SERVICE_KEY;
        url += "&contentTypeId=" + contentTypeId;
        url += "&cat1=" + cat1;
        url += "&cat2=" + cat2 + LAST_URL;

        return ResponseEntity.ok(tourService.getApiData(url));
    }

    @GetMapping("/store/tour/getAreaBaseList")
    public ResponseEntity<?> getAreaBaseList(@RequestParam("contentTypeId") String contentTypeId,
                                             @RequestParam("areaCode") String areaCode,
                                             @RequestParam("sigunguCode") String sigunguCode,
                                             @RequestParam("cat1") String cat1,
                                             @RequestParam("cat2") String cat2,
                                             @RequestParam("cat3") String cat3,
                                             @RequestParam("pageNo") int pageNo) {

        log.info("contentTypeId={}", contentTypeId);
        log.info("areaCode={}", areaCode);
        log.info("sigunguCode={}", sigunguCode);
        log.info("cat1={}", cat1);
        log.info("cat2={}", cat2);
        log.info("cat3={}", cat3);

        String url = TOURAPI_URL + "areaBasedList1?" + SERVICE_KEY;
        url += "&numOfRows=12" + "&pageNo=" + pageNo;
        url += "&listYN=Y&arrange=A";
        url += "&contentTypeId=" + contentTypeId;
        url += "&areaCode=" + areaCode;
        url += "&sigunguCode=" + sigunguCode;
        url += "&cat1=" + cat1;
        url += "&cat2=" + cat2;
        url += "&cat3=" + cat3 + LAST_URL;

        return ResponseEntity.ok(tourService.getApiData(url));
    }

    @GetMapping("/store/tour/getPreviousPageList")
    public ResponseEntity<?> getPreviousPageList(@RequestParam("contentTypeId") String contentTypeId,
                                                 @RequestParam("areaCode") String areaCode,
                                                 @RequestParam("sigunguCode") String sigunguCode,
                                                 @RequestParam("cat1") String cat1,
                                                 @RequestParam("cat2") String cat2,
                                                 @RequestParam("cat3") String cat3,
                                                 @RequestParam("pageNo") int pageNo){

        String url = TOURAPI_URL + "areaBasedList1?" + SERVICE_KEY;
        url += "&numOfRows=12" + "&pageNo=" + pageNo;
        url += "&listYN=Y&arrange=A";
        url += "&contentTypeId=" + contentTypeId;
        url += "&areaCode=" + areaCode;
        url += "&sigunguCode=" + sigunguCode;
        url += "&cat1=" + cat1;
        url += "&cat2=" + cat2;
        url += "&cat3=" + cat3 + LAST_URL;

        return ResponseEntity.ok(tourService.getApiData(url));
    }
    @GetMapping("/store/tour/getMainPageList")
    public ResponseEntity<?> getMainPageList(@RequestParam("contentTypeId") String contentTypeId,
                                                 @RequestParam("areaCode") String areaCode,
                                                 @RequestParam("sigunguCode") String sigunguCode,
                                                 @RequestParam("cat1") String cat1,
                                                 @RequestParam("cat2") String cat2,
                                                 @RequestParam("cat3") String cat3,
                                                 @RequestParam("pageNo") int pageNo){

        String url = TOURAPI_URL + "areaBasedList1?" + SERVICE_KEY;
        url += "&numOfRows=12" + "&pageNo=" + pageNo;
        url += "&listYN=Y&arrange=A";
        url += "&contentTypeId=" + contentTypeId;
        url += "&areaCode=" + areaCode;
        url += "&sigunguCode=" + sigunguCode;
        url += "&cat1=" + cat1;
        url += "&cat2=" + cat2;
        url += "&cat3=" + cat3 + LAST_URL;

        return ResponseEntity.ok(tourService.getApiData(url));
    }
    @GetMapping("/store/tour/getNextPageList")
    public ResponseEntity<?> getNextPageList(@RequestParam("contentTypeId") String contentTypeId,
                                                 @RequestParam("areaCode") String areaCode,
                                                 @RequestParam("sigunguCode") String sigunguCode,
                                                 @RequestParam("cat1") String cat1,
                                                 @RequestParam("cat2") String cat2,
                                                 @RequestParam("cat3") String cat3,
                                                 @RequestParam("pageNo") int pageNo){

        String url = TOURAPI_URL + "areaBasedList1?" + SERVICE_KEY;
        url += "&numOfRows=12" + "&pageNo=" + pageNo;
        url += "&listYN=Y&arrange=A";
        url += "&contentTypeId=" + contentTypeId;
        url += "&areaCode=" + areaCode;
        url += "&sigunguCode=" + sigunguCode;
        url += "&cat1=" + cat1;
        url += "&cat2=" + cat2;
        url += "&cat3=" + cat3 + LAST_URL;

        return ResponseEntity.ok(tourService.getApiData(url));
    }
    @GetMapping("/store/tour/getLastPageList")
    public ResponseEntity<?> getLastPageList(@RequestParam("contentTypeId") String contentTypeId,
                                                 @RequestParam("areaCode") String areaCode,
                                                 @RequestParam("sigunguCode") String sigunguCode,
                                                 @RequestParam("cat1") String cat1,
                                                 @RequestParam("cat2") String cat2,
                                                 @RequestParam("cat3") String cat3,
                                                 @RequestParam("pageNo") int pageNo){

        String url = TOURAPI_URL + "areaBasedList1?" + SERVICE_KEY;
        url += "&numOfRows=12" + "&pageNo=" + pageNo;
        url += "&listYN=Y&arrange=A";
        url += "&contentTypeId=" + contentTypeId;
        url += "&areaCode=" + areaCode;
        url += "&sigunguCode=" + sigunguCode;
        url += "&cat1=" + cat1;
        url += "&cat2=" + cat2;
        url += "&cat3=" + cat3 + LAST_URL;

        return ResponseEntity.ok(tourService.getApiData(url));
    }

    @GetMapping("/searchBlog")
    public String searchBlog(@RequestParam("title") String title) {

        return BlogSearch.getSearch(title);
    }
}
