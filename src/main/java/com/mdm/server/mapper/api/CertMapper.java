package com.mdm.server.mapper.api;

import com.mdm.server.domain.response.AdminResponse;
import com.mdm.server.domain.response.DeviceResponse;
import org.apache.ibatis.annotations.Mapper;
import java.util.Optional;

/**
 * name : CertMapper
 * usage : 토큰 OR 로그인 부분 쿼리 매퍼
 * @author ash
 * @date 2021-06-17
**/
@Mapper
public interface CertMapper {
    /*토큰 가져오기*/
    Optional<DeviceResponse> findDeviceByDeviceAndMac(String deviceId);
    Optional<AdminResponse> findAdminByIdAndPw(String deviceId);
    boolean deviceCheck(String device);
}
