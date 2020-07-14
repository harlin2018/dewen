package com.dewen.project.utils;


/**
 * Created by 01468339 on 3/8/2016.
 */

public interface IBaseManager {

    public BaseResponse composeCommonFailResult(int resultCodeInt);

    public BaseResponse composeFailResponse(String resultCode, String resultMsg);

    public BaseResponse composeFailResponse(String resultCode);

    public BaseResponse composeFailResponse(String resultCode, Object[] args);

    public BaseResponse composeDBFailResponse();

    public BaseResponse composeCommonSuccessResponse();

    public BaseResponse composeSuccessBaseResponse(Object payload);

}
