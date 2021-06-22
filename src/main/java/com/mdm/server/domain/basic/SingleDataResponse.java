package com.mdm.server.domain.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * name : SingleDataResponse
 * usage : 싱글 결과 출력
 * @author ash
 * @date 2021-06-17
**/
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SingleDataResponse<T> extends BaseResponse {
    private T result; // 전달할 데이터
}
