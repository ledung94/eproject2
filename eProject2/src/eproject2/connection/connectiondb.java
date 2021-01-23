/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eproject2.connection;

import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author THINH PC
 */
public class connectiondb {
    java.sql.Connection con = null;
    java.sql.Statement stmt = null;
    ResultSet rs = null;
    
    public connectiondb(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/eproject22","root","");
            stmt=con.createStatement();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public java.sql.Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/eproject22","root","");
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }

    public boolean checkLogin(String username,String password, String user){
        String query="SELECT * FROM users WHERE username='"+username+"' AND password='"+password+"' LIMIT 1";
        try{
            rs=stmt.executeQuery(query);
            if(rs.next()){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
            
        return false;
    }
 
}

 
