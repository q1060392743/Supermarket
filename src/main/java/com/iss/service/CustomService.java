package com.iss.service;

import com.iss.pojo.Custom;

public interface CustomService {
    /**
     * 登录功能
     *
     * @return java.lang.String json串
     * @author SongChong created by10:16 2018/10/10 0010
     */
    String login();

    /**
     * 注册功能
     *
     * @return java.lang.String json串
     * @author SongChong created by10:16 2018/10/10 0010
     */
    String register();

    /**
     * 获取顾客的积分和会员等级
     *
     * @param custom 要查找的顾客对象
     * @return java.lang.String json串
     */
    String getScoreAndRank(Custom custom);

    /**
     * 更改密码
     *
     * @param custom 顾客对象
     * @return java.lang.String json串
     * @author SongChong created by17:05 2018/10/10 0010
     */
    String changePassword(Custom custom);

    /**
     * 获取管理权限
     *
     * @return java.lang.String json串
     * @author SongChong created by18:22 2018/10/10 0010
     */
    String admin();

    /**
     * 给顾客展示的商品信息
     *
     * @return java.lang.String json串
     * @author SongChong created by10:35 2018/10/11 0011
     */
    String showAllProduct();

    /**
     * @param custom 顾客对象
     * @return java.lang.String json串
     * @author SongChong created by9:20 2018/10/15 0015
     */
    String buyProduct(Custom custom);
}
