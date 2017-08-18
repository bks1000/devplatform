package com.june.core.utils;

import org.activiti.engine.identity.User;

import javax.servlet.http.HttpSession;

/**
 * 工作流用户工具类
 * Created by lenovo on 2017/8/18.
 */
public class UserUtil {
    public static final String USER = "user";

    /**
     * 设置用户到session
     *
     * @param session
     * @param user
     */
    public static void saveUserToSession(HttpSession session, User user) {
        session.setAttribute(USER, user);
    }

    /**
     * 从Session获取当前用户信息
     *
     * @param session
     * @return
     */
    public static User getUserFromSession(HttpSession session) {
        Object attribute = session.getAttribute(USER);
        return attribute == null ? null : (User) attribute;
    }
}
