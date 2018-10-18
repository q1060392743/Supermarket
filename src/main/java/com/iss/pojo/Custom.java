package com.iss.pojo;

/**
 * 用户类
 *
 * @author SongChong created by 2018/10/9 0009 15:33
 */
public class Custom {
    private String id;//用户id
    private String username;//用户名
    private String password;//密码
    private int score;//会员积分
    private int rank;//会员等级

    public Custom() {
    }

    public Custom(String id, String username, String password, int score, int rank) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.score = score;
        this.rank = rank;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Custom{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", score=" + score +
                ", rank=" + rank +
                '}';
    }
}