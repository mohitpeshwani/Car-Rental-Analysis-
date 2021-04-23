import java.sql.*;

public class Sales_Analysis {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/sale_analysis";

   //  Database credentials
   static final String USER = "root";
   
   static float luxury,c_luxury=0;
   static float suv,c_suv=0;
   static float bluxury,c_bluxury=0;
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,"");

      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql;
      sql = "SELECT car_type FROM car where car_type='luxury' ";
      ResultSet rs = stmt.executeQuery(sql);

      //STEP 5: Extract data from result set
      while(rs.next()){
          c_luxury++;
         //Display values
         
        }
        String sql1;
      sql1 = "SELECT car_type FROM car where car_type='suv' ";
      ResultSet rs1 = stmt.executeQuery(sql1);
         while(rs1.next()){
          c_suv++;
         //Display values
         
        }
        String sql2;
      sql2 = "SELECT car_type,car_color FROM car where car_type='luxury' and car_color='black' " ;
      ResultSet rs2 = stmt.executeQuery(sql2);
         while(rs2.next()){
          c_bluxury++;
         //Display values
         
        }

      //STEP 6: Clean-up environment
      rs.close();
      stmt.close();
      conn.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
}//end FirstExample