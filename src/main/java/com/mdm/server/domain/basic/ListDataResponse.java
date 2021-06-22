package com.mdm.server.domain.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * name : ListDataResponse
 * usage : 결과 리스트 출력
 * @author ash
 * @date 2021-06-17
**/
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListDataResponse<T> extends BaseResponse{
    private List<T> data; // 리스트 형태 데이터
}
