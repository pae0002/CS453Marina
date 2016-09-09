package MarinaWebService;
 
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import MarinaWebService.MarinaWebServiceInterface;
import java.io.Console;
import java.sql.*;
 
public class MarinaWebServiceClient{
 
	public static void main(String[] args) throws Exception {
 
    System.out.println("Enter information about the sailor you wish to look up.");
    Console con = System.console();
    System.out.println("First name:");
    String firstName = con.readLine();
    System.out.println("Last name:");
    String lastName = con.readLine();
    System.out.println("Boat type:");
    String boatType = con.readLine();
    System.out.println("Length:");
    int length = Integer.parseInt(con.readLine());
    System.out.println("Year:");
    int year = Integer.parseInt(con.readLine());
    System.out.println("Motor type:");
    String motorType = con.readLine();
    System.out.println("Fee paid (y/n):");
    String feePaid = con.readLine().toLowerCase();
    System.out.println("Assigned slip:");
    String assignedSlip = con.readLine();
    
    URL url = new URL("http://localhost:9999/ws/marina?wsdl");
 
    //1st argument service URI, refer to wsdl document above
    //2nd argument is service name, refer to wsdl document above
    QName qname = new QName("http://MarinaWebService/", "MarinaWebServiceImplService");

    Service service = Service.create(url, qname);

    MarinaWebServiceInterface marina = service.getPort(MarinaWebServiceInterface.class);

    System.out.println(marina.getSailorInfoAsString(firstName, lastName, boatType, length, year, motorType, feePaid, assignedSlip));
  }
}