package com.mdm.server.service;

import com.mdm.server.common.ResponseMessage;
import com.mdm.server.controller.api.CertController;
import com.mdm.server.domain.request.AdminAuth;
import com.mdm.server.domain.request.DeviceAuth;
import com.mdm.server.domain.response.AdminResponse;
import com.mdm.server.domain.response.DeviceResponse;
import com.mdm.server.utils.JwtTokenProvider;
import com.mdm.server.exception.CertFailedException;
import com.mdm.server.mapper.api.CertMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.regex.Pattern;


/**
 * name : CertService
 * usage : 사용자 로그인 및 기기인증 부분 서비스
 * @author ash
 * @date 2021-06-17
**/
@Service
@RequiredArgsConstructor
public class CertService {

    private final CertMapper certMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final Logger logger = LoggerFactory.getLogger(CertController.class);
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public String cert(DeviceAuth device) {
        try {
            DeviceResponse deviceResponse = certMapper.findDeviceByDeviceAndMac(device.getId())
                    .orElseThrow(() -> new CertFailedException(ResponseMessage.CERT_FAIL));
            if (!Pattern.matches(device.getMacAddress(), deviceResponse.getMac())) {
                throw new CertFailedException(ResponseMessage.CERT_FAIL);
            }
            logger.error(device.getDeviceName());
            return jwtTokenProvider.createToken(deviceResponse.getId(), Collections.singletonList("DEVICE"));
        } catch (Exception e) {
            throw new CertFailedException();
        }
    }

    @Transactional(readOnly = true)
    public String login(AdminAuth adminAuth) {
        try {
            AdminResponse adminResponse = certMapper.findAdminByIdAndPw(adminAuth.getLoginId())
                    .orElseThrow(() -> new CertFailedException(ResponseMessage.CERT_FAIL));
            if (!passwordEncoder.matches(adminAuth.getLoginPwd(), adminResponse.getPassword())) {
                throw new CertFailedException(ResponseMessage.CERT_FAIL);
            }
            return jwtTokenProvider.createToken(adminResponse.getId(), Collections.singletonList("ROLE_ADMIN"));
        } catch (Exception e) {
            throw new CertFailedException();
        }
    }

    public boolean deviceCheck(DeviceAuth device) {
        try {
            boolean ret = certMapper.deviceCheck(device.getId());
            return ret;
        } catch (Exception e) {
            throw new CertFailedException();
        }
    }
}
