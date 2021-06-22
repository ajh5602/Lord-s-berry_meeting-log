package com.mdm.server.mapper.api;

import com.mdm.server.domain.request.LogRequest;
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
public interface DeviceMapper {
    /*토큰 가져오기*/
    int addLog(LogRequest logRequest);
    int addLogImage(LogRequest logRequest);
}
