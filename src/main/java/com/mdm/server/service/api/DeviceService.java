package com.mdm.server.service.api;

import com.mdm.server.domain.request.DepartmentRequest;
import com.mdm.server.domain.request.LogRequest;
import com.mdm.server.exception.CertFailedException;
import com.mdm.server.mapper.api.DepartmentMapper;
import com.mdm.server.mapper.api.DeviceMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
/**
 * name : DeviceService
 * usage : 디바이스 API 부분에 대한 서비스
 * @author ash
 * @date 2021-06-18
**/
public class DeviceService {

    private final DeviceMapper deviceMapper;
    private final Logger logger = LoggerFactory.getLogger(DeviceService.class);

    /**
     * name : addLog
     * usage : 로그 추가
     * param : [LogRequest]
     * return : int (성공 수 )
     * @author ash
     * @date 2021-06-18
    **/
    public int addLog(LogRequest logRequest){
        try {
            int ret = deviceMapper.addLog(logRequest);
            if(ret < 1 ){
                throw new CertFailedException("로그 저장에 실패하였습니다");
            }
            logger.error("ret:::::" + logRequest.getId());
            int ret2 = deviceMapper.addLogImage(logRequest);

            return ret;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new CertFailedException();
        }
    }
}
