package com.winter.controller;

import com.winter.common.ServerResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/meeting")
public class MeetingController {

    @ResponseBody
    @RequestMapping()
    public ServerResponse<> get
}
