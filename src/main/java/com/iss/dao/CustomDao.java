package com.iss.dao;

import com.iss.pojo.Custom;

public interface CustomDao {
    /**
     * 根据用户名和密码获取顾客对象相关信息
     *
     * @param custom 顾客对象
     * @return com.iss.pojo.Custom 顾客对象
     * @author SongChong created by17:03 2018/10/10 0010
     */
    Custom getCustomByUsernameAndPassword(Custom custom);

    /**
     * 增添顾客数据
     *
     * @param custom 顾客对象
     * @return int 增添的数据数量
     * @author SongChong created by17:04 2018/10/10 0010
     */
    int insertCustom(Custom custom);

    /**
     * 更改密码
     *
     * @param custom 顾客对象
     * @return int 影响的数据数量
     * @author SongChong created by17:04 2018/10/10 0010
     */
    int updatePassword(Custom custom);

    /**
     * 更新顾客积分和会员等级
     *
     * @param custom 顾客对象
     * @return int 更新的数据数量
     * @author SongChong created by9:08 2018/10/12 0012
     */
    Custom updateScoreAndRank(Custom custom);
}
