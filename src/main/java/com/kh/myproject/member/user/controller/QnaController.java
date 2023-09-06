package com.kh.myproject.member.user.controller;

import com.kh.myproject.member.user.model.dto.QnaForm;
import com.kh.myproject.member.user.model.dto.UserForm;
import com.kh.myproject.member.user.model.entity.Qna;
import com.kh.myproject.member.user.model.entity.User;
import com.kh.myproject.member.user.service.QnaService;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.persistence.Column;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@SessionAttributes("user")
public class QnaController {

    @Autowired
    QnaService qnaService;

    @PostMapping("member/questionSubmit")
    public String questionSubmit(
            @RequestParam("qna_title") String qna_title,
            @RequestParam("qna_content") String qna_content,
            @ModelAttribute("user") User session_user,
            QnaForm qnaForm,
            Model model
    ) {

        // 기본키값을 넘겨줘야 save메서드에서 id값을 이용해 수정이 가능하다....

        Qna qna = new Qna();

        qna.setQnaWriter(session_user.getUserId());
        qna.setQnaTitle(qnaForm.getQna_title());
        qna.setQnaContent(qnaForm.getQna_content());

        qnaService.submitQna(qna);

        return "member/user/mypage";
    }
}
