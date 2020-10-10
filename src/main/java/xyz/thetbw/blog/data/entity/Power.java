package xyz.thetbw.blog.data.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * 权限表
 */
public class Power {
    public static final int POWER_TYPE_MENU=1; //权限类型 菜单
    public static final int POWER_TYPE_ACTION=2; //权限类型 操作 增删该之类


    private int power_id;

    /**
     *权限名称
     */
    private String power_name;


    /**
     * 权限标识，用于后台鉴别权限
     */
    private String power_ident;

    /**
     * 权限类型 取自静态变量 POWER_TYPE_MENU POWER_TYPE_ACTION
     */
    private Integer power_type;

    /**
     * 父权限id
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer power_parent_id;

    /**
     * 父权限 （暂时弃用）
     */
    private Power parent;

    /**
     * 子权限
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Power> children;


    public String getPower_ident() {
        return power_ident;
    }

    public void setPower_ident(String power_ident) {
        this.power_ident = power_ident;
    }

    public List<Power> getChildren() {
        return children;
    }

    public void setChildren(List<Power> children) {
        this.children = children;
    }

    public int getPower_id() {
        return power_id;
    }

    public void setPower_id(int power_id) {
        this.power_id = power_id;
    }

    public String getPower_name() {
        return power_name;
    }

    public void setPower_name(String power_name) {
        this.power_name = power_name;
    }

    public Integer getPower_type() {
        return power_type;
    }

    public void setPower_type(Integer power_type) {
        this.power_type = power_type;
    }

    public Integer getPower_parent_id() {
        return power_parent_id;
    }

    public void setPower_parent_id(Integer power_parent_id) {
        this.power_parent_id = power_parent_id;
    }

    public Power getParent() {
        return parent;
    }

    public void setParent(Power parent) {
        this.parent = parent;
    }
}
