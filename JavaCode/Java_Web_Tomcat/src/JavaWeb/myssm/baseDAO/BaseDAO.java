package JavaWeb.myssm.baseDAO;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDAO<T> {
    // dbUtils.jar => QueryRunner工具類，實現SQL CRUD 操作
    private static QueryRunner runner = new QueryRunner();
    // 透過映射取出子類傳入的泛型類型
    private Class<T> clazz = null;
    {
        Type type = this.getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType)type;
        clazz = (Class<T>)paramType.getActualTypeArguments()[0];
    }

    // 通用查詢多個操作 (考慮事務)
    public List<T> getForList(Connection conn, String sql, Object... args) {
        try {
            BeanListHandler<T> handler = new BeanListHandler<>(clazz);
            return runner.query(conn, sql, handler, args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 通用查詢單個操作 (考慮事務)
    public T getInstance( Connection conn, String sql, Object... args) {
        try {
            BeanHandler<T> handler = new BeanHandler<>(clazz);
            return runner.query(conn,sql,handler,args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 通用Update(考慮事務)
    public int update(Connection conn, String sql, Object... args) {
        try {
            return runner.update(conn,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 用於查詢特殊值得通用方法
    public <E> E getValue(Connection conn, String sql, Object... args) {
        try {
            ScalarHandler<E> handler = new ScalarHandler<E>();
            return runner.query(conn, sql, handler, args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
