package xyz.thetbw.blog.util;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.Properties;

@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
@Component
public class MysqlInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation u) throws Throwable {
//        Executor  executor =(Executor) invocation.getTarget();
//        System.out.println("name:"+invocation.getMethod().getName());
//        System.out.println("object:"+invocation.getTarget().getClass().getName());
//        MappedStatement statement = (MappedStatement) invocation.getArgs()[0];
//        statement.
//        MetaObject metaObject = SystemMetaObject.forObject(statement);
//        Statement statement1 = (Statement) metaObject.getValue("stmt.statement");
//        Object result = invocation.proceed();
        return null;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
