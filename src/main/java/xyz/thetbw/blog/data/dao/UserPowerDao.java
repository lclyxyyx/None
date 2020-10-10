package xyz.thetbw.blog.data.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import xyz.thetbw.blog.data.entity.Power;

import java.util.List;

/**
 * 负责用户和权限之间的关系
 */
@Mapper
@Component
public interface UserPowerDao {

    /**
     * 查询一个用户的所有权限
     * @return
     */
    @Select("select * from blog_power where power_id in (select power_id from blog_user_power where user_id=#{user_id})")
    List<Power> selectUserPowers(int user_id);

    /**
     * 设置用户和权限的关联
     * @param user_id
     * @param power_id
     * @return
     */
    @Insert("insert into blog_user_power(user_id,power_id) values(#{user_id},#{power_id})")
    int insertUserPower(int user_id,int power_id);


    /**
     * 删除用户的全部权限
     * @return
     */
    @Delete("delete from blog_user_power where user_id=#{user_id}")
    int deleteUserPowers(int user_id);
}
