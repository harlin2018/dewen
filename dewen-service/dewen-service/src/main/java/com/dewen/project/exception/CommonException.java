package com.dewen.project.exception;

/**
 * <p>Title:公共异常</p>
 * <p>Description:可预期的系统异常</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: PCCW</p>
 *
 * @author Welge
 * @version 1.0
 * @date 2019/7/23
 */
public class CommonException extends RuntimeException{

    /**
     * 错误编码
     */
    private String code;

    /**
     * 参数
     */
    private Object[] args;

    public CommonException() {
    }

    public CommonException(String message) {
        super(message);
    }

    public CommonException(String message, Object ... args) {
        super(message);
        this.args = args;
    }
    public CommonException(String code, Throwable e) {
        super(e);
        this.code = code;
    }

    public CommonException(Throwable e) {
        super(e);
    }

    public CommonException(Throwable e, String code, String message) {
        super(message, e);
        this.code = code;
    }

    public CommonException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
