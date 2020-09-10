import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;


public class Insert extends HttpServlet {
  public boolean Check_num(String Num)
  {
    if (Num == null || Num.length()<11) {
      return false;
  }
  try {
      double d = Double.parseDouble(Num);
  } catch (NumberFormatException nfe) {
      return false;
  }
  return true;
}
  //Process the HTTP Get request
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
	response.setContentType("text/html");
    
	// get PrintWriter object
	PrintWriter out = response.getWriter();

    String name=request.getParameter("name");
    String address=request.getParameter("address");
    String phonenumber=request.getParameter("phonenumber");

    out.println("<html>");
    out.println("<head><title>Response</title></head>");
    out.println("<body bgcolor=\"#ffffff\">");


    try{

    Class.forName("com.mysql.jdbc.Driver");

    String url = "jdbc:mysql://127.0.0.1/lab11";

    Connection con=DriverManager.getConnection(url, "root", "root");

    Statement st=con.createStatement();
      if(Check_num(phonenumber))
      {
        out.println("<h1>Number is correct.</h1>");
      }
     if(address !="" && name != "" && phonenumber!="" && Check_num(phonenumber))
     {
      String query = "INSERT INTO info(username,address,phonenumber)VALUES('"+ name + "','" + address+ "','" + phonenumber+ "') ";

      System.out.println(query);
      int rs = st.executeUpdate( query );
      if(rs==1){	out.println("<h1>Insertion successful</h1>"); 		}
   else{	out.println("<h1>Record could not be inserted.</h1>"); 		}
 
      out.println("</body></html>");
    
     }
     else
     {
      out.println("<h1>Record could not be inserted.</h1>"); 
      out.println("</body></html>");
     }
    
    

           st.close();
           con.close();

    }catch(Exception e){

      out.println(e);
      out.println("<p>This Name Already Exists.Try another one</p>");
    }
    out.println("<button onclick=window.location.href='http://localhost:8080/lab11/main.html'>Main Menu</button>");

  }

}
