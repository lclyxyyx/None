package xyz.thetbw.blog.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.thetbw.blog.annotation.PowerCheck;
import xyz.thetbw.blog.data.entity.Power;
import xyz.thetbw.blog.exception.RequestException;
import xyz.thetbw.blog.service.PowerService;

/**
 * 负责权限管理相关
 */
@RestController
@RequestMapping("/admin/api/power")
public class AdminPowerController {

    @Autowired
    PowerService powerService;

    @GetMapping("/list")
    public Object listPower(){
        return powerService.listPower();
    }


    @PostMapping("/add")
    @PowerCheck(role = -1)
    public Object addPower(@RequestBody Power power) throws RequestException {
        powerService.addPower(power);
        return null;
    }

    @PostMapping("/update")
    @PowerCheck(role = -1)
    public Object updatePower(@RequestBody Power power) throws RequestException {
        powerService.updatePower(power);
        return null;
    }

    @GetMapping("/delete")
    @PowerCheck(role = -1)
    public Object deletePower(@RequestParam Integer id) throws RequestException {
        powerService.deletePower(id);
        return null;
    }





}
