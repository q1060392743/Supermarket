package com.iss.serviceImpl;

import com.iss.dao.ProductDao;
import com.iss.daoImpl.ProductDaoImpl;
import com.iss.pojo.Product;
import com.iss.service.ProductService;
import com.iss.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author SongChong created by 2018/10/10 0010 19:10
 */
public class ProductServiceImpl implements ProductService {
    private Scanner scanner = new Scanner(System.in);
    private ProductDao dao = new ProductDaoImpl();

    @Override
    public String insertProduct() {
        Product product = new Product();
        System.out.println("请输入商品名：");
        product.setName(scanner.nextLine());
        System.out.println("请输入商品价格");
        product.setPrice(Double.parseDouble(scanner.nextLine()));
        List<Product> list = new ArrayList<>();
        list.add(product);
        return JsonUtil.toJson("insertProduct", list);
    }

    @Override
    public String deleteProduct() {
        System.out.println("请输入商品名：");
        return JsonUtil.toJson("deleteProduct", scanner.nextLine());
    }

    @Override
    public String editProduct() {
        Product oldProduct = new Product();
        Product newProduct = new Product();
        //根据商品名查到需要修改的商品
        System.out.println("请输入商品名：");
        oldProduct.setName(scanner.nextLine());

        while (true) {
            //选择修改的项
            System.out.println("请选择要修改的项：");
            System.out.println("1.商品名  2.价格  3.返回上一级  4.提交");
            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("请输入新商品名：");
                    newProduct.setName(scanner.nextLine());
                    break;

                case "2":
                    System.out.println("请输入新价格：");
                    newProduct.setPrice(Double.parseDouble(scanner.nextLine()));
                    break;

                case "3":
                    return "toAdmin";

                case "4":
                    List<Product> list = new ArrayList<>();
                    list.add(oldProduct);
                    list.add(newProduct);
                    return JsonUtil.toJson("editProduct", list);

                default:
                    System.out.println("请输入正确序号");
            }


        }
    }

    @Override
    public String showAllProduct() {
        return JsonUtil.toJson("showAllProduct", "");

    }
}