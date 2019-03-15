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

    /**
     * 获取短信验证码
     * @param phoneNumber
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getVerificationCode.do",method = RequestMethod.POST)
    public ServerResponse getVerificationCode(String phoneNumber) {
        String code = PhoneUtil.getVerificationCode(phoneNumber);
        return ServerResponse.createBySuccess(code);
    }

    /**
     * 验证用户验证码
     * @param code
     * @param phoneNumber
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/judgeCode.do",method = RequestMethod.POST)
    public ServerResponse judgeCode(String code,String phoneNumber) {
        if (PhoneUtil.judgeCodeIsTrue(code,phoneNumber)) {
            return ServerResponse.createBySuccessMessage("验证成功");
        }
        return ServerResponse.createByErrorMessage("验证失败");
    }
}
