package xyz.thetbw.blog.data.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;
import xyz.thetbw.blog.data.entity.User;

import java.util.List;

@Component
@Mapper
public interface UserDao {

     int getCount();

     @Select("select * from blog_user where user_role=-1 order by user_id limit 1")
     User getAdminUser();

     List<User> getAll();


     List<User> getAllPaging(int index, int length) ;


     List<User> getAllPagingAndFilter(int index, int length, int filter);


     List<User> getAllPagingOrderAndFilter(int index, int length, int filter, String order);


    @Insert("insert into blog_user(user_name,user_nickname,user_pass,user_role,user_avatar_url,user_email) values(" +
            "#{user_name},#{user_nickname},#{user_pass},#{user_role},#{user_avatar_url},#{user_email})")
    @Options(useGeneratedKeys = true,keyProperty = "user_id")
     void add(User o);


    @Update("update blog_user set user_name=#{user_name},user_nickname=#{user_nickname},user_pass=#{user_pass}," +
            "user_role=#{user_role},user_avatar_url=#{user_avatar_url},user_email=#{user_email} where user_id=#{user_id}")
     void update(User o) ;

    @Update("update blog_user set user_name=#{user_name},user_nickname=#{user_nickname}, " +
            "user_role=#{user_role},user_avatar_url=#{user_avatar_url},user_email=#{user_email} where user_id=#{user_id}")
    void updateWithOutPass(User o);


    @Delete("delete from blog_user where user_id=#{id}")
     void delete(int id) ;


    @Select("select * from blog_user where user_id =#{id}")
     User get(int id);

    @Select("select * from blog_user where user_role=#{user_role}")
    List<User> selectAllUserByRole(int user_role);

    @Select("select * from blog_user where user_name =#{user_name}")
    @Results({
            @Result(property = "user_id",column = "user_id"),
            @Result(property = "powers",column = "user_id",javaType = List.class,
                    many = @Many(select = "xyz.thetbw.blog.data.dao.UserPowerDao.selectUserPowers"))
    })
    User getUserByName(String user_name);

    @Select("select * from blog_user where user_nickname =#{user_nickname}")
    User getUserByNickName(String user_nickname);

    /**
     * 根据角色来批量删除用户
     */
    @Delete("delete from blog_user where user_role=#{user_role}")
    void deleteUserByRole(int user_role);


    /**
     * 以上为遗留方法
     */

    @SelectProvider(value = UserDynaSqlProvider.class,method = "selectSql")
    @Results({
            @Result(property = "user_id",column = "user_id"),
            @Result(property = "powers",column = "user_id",javaType = List.class,
            many = @Many(select = "xyz.thetbw.blog.data.dao.UserPowerDao.selectUserPowers"))
    })
    List<User> selectUser(@Param("index") int index,@Param("length") int length,User user,Integer user_role);

    @SelectProvider(value = UserDynaSqlProvider.class,method = "countSql")
    int selectUserCount(User user);

    @Delete("delete from blog_user where user_id=#{user_id}")
    int deleteUser(int user_id);

    /**
     * 提供查询时筛选的动态sql
     */
    class UserDynaSqlProvider{
        public String  selectSql(User user){
            String sql =new SQL(){
                {
                    SELECT("*");
                    FROM("blog_user");
                    if (user!=null){
                        if (user.getUser_role()!=null){
                            WHERE("user_role=#{user_role}");
                        }
                        if (user.getUser_nickname()!=null){
                            WHERE("user_nickname=#{user_nickname}");
                        }
                    }
                    LIMIT("#{index},#{length}");
                }
            }.toString();
            System.out.println("----->"+sql);
            return sql;
        }

        public String countSql(User user){
            return new SQL(){
                {
                    SELECT("count(1)");
                    FROM("blog_user");
                    if (user!=null){
                        if (user.getUser_role()!=null){
                            WHERE("user_role=#{user_role}");
                        }
                        if (user.getUser_nickname()!=null){
                            WHERE("user_nickname=#{user_nickname}");
                        }
                    }
                }
            }.toString();
        }
    }

}
