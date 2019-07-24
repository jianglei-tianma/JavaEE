package com.atguigu.bookstore.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.atguigu.bookstore.utils.JDBCUtils;

/**
 * 封装通用的数据库增删改查的操作
 * 
 * @author Administrator 继承baseDao的类需要指定泛型
 *
 *         其实就是你们以前写的Dao，泛型从参数中传递过来的 泛型就是为了指定当前类 操作的对象的类 BaseDao<User>
 */
public class BaseDao<T> {
	// 通过dbutils操作数据库 可以帮助我们封装记录成 对象 或集合
	private QueryRunner runner = new QueryRunner();

	// beanhandler需要的参数
	// type其实就是 T的类型
	// BaseDao是交给其他dao继承的父类,不会直接创建其对象
	// UserDao extends BaseDao<User>
	public BaseDao() {
		// 因为不创建BaseDao的对象，所以此构造器都是由子类调用
		// this代表子类
		// 获取子类类型 UserDao
		Class cla = this.getClass();
		// 通过子类获取父类 BaseDao<User> getGenericSuperclass
		// 获取的父类其实是一个 有参数的类型ParameterizedType
		ParameterizedType pt = (ParameterizedType) cla.getGenericSuperclass();
		System.out.println("父类的真实类型是：" + pt);
		// 通过 获取的父类 获取 参数 <T>
		Type[] types = pt.getActualTypeArguments();
		// 只有一个参数 索引为0就可以读取到 T
		type = (Class<T>) types[0];
		System.out.println("泛型类型：" + type);
	}

	private Class<T> type;

	/**
	 * 对数据库表的增 insert 删 delete 改 update params 是一个可变参数 后面可以有0到多个Object类型的参数
	 * String sql = insert into bs_user(username,password,email) values(?,?,?);
	 * 可变参数就是为了填充sql的占位符 name ,pwd update(String sql,name,pwd,email);
	 * 
	 * @param sql
	 * @param params
	 * @return 如果返回值大于0 操作数据库成功
	 */
	public int update(String sql, Object... params) {
		int num = 0;
		// runner的最后一个update方法
		// 参数1：数据库连接
		// 参数2：sql语句
		// 参数3：sql语句使用的参数列表
		Connection conn = JDBCUtils.getConnection();
		try {
			num = runner.update(conn, sql, params);
		} catch (SQLException e) {
			//e.printStackTrace();
			//将编译时异常转换成运行时异常向上抛
			throw new RuntimeException(e);
		} finally {
			// 关闭资源
			//JDBCUtils.releaseConnection(conn);
		}
		return num;
	}

	/**
	 * 查询单个javabean的方法 user
	 * 
	 * @param sql
	 * @param params
	 * @return String sql =
	 *         "select id , username , password , email form bs_user where id=?"
	 *         ; getBean(sql,id)
	 */
	public T getBean(String sql, Object... params) {
		T t = null;
		Connection conn = JDBCUtils.getConnection();
		// ResultSetHandler 得到一个结果集的帮助类
		// BeanHandler :封装单条数据库记录的帮助类
		// 参数：Class<T> type; 泛型不能获取自己的class
		// BeanHandler.BeanHandler<T>(Class<T> type)
		try {
			// conn sql params已经可以查询到一条对应的记录了
			// 可以通过ResultSetHandler<>(Class<T>type)帮助类 通过反射 将记录创建为type类型的对象
			t = runner.query(conn, sql, new BeanHandler<>(type), params);
		} catch (SQLException e) {
			//e.printStackTrace();
			//将编译时异常转换成运行时异常向上抛
			throw new RuntimeException(e);
		} finally {
			// 关闭资源
			//JDBCUtils.releaseConnection(conn);
		}
		return t;
	}

	public List<T> getBeanList(String sql, Object... params) {
		List<T> list = null;
		Connection conn = JDBCUtils.getConnection();
		// 需要一个封装多条记录为一个对象集合的帮助类
		try {
			list = runner.query(conn, sql, new BeanListHandler<>(type), params);
		} catch (SQLException e) {
			//e.printStackTrace();
			//将编译时异常转换成运行时异常向上抛
			throw new RuntimeException(e);
		} finally {
			// 关闭资源
			//JDBCUtils.releaseConnection(conn);
		}
		return list;
	}

	/**
	 * 返回一个单一的值，专门用来执行像select count(*) ...这样的sql语句
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public Object getSingleValue(String sql, Object... params) {
		// 获取连接
		Connection connection = JDBCUtils.getConnection();
		Object count = null;
		try {
			count = runner.query(connection, sql, new ScalarHandler(), params);
		} catch (SQLException e) {
			//e.printStackTrace();
			
			//将编译时异常转换成运行时异常向上抛
			throw new RuntimeException(e);
			
		} finally {
			//JDBCUtils.releaseConnection(connection);
		}
		return count;
	}
	
	/**
	 * 批处理方法
	 * 关于二维数组object[][] params
	 * 		二维数组中的第一维 是sql 要执行的次数
	 * 		二维数组中的第二维是sql 语句中的占位符的个数
	 * @param sql
	 * @param params
	 */
	public void batchUpdate(String sql,Object[][] params){
		//获取连接
		Connection connection = JDBCUtils.getConnection();
		try {
			runner.batch(connection, sql, params);
		} catch (SQLException e) {
			//e.printStackTrace();
			//将编译时异常转换成运行时异常向上抛
			throw new RuntimeException(e);
		}finally{
			//JDBCUtils.releaseConnection(connection);
		}

	}
}
