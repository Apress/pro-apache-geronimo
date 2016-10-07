
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import javax.xml.namespace.QName;
import org.apache.axis.encoding.XMLType;
import javax.xml.rpc.ParameterMode;


public class CalculatorClient {

	public static void main(String[] args) {
		String endpoint ="http://localhost:8080/sampleWS/calculatorService";
		try {
			Service  service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress( new java.net.URL(endpoint) );
			call.setOperationName(new QName("http://samples.ws.web", "add"));
			call.addParameter( "num1", XMLType.XSD_INT, ParameterMode.IN );
			call.addParameter( "num2", XMLType.XSD_INT, ParameterMode.IN );
			call.setReturnType( XMLType.XSD_INT );
			Integer ret = (Integer) call.invoke( new Object [] { new Integer(5), new Integer(10) });
			System.out.println("Got result : " + ret);
		} catch(Exception e) {
			System.out.println("Error:"+e.getMessage());
		}


	}

}

