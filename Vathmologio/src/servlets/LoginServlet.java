package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import db.DBConnection;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/LoginServlet")

public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 
		try {
		   
			
			HttpSession session = request.getSession();
			String un = request.getParameter("uname");
			String pass = request.getParameter("password");
			
			
			
            pass=getHash(pass,"Blowfish");
            String sp=getHash("123","Blowfish");
            session.setAttribute("uname", un);
			session.setAttribute("password", pass);
			String usern = null;
			String passw = null;
			
				if (new String(un).equals("gram") && new String(pass).equals(sp)) {
					response.sendRedirect("insert.html");
					
					

				} else if (new String(un).equals("") || new String(pass).equals("")) {
					createDynPage1(response, "Insert username and password!");

				} else {
					
					Connection con = DBConnection.getMysqlConnection();

					Statement stmt = con.createStatement();
					Statement stmt1 = con.createStatement();
					ResultSet rs = stmt.executeQuery(
							"Select id_s, username from students where username='" + un + "'and id_s=" + decrypt(pass,"Blowfish")+"");
					
					while (rs.next()) {
						
						usern = rs.getString("username");
						passw = String.valueOf(rs.getInt("id_s"));
                        passw=getHash(passw,"Blowfish");
					}
					
					if (new String(un).equals(usern) && new String(pass).equals(passw)) {
						response.sendRedirect("index2.html");

						session.setAttribute("uname", usern);
						session.setAttribute("password", passw);

					} else {
                      
						ResultSet rs1 = stmt1.executeQuery(
								"Select afm, username from professors where afm=" + decrypt(pass,"Blowfish") + "and username='" + un + "'");

						while (rs1.next()) {

							usern = rs1.getString("username");
							passw = String.valueOf(rs1.getInt("afm"));
							 passw=getHash(passw,"Blowfish");
						}

						if (new String(un).equals(usern) && new String(pass).equals(passw)) {
							response.sendRedirect("index3.html");
							session.setAttribute("uname", usern);
							session.setAttribute("password", passw);

						} else {
							createDynPage1(response,
									"You are not signed in system! Check your us/passwd or sign in first!");
						}

					}
					  stmt.close();
					  stmt1.close();
				}
      
			} catch (Exception ex) {
				response.sendRedirect("index.html");
				
			}

		
	} 
	private void createDynPage1(HttpServletResponse response, String message) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Εισαγωγή στοιχείων φοιτητή</title></head>");
		out.println("<body>");
		out.println("<p>" + message + "</p>");
		out.println("<a href=\"index.html\">Πίσω</a>");
		out.println("</body></html>");

	}
	public static String getHash(String strClearText,String strKey) throws Exception{
		String strData="";
		
		try {
			SecretKeySpec skeyspec=new SecretKeySpec(strKey.getBytes(),"Blowfish");
			Cipher cipher=Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
			byte[] encrypted=cipher.doFinal(strClearText.getBytes());
			strData=new String(encrypted);
			
		} catch (Exception e) {
			
			throw new Exception(e);
		}
		return strData;
	}
	public static String decrypt(String strEncrypted,String strKey) throws Exception{
		String strData="";
		
		try {
			SecretKeySpec skeyspec=new SecretKeySpec(strKey.getBytes(),"Blowfish");
			Cipher cipher=Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, skeyspec);
			byte[] decrypted=cipher.doFinal(strEncrypted.getBytes());
			strData=new String(decrypted);
			
		} catch (Exception e) {
			
			throw new Exception(e);
		}
		return strData;
	}
}