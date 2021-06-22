package com.mdm.server.controller.dashboard;

import com.mdm.server.common.CodeMessage;
import com.mdm.server.common.ResponseMessage;
import com.mdm.server.controller.api.DepartmentController;
import com.mdm.server.domain.basic.BaseResponse;
import com.mdm.server.domain.request.LogRequest;
import com.mdm.server.exception.CertFailedException;
import com.mdm.server.service.ResponseService;
import com.mdm.server.service.api.DeviceService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class WebDeviceController {
    private final Logger logger = LoggerFactory.getLogger(WebDeviceController.class);
    private final ResponseService responseService;
    private final DeviceService deviceService;


    @GetMapping("/devices")
    public String devicePage(@ModelAttribute("params") final LogRequest logRequest) {
        ResponseEntity responseEntity = null;
        try {
            //ToDo: 해당 parentId가 있는지 체크
            int ret = deviceService.addLog(logRequest);
            BaseResponse response = responseService.getBaseResponse(CodeMessage.SUCCESS, ResponseMessage.SUCCESS);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (CertFailedException exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(CodeMessage.TOKEN_VALIDATION_ERROR, ResponseMessage.CERT_FAIL);
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return "devices";
    }
}
