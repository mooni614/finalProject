package com.kh.myproject.member.user.service;

import com.kh.myproject.member.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ManagerService {

    @Autowired
    UserRepository userRepository;



}
