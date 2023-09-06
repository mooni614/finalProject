
/*
package com.kh.myproject.api.kakaoPay.service;

import com.kh.myproject.store.rentcar.model.dto.CrawlingDto;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SeleniumComponent {

    public SeleniumComponent() {
        this.chrome();
    }

    public static String WEB_DRIVER_ID = "webdriver.chrome.driver"; // Properties 설정
    public static String WEB_DRIVER_PATH = "\\Users\\user1\\Desktop\\chromedriver-win64\\chromedriver.exe"; // WebDriver 경로

    private WebDriver driver;

//    private CrawlingDto crawlingDto;

    private final String url = "https://whitelabel.imsmobility.co.kr/tripsoda/reservation/request/287?pickupAt=2023-10-18T01%3A00%3A00.000Z&dropoffAt=2023-10-19T01%3A00%3A00.000Z&pickupLat=33.50707895781836&pickupLng=126.492769004244&birth=000101&pickupAddress=%EC%A0%9C%EC%A3%BC%EA%B5%AD%EC%A0%9C%EA%B3%B5%ED%95%AD&pickupFullAddress=%EC%A0%9C%EC%A3%BC%ED%8A%B9%EB%B3%84%EC%9E%90%EC%B9%98%EB%8F%84+%EC%A0%9C%EC%A3%BC%EC%8B%9C+%EA%B3%B5%ED%95%AD%EB%A1%9C+2+%EC%A0%9C%EC%A3%BC%EA%B5%AD%EC%A0%9C%EA%B3%B5%ED%95%AD&insuranceAge=21&isJeju=true&sortOption=low_price&rentType=DAILY&submodelId=50";

    private void chrome() {
        log.info("################################driver options");
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        // webDriver 옵션 설정.
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.addArguments("--lang=ko");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        options.setCapability("ignoreProtectedModeSettings", true);

        // weDriver 생성.
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        log.info("################################driver options done");
    }


    @PostConstruct
    public List<CrawlingDto> getReviews() throws InterruptedException {
        log.info("################################crawling start");

        driver.get(url);
        Thread.sleep(10000);


        List<CrawlingDto> reviewList = new ArrayList<>();
        List<WebElement> reviewElements = driver.findElements(By.className("//*[@id=\"root\"]/div[2]/div[2]/div/div[1]/div[3]/section/div/section[2]")); // 리뷰박스를 감싸는 클래스
        log.info("#########################reviewElements{}", reviewElements);
        for (WebElement reviewElement : reviewElements) {
            log.info("#########################들어왔니??");

            driver.get(String.valueOf(reviewElement));

            WebElement imgElement = driver.findElement(By.cssSelector("//*[@id=\"root\"]/div[2]/div[2]/div/div[1]/div[3]/section/div/section[2]/div[2]/div[1]/div[1]/div[1]/div[1]")); // 별 이미지

            WebElement ratingElement = driver.findElement(By.cssSelector("div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2)")); // 평점

            WebElement ratingDetailElement = driver.findElement(By.cssSelector("div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2)")); // 세부평점

            WebElement dateElement = driver.findElement(By.cssSelector("div:nth-child(2) > div:nth-child(1) > div:nth-child(2)")); // 추가 날짜

            WebElement reviewTextElement = driver.findElement(By.cssSelector("div:nth-child(2) > div:nth-child(2)")); // 평글
            String imgSrc = imgElement.getAttribute("src");
            String rating = ratingElement.getText();
            String ratingDetail = ratingDetailElement.getText();
            String date = dateElement.getText();
            String reviewText = reviewTextElement.getText();
            CrawlingDto crwl = new CrawlingDto(imgSrc, rating, ratingDetail, date, reviewText);
            reviewList.add(crwl);
//            reviewList.add(CrawlingDto.builder()
//                    .imgSrc(imgElement.getAttribute("src"))
//                    .rating(ratingElement.getText())
//                    .ratingDetail(ratingDetailElement.getText())
//                    .date(dateElement.getText())
//                    .reviewText(reviewTextElement.getText())
//                    .build());

            log.info("################################crawling donereviewList{}", reviewList);

        }
        log.info("################################crawling done");
        driver.close();
        driver.quit();
        return reviewList;
    }


    public void quitDriver() {
        driver.quit(); // webDriver 종료
    }
}



 */