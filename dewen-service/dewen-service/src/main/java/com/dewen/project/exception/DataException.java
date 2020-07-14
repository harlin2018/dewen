package com.dewen.project.exception;

import lombok.Data;

/**
 * <p>Title:业务异常</p>
 * <p>Description:类务异常，不影系统运行的异常，相当于只返回信息给前端</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: PCCW</p>
 *
 * @author Welge
 * @version 1.0
 * @date 2019/7/23
 */
@Data
public class DataException extends CommonException{

    /**
     * 错误编码
     */
    private String code;

    /**
     * 参数
     */
    private Object[] args;

    public DataException() {
    }

    public DataException(String message) {
        super(message);
    }

    public DataException(String message, Object ... args) {
        super(message);
        this.args = args;
    }
    public DataException(String code, Throwable e) {
        super(e);
        this.code = code;
    }

    public DataException(String code, String message) {
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
