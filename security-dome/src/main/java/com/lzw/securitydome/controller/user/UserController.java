package com.lzw.securitydome.controller.user;

import com.lzw.securitydome.common.JsonData;
import com.lzw.securitydome.param.UserParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2")
@Api(value = "RestFul测试接口-UserController",tags = "UserController",description = "用户信息接口")
public class UserController {
    @GetMapping(value = "/User" , produces = "application/json; charset=utf-8")// 解决返回值乱码问题
    @ApiOperation(value = "查询用户", notes = "查询用户")
    public JsonData getUse(UserParam param){
        /*System.out.println("param:"+param);
        param.setUsername("11");
        if(param.getDeptId()!=null){
            throw new ParamException("111111111111");
        }*/
        //BeanValidator.check(param);
        return  JsonData.success(param);
    }

    @PostMapping(value = "/User" , produces = "application/json; charset=utf-8")// 解决返回值乱码问题
    @ApiOperation(value = "插入用户", notes = "插入用户")
    public JsonData setUse(UserParam param){
        /*System.out.println("param:"+param);
        param.setUsername("11");
        if(param.getDeptId()!=null){
            throw new ParamException("111111111111");
        }*/
        //BeanValidator.check(param);
        return  JsonData.success(param);
    }

    @PutMapping (value = "/User" , produces = "application/json; charset=utf-8")// 解决返回值乱码问题
    @ApiOperation(value = "更新用户", notes = "更新用户")
    public JsonData putUse(UserParam param){
        /*System.out.println("param:"+param);
        param.setUsername("11");
        if(param.getDeptId()!=null){
            throw new ParamException("111111111111");
        }*/
        //BeanValidator.check(param);
        return  JsonData.success(param);
    }

    @DeleteMapping(value = "/User" , produces = "application/json; charset=utf-8")// 解决返回值乱码问题
    @ApiOperation(value = "更新用户", notes = "更新用户")
    public JsonData deleteUse(UserParam param){
        /*System.out.println("param:"+param);
        param.setUsername("11");
        if(param.getDeptId()!=null){
            throw new ParamException("111111111111");
        }*/
        //BeanValidator.check(param);
        return  JsonData.success(param);
    }
}
