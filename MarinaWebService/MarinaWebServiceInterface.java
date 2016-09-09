package MarinaWebService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
 
//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)
public interface MarinaWebServiceInterface{
	@WebMethod String getSailorInfoAsString(String firstName, String lastName, String boatType, int length, int year, String motorType, String feePaid, String assignedSlip);
}