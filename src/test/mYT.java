package test;

import java.sql.*;
public class mYT {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        PreparedStatement ps=null;      //(����Ҳ����ʹ��statement,���������)
        Connection ct=null;
        ResultSet rs=null;
        
        try {
            
            //1.��������
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //String url="jdbc:sqlserver://localhost:1433;databaseName=test";
            String url2="jdbc:sqlserver://localhost:1433;Database=���ݿ�A";
            //String url3="jdbc:sqlserver://localhost//SQLEXPRESS01;Database=master";
            //String url4="jdbc:sqlserver://localhost:1433;DatabaseName=���ݿ�A";
            String user="wo";//sa��������Ա
            String password="123456";//����
            //2.����
            ct=DriverManager.getConnection( url2,user,password);
            //3.�������Ͷ�         

            //2.����statement���������ִ��SQL��䣡��
            Statement statement = ct.createStatement();
            //Ҫִ�е�SQL���
            String sql = "select * from stu2";
            //3.ResultSet�࣬������Ż�ȡ�Ľ��������
            
            rs = statement.executeQuery(sql);
            System.out.println("-----------------");
            System.out.println("ִ�н��������ʾ:");  
            System.out.println("-----------------");  
            System.out.println(" ѧ��" + "\t" + " ����");  
            System.out.println("-----------------");  
            
            
            String name = null;
            int id = 0;
            while(rs.next()){
                //��ȡstuname��������
                name = rs.getString("sname");
                //��ȡstuid��������
                id = rs.getInt("sid");
                //����ʹ��ISO-8859-1�ַ�����name����Ϊ�ֽ����в�������洢�µ��ֽ������С�
                //Ȼ��ʹ��GB2312�ַ�������ָ�����ֽ����顣
                //name = new String(name.getBytes("ISO-8859-1"),"gb2312");
                //������
                System.out.println(id + "\t" + name);
            }
            
            PreparedStatement psql;
            ResultSet res;
            //Ԥ����������ݣ���������������--������
            psql = ct.prepareStatement("insert into stu2 values(?,?)");
            psql.setString(1, "xiaogang");      //���ò���1��name ΪС��
            psql.setInt(2, 5);              //���ò���2������idΪ5������
           
            psql.setString(1, "xia");      //���ò���1��name ΪС��
            psql.setInt(2, 6);
            //psql.executeUpdate();           //ִ�и���
             
            psql.setString(1, "xiao");      //���ò���1��name ΪС��
            psql.setInt(2, 10);
            psql.executeUpdate(); 
            
            //Ԥ������£��޸ģ�����
            psql = ct.prepareStatement("update stu2 set sname = ? where sid = ?");
            psql.setString(1,"xiao");       //���ò���1����name��Ϊ����
            psql.setInt(2,7);              //���ò���2����idΪ2���������޸� 
            psql.executeUpdate();
             
            //Ԥ����ɾ������
            psql = ct.prepareStatement("delete from stu2 where sid = ?");
            psql.setInt(1, 10);
            psql.executeUpdate();
             
            //��ѯ�޸����ݺ�student���е�����
            psql = ct.prepareStatement("select*from stu2");
            res = psql.executeQuery();          //ִ��Ԥ����sql���
            System.out.println("ִ�����ӡ��޸ġ�ɾ���������");
            while(res.next()){
                name = res.getString("sname");
                id = res.getInt("sid");
                //name = new String(name.getBytes("ISO-8859-1"),"gb2312");
                System.out.println(id + "\t" + name);
            }
            
            
            
            
            
             System.out.println("�ɹ�����һ�����ݼ�¼��");  
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
                    
            //�ر���Դ,��ǿ����Ľ�׳��
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
