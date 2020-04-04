package com.openblog.controller.home;

import com.openblog.entity.User;
import com.openblog.service.UserService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.openblog.util.MyUtils.getIpAddr;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/login")
    private String login() {
        return "Access/login";
    }

    @GetMapping("/forgot")
    private String forgot() {
        return "Access/forgot";
    }

    @RequestMapping("/register")
    private String register() {
        return "Access/register";
    }

    @PostMapping("/registerVerify")
    @ResponseBody
    private String registerVerify(HttpServletRequest request, HttpServletResponse response) throws UnknownHostException {
        Map<String, Object> map = new HashMap<String, Object>();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (userService.getUserByEmail(email) != null) {
            map.put("code", 0);
            map.put("msg", "Email has already been used!");
        } else if (userService.getUserByName(name) != null) {
            map.put("code", 0);
            map.put("msg", "Username has already been used!");
        } else {
            map.put("code", 1);
            map.put("msg", "Successfully registered!");

            User user = new User();
            user.setUserId(UUID.randomUUID().toString());
            user.setUserName(name);
            user.setUserPass(password);
            user.setUserEmail(email);
            user.setUserUrl("user/" + name);
            user.setUserLastLoginIp(getIpAddr(request));
            user.setUserRegisterTime(new Date());
            user.setUserLastLoginTime(new Date());
            user.setUserStatus(1);  // 0 - abnormal, 1 - normal

            userService.addUser(user);
            request.getSession().setAttribute("user", user);
        }
        String result = new JSONObject(map).toString();
        return result;
    }

    @PostMapping("/loginVerify")
    @ResponseBody
    private String loginVerify(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        User user = userService.getUserByEmail(email);
        if (user == null) {
            map.put("code", 0);
            map.put("msg", "Invalid User!");
        } else if (!user.getUserPass().equals(password)) {
            map.put("code", 0);
            map.put("msg", "Wrong Password!");
        } else {
            // login successful
            map.put("code", 1);
            map.put("msg", "Successfully logged in!");
            // add to session
            request.getSession().setAttribute("user", user);
            // add cookie
            if(remember != null) {
                // create two Cookie objs
                Cookie nameCookie = new Cookie("email", email);
                // set the age of Cookies to 3 days
                nameCookie.setMaxAge(60 * 60 * 24 * 3);
                Cookie pwdCookie = new Cookie("password", password);
                pwdCookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(nameCookie);
                response.addCookie(pwdCookie);
            }
            user.setUserLastLoginTime(new Date());
            user.setUserLastLoginIp(getIpAddr(request));
            userService.updateUser(user);
        }
        String result = new JSONObject(map).toString();
        return result;
    }

    @GetMapping("/admin")
    private String admin() {
        return "Admin/admin";
    }

    @GetMapping("/admin/logout")
    private String logout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/index";
    }

}
