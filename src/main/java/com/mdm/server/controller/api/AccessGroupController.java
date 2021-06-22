package com.mdm.server.controller.api;

import com.mdm.server.common.CodeMessage;
import com.mdm.server.common.ResponseMessage;
import com.mdm.server.domain.basic.BaseResponse;
import com.mdm.server.domain.request.AccessGroupRequest;
import com.mdm.server.domain.request.DepartmentRequest;
import com.mdm.server.exception.CertFailedException;
import com.mdm.server.service.ResponseService;
import com.mdm.server.service.api.AccessGroupService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * name : AccessGroupController
 * usage : 출입 그룹 부분 API
 * @author ash
 * @date 2021-06-17
**/
@RestController
@RequiredArgsConstructor
public class AccessGroupController {
    private final Logger logger = LoggerFactory.getLogger(AccessGroupController.class);
    private final ResponseService responseService;
    private final AccessGroupService accessGroupService;


    @PostMapping("/api/access-group-list")
    @ResponseBody
    /**
     * name : addAccessGroup
     * usage : 출입 그룹 추가 API
     * param : [AccessGroupRequest]
     * return : org.springframework.http.ResponseEntity
     * @author ash
     * @date 2021-06-17
     **/
    public ResponseEntity addAccessGroup(@ModelAttribute("params") final AccessGroupRequest accessGroupRequest) {
        ResponseEntity responseEntity = null;
        try {
            int ret = accessGroupService.addAccessGroup(accessGroupRequest);
            BaseResponse response = responseService.getBaseResponse(CodeMessage.SUCCESS, ResponseMessage.SUCCESS);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (CertFailedException exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(CodeMessage.TOKEN_VALIDATION_ERROR, ResponseMessage.CERT_FAIL);
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    @DeleteMapping(value = "/api/access-group-list/{accessGroupId}/{deviceId}")
    @ResponseBody
    /**
     * name : delAccessGroup
     * usage : 출입 그룹 삭제 API
     * param : [departmentRequest]
     * return : org.springframework.http.ResponseEntity
     * @author ash
     * @date 2021-06-17
     **/
    public ResponseEntity delAccessGroup(@PathVariable("accessGroupId") String accessGroupId,@PathVariable("deviceId") String deviceId) {
        ResponseEntity responseEntity = null;
        try {
            AccessGroupRequest accessGroupRequest = new AccessGroupRequest();
            accessGroupRequest.setAccessGroupId(accessGroupId);
            accessGroupRequest.setDeviceId(deviceId);
            int ret = accessGroupService.delAccessGroup(accessGroupRequest);
            BaseResponse response = responseService.getBaseResponse(CodeMessage.SUCCESS, ResponseMessage.SUCCESS);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (CertFailedException exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(CodeMessage.TOKEN_VALIDATION_ERROR, ResponseMessage.CERT_FAIL);
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }
}
