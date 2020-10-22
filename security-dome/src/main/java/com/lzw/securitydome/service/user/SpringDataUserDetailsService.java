package com.lzw.securitydome.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpringDataUserDetailsService implements UserDetailsService {

    //@Autowired
   // private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("=========UserDetailsService=============");
        // 将来链接数据库根据账号查询用户信息
        /*SysUser user = userDao.getUserByUsername(s);
        if(user==null){
            return  null;
        }*/

        // 暂时采用模拟方式
        // 登录账号
       // System.out.println("username="+s+"    "+user.getPassword());
        // 可以使用语句查询url对应的code
        //List permissions = userDao.findPermissionsByUserId(user.getId()+"");
        List<String> permissions= new ArrayList<String>();
        permissions.add("p1");
        permissions.add("p3");
        String[] sts= new String[permissions.size()];
        permissions.toArray(sts);
        // 暂时采用模拟方式
        UserDetails userDetails = User.withUsername(s).password("123").authorities(sts).build();
        return userDetails;
        // 另一种新的返回 ,这种缺陷很明显每个权限需要与“，”号隔开 不推荐
        //return  new User(user.getUsername(),user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("p1,p2"));
    }
}
