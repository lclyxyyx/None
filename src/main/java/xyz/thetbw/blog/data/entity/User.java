package xyz.thetbw.blog.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.lang.model.element.NestingKind;
import java.util.List;

public class User {
    public static final String TABLE_NAME="blog_user";

    /**
     * 超级管理员
     */
    public static final int USER_ROLE_SUPER_ADMIN=-1;
    /**
     * 管理员
     */
    public static final int USER_ROLE_ADMIN=0;
    /**
     * 注册用户
     */
    public static final int USER_ROLE_MEMBER =1;
    /**
     * 访客
     */
    public static final int USER_ROLE_GUEST=2;

    private int user_id;
    private String user_name;
    private String user_nickname;
    @JsonIgnore
    private int user_pass;
    private Integer user_role;
    private String user_avatar_url;
    private String user_email;

    private String user_pass_string;

    /**
     * 用户所具有的权限
     */
    private List<Power> powers;

    public User clearPass(){
        this.user_pass=0;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("user_id:").append(this.user_id).append("\n")
                .append("nick_name:").append(this.user_nickname).append("\n")
                .append("user_name:").append(user_name).append("\n")
                .append("user_pass:").append(user_pass).append("\n")
                .append("email:").append(user_email).append("\n");

        return builder.toString();
    }

    public String getUser_pass_string() {
        return user_pass_string;
    }

    public void setUser_pass_string(String user_pass_string) {
        this.user_pass_string = user_pass_string;
    }

    public List<Power> getPowers() {
        return powers;
    }

    public void setPowers(List<Power> powers) {
        this.powers = powers;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public int getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(int user_pass) {
        this.user_pass = user_pass;
    }

    public Integer getUser_role() {
        return user_role;
    }

    public void setUser_role(Integer user_role) {
        this.user_role = user_role;
    }

    public String getUser_avatar_url() {
        return user_avatar_url;
    }

    public void setUser_avatar_url(String user_avatar_url) {
        this.user_avatar_url = user_avatar_url;
    }
}
