package com.qf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.pojo.User;
import com.qf.service.UserService;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityManager securityManager;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public SecurityManager getSecurityManager() {
        return securityManager;
    }

    public void setSecurityManager(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }

    @RequestMapping("loginPage")
    public String loginPage()
    {
        return "loginPage";
    }
    @RequestMapping("login")
    public String login(User user)
    {
        System.out.println(user);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try
        {
            subject.login(usernamePasswordToken);
            if (subject.isAuthenticated())
            {
                System.out.println("aaaaaa");
                return "redirect:index";
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return "redirect:loginPage";
    }

    @RequestMapping("index")
    public String index(@RequestParam(defaultValue = "1") int pageNum, Model model)
    {
        PageHelper.startPage(pageNum,5);
        List<User> userList = userService.getUserList();
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        model.addAttribute("pageInfo",pageInfo);
        return "index";
    }
    @RequestMapping("update")
    public String update(int uid,Model model)
    {
        User user=userService.getUserByUid(uid);
        model.addAttribute("user",user);

        return "update";
    }
}
