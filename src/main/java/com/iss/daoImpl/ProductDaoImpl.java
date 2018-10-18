package com.iss.daoImpl;

import com.iss.dao.ProductDao;
import com.iss.pojo.Product;
import com.iss.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SongChong created by 2018/10/10 0010 18:59
 */
public class ProductDaoImpl implements ProductDao {
    @Override
    public int insertProduct(Product product) {
        int result = 0;
        String sql = "insert  into PRODUCT values(sys_guid(),?,?)";
        try (Connection con = DBUtils.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int deleteProduct(String name) {
        int result = 0;
        String sql = "delete from PRODUCT where PR_NAME=?";
        try (Connection con = DBUtils.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateProduct(Product product, String oldName) {
        int result = 0;
        String sql = "update PRODUCT set PR_NAME=?, PR_PRICE=? where PR_NAME=?";
        try (Connection con = DBUtils.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, oldName);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Product getProduct(String name) {
        Product product = new Product();
        String sql = "select * from PRODUCT where PR_NAME=?";
        try (Connection con = DBUtils.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    product.setId(rs.getString(1));
                    product.setName(rs.getString(2));
                    product.setPrice(rs.getDouble(3));
                } else {
                    System.out.println("商品名输入错误！");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "select * from PRODUCT";
        try (Connection con = DBUtils.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getString(1));
                    product.setName(rs.getString(2));
                    product.setPrice(rs.getDouble(3));
                    list.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}