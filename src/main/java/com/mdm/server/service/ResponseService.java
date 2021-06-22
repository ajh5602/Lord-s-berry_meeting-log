package com.mdm.server.service;

import com.mdm.server.domain.basic.BaseResponse;
import com.mdm.server.domain.basic.ListDataResponse;
import com.mdm.server.domain.basic.SingleDataResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * name : ResponseService
 * usage : 결과창 만드는 부분 공통 서비스
 * @author ash
 * @date 2021-06-17
**/
@Service
public class ResponseService {

    public <T> SingleDataResponse<T> getSingleDataResponse(String code,String message, T result) {
        SingleDataResponse<T> response = new SingleDataResponse<>();
        response.setCode(code);
        response.setMessage(message);
        response.setResult(result);
        return response;
    }

    public <T> ListDataResponse<T> getListDataResponse(boolean success, String message, List<T> data) {
        ListDataResponse<T> response = new ListDataResponse<>();
        response.setMessage(message);
        response.setData(data);

        return response;
    }

    public BaseResponse getBaseResponse(String code, String message) {
        BaseResponse response = new BaseResponse();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}
