package aop;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AroundLogger {
	private static final Logger log = Logger.getLogger(AroundLogger.class);
	@Around("execution(* service..*.*(..))")
	public Object aroundLogger(ProceedingJoinPoint jp) throws Throwable{
		log.info("����"+jp.getTarget()+"��"
				+jp.getSignature().getName()+"��������������:"
				+Arrays.toString(jp.getArgs()));
		try{
			Object result = jp.proceed();//ִ��Ŀ�귽��������䷵��ֵ
			log.info("����"+jp.getTarget()+"��"
					+jp.getSignature().getName()+"��������������ֵ:"+result);
			return result;
		}catch (Throwable e) {
			log.error(jp.getSignature().getName()+"���������쳣"+e);
			throw e;
		}finally{
			log.error(jp.getSignature().getName()+"��������ִ�С�");
		}
	}
}
