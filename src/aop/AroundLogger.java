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
		log.info("调用"+jp.getTarget()+"的"
				+jp.getSignature().getName()+"方法。方法参数:"
				+Arrays.toString(jp.getArgs()));
		try{
			Object result = jp.proceed();//执行目标方法并获得其返回值
			log.info("调用"+jp.getTarget()+"的"
					+jp.getSignature().getName()+"方法。方法返回值:"+result);
			return result;
		}catch (Throwable e) {
			log.error(jp.getSignature().getName()+"方法发生异常"+e);
			throw e;
		}finally{
			log.error(jp.getSignature().getName()+"方法结束执行。");
		}
	}
}
