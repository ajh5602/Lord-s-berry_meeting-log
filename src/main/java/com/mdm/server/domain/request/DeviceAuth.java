package com.mdm.server.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * name : DeviceAuth
 * usage : 디바이스 DTO
 * @author ash
 * @date 2021-06-17
**/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeviceAuth {
    private String id;
    private String deviceName;
    private String macAddress;
}
