package c3p0test.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.sql.DriverManager;

import javax.sql.DataSource;
import javax.swing.plaf.basic.ComboPopup;

import com.mchange.v2.c3p0.ComboPooledDataSource;



public class JDBCUtil {
	private JDBCUtil() {};
	private static ComboPooledDataSource ds = null;
	private static ThreadLocal<Connection> tLocal = new ThreadLocal<>();
	static {
		ds = new  ComboPooledDataSource();   //读取默认配置文件
	}
	public static DataSource getDataSource() {
		return ds;
	}
	public static Connection getConnection() {
		Connection con = tLocal.get();
		if(con == null) {
			try {
				con = ds.getConnection();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return con;
	}
	
	
	//使用c3p0连接池
    public static void main(String[] args) throws Exception {
        long x=Calendar.getInstance().getTimeInMillis();    //获取链接之前，的时间
        
        ComboPooledDataSource ds=new ComboPooledDataSource();    //读取xml文件的配置
        
        for(int i=0;i<1;i++){                            //循环1000次，即打开链接，关闭链接1000次
            Connection conn=ds.getConnection();
            System.out.println("sss");
            conn.close();
        }
        long y=Calendar.getInstance().getTimeInMillis();    //循环完成后的时间
        System.out.println("c3p0连接池需要的时间是:  "+(y-x));                            //最后打印一下完成整个循环所需要的时间      
        
    }

    
   /*
   //使用jdbc方式链接      5878
    public static void main(String[] args) throws Exception {        
        long x=Calendar.getInstance().getTimeInMillis();        //获取链接之前，的时间
        
        for(int i=0;i<1000;i++){
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp;characterEncoding=utf8","root","lypdatabase");
            conn.close();                //这里也是打开一次之后就关闭一次
        }
        long y=Calendar.getInstance().getTimeInMillis();
        System.out.println("jdbc链接方式需要的时间是  "+(y-x));        //最后打印一下完成整个循环所需要的时间      
        
    }*/
}


