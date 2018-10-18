package com.iss.dao;

import com.iss.pojo.Product;

import java.util.List;

public interface ProductDao {
    /**
     * 添加商品数据
     *
     * @param product 商品对象
     * @return int 添加数据数量
     * @author SongChong created by18:34 2018/10/10 0010
     */
    int insertProduct(Product product);

    /**
     * 删除商品数据
     *
     * @param name 商品名
     * @return int 删除数据数量
     * @author SongChong created by18:38 2018/10/10 0010
     */
    int deleteProduct(String name);

    /**
     * 修改商品数据
     *
     * @param oldName 商品原来的名字
     * @param product 商品对象
     * @return int 修改商品数量
     * @author SongChong created by18:39 2018/10/10 0010
     */
    int updateProduct(Product product, String oldName);

    /**
     * 查看单一商品数据
     *
     * @param name 商品名
     * @return com.iss.pojo.Product 商品对象
     * @author SongChong created by21:39 2018/10/10 0010
     */
    Product getProduct(String name);

    /**
     * 查看所有商品数据
     *
     * @return <com.iss.pojo.Product>java.util.List 商品集合
     * @author SongChong created by21:40 2018/10/10 0010
     */
    List<Product> getAllProduct();
}
