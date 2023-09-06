package com.kh.myproject.member.user.controller;


import com.kh.myproject.member.user.model.entity.Manager;
import com.kh.myproject.member.user.model.entity.Qna;
import com.kh.myproject.member.user.model.entity.User;
import com.kh.myproject.member.user.service.QnaService;
import com.kh.myproject.member.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("manager")
public class ManagerController {


    @Autowired
    UserService userService;

    @Autowired
    QnaService qnaService;


    // 매니저 뷰페이지는 크게 N가지로 나눈다.
    // 처음 메인 페이지를 보여주고
    // 그다음 여러개의 관리 페이지를 둔다.
    // 일정 게시글, 동행 , 렌트카 예약내역, 항공권 예약내역, 문의글 (답변 가능해야함)
    @GetMapping("/manager/user")
    public ModelAndView managerUser(
            @ModelAttribute("check_manager") Manager check_manager,
            ModelAndView modelAndView,
            HttpSession session) {

        System.out.println(check_manager);


        if (check_manager.getManagerId() != null || session.getAttribute("manager") != null) {
            //세션값이 있거나 userCOntroller에서 로그인 요청이 들어왔다면


            modelAndView.setViewName("/member/manager/user");
            List<User> userList = userService.findAllUser();

            modelAndView.addObject("manager", check_manager);
            modelAndView.addObject("userList", userList);

            // 렌트카 테이블에서 상위 예약정보 몇개를 빼온다.
            // 항공편 테이블에서 상위 예약정보 몇개를 빼온다.
            // 게시글 목록에서 각 카ㅌ고리별 상위 게시글 1개씩 빼온다.

        } else {

            // url로 접속했을 경우 에러페이지로 이동시킨다.
            modelAndView.setViewName("redirect:/errorPage");

        }

        return modelAndView;
    }


    @GetMapping("/manager/logout")
    public String flightList(SessionStatus sessionStatus) {

        sessionStatus.setComplete(); // 매니저 세션을 죽인다.

        return "redirect:/";
    }


    // 일정게시글 관리
    @GetMapping("/manager/plan")
    public String plan() {

        return "member/manager/plan";
    }

    // 동행게시글 관리
    @GetMapping("/manager/accompany")
    public String accompany() {

        return "member/manager/accompany";
    }


    @GetMapping("/manager/rentcar")
    public String rentcar() {

        return "member/manager/rentcar";
    }


    @GetMapping("/manager/flight")
    public String flight() {


        return "member/manager/flight";
    }


    // 문의글 가져오기
    @GetMapping("/manager/qna")
    public String qnaList(Model model) {

        List<Qna> qnaList = qnaService.getAllQna();
        model.addAttribute("qnaList", qnaList);

        return "member/manager/qna";
    }

    @PostMapping("/test/getUserChart")
    @ResponseBody
    public Map<String, Object> getUserChart(){

        List<Integer> countList = userService.getUserJoinCount();
        List<Object[]> list = userService.getUserAgeCount();
        Map<String,BigInteger> ageMap = new HashMap<>();


        System.out.println(list);
        for(int i = 0 ; i < list.size() ; i ++){

            for(int j = 0 ; j < list.get(i).length / 2 ; j++){

                ageMap.put((String)list.get(i)[j*2],(BigInteger)list.get(i)[j*2+1]);

            }
        }


        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("countList",countList);
        resultMap.put("ageMap",ageMap);

        return resultMap;
    }


    @GetMapping("/chart")
    public String chart(){


        return "member/manager/chart";
    }

    @ResponseBody
    @PostMapping("/manager/deleteUser")
    public List<User> deleteUser(@ModelAttribute(name = "user_number")
                             String user_number){


        System.out.println(user_number);
        userService.deleteUser(user_number);

        // 외래키로 설정한 테이블의 모든 데이터를 지운다.
        List<User> userList = userService.findAllUser();


        System.out.println(userList);

        return userList;

    }


    // 유저게시판이 보여지는 페이지. 페이징 처리 구현.
    @GetMapping("pageTest")
    public String pageTest(Model model,
                           @RequestParam(value = "pageNo", defaultValue = "1")int pageNo){




        int userCount = userService.selectUserCount();

//        model.addAttribute("pageNo",user)
        // 테이블이 아래쪽에 있기 때문에 페이지를 이동했을때 location.href로 페이지를

        // 여기서 pageNo는 1,2,3,4,5 페이지를 뜻한다.
        // startNo는 글의 번호라고 생각하면 된다.
//        int endNo = 0; // 페이지 번호의 끝. -> 쿼리문 조회시 필요했으나 필요없을 듯.

        int startNo = 0; // 보여줘야할 페이지 번호의 시작 ex) pageno = 3일 경우 startno = 21, endno = 30
        int pageStartNo = 0; // 페이지 시작돼야할 번호. ex) 글이70개면 1번부터 7번까지, 71개면 1번부터 8번까지.
        int pageEndNo = 0; // 페이지 끝나야 할 번호.
        int endNo = 0;

        // pageNo =
        // pageEndNo =


        //유저 데이터가 150개다
        // 현재 페이지가 1~10페이지면 괜찮다. 그러나.
        // 현재페이지가 11~20이라면 20까지 보여줘서는 안된다
        // 따라서 userCOunt/10의 값이 pageEndNo보다 작을 경우 pageEndNo를 바꿔준다.
            startNo =  (pageNo-1) * 10 + 1;
            pageStartNo = pageNo / 10 * 10 + 1;
            pageStartNo = pageNo % 10 == 0 ? pageStartNo-10 : pageStartNo; // 21~30을 보여줘야 하는데 30일떄는 startNo이 31이된다.
            pageEndNo = ( (pageNo / 10 +1) * 10);
            pageEndNo = pageNo % 10 == 0 ? pageEndNo-10 : pageEndNo; // 21~30을 보여줘야 하는데 30일떄는 startNo이 31이된다.


        System.out.println(pageStartNo);
        System.out.println(pageEndNo);

        if(userCount / 10 <= pageEndNo){ // 유저가 100보다 크면 10페이지는 무조건 보여주면되고 100보다 작으면 그 몫에 나머지 있으면 +1만큼 보여준다.

            System.out.println(pageEndNo);
            pageEndNo =  userCount/10 ;
            pageEndNo = userCount%10 > 0 ? pageEndNo+1 : pageEndNo;

        }else{
//            pageEndNo = userCount / 10;
//            pageEndNo = userCount % 10 > 0 ? pageEndNo+1 : pageEndNo;
        }

        List<User> userList =  userService.findUserByPage(pageNo); // pageNo만큼 쿼리문을 조회한다.


        // 이전과 처음페이지도 처음과 끝에는 보여주면 안된다.

        model.addAttribute("pageStartNo",pageStartNo);
        model.addAttribute("pageEndNo",pageEndNo);
        model.addAttribute("userList",userList);
        model.addAttribute("pageNo",pageNo);


        return "member/manager/user";
    }





}




