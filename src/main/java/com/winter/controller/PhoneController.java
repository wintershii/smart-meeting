package com.winter.controller;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.winter.common.ServerResponse;
import com.winter.util.PhoneUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
        if (StringUtils.isBlank(phoneNumber)) {
            return ServerResponse.createByErrorMessage("参数无效!");
        }
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
        if (StringUtils.isAnyBlank(code,phoneNumber)) {
            return ServerResponse.createByErrorMessage("参数无效!");
        }
        if (PhoneUtil.judgeCodeIsTrue(code,phoneNumber)) {
            return ServerResponse.createBySuccessMessage("验证成功");
        }
        return ServerResponse.createByErrorMessage("验证失败");
    }
}
