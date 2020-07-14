package com.dewen.project.constants;

public interface MessageConstant {
    /**
     * Common result codes and messages
     */
    //API Request with unknown error
    public static final int RESULT_CODE_UNKNOWN_ERROR_INT = 999;
    public static final String RESULT_CODE_UNKNOWN_ERROR = "0999";

    public static final int RESULT_CODE_OK_INT = 0;
    public static final String RESULT_CODE_OK = "0";
    public static final String RESULT_MSG_OK = "Request Successful";

    public static final int RESULT_CODE_BAD_REQUEST_INT = 400;
    public static final String RESULT_CODE_BAD_REQUEST = "0400";

    //API Request Unauthorized
    public static final int RESULT_CODE_UNAUTHORIZED_INT = 401;
    public static final String RESULT_CODE_UNAUTHORIZED = "0401";

    //API Request Forbidden
    public static final int RESULT_CODE_FORBIDDEN_INT = 403;
    public static final String RESULT_CODE_FORBIDDEN = "0403";
    public static final String RESULT_MSG_FORBIDDEN = "Forbidden, Request failed";

    //API Request with invalid request path
    public static final int RESULT_CODE_PATH_INVALID_INT = 404;
    public static final String RESULT_CODE_PATH_INVALID = "0404";


    //API Request with db path
    public static final int RESULT_DB_INVALID_INT = 901;
    public static final String RESULT_DB_INVALID = "0901";
    public static final String RESULT_DB_INVALID_MSG = "Fail to get data";
    //API Request with method not supported
    public static final int RESULT_CODE_METHOD_NOT_SUPPORTED_INT = 405;

    public static final String RESULT_CODE_FAIL_TO_GET_RESULT_MSG = "0449";
    public static final String RESULT_MSG_FAIL_TO_GET_RESULT_MSG = "Fail to get result msg";

    /**
     * 用户已存在
     */
    public static final int USER_EXISTS_INT = 430;
    public static final String USER_EXISTS = "User already exists";

    /**
     * 昵称已存在
     */
    public static final int DISPLAY_NAME_EXISTS_INT = 431;
    public static final String DISPLAY_NAME_EXISTS = "Nickname already exists";
}
