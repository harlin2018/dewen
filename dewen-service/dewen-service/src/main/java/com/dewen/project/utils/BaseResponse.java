package com.dewen.project.utils;


import com.dewen.project.constants.ErrorCode;
import com.dewen.project.exception.DataException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {

    private String resultCode;

    private String resultMsg;

    private T payload;

    private String totalPage;

    private String token;

    private String displayUsername;

    private String username;

    private String role;

    @JsonIgnore
    public boolean isOk(){
        return ErrorCode.SUCCESS.equals(resultCode);
    }

    public BaseResponse() {
    }

    public BaseResponse(String code, String message) {
        this.resultCode = code;
        this.resultMsg = message;
    }



    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }


    @JsonIgnore
    public void check(){
        if (!isOk()) {
            throw new DataException(resultCode, resultMsg);
        }
    }

    @JsonIgnore
    public T getData(){
        if (!isOk()) {
            throw new DataException(resultCode, resultMsg);
        }
        return payload;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }


    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayUsername() {
        return displayUsername;
    }

    public void setDisplayUsername(String displayUsername) {
        this.displayUsername = displayUsername;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseResponse{");
        if (resultCode != null) {
            sb.append("resultCode='").append(resultCode).append('\'');
        }
        if (resultMsg != null) {
            sb.append(",resultMsg='").append(resultMsg).append('\'');
        }
        if (payload != null) {
            sb.append(",payload=").append(payload);
        }

        if (totalPage != null) {
            sb.append(",totalPage='").append(totalPage).append('\'');
        }
        if (token != null) {
            sb.append(",token='").append(token).append('\'');
        }
        if (displayUsername != null) {
            sb.append(",displayUsername='").append(displayUsername).append('\'');
        }
        if (username != null) {
            sb.append(",username='").append(username).append('\'');
        }
        if (role != null) {
            sb.append(",role='").append(role).append('\'');
        }

        sb.append('}');
        return sb.toString();
    }
}
