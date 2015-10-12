/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.pos.database;

import com.mysql.jdbc.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MysqlConnect {
    
 /* @desc A singleton database access class for MySQL
 * @author Ramindu
 */
    public Connection conn;
    private PreparedStatement statement;
    private Statement statementUD;
    public static MysqlConnect db;
    private MysqlConnect() {
        String url      = "jdbc:mysql://localhost:3306/";
        String dbName   = "resto";
        String driver   = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";
        try {
            Class.forName(driver).newInstance();
            this.conn = (Connection)DriverManager.getConnection(url+dbName,userName,password);
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException sqle) {
        }
    }
    /**
     *
     * @return MysqlConnect Database connection object
     */
    public static synchronized MysqlConnect getDbCon() {
        if ( db == null ) {
            db = new MysqlConnect();
        }
        return db;
    }
    /**
     *
     * @param query String The query to be executed
     * @param param
     * @return a ResultSet object containing the results or null if not available
     * @throws SQLException
     */
    public ResultSet query(String query, ArrayList<String> param) throws SQLException{
        statement = db.conn.prepareStatement(query);
        if (param!=null) { for (int i=0; i<param.size(); i++) {
            statement.setString(i+1, param.get(i));            
        }}
        System.out.println(statement);
        return statement.executeQuery();
    }
    /**
     * @desc Method to insert data to a table
     * @param insertQuery String The Insert query
     * @return boolean
     */
//    public int insert(String insertQuery){
//        try {
//            statement = db.conn.createStatement();
//            int result = statement.executeUpdate(insertQuery);
//            return result;
//        } catch (SQLException ex) {
//            Logger.getLogger(MysqlConnect.class.getName()).log(Level.SEVERE, null, ex);
//            return 0;
//        }
//    }
    
    /**
     *
     * @param query
     * @param param
     * @desc Method to insert data to a table
     * @return boolean
     */
    public boolean insert (String query, ArrayList<String> param) {
        try {
            statement = db.conn.prepareStatement(query);
            if (param!=null) { 
                for (int i=0; i<param.size(); i++) {            
                    statement.setString(i+1, param.get(i));
                }
            }
            System.out.println(statement);
            statement.executeUpdate();
            System.out.println("Affected "+statement.getUpdateCount());
            return statement.getUpdateCount()>0;
        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean insertTransaction (List<String> queries, ArrayList<ArrayList<String>> params){
        try {
            db.conn.setAutoCommit(false);
            for (int i=0; i<queries.size(); i++) {
//                if (this.insert(queries.get(i), params.get(i))){
//                    db.conn.rollback();
//                    db.conn.setAutoCommit(true);
//                    return false; 
//                }
                boolean insert = this.insert(queries.get(i), params.get(i));
            }
            db.conn.commit();
            db.conn.setAutoCommit(true);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnect.class.getName()).log(Level.SEVERE, null, ex);
            try {
                db.conn.rollback();
                db.conn.setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(MysqlConnect.class.getName()).log(Level.SEVERE, null, ex1);
            }            
        }
        return false;
    }
    
    public boolean updateOrDelete (String query) {
        try {
//            MysqlConnect.db = new MysqlConnect();
            statementUD = db.conn.createStatement();  
            System.out.println(query);
            statementUD.executeUpdate(query);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
 
}
