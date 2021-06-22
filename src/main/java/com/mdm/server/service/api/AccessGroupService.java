package com.mdm.server.service.api;

import com.mdm.server.domain.request.AccessGroupRequest;
import com.mdm.server.domain.request.DepartmentRequest;
import com.mdm.server.exception.CertFailedException;
import com.mdm.server.mapper.api.AccessGroupMapper;
import com.mdm.server.mapper.api.DepartmentMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
/**
 * name : DepartmentService
 * usage : 부서 부분 서비스
 * @author ash
 * @date 2021-06-17
**/
public class AccessGroupService {

    private final AccessGroupMapper accessGroupMapper;
    private final Logger logger = LoggerFactory.getLogger(AccessGroupService.class);

    /**
     * name : addAccessGroup
     * usage : 출입그룹 추가 서비스
     * param : AccessGroupRequest
     * return : int
     * @author ash
     * @date 2021-06-17
    **/
    public int addAccessGroup(AccessGroupRequest accessGroupRequest){
        try {
            if(accessGroupMapper.isExistAccessGroup(accessGroupRequest) < 1){
                throw new CertFailedException("수정에 실패했습니다.");
            }
            if(accessGroupMapper.isExistDeviceId(accessGroupRequest) < 1){
                throw new CertFailedException("수정에 실패했습니다.");
            }
            int ret = accessGroupMapper.addAccessGroup(accessGroupRequest);
            if(ret < 1 ){
                throw new CertFailedException("수정에 실패했습니다.");
            }

            return ret;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new CertFailedException();
        }
    }

    /**
     * name : delAccessGroup
     * usage : 출입 그룹 삭제 서비스
     * param : AccessGroupRequest
     * return : int
     * @author ash
     * @date 2021-06-17
     **/
    public int delAccessGroup(AccessGroupRequest accessGroupRequest){
        try {
            int ret = accessGroupMapper.delAccessGroup(accessGroupRequest);
            if(ret < 1 ){
                throw new CertFailedException();
            }
            return ret;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new CertFailedException();
        }
    }
}
