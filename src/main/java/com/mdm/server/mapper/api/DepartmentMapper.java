package com.mdm.server.mapper.api;

import com.mdm.server.domain.request.DepartmentRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * name : DepartmentMapper
 * usage : 부서부분 쿼리 매퍼 연결
 * @author ash
 * @date 2021-06-17
**/
public interface DepartmentMapper {
    int addDepartment(DepartmentRequest departmentRequest);
    int setDepartment(DepartmentRequest departmentRequest);
    int delDepartment(DepartmentRequest departmentRequest);
    int isExistDepartment(DepartmentRequest departmentRequest);
}
