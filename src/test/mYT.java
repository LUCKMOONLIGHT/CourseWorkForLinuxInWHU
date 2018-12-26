package test;

import java.sql.*;
public class mYT {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        PreparedStatement ps=null;      //(这里也可以使用statement,视情况而定)
        Connection ct=null;
        ResultSet rs=null;
        
        try {
            
            //1.加载驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //String url="jdbc:sqlserver://localhost:1433;databaseName=test";
            String url2="jdbc:sqlserver://localhost:1433;Database=数据库A";
            //String url3="jdbc:sqlserver://localhost//SQLEXPRESS01;Database=master";
            //String url4="jdbc:sqlserver://localhost:1433;DatabaseName=数据库A";
            String user="wo";//sa超级管理员
            String password="123456";//密码
            //2.连接
            ct=DriverManager.getConnection( url2,user,password);
            //3.创建发送端         

            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = ct.createStatement();
            //要执行的SQL语句
            String sql = "select * from stu2";
            //3.ResultSet类，用来存放获取的结果集！！
            
            rs = statement.executeQuery(sql);
            System.out.println("-----------------");
            System.out.println("执行结果如下所示:");  
            System.out.println("-----------------");  
            System.out.println(" 学号" + "\t" + " 姓名");  
            System.out.println("-----------------");  
            
            
            String name = null;
            int id = 0;
            while(rs.next()){
                //获取stuname这列数据
                name = rs.getString("sname");
                //获取stuid这列数据
                id = rs.getInt("sid");
                //首先使用ISO-8859-1字符集将name解码为字节序列并将结果存储新的字节数组中。
                //然后使用GB2312字符集解码指定的字节数组。
                //name = new String(name.getBytes("ISO-8859-1"),"gb2312");
                //输出结果
                System.out.println(id + "\t" + name);
            }
            
            PreparedStatement psql;
            ResultSet res;
            //预处理添加数据，其中有两个参数--“？”
            psql = ct.prepareStatement("insert into stu2 values(?,?)");
            psql.setString(1, "xiaogang");      //设置参数1，name 为小明
            psql.setInt(2, 5);              //设置参数2，创建id为5的数据
           
            psql.setString(1, "xia");      //设置参数1，name 为小明
            psql.setInt(2, 6);
            //psql.executeUpdate();           //执行更新
             
            psql.setString(1, "xiao");      //设置参数1，name 为小明
            psql.setInt(2, 10);
            psql.executeUpdate(); 
            
            //预处理更新（修改）数据
            psql = ct.prepareStatement("update stu2 set sname = ? where sid = ?");
            psql.setString(1,"xiao");       //设置参数1，将name改为王五
            psql.setInt(2,7);              //设置参数2，将id为2的数据做修改 
            psql.executeUpdate();
             
            //预处理删除数据
            psql = ct.prepareStatement("delete from stu2 where sid = ?");
            psql.setInt(1, 10);
            psql.executeUpdate();
             
            //查询修改数据后student表中的数据
            psql = ct.prepareStatement("select*from stu2");
            res = psql.executeQuery();          //执行预处理sql语句
            System.out.println("执行增加、修改、删除后的数据");
            while(res.next()){
                name = res.getString("sname");
                id = res.getInt("sid");
                //name = new String(name.getBytes("ISO-8859-1"),"gb2312");
                System.out.println(id + "\t" + name);
            }
            
            
            
            
            
             System.out.println("成功插入一条数据记录！");  
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
                    
            //关闭资源,加强程序的健壮性
            try {
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
