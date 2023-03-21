package com.bjpowernode.crm.settings.web.controller;

import com.bjpowernode.crm.commons.contans.Contants;
import com.bjpowernode.crm.commons.entity.ReturnObject;
import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.settings.model.User;
import com.bjpowernode.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname UserController
 * @Date 2022/12/19
 * @Created by YQ
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 访问欢迎页面---登陆页面
     * @return
     */
    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin(){
        return "settings/qx/user/login";
    }

    @RequestMapping("/settings/qx/user/login.do")
    @ResponseBody
    public Object login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request, HttpSession session, HttpServletResponse response){
        //封装参数
        Map<String,Object> map = new HashMap<>();
        map.put("loginAct", loginAct);
        map.put("loginPwd", loginPwd);

        //查询用户信息，封装到User对象中
        User user = userService.selectUserByLoginActAndPwd(map);

        ReturnObject returnObject = new ReturnObject();

        if(user == null){
            //登陆失败，用户名或密码错误
            returnObject.setCode("0");
            returnObject.setMessage("登陆失败，用户名或密码错误");
        }else {
            //进一步判断是否合法
            //此处有BUG
            if (DateUtils.forMateDateTime(new Date()).compareTo(user.getExpireTime()) < 0 ){
                //登录失败，账号已过期
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("登录失败，账号已过期");
            }else if ("0".equals(user.getLockState())){
                //登陆失败，账户被锁定
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("登陆失败，账户被锁定");
            } else if (!user.getAllowIps().contains(request.getRemoteAddr())) {
                //登陆失败，ip受限
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("登陆失败，ip受限");
            }else {
                //登录成功
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
                session.setAttribute(Contants.SESSION_USER,user);

                //如果需要记住密码，则往外写cookie
                if ("true".equals(isRemPwd)){
                    Cookie c1 = new Cookie("loginAct",user.getLoginAct());
                    c1.setMaxAge(10*24*60*60);
                    response.addCookie(c1);
                    Cookie c2 = new Cookie("loginPwd",user.getLoginPwd());
                    c2.setMaxAge(10*24*60*60);
                    response.addCookie(c2);
                }else {
                    //把没有过期的cookie删除
                    Cookie c1 = new Cookie("loginAct","1");
                    c1.setMaxAge(0);
                    response.addCookie(c1);
                    Cookie c2 = new Cookie("loginPwd","1");
                    c2.setMaxAge(0);
                    response.addCookie(c2);
                }
            }
        }
        return returnObject;
    }

    /**
     * 安全退出的控制器
     */
    @RequestMapping("/settings/qx/user/loginOut.do")
    public String loginOut(HttpServletResponse response,HttpSession session){
        //删除cookie
        Cookie c1 = new Cookie("loginAct","1");
        c1.setMaxAge(0);
        response.addCookie(c1);
        Cookie c2 = new Cookie("loginPwd","1");
        c2.setMaxAge(0);
        response.addCookie(c2);
        //清空session
        session.invalidate();

        //重定像跳转至登录页面
        return "redirect:/";
    }
}
