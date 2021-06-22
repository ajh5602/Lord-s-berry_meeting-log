package com.mdm.server.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * name : DepartmentRequest
 * usage : 부서 부분 DTO/VO
 * @author ash
 * @date 2021-06-17
**/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequest {
    private int id;
    private String name;
    private String parentId;
}
