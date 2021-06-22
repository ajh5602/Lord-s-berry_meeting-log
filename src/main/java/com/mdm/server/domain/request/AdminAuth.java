package com.mdm.server.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * name : AdminAuth
 * usage : 사용자 로그인 DTO
 * @author ash
 * @date 2021-06-17
**/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminAuth {
    private String id;
    private String loginId;
    private String loginPwd;
}
