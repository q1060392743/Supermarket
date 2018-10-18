package com.iss.daoImpl;

import com.iss.dao.CustomDao;
import com.iss.pojo.Custom;
import com.iss.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author SongChong created by 2018/10/9 0009 16:12
 */
public class CustomDaoImpl implements CustomDao {
    @Override
    public Custom getCustomByUsernameAndPassword(Custom custom) {
        String sql = "select * from CUSTOM where CU_USERNAME=? and CU_PASSWORD=?";
        try (Connection con = DBUtils.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, custom.getUsername());
            ps.setString(2, custom.getPassword());
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                } else {
                    custom.setId(rs.getString(1));
                    custom.setScore(rs.getInt(4));
                    custom.setRank(rs.getInt(5));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return custom;
    }

    @Override
    public int insertCustom(Custom custom) {
        int result = 0;
        String sql = "insert into CUSTOM values(sys_guid(), ?,?,?,?)";
        try (Connection con = DBUtils.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, custom.getUsername());
            ps.setString(2, custom.getPassword());
            ps.setInt(3, custom.getScore());
            ps.setInt(4, custom.getRank());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updatePassword(Custom custom) {
        int result = 0;
        String sql = "update CUSTOM set CU_PASSWORD=? where CU_ID=?";
        try (Connection con = DBUtils.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, custom.getPassword());
            ps.setString(2, custom.getId());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Custom updateScoreAndRank(Custom custom) {
        int result = 0;
        String sql = "update CUSTOM set CU_SCORE=?,CU_RANK=? where CU_ID=?";
        try (Connection con = DBUtils.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, custom.getScore());
            ps.setInt(2, custom.getRank());
            ps.setString(3, custom.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return custom;
    }


}