package com.mdm.server.common;

/**
 * name : CodeMessage
 * usage : 결과 코드 출력용 메세지
 * @author ash
 * @date 2021-06-17
**/
public class CodeMessage {
    public static final String SUCCESS = "000"; /*정상*/
    public static final String SUCCESS_NONE = "001";    /*정상적으로 처리되었으나 변경 또는 추가된 값 없음*/
    public static final String MISS_REQUIRED_VALUES = "101";   /*필수 값 누락*/
    public static final String LESS_THAN_MINIMUM = "102";   /*특정 값이 최소 값 미만*/
    public static final String EXCEED_THE_MAXIMUM = "103"; /*특정 값이 최대 값 초과*/
    public static final String NO_VALUE_FOUND = "104";   /*해당 값을 찾을 수 없음*/
    public static final String DECRYPTION_ERROR = "201";  /*복호화 오류*/
    public static final String NO_READ_PERMISSION = "202"; /*읽기권한 없음*/
    public static final String NO_EDIT_PERMISSION = "203"; /*수정권한 없음*/
    public static final String NO_DELETE_PERMISSION = "204"; /*삭제권한 없음*/
    public static final String WRONG_PATH_ACCESS = "301";   /*잘못된 경로 접근*/
    public static final String DATABASE_ACCESS_FAILURE = "302"; /*데이터베이스 접근 실패*/
    public static final String HANDING_SYSTEM_EXCEPTIONS = "303";   /*시스템 예외 처리*/
    public static final String TOKEN_VALIDATION_ERROR = "999";   /*토큰 검증 오류*/
}
