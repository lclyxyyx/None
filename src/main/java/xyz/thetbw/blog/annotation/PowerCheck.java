package xyz.thetbw.blog.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import xyz.thetbw.blog.data.entity.User;

import java.lang.annotation.*;

/**
 * 权限控制注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PowerCheck {

    /**
     *只允许拥有指定权限的访问
     * @return
     */
    String[] powers() default {};

    /**
     * 只允许某个等级以上的访问
     * @return
     */
    int role() default User.USER_ROLE_GUEST;

}
