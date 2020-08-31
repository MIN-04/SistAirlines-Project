/**
 * Descripttion
 * 
 */
package com.hr.cmn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;


public class ConnectionMaker {
   private final Logger LOG =Logger.getLogger(this.getClass());
   
   public Connection getConnection() {
    
         Connection connection = null; 
         try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
         } catch (ClassNotFoundException e) {
            LOG.debug("===========================");
            LOG.debug("=Class Not Found Exception="+e.getMessage());
            LOG.debug("===========================");
            e.printStackTrace();
         }
         
         String url = "jdbc:oracle:thin:@211.238.142.124:1521:orcl";
         String user = "SIST_A";
         String password = "0306";
//         
//         String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//         String user = "scott";
//         String password = "scott";
         try {
            connection = DriverManager.getConnection(url, user, password);
            LOG.debug("1.connection="+connection);
         } catch (SQLException e) {
            LOG.debug("===========================");
            LOG.debug("=SQLException             ="+e.getMessage());
            LOG.debug("===========================");
            e.printStackTrace();
         }
         
         return connection;
         
      }
}