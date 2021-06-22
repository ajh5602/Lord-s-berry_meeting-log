package com.mdm.server.controller.api;

import com.mdm.server.common.CodeMessage;
import com.mdm.server.common.ResponseMessage;
import com.mdm.server.domain.basic.BaseResponse;
import com.mdm.server.domain.request.DepartmentRequest;
import com.mdm.server.domain.request.DeviceAuth;
import com.mdm.server.exception.CertFailedException;
import com.mdm.server.service.ResponseService;
import com.mdm.server.service.api.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * name : DepartmentController
 * usage : 조직 관리 API controller
 * @author ash
 * @date 2021-06-17
**/
@RestController
@RequiredArgsConstructor
public class DepartmentController {
    private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
    private final ResponseService responseService;
    private final DepartmentService departmentService;

    @PostMapping("/api/departments")
    @ResponseBody
    /**
     * name : addDepartment
     * usage : 조직 추가 API
     * param : [departmentRequest]
     * return : org.springframework.http.ResponseEntity
     * @author ash
     * @date 2021-06-17
    **/
    public ResponseEntity addDepartment(@ModelAttribute("params") final DepartmentRequest departmentRequest) {
        ResponseEntity responseEntity = null;
        try {
            //ToDo: 해당 parentId가 있는지 체크
            String ret = departmentService.addDepartment(departmentRequest);
            Map<String,Object> rets = new HashMap<>();
            rets.put("departmentId",ret);
            BaseResponse response = responseService.getSingleDataResponse(CodeMessage.SUCCESS, ResponseMessage.SUCCESS,rets);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (CertFailedException exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(CodeMessage.TOKEN_VALIDATION_ERROR, ResponseMessage.CERT_FAIL);
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }


    @PutMapping(value = "/api/departments/{id}")
    @ResponseBody
    /**
     * name : addDepartment
     * usage : 조직 수정 API
     * param : [departmentRequest]
     * return : org.springframework.http.ResponseEntity
     * @author ash
     * @date 2021-06-17
     **/
    public ResponseEntity setDepartment(@ModelAttribute("params") final DepartmentRequest departmentRequest,@PathVariable("departmentId") int id) {
        ResponseEntity responseEntity = null;
        try {
            departmentRequest.setId(id);
            int ret = departmentService.setDepartment(departmentRequest);
            BaseResponse response = responseService.getBaseResponse(CodeMessage.SUCCESS, ResponseMessage.SUCCESS);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (CertFailedException exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(CodeMessage.TOKEN_VALIDATION_ERROR, ResponseMessage.CERT_FAIL);
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }


    @DeleteMapping(value = "/api/departments/{departmentId}")
    @ResponseBody
    /**
     * name : addDepartment
     * usage : 조직 삭제 API
     * param : [departmentRequest]
     * return : org.springframework.http.ResponseEntity
     * @author ash
     * @date 2021-06-17
     **/
    public ResponseEntity delDepartment(@ModelAttribute("params") final DepartmentRequest departmentRequest,@PathVariable("departmentId") int id) {
        ResponseEntity responseEntity = null;
        try {
            departmentRequest.setId(id);
            int ret = departmentService.delDepartment(departmentRequest);
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
