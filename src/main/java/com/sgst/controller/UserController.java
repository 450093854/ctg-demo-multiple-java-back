package com.sgst.controller;

import com.sgst.domain.User;
import com.sgst.mapper.UserMapper;
import com.sgst.utils.R;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("save")
    @ApiOperation(tags = {"mysql"}, value = "添加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "昵称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "name", value = "姓名", required = true, paramType = "query"),
            @ApiImplicitParam(name = "mobile", value = "手机号", required = true, paramType = "query")
    })
    R saveUser(String username, String name, String mobile) {
        Pattern p = null;
        Matcher m = null;
        boolean isMatch = false;
        //制定验证条件
        String regex2 = "^((13[0-9])|(14[579])|(15([0-3,5-9]))|(16[6])|(17[0135678])|(18[0-9]|19[89]))\\d{8}$";
        if(null != mobile){
            p = Pattern.compile(regex2);
            m = p.matcher(mobile);
            isMatch = m.matches();
        }
        if(!isMatch){
            return R.error(500,"手机号格式不正确");
        }
        User user = new User(username, name, mobile);
        userMapper.insert(user);
        return R.ok();
    }

    @GetMapping("list")
    @ApiOperation(tags = {"mysql"}, value = "查看所有用户")
    List<User> list() {
        return userMapper.selectAll();
    }


    @GetMapping("get")
    @ApiOperation(tags = {"mysql"}, value = "查看指定用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true, paramType = "query", dataType = "int"),
    })
    User get(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @PutMapping("update")
    @ApiOperation(tags = {"mysql"}, value = "更改用户")
    R update(User user) {
        String mobile = user.getMobile();
        // 检测重复代码
        Pattern p = null;
        Matcher m = null;
        boolean isMatch = false;
        //制定验证条件
        String regex2 = "^((13[0-9])|(14[579])|(15([0-3,5-9]))|(16[6])|(17[0135678])|(18[0-9]|19[89]))\\d{8}$";
        if(null != mobile){
            p = Pattern.compile(regex2);
            m = p.matcher(mobile);
            isMatch = m.matches();
        }
        if(!isMatch){
            return R.error(500,"手机号格式不正确");
        }
        if (userMapper.updateByPrimaryKey(user) == 1) {
            return R.ok();
        }
        return R.error();
    }

    @DeleteMapping("remove")
    @ApiOperation(tags = {"mysql"}, value = "查看指定用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true, paramType = "query", dataType = "int"),
    })
    R remove(Integer id) {
        if (userMapper.deleteByPrimaryKey(id) == 1) {
            return R.ok();
        }
        return R.error();
    }

}
