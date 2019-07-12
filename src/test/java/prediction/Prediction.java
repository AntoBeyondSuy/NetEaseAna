package prediction;

import java.sql.*;
import java.util.Scanner;

public class Prediction {
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/SongLists?useSSL=false&serverTimezone=UTC";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "12345678";

    static Connection conn = null;
    static Statement stmt = null;

    public static void main(String[] args) {
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            System.out.println(" 实例化Statement对象...");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户ID：(此处输入了16046004)");
//        int userId = scanner.nextInt();
        int userId = 16046004;

        // get labels by userId from db
        String[] labels = getLabels(userId);

        for (String label : labels) {
            if (null == label) continue;
            System.out.println("label=" + label);
            SongList songList = getSongList(label);
            System.out.println(songList.getListName());
        }

        // 关闭资源
        try {
            conn.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
            System.out.println("JDBC连接关闭");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }



    private static NMUser sql2User(String sql) {
        NMUser user = new NMUser();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                int id_user = rs.getInt("id_user");
                String name_user = rs.getString("name_user");
                String[] labels = {rs.getString("label_1"),
                        rs.getString("label_2"),
                        rs.getString("label_3"),
                        rs.getString("label_4"),
                        rs.getString("label_5")};
                user.setUserId(id_user);
                user.setUserName(name_user);
                user.setLabels(labels);
            }
            // 完成后关闭
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
        return user;
    }

    private static SongList sql2List(String sql) {
        SongList list = new SongList();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                String id_list = rs.getString("id_list");
                String name_list = rs.getString("name_list");
                String[] labels = {rs.getString("label_1"),
                        rs.getString("label_2"),
                        rs.getString("label_3"),
                        rs.getString("label_4"),
                        rs.getString("label_5")};
                list.setListId(id_list);
                list.setListName(name_list);
                list.setLabels(labels);
            }
            // 完成后关闭
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据用户ID，获取该用户听歌的标签
     * @param userId
     * @return
     */
    private static String[] getLabels(int userId) {
        String sql = "SELECT id_user,name_user,label_1,label_2,label_3,label_4,label_5 " +
                "FROM SongLists.user " +
                "WHERE '" + userId + "'=id_user;";
        NMUser user = sql2User(sql);

        return user.getLabels();
    }

    /**
     * 根据标签，获取包含改标签的歌单
     * @param label
     * @return
     */
    private static SongList getSongList(String label) {
        String sql = "SELECT id_list,name_list,label_1,label_2,label_3,label_4,label_5 " +
                "FROM SongLists.list " +
                "WHERE '" + label + "'=label_1 " +
                "OR '" + label + "'=label_2 " +
                "OR '" + label + "'=label_3;";

        return sql2List(sql);
    }


}
