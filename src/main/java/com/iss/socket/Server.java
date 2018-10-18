package com.iss.socket;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iss.dao.CustomDao;
import com.iss.dao.ProductDao;
import com.iss.daoImpl.CustomDaoImpl;
import com.iss.daoImpl.ProductDaoImpl;
import com.iss.pojo.Custom;
import com.iss.pojo.Product;
import com.iss.util.JsonUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author SongChong created by 2018/10/10 0010 10:59
 */
public class Server {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        // TCP通讯
        // 1.服务器端的Socket
        System.out.println("启动服务器");
        ServerSocket ser = new ServerSocket(9998);

        // 每连接一个客户端，启动一个线程

        while (true) {
            final Socket soc = ser.accept();
            new Thread() {

                public void run() {
                    try {
                        InputStream is = soc.getInputStream();
                        OutputStream os = soc.getOutputStream();
                        CustomDao dao = new CustomDaoImpl();
                        ProductDao dao1 = new ProductDaoImpl();
                        Custom custom = null;
                        String count = "";
                        byte[] b = new byte[1024];
                        int x = 0;
                        Gson gson = new Gson();

                        while (true) {
                            //读取客户端传来的json
                            x = is.read(b);
                            String str = new String(b, 0, x);

                            //解析json
                            Map map = gson.fromJson(str, Map.class);
                            //获取根据json要调用的方法
                            String method = map.get("method").toString();
                            //获取json的参数
                            String param = map.get("param").toString();

                            List<Custom> list = null;
                            List<Product> list1 = null;

                            //要传给客户端的json
                            String res = "";

                            switch (method) {
                                //执行登录
                                case "login":
                                    list = gson.fromJson(param, new TypeToken<List<Custom>>() {
                                    }.getType());
                                    custom = dao.getCustomByUsernameAndPassword(list.get(0));
                                    List<Custom> newList = new ArrayList<>();
                                    newList.add(custom);
                                    res = JsonUtil.toJson("login", newList);
                                    break;

                                case "register":
                                    list = gson.fromJson(param, new TypeToken<List<Custom>>() {
                                    }.getType());
                                    custom = list.get(0);
                                    count = dao.insertCustom(custom) + "";
                                    res = JsonUtil.toJson("register", count);
                                    break;

                                case "admin":
                                    if (param.equals("admin")) {
                                        res = JsonUtil.toJson("admin", "true");
                                    } else {
                                        res = JsonUtil.toJson("admin", "false");
                                    }
                                    break;

                                case "indexWrong":
                                    if (param != null) {
                                        res = JsonUtil.toJson("indexWrong", param);
                                    }
                                    break;

                                case "buyProduct":
                                    list = gson.fromJson(param, new TypeToken<List<Custom>>() {
                                    }.getType());
                                    custom = dao.updateScoreAndRank(list.get(0));
                                    List<Custom> newList1 = new ArrayList<>();
                                    newList1.add(custom);
                                    res = JsonUtil.toJson("buyProduct", newList1);
                                    break;

                                case "getScoreAndRank":
                                    list = gson.fromJson(param, new TypeToken<List<Custom>>() {
                                    }.getType());
                                    res = JsonUtil.toJson("getScoreAndRank", list);
                                    break;

                                case "changePassword":
                                    list = gson.fromJson(param, new TypeToken<List<Custom>>() {
                                    }.getType());
                                    count = dao.updatePassword(list.get(0)) + "";
                                    res = JsonUtil.toJson("changePassword", count);
                                    break;

                                case "insertProduct":
                                    list1 = gson.fromJson(param, new TypeToken<List<Product>>() {
                                    }.getType());
                                    count = dao1.insertProduct(list1.get(0)) + "";
                                    res = JsonUtil.toJson("insertProduct", count);
                                    break;

                                case "deleteProduct":
                                    count = dao1.deleteProduct(param) + "";
                                    res = JsonUtil.toJson("insertProduct", count);
                                    break;

                                case "editProduct":
                                    list1 = gson.fromJson(param, new TypeToken<List<Product>>() {
                                    }.getType());
                                    count = dao1.updateProduct(list1.get(1), list1.get(0).getName()) + "";
                                    res = JsonUtil.toJson("editProduct", count);
                                    break;

                                case "showAllProduct":
                                    list1 = dao1.getAllProduct();
                                    res = JsonUtil.toJson("showAllProduct", list1);
                                    break;


                            }
                            os.write(res.getBytes());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }.start();
        }

    }
}
