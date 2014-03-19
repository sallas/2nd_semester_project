package dataSource;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
    	  private static String id = "";
	  private static String pw = "";
	 
          
	  public Connection getConnection()
	  {
	    Connection con = null;
	    try 
	    {  
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      con = DriverManager.getConnection(
	          "jdbc:oracle:thin:@datdb.cphbusiness.dk:1521:dat", DBConnector.id,  DBConnector.pw );  
	    }
	    catch (Exception e) 
	    {   
	    	System.out.println("\n*** Remember to insert your Oracle ID and PW in the DBConnector class! ***\n");
	    	System.out.println("error in DBConnector.getConnection()");
	        System.out.println(e); 	     
	    }    

	    return con;
	  }

}
