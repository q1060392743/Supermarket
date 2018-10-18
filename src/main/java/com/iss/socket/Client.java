package com.iss.socket;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iss.menu.AllMenu;
import com.iss.pojo.Custom;
import com.iss.pojo.Product;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

/**
 * @author SongChong created by 2018/10/10 0010 10:54
 */

//客户端
public class Client {

    public static void main(String[] args) throws Exception {
        AllMenu allMenu = new AllMenu();

        System.out.println("启动客户端");
        Socket soc = new Socket("127.0.0.1", 9998);

        InputStream is = soc.getInputStream();
        OutputStream os = soc.getOutputStream();

        Gson gson = new Gson();

        byte[] b = new byte[1024];
        int x = 0;

        String choose = "";

        while (true) {
            //执行开始菜单及接受的json
            choose = allMenu.startMenu();

            //将选项写入流中传给服务器
            os.write(choose.getBytes());

            //读取服务器传来的json
            x = is.read(b);
            String str = new String(b, 0, x);

            //解析json
            Map map = gson.fromJson(str, Map.class);
            //获取根据json要调用的方法
            String method = map.get("method").toString();
            //获取json的参数
            String param = map.get("param").toString();
            List<Custom> list = null;

            //登录标志
            boolean isLogin = false;
            //获取权限标志
            boolean isAdmin = false;

            switch (method) {
                case "login":
                    //将接收的参数解析成集合
                    list = gson.fromJson(param, new TypeToken<List<Custom>>() {
                    }.getType());

                    if (list.get(0) != null) {
                        System.out.println("登录成功");
                        isLogin = true;
                    } else {
                        System.out.println("登录失败");
                    }
                    break;

                case "register":
                    if (Integer.parseInt(param) > 0) {
                        System.out.println("注册成功");
                    } else {
                        System.out.println("注册失败");
                    }
                    break;

                case "admin":
                    if (param.equals("true")) {
                        System.out.println("获取成功！");
                        isAdmin = true;
                    } else {
                        System.out.println("获取失败！");
                    }
                    break;

                case "indexWrong":
                    System.out.println("请输入正确序号");
            }

            while (isLogin == true) {
                Custom custom = null;

                //执行顾客菜单及接受的json
                choose = allMenu.customMenu(list.get(0));

                if (choose.equals("exitLogin")) {
                    System.out.println("退出登录成功！");
                    isLogin = false;
                } else if (choose.equals("toCustom")) {
                    continue;
                } else {
                    //将选项写入流中传给服务器
                    os.write(choose.getBytes());

                    //读取服务器传来的json
                    x = is.read(b);
                    str = new String(b, 0, x);

                    //解析json
                    map = gson.fromJson(str, Map.class);
                    //获取根据json要调用的方法
                    method = map.get("method").toString();
                    //获取json的参数
                    param = map.get("param").toString();


                    switch (method) {
                        case "getScoreAndRank":
                            //将接收的参数解析成集合
                            list = gson.fromJson(param, new TypeToken<List<Custom>>() {
                            }.getType());
                            custom = list.get(0);
                            System.out.println(custom.getUsername() + "的积分为：" + custom.getScore() + "\t会员等级为：" + custom.getRank());
                            break;

                        case "changePassword":
                            if (Integer.parseInt(param) > 0) {
                                System.out.println("修改成功！");
                            } else {
                                System.out.println("修改失败！");
                            }
                            break;

                        case "showAllProduct":
                            List<Product> list1 = gson.fromJson(param, new TypeToken<List<Product>>() {
                            }.getType());
                            System.out.println("商品名\t价格\t");
                            for (Product product : list1) {
                                System.out.println(product.getName() + "\t" + product.getPrice());
                            }
                            break;

                        case "buyProduct":
                            list = gson.fromJson(param, new TypeToken<List<Custom>>() {
                            }.getType());
                            custom = list.get(0);
                            System.out.println("购买成功！，您现在的积分为：" + custom.getScore() + "\t您的等级为：" + custom.getRank());

                        case "indexWrong":
                            System.out.println("请输入正确序号");
                            break;
                    }
                }
            }

            while (isAdmin == true) {
                //执行管理员菜单及接受的json
                choose = allMenu.adminMenu();

                if (choose.equals("exitAdmin")) {
                    System.out.println("返回上一级成功！");
                    isAdmin = false;
                } else if (choose.equals("toAdmin")) {
                    continue;
                } else {
                    //将选项写入流中传给服务器
                    os.write(choose.getBytes());

                    //读取服务器传来的json
                    x = is.read(b);
                    str = new String(b, 0, x);

                    //解析json
                    map = gson.fromJson(str, Map.class);
                    //获取根据json要调用的方法
                    method = map.get("method").toString();
                    //获取json的参数
                    param = map.get("param").toString();

                    switch (method) {
                        case "insertProduct":
                            if (Integer.parseInt(param) > 0) {
                                System.out.println("添加成功！");
                            } else {
                                System.out.println("添加失败！");
                            }
                            break;

                        case "deleteProduct":
                            if (Integer.parseInt(param) > 0) {
                                System.out.println("删除成功！");
                            } else {
                                System.out.println("删除失败！");
                            }
                            break;

                        case "editProduct":
                            if (Integer.parseInt(param) > 0) {
                                System.out.println("修改成功！");
                            } else {
                                System.out.println("修改失败！");
                            }
                            break;

                        case "showAllProduct":
                            List<Product> list1 = gson.fromJson(param, new TypeToken<List<Product>>() {
                            }.getType());
                            System.out.println("id\t商品名\t价格\t");
                            for (Product product : list1) {
                                System.out.println(product.getId() + "\t" + product.getName() + "\t" + product.getPrice());
                            }
                            break;

                        case "indexWrong":
                            System.out.println("请输入正确序号");
                            break;
                    }
                }
            }
        }
    }
}
