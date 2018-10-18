package com.iss.menu;

import com.iss.pojo.Custom;
import com.iss.service.CustomService;
import com.iss.service.ProductService;
import com.iss.serviceImpl.CustomServiceImpl;
import com.iss.serviceImpl.ProductServiceImpl;
import com.iss.util.JsonUtil;

import java.util.Scanner;

/**
 * @author SongChong created by 2018/10/9 0009 16:57
 */
public class AllMenu {
    private Scanner scanner = new Scanner(System.in);
    private CustomService customService = new CustomServiceImpl();
    private ProductService productService = new ProductServiceImpl();

    /**
     * 开始界面
     *
     * @return java.lang.String json串
     * @author SongChong created by9:57 2018/10/10 0010
     */
    public String startMenu() {

        System.out.println("****************欢迎来到小小超市****************");
        System.out.println("请选择功能：");
        System.out.println("1 .用户注册  2.用户登录  3.管理员权限  4.退出");

        String s = scanner.nextLine();
        String json = "";
        switch (s) {
            case "1":
                json = customService.register();
                break;
            case "2":
                json = customService.login();
                break;
            case "3":
                json = customService.admin();
                break;
            case "4":
                System.out.println("退出成功！");
                System.exit(0);
                break;
            default:
                json = JsonUtil.toJson("indexWrong", "");
        }
        return json;
    }

    /**
     * 顾客界面
     * @param custom 登录后的顾客的对象
     * @return java.lang.String json串
     * @author SongChong created by9:57 2018/10/10 0010
     */
    public String customMenu(Custom custom) {

        System.out.println("****************欢迎来到小小超市****************");
        System.out.println("请选择功能：");
        System.out.println("1.查看积分  2.更改密码  3.查看商品  4.购买商品  5.退出登录  6.退出");
        String s = scanner.nextLine();
        String json = "";
        switch (s) {
            case "1":
                json = customService.getScoreAndRank(custom);
                break;
            case "2":
                json = customService.changePassword(custom);
                break;
            case "3":
                json = customService.showAllProduct();
                break;
            case "4":
                json = customService.buyProduct(custom);
                break;
            case "5":
                json = "exitLogin";
                break;
            case "6":
                System.out.println("退出成功！");
                System.exit(0);
                break;
            default:
                json = JsonUtil.toJson("indexWrong", "error");
        }
        return json;

    }

    /**
     * 管理员界面
     *
     * @return java.lang.String json串
     * @author SongChong created by9:17 2018/10/15 0015
     */
    public String adminMenu() {

        System.out.println("****************欢迎来到小小超市****************");
        System.out.println("请选择功能：");
        System.out.println("1.添加商品  2.删除商品  3.编辑商品 4.查看商品  5.返回上一级");
        String s = scanner.nextLine();
        String json;

        switch (s) {
            case "1":
                json = productService.insertProduct();
                break;
            case "2":
                json = productService.deleteProduct();
                break;
            case "3":
                json = productService.editProduct();
                break;
            case "4":
                json = productService.showAllProduct();
                break;
            case "5":
                json = "exitAdmin";
                break;
            default:
                json = JsonUtil.toJson("indexWrong", "error");
        }
        return json;
    }
}
