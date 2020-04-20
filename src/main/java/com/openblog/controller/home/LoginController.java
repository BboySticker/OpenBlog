package com.openblog.controller.home;

import com.openblog.entity.User;
import com.openblog.service.UserService;
import com.openblog.util.EmailUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.openblog.util.MyUtils.getIpAddr;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * If user is already logged in,
     * he/she is not allowed to access this page again
     *
     * @param session
     * @return login page or redirect to index page
     */
    @RequestMapping("/login")
    private String login(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/index";
        }
        return "Access/login";
    }

    /**
     * Delete session's 'user' attribute when log out
     *
     * @param session
     * @return redirect to index page
     */
    @GetMapping("/logout")
    private String logout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/index";
    }

    /**
     * If user is already logged in,
     * he/she is not allowed to access this page again
     *
     * @param session
     * @return forgot page or redirect to index page
     */
    @GetMapping("/forgot")
    private String forgot(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/index";
        }
        return "Access/forgot";
    }

    /**
     * If user is already logged in,
     * he/she is not allowed to access this page again
     *
     * @param session
     * @return register page or redirect to index page
     */
    @RequestMapping("/register")
    private String register(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/index";
        }
        return "Access/register";
    }

    /**
     * Verify whether user input is already been used before
     *
     * @param request
     * @param response
     * @return String that in JSON format, include status code and message
     */
    @PostMapping("/registerVerify")
    @ResponseBody
    private String registerVerify(HttpServletRequest request, HttpServletResponse response) {
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
            user.setUserPass(passwordEncoder.encode(password));
            user.setUserEmail(email);
            user.setUserUrl("user/" + name);
            user.setUserLastLoginIp(getIpAddr(request));
            user.setUserRegisterTime(new Date());
            user.setUserLastLoginTime(new Date());
            user.setUserStatus(1);  // 0 - abnormal, 1 - normal
            user.setIsAdmin(0);  // 0 - not admin, 1 - admin

            userService.addUser(user);
            request.getSession().setAttribute("user", user);
        }
        String result = new JSONObject(map).toString();
        return result;
    }

    /**
     * Verify whether user email and password are valid,
     * if both valid, add user to session,
     * if 'remember me' is selected, add email and password to cookie,
     * set age of the cookie to 3 days
     *
     * @param request
     * @param response
     * @return String that in JSON format, include status code and message
     */
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
        } else if (user.getUserPass() == null || !passwordEncoder.matches(password, user.getUserPass())) {
            map.put("code", 0);
            map.put("msg", "Wrong Password!");
        } else {
            // login successful
            map.put("code", 1);
            map.put("msg", "Successfully logged in!");
            map.put("isAdmin", user.getIsAdmin());
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

    /**
     * Get password reset token by calling the EmailService,
     * and send the token to user email,
     * store token into database for later verification
     *
     * @param request
     * @return String that in JSON format, include status code and message
     */
    @PostMapping("/getResetToken")
    @ResponseBody
    private String getToken(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String email = request.getParameter("email");
        User user = userService.getUserByEmail(email);
        if (user == null) {
            map.put("code", 0);
            map.put("msg", "User Email Does Not Exists");
        } else {
            String token = EmailUtil.sendResetEmail(email);
            if (token == null) {
                map.put("code", 0);
                map.put("msg", "Failed to send email, please try again");
            } else {
                user.setToken(token);
                userService.updateUser(user);
                map.put("code", 1);
                map.put("msg", "Successfully sent email");
            }
        }
        String result = new JSONObject(map).toString();
        return result;
    }

    /**
     * This page can be only accessed from Reset page,
     * by verifying if session contains specific attribute,
     * if it's not, it means user directly type the usl into browser,
     * if it has, it means the request come from Reset page
     *
     * @param session
     * @return reset page or redirect to forgot page
     */
    @GetMapping("/reset")
    private String reset(HttpSession session) {
        Object code = session.getAttribute("resetVerified");
        if (code == null || (Integer) code != 1) {
            return "redirect:/forgot";
        }
        return "Access/reset";
    }

    /**
     * Verify whether user email and token are valid,
     * if token is valid, add resetVerified tag into session
     *
     * @param request
     * @return String that in JSON format, include status code and message
     */
    @PostMapping("/resetVerify")
    @ResponseBody
    private String resetVerify(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();

        String email = request.getParameter("email");
        String token = request.getParameter("token");
        User user = userService.getUserByEmail(email);
        if (user == null) {
            // user not exist
            map.put("code", 0);
            map.put("msg", "User Email Does Not Exists");
        } else if (user.getToken() == null) {
            // user's token not exist, something wrong with email service
            map.put("code", 0);
            map.put("msg", "There is something wrong, please try to click Re-send again");
        } else if (! user.getToken().equals(token)) {
            // tokens are different
            map.put("code", 0);
            map.put("msg", "Wrong token");
        } else if (user.getToken().equals(token)) {
            // successfully verified
            map.put("code", 1);
            map.put("msg", "Successfully verified");
            // add verification code into session,
            // prevent user access the reset page directly from the url
            request.getSession().setAttribute("resetVerified", 1);
        }
        String result = new JSONObject(map).toString();
        return result;
    }

    /**
     * Clear current token and update new password
     *
     * @param request
     * @return String that in JSON format, include status code and message
     */
    @PostMapping("/resetPasswordVerify")
    @ResponseBody
    private String resetPasswordVerify(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = userService.getUserByEmail(email);
        if (user == null) {
            // user not exist
            map.put("code", 0);
            map.put("msg", "User Email Does Not Exists");
        } else {
            // clear the token
            user.setToken("");
            // update the password
            user.setUserPass(passwordEncoder.encode(password));
            userService.updateUser(user);
            map.put("code", 1);
            map.put("msg", "Successfully updated user password");
        }
        String result = new JSONObject(map).toString();
        return result;
    }

}
