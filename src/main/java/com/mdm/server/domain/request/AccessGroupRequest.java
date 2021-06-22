package com.mdm.server.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * name : AccessGroupRequest
 * usage : 출입 그룹 DTO
 * @author ash
 * @date 2021-06-17
**/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccessGroupRequest {
    private int id;
    private String accessGroupId;
    private String deviceId;
}
