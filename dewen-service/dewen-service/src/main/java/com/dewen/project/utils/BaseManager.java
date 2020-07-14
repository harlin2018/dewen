package com.dewen.project.utils;


import com.dewen.project.constants.MessageConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;


/**
 * Created by 01468339 on 19/8/2016.
 */

@Component
public class BaseManager implements IBaseManager, MessageConstant {


    @Autowired
    private MessageSource messageSource;

    private static final Logger logger = LoggerFactory.getLogger(BaseManager.class);

    private static final char[] HEXADECIMAL = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    @Value("${error.msg.default:server inner errors}")
    private String errorMsgDefault;
    @Override
    public BaseResponse composeCommonFailResult(int resultCodeInt) {

        BaseResponse failResult = new BaseResponse();
        logger.info("Fail to this request , result code is %s ",resultCodeInt);
        switch (resultCodeInt) {
            case RESULT_CODE_METHOD_NOT_SUPPORTED_INT:
            case RESULT_CODE_BAD_REQUEST_INT: {
                failResult.setResultCode(RESULT_CODE_BAD_REQUEST);
                failResult.setResultMsg(getErrorMsg(RESULT_CODE_BAD_REQUEST));
                break;
            }
            case RESULT_CODE_PATH_INVALID_INT: {
                failResult.setResultCode(RESULT_CODE_PATH_INVALID);
                failResult.setResultMsg(getErrorMsg(RESULT_CODE_PATH_INVALID));
                break;
            }
            case RESULT_CODE_UNAUTHORIZED_INT: {
                failResult.setResultCode(RESULT_CODE_UNAUTHORIZED);
                failResult.setResultMsg(getErrorMsg(RESULT_CODE_UNAUTHORIZED));
                break;
            }
            case RESULT_CODE_FORBIDDEN_INT: {
                failResult.setResultCode(RESULT_CODE_FORBIDDEN);
                failResult.setResultMsg(getErrorMsg(RESULT_CODE_FORBIDDEN));
                break;
            }

            case RESULT_CODE_UNKNOWN_ERROR_INT:
            default: {
                failResult.setResultCode(RESULT_CODE_UNKNOWN_ERROR);
                failResult.setResultMsg(getErrorMsg(RESULT_CODE_UNKNOWN_ERROR));
                break;
            }
        }
        return failResult;
    }

    @Override
    public BaseResponse composeFailResponse(String resultCode, String resultMsg) {
        BaseResponse failResult = new BaseResponse();
        failResult.setResultCode(resultCode);
        failResult.setResultMsg(resultMsg);
        return failResult;
    }

    @Override
    public BaseResponse composeFailResponse(String resultCode) {
        Object[] args = null;
        return composeFailResponse(resultCode, args);
    }

    @Override
    public BaseResponse composeFailResponse(String resultCode, Object[] args) {
        BaseResponse failResult = new BaseResponse();
        String resultMsg = getErrorMsg(resultCode, args);
        if (TextUtils.isEmpty(resultMsg)) {
            //logger.info("Fail to get the result msg , resultCode={}", resultCode);
            resultCode = RESULT_CODE_FAIL_TO_GET_RESULT_MSG;
            resultMsg = RESULT_MSG_FAIL_TO_GET_RESULT_MSG;
        }
        failResult.setResultCode(resultCode);
        failResult.setResultMsg(resultMsg);
        return failResult;
    }


    public BaseResponse composeCommonSuccessResponse(){

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResultCode(RESULT_CODE_OK);
        baseResponse.setResultMsg(RESULT_MSG_OK);
        return baseResponse;
    }

    @Override
    public BaseResponse composeDBFailResponse(){
        BaseResponse failResult = new BaseResponse();

        logger.info("Fail to get the result msg ");
        String  resultCode = RESULT_DB_INVALID;
        String   resultMsg = RESULT_DB_INVALID_MSG;

        failResult.setResultCode(resultCode);
        failResult.setResultMsg(resultMsg);
        return failResult;
    }

    public String getErrorMsg(String errorCode) {
        return getErrorMsg(errorCode,null);
    }

    public String getErrorMsg(String errorCode, Object[] args) {
        if (TextUtils.isEmpty(errorCode)) {
            return null;
        }

        String errorMsg =  messageSource.getMessage(errorCode, args, errorMsgDefault, Locale.getDefault());
        return errorMsg;
    }

    @Override
    public BaseResponse composeSuccessBaseResponse(Object payload) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResultCode(RESULT_CODE_OK);
        baseResponse.setResultMsg(RESULT_MSG_OK);
        if (payload != null) {
            baseResponse.setPayload(payload);
        }
        return baseResponse;
    }

}