/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.pos.database;

import com.mysql.jdbc.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MysqlConnect {
    
 /* @desc A singleton database access class for MySQL
 * @author Ramindu
 */
    public Connection conn;
    private Statement statement;
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
     * @return a ResultSet object containing the results or null if not available
     * @throws SQLException
     */
    public ResultSet query(String query) throws SQLException{
        statement = db.conn.createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;
    }
    /**
     * @desc Method to insert data to a table
     * @param insertQuery String The Insert query
     * @return boolean
     */
    public int insert(String insertQuery){
        try {
            statement = db.conn.createStatement();
            int result = statement.executeUpdate(insertQuery);
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnect.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            try {                
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(MysqlConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public int insertTransaction (List<String> queries){
        try {
            db.conn.setAutoCommit(false);
            for (String query : queries) {                
                if (this.insert(query)<=0) return 0;
            }
            db.conn.commit();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnect.class.getName()).log(Level.SEVERE, null, ex);
            try {
                db.conn.rollback();
                return 0;
            } catch (SQLException ex1) {
                Logger.getLogger(MysqlConnect.class.getName()).log(Level.SEVERE, null, ex1);
            }            
        }
        return 0;
    }
 
}
