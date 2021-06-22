package com.mdm.server.controller.api;

import com.mdm.server.common.CodeMessage;
import com.mdm.server.common.ResponseMessage;
import com.mdm.server.domain.basic.BaseResponse;
import com.mdm.server.domain.request.AdminAuth;
import com.mdm.server.domain.request.DeviceAuth;
import com.mdm.server.domain.basic.SingleDataResponse;
import com.mdm.server.exception.CertFailedException;
import com.mdm.server.service.ResponseService;
import com.mdm.server.service.CertService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * name : CertController
 * usage : 인증 부분 공통 컨트롤러(API/DASHBOARD 공통)
 * @author ash
 * @date 2021-06-17
**/
@RestController
@RequiredArgsConstructor
public class CertController {

    private final CertService certService;
    private final ResponseService responseService;
    private final Logger logger = LoggerFactory.getLogger(CertController.class);


    @PostMapping("/api/auth")
    @ResponseBody
    /**
     * name : cert
     * usage : API 단말 전용 토큰 생성 함수
     * param : [deviceAuth]
     * return : org.springframework.http.ResponseEntity
     * @author ash
     * @date 2021-06-17
    **/
    public ResponseEntity cert(@ModelAttribute("params") final DeviceAuth deviceAuth) {
        ResponseEntity responseEntity = null;
        try {
            String token = certService.cert(deviceAuth);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + token);
            Map<String,Object> rets = new HashMap<>();
            rets.put("accessToken",token);
            BaseResponse response = responseService.getSingleDataResponse(CodeMessage.SUCCESS, ResponseMessage.CERT_SUCCESS, rets);
            responseEntity = ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(response);
        } catch (CertFailedException exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(CodeMessage.TOKEN_VALIDATION_ERROR, ResponseMessage.CERT_FAIL);
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    @PostMapping("/login")
    @ResponseBody
    /**
     * name : login
     * usage : 유저 로그인 전용 토큰 생성 함수
     * param : [adminAuth]
     * return : org.springframework.http.ResponseEntity
     * @author ash
     * @date 2021-06-17
    **/
    public ResponseEntity login(@ModelAttribute("params") final AdminAuth adminAuth) {
        ResponseEntity responseEntity = null;
        try {
            String token = certService.login(adminAuth);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + token);
            SingleDataResponse<String> response = responseService.getSingleDataResponse(CodeMessage.SUCCESS, ResponseMessage.CERT_SUCCESS, token);
            responseEntity = ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(response);
        } catch (CertFailedException exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(CodeMessage.TOKEN_VALIDATION_ERROR, ResponseMessage.CERT_FAIL);
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }


    @PostMapping("/api/deviceCheck")
    @ResponseBody
    /**
     * name : deviceCheck
     * usage : API 단말용 매 주기마다 단말 상태 확인용 디바이스 체크 함수
     * param : [deviceAuth]
     * return : org.springframework.http.ResponseEntity
     * @author ash
     * @date 2021-06-17
    **/
    public ResponseEntity deviceCheck(@ModelAttribute("params") final DeviceAuth deviceAuth) {
        ResponseEntity responseEntity = null;
        try {
            boolean ret = certService.deviceCheck(deviceAuth);
            if(!ret) {
                BaseResponse response = responseService.getBaseResponse(CodeMessage.SUCCESS_NONE, ResponseMessage.CERT_SUCCESS);
                responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
                return responseEntity;
            }
            BaseResponse response = responseService.getBaseResponse(CodeMessage.SUCCESS, ResponseMessage.CERT_SUCCESS);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (CertFailedException exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(CodeMessage.TOKEN_VALIDATION_ERROR, ResponseMessage.CERT_FAIL);
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }
}
