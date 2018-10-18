package com.iss.serviceImpl;

import com.iss.dao.ProductDao;
import com.iss.daoImpl.ProductDaoImpl;
import com.iss.pojo.Custom;
import com.iss.pojo.Product;
import com.iss.service.CustomService;
import com.iss.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author SongChong created by 2018/10/9 0009 16:37
 */
public class CustomServiceImpl implements CustomService {
    private Scanner scanner = new Scanner(System.in);
    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public String login() {
        Custom custom = new Custom();
        System.out.println("请输入用户名：");
        custom.setUsername(scanner.nextLine());
        System.out.println(("请输入密码："));
        custom.setPassword(scanner.nextLine());

        List<Custom> list = new ArrayList<>();
        list.add(custom);
        return JsonUtil.toJson("login", list);
    }


    @Override
    public String register() {
        Custom custom = new Custom();

        System.out.println("请输入用户名：");
        custom.setUsername(scanner.nextLine());
        System.out.println("请输入密码");
        custom.setPassword(scanner.nextLine());
        custom.setScore(0);
        custom.setRank(0);

        List<Custom> list = new ArrayList<>();
        list.add(custom);
        return JsonUtil.toJson("register", list);
    }

    @Override
    public String getScoreAndRank(Custom custom) {
        List<Custom> list = new ArrayList<>();
        list.add(custom);
        return JsonUtil.toJson("getScoreAndRank", list);
    }

    @Override
    public String changePassword(Custom custom) {
        System.out.println("请输入新密码：");
        String newPassword = scanner.nextLine();
        System.out.println("请再次输入新密码");
        String reNewPassword = scanner.nextLine();
        if (!newPassword.equals(reNewPassword)) {
            System.out.println("两次密码不同，请重新输入");
            changePassword(custom);
        } else {
            custom.setPassword(newPassword);
        }
        List<Custom> list = new ArrayList<>();
        list.add(custom);
        return JsonUtil.toJson("changePassword", list);
    }

    @Override
    public String admin() {
        System.out.println("请输入管理密码");
        String adminPsw = scanner.nextLine();
        return JsonUtil.toJson("admin", adminPsw);
    }

    @Override
    public String showAllProduct() {
        return JsonUtil.toJson("showAllProduct", "");
    }

    @Override
    public String buyProduct(Custom custom) {
        Product product = null;
        List<Custom> list = null;
        System.out.println("请输入要购买的商品：");

        while (true) {
            product = productDao.getProduct(scanner.nextLine());
            if (product.getId() == null) {
                System.out.println("请重新输入");
            } else break;
        }

        System.out.println("请输入购买的数量：");
        int count = Integer.parseInt(scanner.nextLine());
        double cost = product.getPrice() * count;
        int discount = 10 - custom.getRank();
        double finalCost = cost * discount / 10;
        System.out.println("您购买了：" + product.getName() + "\t数量：" + count + "\t单价：" + product.getPrice() + "\t共" + cost + "元" + "\t折扣：" + discount + "\t最终价格：" + finalCost + "\t积分增加" + (int) finalCost / 100);

        String s = "";

        System.out.println("您是否要购买？y/n");


        do {
            s = scanner.nextLine();

            if (s.equals("y")) {
                custom.setScore(custom.getScore() + (int) finalCost / 100);
                while (custom.getScore() >= 1000 && custom.getRank() < 9) {
                    custom.setScore(custom.getScore() - 1000);
                    custom.setRank(custom.getRank() + 1);
                }
                list = new ArrayList<>();
                list.add(custom);
            } else if (s.equals("n")) {
                return "toCustom";
            } else {
                System.out.println("请输入正确的序号");
            }
        } while (!s.equals("y") && !s.equals("n"));

        return JsonUtil.toJson("buyProduct", list);
    }
}