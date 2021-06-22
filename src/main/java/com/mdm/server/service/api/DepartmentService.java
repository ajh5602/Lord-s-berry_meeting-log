package com.mdm.server.service.api;

import com.mdm.server.controller.api.CertController;
import com.mdm.server.domain.request.DepartmentRequest;
import com.mdm.server.exception.CertFailedException;
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
public class DepartmentService {

    private final DepartmentMapper departmentMapper;
    private final Logger logger = LoggerFactory.getLogger(DepartmentService.class);
    /**
     * name : addDepartment
     * usage : 부서 추가 서비스
     * param : DepartmentRequest
     * return : String id = 추가된 부서 아이디
     * @author ash
     * @date 2021-06-17
    **/
    public String addDepartment(DepartmentRequest departmentRequest){
        try {
            if(departmentMapper.isExistDepartment(departmentRequest) < 1){
                throw new CertFailedException("parent에 해당하는 조직이 없습니다.");
            }
           departmentMapper.addDepartment(departmentRequest);
           return String.valueOf(departmentRequest.getId());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new CertFailedException();
        }
    }

    /**
     * name : setDepartment
     * usage : 부서 수정 서비스
     * param : DepartmentRequest
     * return : int
     * @author ash
     * @date 2021-06-17
    **/
    @Transactional
    public int setDepartment(DepartmentRequest departmentRequest){
        try {
            if(departmentMapper.isExistDepartment(departmentRequest) < 1){
                throw new CertFailedException("parent에 해당하는 조직이 없습니다.");
            }
            int ret = departmentMapper.setDepartment(departmentRequest);
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
     * name : deLDepartment
     * usage : 부서 삭제 서비스
     * param : DepartmentRequest
     * return : int
     * @author ash
     * @date 2021-06-17
     **/
    public int delDepartment(DepartmentRequest departmentRequest){
        try {
            int ret = departmentMapper.delDepartment(departmentRequest);
            if(ret < 1 ){
                throw new CertFailedException("parent에 해당하는 조직이 없습니다.");
            }
            return ret;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new CertFailedException();
        }
    }



}
