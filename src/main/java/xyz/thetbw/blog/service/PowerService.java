package xyz.thetbw.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.thetbw.blog.data.dao.PowerDao;
import xyz.thetbw.blog.data.entity.Power;
import xyz.thetbw.blog.exception.RequestException;

import java.util.List;

/**
 * 权限控制相关
 */
@Service
public class PowerService {

    @Autowired
    PowerDao powerDao;

    //列表
    public List<Power> listPower(){
        List<Power> power = powerDao.listRootPower(0,100);


        return power;
    }

    //添加权限数据
    public void addPower(Power power) throws RequestException {
        powerDao.insertPower(power);
    }

    //更新权限数据
    @Transactional
    public void updatePower(Power power) throws RequestException {
        Integer parent_id = power.getPower_parent_id();
        if (parent_id!=null){
            Power parent = powerDao.selectPower(parent_id);
            if (parent==null){
                throw new RequestException("不存在的父权限");
            }
            List<Power> children = powerDao.listChildren(power.getPower_id());
            for (Power child:children){
                if (parent_id.equals(child.getPower_id())){
                    throw new RequestException("禁止父子嵌套");
                }
            }
            if (parent_id.equals(power.getPower_id())){
                throw new RequestException("父元素不能是自己");
            }
        }

        powerDao.updatePower(power);
    }

    //删除权限数据
    @Transactional
    public void deletePower(int power_id) throws RequestException {
        Power power =powerDao.selectPower(power_id);
        if (power==null) return;
        if (power.getChildren()!=null&&power.getChildren().size()>0){
            throw new RequestException("子权限不为空，不能删除");
        }
        powerDao.deletePower(power_id);
    }

}
