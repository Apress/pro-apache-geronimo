
import javax.servlet.http.HttpServletRequest;

public interface Command{
	public Object execute(HttpServletRequest request);
	public String getNextView(HttpServletRequest request, Object result);
}