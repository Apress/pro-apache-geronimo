


import javax.servlet.http.HttpServletRequest;

public class WelcomeCommand implements Command {
	public Object execute(HttpServletRequest request){
		return "Hello World !!!";
	}
	public String getNextView(HttpServletRequest request, Object result){
		return "/welcome.jsp";
	}
}
