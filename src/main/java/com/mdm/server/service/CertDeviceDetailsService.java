package com.mdm.server.service;

import com.mdm.server.domain.response.DeviceResponse;
import com.mdm.server.exception.CertFailedException;
import com.mdm.server.mapper.api.CertMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@RequiredArgsConstructor
@Service
/**
 * name : CertDeviceDetailsService
 * usage : 스프링 시큐리티 인증때 사용
 * @author ash
 * @date 2021-06-17
**/
public class CertDeviceDetailsService implements UserDetailsService {
    private final CertMapper certMapper;


    @Override
    /**
     * name : loadUserByUsername
     * usage : deviceId로 권한 체크
     * param : [deviceId]
     * return : org.springframework.security.core.userdetails.UserDetails
     * @author ash
     * @date 2021-06-17
    **/
    public UserDetails loadUserByUsername(String deviceId) throws UsernameNotFoundException {
        return certMapper.findDeviceByDeviceAndMac(deviceId)
                .map(deviceResponse -> addAuthorities(deviceResponse))
                .orElseThrow(() -> new CertFailedException(deviceId + "> can't search"));
    }
    private DeviceResponse addAuthorities(DeviceResponse deviceResponse) {
        deviceResponse.setAuthorities(Arrays.asList(new SimpleGrantedAuthority("DEVICE")));
        return deviceResponse;
    }
}
