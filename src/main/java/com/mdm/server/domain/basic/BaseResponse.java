package com.mdm.server.domain.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * name : BaseResponse
 * usage : 기본적인 결과 출력
 * @author ash
 * @date 2021-06-17
**/
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    private String code;
    private String message; // 응답 메세지
}
