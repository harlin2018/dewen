package com.dewen.project.exception;

/**
 * <p>Title:系统异常</p>
 * <p>Description:不可预期的系统异常</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: PCCW</p>
 *
 * @author Welge
 * @version 1.0
 * @date 2019/7/23
 */
public class SysException extends CommonException {
    public SysException(Throwable e){
        super(e, null, e.getMessage());
    }

    public SysException(Throwable e, String code, String message){
        super(e, code, message);
    }


    public SysException(String code, String message){
        super(code, message);
    }

    public SysException(String code){
        super(code, (String) null);
    }
}
