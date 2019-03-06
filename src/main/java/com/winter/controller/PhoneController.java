package com.winter.controller;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.winter.common.ServerResponse;
import com.winter.util.PhoneUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/phone")
public class PhoneController {

    @ResponseBody
    @RequestMapping(value = "/getVerificationCode.do",method = RequestMethod.POST)
    public ServerResponse getVerificationCode(String phoneNumber) {
        String code = PhoneUtil.getVerificationCode(phoneNumber);
        return ServerResponse.createBySuccess(code);
    }
}
