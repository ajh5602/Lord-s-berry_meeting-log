package com.mdm.server.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

/**
 * name : LogRequest
 * usage : 로그 DTO
 * @author ash
 * @date 2021-06-21
**/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogRequest {
    private int id;
    private String deviceId;
    private String userCode;
    private Float confidence;
    private Float temperature;
    private String logImage; //DB상에서는 Blob로 변환되어 들어감
    private String userId;
}
