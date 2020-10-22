package com.lzw.securitydome.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

//@Component
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    // 定义用户信息服务（查询用户信息） 弃掉是因为service层需要一个类继承UserDetailsService接口
    /*@Bean
    @Override
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin").password("123").authorities("p1").build());
        manager.createUser(User.withUsername("lzw").password("123").authorities("p2").build());
        return manager;
    }*/

    // 密码编码器  无需加密处理
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    // 密码编码器
   /* @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
*/
    // 安全拦截机制
    /**
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected  void configure(HttpSecurity http)throws Exception{
        http.authorizeRequests()
//                .antMatchers("/**/r/r1/**").hasAuthority("p1")// 判断用户是否存在p1权限，应为p1是对应这个链接的
 //               .antMatchers("/**/r/r2/**").hasAuthority("p2")
                .antMatchers("/login").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/**/r/**").authenticated()// 拦截url有/r
            .anyRequest().authenticated()//.permitAll() // 除了/r/**，其它的请求可以访问
            .and()
            //.formLogin()// 允许表单登录
            //.loginPage("/login-view")
            //.loginProcessingUrl("/login")// 表单提交的url
            //.successForwardUrl("/login/login-success") // 登录成功后跳转页面
                // 配置ajax登录成功后返回实体对象
                /*.successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter out = httpServletResponse.getWriter();
                        out.write("{\"status\":\"ok\",\"msg\":\"登录成功\"}");
                        out.flush();
                        out.close();
                    }
                })
                // 配置ajax登录失败后返回实体对象
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter out = httpServletResponse.getWriter();
                        out.write("{\"status\":\"error\",\"msg\":\"登录失败\"}");
                        out.flush();
                        out.close();
                    }
                })*/
            //.and()
            //.sessionManagement()
            //.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) //STATELESS 不创建session
            //.and()

            // 不允许表单提交在controller层处理
            .httpBasic().disable()
            .formLogin().disable()
            .csrf().disable()
            ;// 登录成功跳转地址

        // 异常处理
       // http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);

        // 记住我
       /* http.rememberMe()
                // 设置失效时间，单位秒
                .tokenValiditySeconds(60)
                // 自定义登录逻辑
                .userDetailsService(userDetailsService)
                // 持久层对象
                .tokenRepository(persistentTokenRepository);*/
    }

    /**
     * 处理记住信息
     * @return
     */
   /* @Bean
    public PersistentTokenRepository getPersistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        // 自动建表，第一次启动时候需要，第二次启动注释掉
        //jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }*/

    @Override
    @Bean
    //注入authenticationManager
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /*@Bean
    public SpringSessionBackedSessionRegistry sessionRegistry() {
        return new SpringSessionBackedSessionRegistry(sessionRepository);
    }*/
}
