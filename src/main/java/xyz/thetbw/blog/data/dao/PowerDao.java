package xyz.thetbw.blog.data.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import xyz.thetbw.blog.data.entity.Power;

import java.util.List;

/**
 * 负责权限的增删改查
 */
@Mapper
@Component
public interface PowerDao {

    /**
     * 查询指定权限
     * @param power_id
     * @return
     */
    @Select("select * from blog_power where power_id=#{power_id}")
    @Results({
            @Result(property = "power_id",column = "power_id"),
            @Result(property = "children",column = "power_id",javaType = List.class,
                    many = @Many(select = "xyz.thetbw.blog.data.dao.PowerDao.listChildren"))
    })
    Power selectPower(int power_id);


    /**
     * 根据标识符查找权限
     * @param power_ident
     * @return
     */
    @Select("select * from blog_power where power_ident=#{power_ident}")
    @Results({
            @Result(property = "power_id",column = "power_id"),
            @Result(property = "children",column = "power_id",javaType = List.class,
                    many = @Many(select = "xyz.thetbw.blog.data.dao.PowerDao.listChildren"))
    })
    Power selectPowerByIndent(String power_ident);

    /**
     * 查询某个权限下面的所有子权限
     * @param power_id 权限id
     * @return 权限列表
     */
    @Select("select * from blog_power where power_parent_id = #{power_id}")
    @Results({
            @Result(property = "power_id",column = "power_id"),
            @Result(property = "children",column = "power_id",javaType = List.class,
                    many = @Many(select = "xyz.thetbw.blog.data.dao.PowerDao.listChildren"))
    })
    List<Power> listChildren(int power_id);

    /**
     * 查询所有权限 ，不区分父子关系
     * @param index 分页开始
     * @param length 分页长度
     * @return
     */
    @Select("select * from blog_power limit #{index},#{length}")
    List<Power> listPower(int index,int length);

    /**
     * 查询根权限节点
     * @return
     */
    @Select("select * from blog_power where power_parent_id is NULL or power_parent_id =0 limit #{index},#{length}")
    @Results({
            @Result(property = "power_id",column = "power_id"),
            @Result(property = "children",column = "power_id",javaType = List.class,
            many = @Many(select = "xyz.thetbw.blog.data.dao.PowerDao.listChildren"))
    })
    List<Power> listRootPower(int index,int length);


    @Delete("delete from blog_power where power_id=#{power_id}")
    int deletePower(int power_id);

    @Update("update blog_power set " +
            "power_name = #{power_name}," +
            "power_ident=#{power_ident}," +
            "power_type=#{power_type}," +
            "power_parent_id=#{power_parent_id} where power_id = #{power_id}")
    int updatePower(Power power);


    @Insert("insert into blog_power(power_name,power_ident,power_type,power_parent_id) values " +
            "(#{power_name},#{power_ident},#{power_type},#{power_parent_id})")
    int insertPower(Power power);
}
