package xyz.thetbw.blog.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.thetbw.blog.data.entity.User;
import xyz.thetbw.blog.exception.RequestException;
import xyz.thetbw.blog.service.UserService;
import xyz.thetbw.blog.util.PageHelper;

import java.util.List;

/**
 * 后台用户管理相关
 */
@RestController
@RequestMapping("/admin/api/user")
public class AdminUserController {

    @Autowired
    UserService userService;

    @GetMapping("list")
    public Object listUser(@RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "20") Integer length,
                           @RequestParam(required = false,name = "user_role") Integer user_role){
        User user = new User();
        user.setUser_role(user_role);
        List<User> users = userService.listUser(page,length,user);
        int count = userService.listUserCount(user);
        PageHelper<User> userPageHelper = new PageHelper<>(users,page,length,count);
        return userPageHelper;
    }

    @PutMapping("/add")
    public void addUser(@RequestBody User user) throws RequestException {
        userService.addUser(user);
    }

    @PostMapping("/update")
    public void updateUser(@RequestBody User user) throws RequestException {
        userService.updateUser(user);
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) throws RequestException {
        if (id==null){
            throw new RequestException("删除id不能为空");
        }
        userService.deleteUser(id);

    }



}
