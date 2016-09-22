package MarinaWebService;

import javax.jws.WebService;
import java.sql.*;
 
//Service Implementation
@WebService(endpointInterface = "MarinaWebService.MarinaWebServiceInterface")
public class MarinaWebServiceImpl implements MarinaWebServiceInterface {
      
    @Override //looks up a sailor in the marina's database
    public String getSailorInfoAsString(String firstName, String lastName, String boatType, int length, int year, String motorType, String feePaid, String assignedSlip) {

      try {
          Class.forName("com.mysql.jdbc.Driver"); //make sure the driver is available
          Connection conn = DriverManager.getConnection(
              "jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9134943","sql9134943","lhnt9n8tY2"); //connect to the database
          Statement stmt = conn.createStatement(); //create a query object
          System.err.println("Connection valid: " + String.valueOf(conn.isValid(3)));
          ResultSet reply; //create a result object

          reply = stmt.executeQuery("SELECT firstName, lastName, boatType, length, year, motorType, feePaid FROM sailboats " +
              "WHERE firstName = '" + firstName + "' AND lastName = '" + lastName + "' AND boatType = '" + boatType + "' AND length = '" + length + "' AND year =  '" + year + "' AND motorType = '" + motorType + "'"); //get matching sailors
          if( reply.next() ) { //if there was a match
              String result = " First Name: " + reply.getString("firstName"); //build a string with all the desired information
              result = result + ", Last Name: " + reply.getString("lastName");
              result = result + ", Boat Type: " + reply.getString("boatType");
              result = result + ", Length: " + reply.getString("length");
              result = result + ", Year: " + reply.getString("year");
              result = result + ", Motor: " + reply.getString("motorType");
              if(reply.getString("feePaid").equals("n")) result = "FEE NOT PAID. " + result; //tag sailors who haven't paid their fee
              System.err.println("Sending client: " + result);
              return result;
          }
          conn.close();
      } catch (Exception e) {
          System.err.println("Exception caught:"); //show any exceptions
          System.err.println(e.getMessage());
      }
          return "No matching sailor found."; //if the reply was empty, notify the client
    }
}