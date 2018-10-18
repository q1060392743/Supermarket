package com.iss.service;

public interface ProductService {
    /**
     * 添加商品数据
     *
     * @return java.lang.String json串
     * @author SongChong created by19:09 2018/10/10 0010
     */
    String insertProduct();

    /**
     * 删除商品数据
     *
     * @return java.lang.String json串
     * @author SongChong created by20:26 2018/10/10 0010
     */
    String deleteProduct();

    /**
     * 修改商品数据
     *
     * @return java.lang.String json串
     * @author SongChong created by20:32 2018/10/10 0010
     */
    String editProduct();

    /**
     * 显示所有商品数据
     *
     * @return java.lang.String json串
     * @author SongChong created by21:53 2018/10/10 0010
     */
    String showAllProduct();

}
