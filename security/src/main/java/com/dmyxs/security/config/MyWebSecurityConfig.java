package com.dmyxs.security.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;


@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private DataSource dataSource;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启表单提交
        http.formLogin()
                // 自定义别名，和from表单的name一致
                // .usernameParameter("")
                // .passwordParameter("")


                // 匹配/login时被认为是登录，才能处理登录请求，和from表单的action一致
                .loginProcessingUrl("/login")
                // 自定义登录页面
                .loginPage("/login.html")


                // 登录成功后跳转的页面，必须是post请求
                // .successForwardUrl("/toMain")
                // 使用自定义的成功跳转
                 .successHandler(new MyAuthenticationSuccessHandler("/main.html"))
                //  登录失败后跳转的页面
                //  .failureForwardUrl("/toError")
                // 使用自定义的失败跳转
                .failureHandler(new MyAuthenticationFailureHandler("/error.html"));


        // 开启请求认证
        http.authorizeHttpRequests()
                // 匹配static中的文件/目录，命中则放行，不需要认证
                .antMatchers("/login.html").permitAll()
                .antMatchers("/error.html").permitAll()
                .antMatchers("/js/**, /css/**, /images/**").permitAll()
                .antMatchers("/**/*.png").permitAll()

                // 正则匹配
                .regexMatchers(".+[.]jpg").permitAll()
                // .regexMatchers(HttpMethod.GET, "/demo").permitAll()

                // mvc前缀放行
                // .mvcMatchers("/demo").servletPath("/xxxx").permitAll()
                // 和mvc等价
                // .antMatchers("/xxxx/demo").permitAll()

                // 权限判断
                 .antMatchers("/write.html").hasAnyAuthority("write")
                 .antMatchers("/read.html").hasAnyAuthority("read")

                // 角色判断
                // .antMatchers("/write.html").hasRole("adminXXX")
                // .antMatchers("/write.html").hasAnyRole("admin", "adminXXX")

                // ip地址
                // .antMatchers("/read.html").hasIpAddress()

                // 任何请求都需要认证
                 .anyRequest().authenticated();
                // .anyRequest().access(); //22p失败

        // 关闭防护
        http.csrf().disable();

        // 异常处理
        http.exceptionHandling()
                .accessDeniedHandler(myAccessDeniedHandler);

        // 记住我功能
        http.rememberMe()
                // token失效时间，默认2周，单位秒
                // .tokenValiditySeconds()

                // 修改form表单的默认字段
                // .rememberMeParameter()

                // 自定义登录逻辑
                .userDetailsService(userDetailsService)

                // 持久层对象，底层通过JDBC实现
                .tokenRepository(getPTR());

        // 退出功能
        http.logout()
                // 自定义logout退出url，默认是/logout
                // .logoutUrl("/user/logout")

                // 退出成功跳转页面
                .logoutSuccessUrl("/login.html");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public PersistentTokenRepository getPTR(){
        // 获取PersistentTokenRepository的实现类，用来操作持久层对象，实现记住我功能
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();

        // 设置数据源
        jdbcTokenRepository.setDataSource(dataSource);

        // 第一次自动创建一张表，第二次启动注释掉
        // jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }
}

