package com.dewen.project.aspect;


import com.dewen.project.utils.NullAwareBeanUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/7/14 14:52
 */
@Aspect
@Configuration
public class ControllerAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

	//切点
	@Pointcut("execution(* com.dewen.project.controller.*.list(..))")
	private void list(){}

    //切点
    @Pointcut("execution(* com.dewen.project.service.impl.*.list(..))")
    private void serviceList(){}

    //切点
    @Pointcut("execution(* com.dewen.project.service.impl.*.findById(..))")
    private void serviceFindById(){}

	@Around("com.dewen.project.aspect.ControllerAspect.list()")
	public Object list(ProceedingJoinPoint joinPoint) throws Throwable {
		//增加 enable =1 条件
		Object[] args = joinPoint.getArgs();
		if(args.length>0){
			Object domain = args[0];
			//如果多对一，当前是多，必须依赖一端，可以设置domain对象的属性“一”对象的enabled=1
			domain = setDomainField(joinPoint, domain, "enabled", 1);
			args[0] = domain;
			if(args.length>1 && args[1] instanceof Integer){
				Integer pageNumber = (Integer) args[1];
				if(pageNumber>0) {
					pageNumber--;
					args[1]=pageNumber;
				}
			}
		}
		Object obj = joinPoint.proceed(args);
		return obj;
	}

	private Object setDomainField(ProceedingJoinPoint joinPoint, Object domain, String fieldName, Object value) throws Throwable {
		String controllerName = joinPoint.getSignature().getDeclaringType().getName();
		if(domain == null){
			String modelName = StringUtils.replace(controllerName,".controller",".domain");
			modelName = StringUtils.substringBeforeLast(modelName, "Controller");
			domain = ClassUtils.forName(modelName, ClassUtils.getDefaultClassLoader()).newInstance();
		}
		Field field = ReflectionUtils.findField(domain.getClass(),fieldName);
		if(field != null){
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field,domain,value);
		}
		return domain;
	}





    @Around("com.dewen.project.aspect.ControllerAspect.serviceList()")
    public Object listAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = joinPoint.proceed();
        try{
            NullAwareBeanUtilsBean.removeContentRelations(obj);
        }catch (Exception e){
            logger.error("",e);
        }
        return obj;
    }

    @Around("com.dewen.project.aspect.ControllerAspect.serviceFindById()")
    public Object getAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = joinPoint.proceed();
        try{
            NullAwareBeanUtilsBean.removeRelations(obj);
        }catch (Exception e){
            logger.error("",e);
        }
        return obj;
    }

}