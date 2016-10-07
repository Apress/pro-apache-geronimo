
package samples.ws.web;

import java.rmi.Remote;

public class CalculatorImpl implements Calculator {

	public int add(int num1, int num2){
		return num1+num2;
	}
}