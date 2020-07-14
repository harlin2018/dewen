package com.dewen.project.constants;

public interface ErrorCode {

    //SUCCESS 0
    public final static String SUCCESS = "0";

    //MethodArgumentNotValidException 100000+
    public final static String PARAM_NOT_VALID = "100000";
    //404
    public final static String URL_NOT_FOUND = "100001";
    //404
    public final static String TOKEN_INVALID = "100002";
    public final static String LOGIN_INFO_ERROR = "100003";
    //EpfException 110000+
    public final static String EPF_EXCEPTION = "110000";
    public final static String NOT_AUTH = "110001";

    //DataAccessException 120000+
    public final static String DATA_ACCESS_EXCEPTION = "120000";

    //OTHER  RuntimeException 130000+
    public final static String RUNTIME_EXCEPTION = "130000";

    //Permission denied 140000+
    public final static String PERMISSION_DENIED_EXCEPTION = "140000";



}
