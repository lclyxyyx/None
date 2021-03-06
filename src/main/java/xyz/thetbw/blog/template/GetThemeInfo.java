package xyz.thetbw.blog.template;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;
import org.springframework.stereotype.Component;
import xyz.thetbw.blog.data.AppContext;
import xyz.thetbw.blog.data.constant.ConstValue;
import xyz.thetbw.blog.util.ReflectUtils;

import java.util.List;

@Component
public class GetThemeInfo implements TemplateMethodModelEx {

    @Override
    public Object exec(List list) throws TemplateModelException {
        if (list.size()==0) return null;
        TemplateScalarModel templateScalarModel = (TemplateScalarModel) list.get(0);
        Object value = ReflectUtils.getInstance().getFieldValue(templateScalarModel.getAsString(), AppContext.getInstance().theme,Object.class);
        if (value!=null) return value;
        return "";
    }
}
