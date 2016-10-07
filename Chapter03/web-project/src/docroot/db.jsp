
<%@ page import="javax.sql.*" %>
<%@ page import="java.sql.*" %>
<%@ page import ="javax.naming.*" %>

<%
Connection con = null;
Statement stmt = null;
ResultSet rs = null;

try {
	Context initContext = new InitialContext();
	Context envContext = (Context) initContext.lookup("java:/comp/env");
	DataSource ds = (DataSource) envContext.lookup("jdbc/myresource");

	con = ds.getConnection();
	
	stmt = con.createStatement();
	
	try{
		stmt.executeUpdate("DROP TABLE TMP_USERS");
	} catch(Exception e) {
		// ignore
		
	} finally{
		stmt.executeUpdate ("CREATE TABLE TMP_USERS (USERNAME VARCHAR(20),PASSWORD VARCHAR(20), FIRSTNAME VARCHAR(20),LASTNAME VARCHAR(20), EMAIL VARCHAR(40))");
	}
	
	stmt.executeUpdate ("Insert into TMP_USERS (USERNAME, PASSWORD,FIRSTNAME,LASTNAME, EMAIL) values ('guser', 'mypw', 'ger','onimo', 'geronimo@geronimo.com')");


	rs = stmt.executeQuery ("SELECT * FROM TMP_USERS");
	
%>	

Your user table contains the following entries:<BR>
<table>
<tr><B><th>Name></th><th>Password</th><th>Firstname
</th><th>Lastname</th><th>Email Address</th></B></tr>

<%
while (rs.next()) {
String name = rs.getString("USERNAME");
String pw = rs.getString("PASSWORD");
String fn = rs.getString("FIRSTNAME");
String ln = rs.getString("LASTNAME");
String em = rs.getString("EMAIL");
%>
<tr>
<td><%=name%></td>
<td><%=pw%></td>
<td><%=fn%></td>
<td><%=ln%></td>
<td><%=em%></td>
</tr>
</table>

<%
}

} catch (java.lang.Exception e) {
	e.printStackTrace();
	System.err.println(e.getMessage());
} finally {

try {rs.close();}catch(Exception e){}
try {stmt.close();}catch(Exception e){}
try {con.close();}catch(Exception e){}
}

%>