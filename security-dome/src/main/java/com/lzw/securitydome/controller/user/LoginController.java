package com.lzw.securitydome.controller.user;

import com.lzw.securitydome.common.JsonData;
import com.lzw.securitydome.param.UserParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * swagger 访问地址 http://127.0.0.1:8080/swagger-ui.html
 */

@RestController
@RequestMapping("/login")
@Api(value = "RestFul测试接口",tags = "RestFulController",description = "测试接口")
public class LoginController {

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

    @DeleteMapping (value = "/User" , produces = "application/json; charset=utf-8")// 解决返回值乱码问题
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

    @GetMapping (value = "/login-success" , produces = {"text/plain;charset=UTF-8"})
    public String loginSuccess(){
        return "登录成功";
    }

    @PreAuthorize("hasAuthority('p1')")
    @GetMapping("/r/r1")
    public String r1(){
        String username = null;

        // 当前认证通过的用户身份
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 用户身份
        Object principal = authentication.getPrincipal();
        if(principal ==  null){
            username = "匿名";
        }
        if (principal instanceof UserDetails){
            UserDetails userDetails = (UserDetails)principal;
            username = userDetails.getUsername();
        }
        return "okr1:"+username;
    }


    @PreAuthorize("hasAuthority('p2')")
    @GetMapping("/r/r2")
    public String r2(){
        return "okr2";
    }
}
