package com.mdm.server.mapper.api;

import com.mdm.server.domain.request.AccessGroupRequest;
import com.mdm.server.domain.request.DepartmentRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * name : AccessGroupMapper
 * usage : 출입 그룹 부분 매퍼연결
 * @author ash
 * @date 2021-06-17
**/
public interface AccessGroupMapper {
    int addAccessGroup(AccessGroupRequest accessGroupRequest);
    int delAccessGroup(AccessGroupRequest accessGroupRequest);
    int isExistAccessGroup(AccessGroupRequest accessGroupRequest);
    int isExistDeviceId(AccessGroupRequest accessGroupRequest);
}
