package com.winter.controller.manage;

import com.winter.common.ServerResponse;
import com.winter.service.IRoomService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hardwareManage")
public class HardwareManageController {

    private IRoomService roomService;

    @Autowired
    public void setRoomService(IRoomService roomService) {
        this.roomService = roomService;
    }

    @RequestMapping(value = "bindAccessMachineNumber",method = RequestMethod.POST)
    public ServerResponse bindAccessMachineNumber(String roomNumber, String machineNumber) {
        if (StringUtils.isAnyBlank(roomNumber,machineNumber)) {
            return ServerResponse.createByErrorMessage("参数无效!");
        }
        return roomService.bindAccessMachineNumber(roomNumber, machineNumber);
    }
}
