
package samples.connectors.inbound;



public class SampleMessage
{
	private String msg;
    public SampleMessage(String msg){
    	this.msg=msg;
    }

    public String getMsg(){
		return msg;
	}
}