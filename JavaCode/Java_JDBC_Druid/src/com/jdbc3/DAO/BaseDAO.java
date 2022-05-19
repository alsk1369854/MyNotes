package com.jdbc3.DAO;

import com.jdbc3.DAO.Util.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public abstract class BaseDAO {
    // 通用查詢多個操作 (考慮事務)
    public <T> List<T> getForList(Connection conn, Class<T> clazz, String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1. 預先載入sql，並填充佔位符
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            // 2. 查詢
            rs = ps.executeQuery();
            // 3. 獲取結果集的元數據: ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            // 4. 通過ResultSetMetaData獲取結果集的列數
            int columnCount = rsmd.getColumnCount();
            // 建立數據集合
            LinkedList<T> list = new LinkedList<>();
            // 是否有查到東西
            while (rs.next()) {
                // 5. 透過反射建立通用的類實例
                T t = clazz.newInstance();
                // 6. 處理結果集一行數據中的每一個列
                for (int i = 0; i < columnCount; i++) {
                    // 獲取列值
                    Object columnValue = rs.getObject(i + 1);
                    // 獲取列別名(別名必須與java類屬性名一致)
                    String columnName = rsmd.getColumnLabel(i + 1);
                    // 通過反射: 給對象指定的columnName屬性，賦值為columnValue
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                list.add(t);
            }
            // 7. 返回結果集合
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 關閉資源
            JDBCUtils.closeResource(ps, rs);
        }
        return null;
    }

    // 通用查詢單個操作 (考慮事務)
    public <T> T getInstance( Connection conn, Class<T> clazz, String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            // 獲取結果集的元數據: ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            // 通過ResultSetMetaData獲取結果集的列數
            int columnCount = rsmd.getColumnCount();
            // 是否有查到東西
            if (rs.next()) {
                T t = clazz.newInstance();
                // 處理結果集一行數據中的每一個列
                for (int i = 0; i < columnCount; i++) {
                    // 獲取列值
                    Object columnValue = rs.getObject(i + 1);
                    // 獲取列別名(別名必須與java類屬性名一致)
                    String columnName = rsmd.getColumnLabel(i + 1);
                    // 通過反射: 給對象指定的columnName屬性，賦值為columnValue
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 關閉資源
            JDBCUtils.closeResource(ps, rs);
        }
        return null;
    }

    // 通用Update(考慮事務)
    public int update(Connection conn, String sql, Object... args) {
        PreparedStatement ps = null;
        try {
            // 1. 預編譯sql語句，返回PreparedStatement的實例
            ps = conn.prepareStatement(sql);
            // 2. 填充佔位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            // 3. 執行: 回傳數據庫引響行數
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 4. 關閉資源
            JDBCUtils.closeResource(ps);
        }
        return 0;
    }

    // 用於查詢特殊值得通用方法
    public <E> E getValue(Connection conn, String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for(int i=0; i<args.length; i++){
                ps.setObject(i+1, args[i]);
            }
            rs = ps.executeQuery();
            if(rs.next()){
                return (E) rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(ps, rs);
        }
        return null;
    }
}
