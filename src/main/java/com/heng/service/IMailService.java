package com.heng.service;

import org.springframework.stereotype.Service;


@Service
public interface IMailService {

    void sendCheckCode(String to, String subject, String content);

}
