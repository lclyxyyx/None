package xyz.thetbw.blog.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.thetbw.blog.annotation.PowerCheck;
import xyz.thetbw.blog.data.constant.FieldKey;
import xyz.thetbw.blog.data.entity.Power;
import xyz.thetbw.blog.data.entity.User;
import xyz.thetbw.blog.exception.RequestException;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;


/**
 * 通过注解实现权限控制
 */
@Aspect
@Component
public class PowerAspect {
    private static final Logger LOG = LoggerFactory.getLogger(PowerAspect.class);
    @Autowired
    HttpServletRequest request;

    @Around("@annotation(xyz.thetbw.blog.annotation.PowerCheck)")
    public Object check(ProceedingJoinPoint joinPoint) throws RequestException {
        MethodSignature methodSignature =(MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        PowerCheck powerCheck = method.getAnnotation(PowerCheck.class);
        User user =(User) request.getSession().getAttribute(FieldKey.USER_ACCESS);
        if (user==null){
            throw new RequestException("你必须登录才能继续操作");
        }
        if (user.getUser_role()==User.USER_ROLE_SUPER_ADMIN){
            try {
               return joinPoint.proceed(joinPoint.getArgs());
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }

        }
        if (user.getUser_role()>powerCheck.role()){
            throw new RequestException("你没有足够的权限继续操作");
        }
        if (powerCheck.powers()!=null||powerCheck.powers().length>0){
            List<Power> userPowers = user.getPowers();
            if (userPowers==null||userPowers.size()==0){
                throw new RuntimeException("你没有足够的权限继续操作");
            }
            for (String power:powerCheck.powers()){
                boolean has =false;
                LOG.debug("---当前用户->"+user.getUser_name());
                LOG.debug("---当前用户权限数量->"+userPowers.size());
                LOG.debug("---当前需要权限->"+power);
                for (Power userPower:userPowers){
                    LOG.debug("用户权限->"+userPower.getPower_ident());
                    if (userPower.getPower_ident().equals(power)){

                        has=true;
                    }
                }
                if (!has){
                    throw new RuntimeException("你没有足够的权限继续操作");
                }
            }
        }
        try {
            return joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
