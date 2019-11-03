package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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

import org.postgresql.util.PSQLException;

import db.DBConnection;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/SecretariesServlet")
public class SecretariesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
		HttpSession session = request.getSession(false);
		String un = (String) session.getAttribute("uname");
		String pass = (String) session.getAttribute("password");
		 String sp=getHash("123","Blowfish");
		response.setContentType("text/html");

		
		
		if (new String(un).equals("gram") && new String(pass).equals(sp)) {
			String button = request.getParameter("button");

			
				if ("button2".equals(button)) {
					
					response.setContentType("text/html; charset=UTF-8");
					response.setCharacterEncoding("UTF-8");
					request.setCharacterEncoding("UTF-8");
					PrintWriter out = response.getWriter();

					try {
						Connection con = DBConnection.getMysqlConnection();

						Statement stmt = con.createStatement();
						out.println("<html>");
						out.println("<body bgcolor='gray'>");
						out.println("<table bgcolor='white' border=\"1\">");
						out.println("<tr>");

						out.println("<th>ma8hma</th>");
						out.println("<th>ka8hghths</th>");

						out.println("</tr>");

						ResultSet rs = stmt.executeQuery(
								"Select courses.title,professors.onoma,professors.epitheto from courses,professors where (courses.id_prof=professors.afm);");
						while (rs.next()) {

							String titl = rs.getString("title");
							String onoma = rs.getString("onoma");
							String epitheto = rs.getString("epitheto");

							String htmlRow = createHTMLRow(titl, onoma, epitheto);

							out.println(htmlRow);

						}
						rs.close();

						con.close();
					} catch (Exception e) {
						out.println("Database connection problem1");
					}
					out.println("<a href=\"insert.html\">Back</a>");
					out.println("</body>");
					out.println("</html>");

				} else if ("button3".equals(button)) {
					
					response.setContentType("text/html; charset=UTF-8");
					request.setCharacterEncoding("UTF-8");
					response.setCharacterEncoding("UTF-8");
					String afm = request.getParameter("af");
					String dep = request.getParameter("dp");
					String sub = request.getParameter("ma");
					String sem = request.getParameter("ek");
					int idx1 = 0;
					int idx7 = 0;

					if ((afm == "") || (dep == "") || (sub == "") || (sem == "")) {
						createDynPage(response, "Invalid request type");

					} else {

						try {
							idx1 = Integer.parseInt(afm);
							idx7 = Integer.parseInt(sem);

							Connection con = DBConnection.getMysqlConnection();

							Statement stmt1 = con.createStatement();

							String updateStmt1 = ("UPDATE courses SET id_prof=" + afm + " where title='" + sub
									+ "'and course_dept='" + dep + "'and sem=" + sem + "");

							stmt1.executeUpdate(updateStmt1);

							createDynPage(response, "Επιτυχής ανάθεση σε καθηγητή!");
							stmt1.close();

							con.close();

						} catch (PSQLException sqle) {
							createDynPage(response, "Invalid request type");

						}

					}

				} else if ("button1".equals(button)) {
					
					response.setContentType("text/html; charset=UTF-8");
					response.setCharacterEncoding("UTF-8");
					request.setCharacterEncoding("UTF-8");
					PrintWriter out = response.getWriter();

					try {
						Connection con = DBConnection.getMysqlConnection();

						Statement stmt = con.createStatement();
						out.println("<html>");
						out.println("<body bgcolor='gray'>");
						out.println("<table bgcolor='white' border=\"1\">");
						out.println("<tr>");
                        
						out.println("<th>Mathima</th>");
						out.println("<th>eksamhno</th>");
						out.println("<th>id ka8hghth</th>");
						out.println("<th>tmhma</th>");

						out.println("</tr>");

						ResultSet rs = stmt.executeQuery("Select * from courses;");
						while (rs.next()) {

							String ma8 = rs.getString("title");
							int eks = rs.getInt("sem");
							int id = rs.getInt("id_prof");
							String dep = rs.getString("course_dept");

							String htmlRow = createHTMLRow2(ma8, eks, id, dep);

							out.println(htmlRow);

						}
						
						rs.close();

						con.close();
					} catch (Exception e) {
						out.println("Database connection problem");
					}
					out.println("<a href=\"insert.html\">Back</a>");
					out.println("</body>");
					out.println("</html>");

				}else if ("button4".equals(button)) {
					
					request.getSession().invalidate();
					response.sendRedirect(request.getContextPath());
					response.setHeader("Pragma", "No-cache");
					response.setHeader("Cache-Control", "no-cache");
					response.setDateHeader("Expires", 1);
					

				} else if ("button5".equals(button)) {
					
					response.setContentType("text/html; charset=UTF-8");
					request.setCharacterEncoding("UTF-8");
					response.setCharacterEncoding("UTF-8");
					String am = request.getParameter("am");
					String on = request.getParameter("on1");
					String ep = request.getParameter("ep");
					String us1 = request.getParameter("us");
					String dp1 = request.getParameter("dp2");
					String ek2 = request.getParameter("ek2");
					int idx1 = 0;
					int idx7 = 0;

					if ((am== "") || (on == "") || (ep == "") || (us1 == "")|| (dp1 == "")|| (ek2 == "")) {
						createDynPage(response, "Όλα τα στοιχεία είναι υποχρεωτικά!");

					} else {

						try {
							idx1 = Integer.parseInt(am);
							idx7 = Integer.parseInt(ek2);

							Connection con = DBConnection.getMysqlConnection();

							Statement stmt1 = con.createStatement();

							String insertStmt1 = ("Insert into users VALUES("+ idx1+",'"+ on +"','"+ep+"','"+us1+"','"+dp1+"')");
							String insertStmt2 = ("Insert into students VALUES("+ am +",'"+ on +"','"+ep+"','"+us1+"','"+dp1+"',"+idx7+")");

							stmt1.executeUpdate(insertStmt1);
							stmt1.executeUpdate(insertStmt2);
							

							createDynPage(response, "Επιτυχής εγγραφή Φοιτητή!");
							stmt1.close();

							con.close();

						} catch (PSQLException sqle) {
							createDynPage(response, "Invalid request type");

						}

					}

				} else if ("button6".equals(button)) {
					
					response.setContentType("text/html; charset=UTF-8");
					request.setCharacterEncoding("UTF-8");
					response.setCharacterEncoding("UTF-8");
					String afm1 = request.getParameter("af2");
					String on2 = request.getParameter("on2");
					String ep2 = request.getParameter("ep2");
					String us2 = request.getParameter("us2");
					String dp2 = request.getParameter("dp3");
					
					int idx1 = 0;
					

					if ((afm1== "") || (on2 == "") || (ep2 == "") || (us2 == "")|| (dp2 == "")) {
						createDynPage(response, "Όλα τα στοιχεία είναι υποχρεωτικά!");

					} else {

						try {
							idx1 = Integer.parseInt(afm1);
							

							Connection con = DBConnection.getMysqlConnection();

							Statement stmt1 = con.createStatement();

							String insertStmt1 = ("Insert into users VALUES("+ idx1+",'"+ on2 +"','"+ep2+"','"+us2+"','"+dp2+"')");
							String insertStmt2 = ("Insert into professors VALUES("+ afm1 +",'"+ on2 +"','"+ep2+"','"+us2+"','"+dp2+"')");

							stmt1.executeUpdate(insertStmt1);
							stmt1.executeUpdate(insertStmt2);
							

							createDynPage(response, "Επιτυχής εγγραφή Καθηγητή!");
							stmt1.close();

							con.close();

						} catch (PSQLException sqle) {
							
							createDynPage(response, "Invalid request type");

						}

					}

				}
		   }else {
			   response.sendRedirect("index.html"); 
		   }
		} catch (Exception a) {
			response.sendRedirect("index.html");
		}
	}
	
	private String createHTMLRow(String title, String epitheto, String onoma) {
		String row = "<tr>";

		row += "<td>" + title + "</td>";
		row += "<td>" + epitheto + "</td>";
		row += "<td>" + onoma + "</td>";
		row += "</tr>";
		
		return row;

	}

	private String createHTMLRow2(String ma8, int sem, int id, String dept) {
		String row = "<tr>";

		row += "<td>" + ma8 + "</td>";
		row += "<td>" + sem + "</td>";
		row += "<td>" + id + "</td>";
		row += "<td>" + dept + "</td>";
		row += "</tr>";
		return row;
	}

	private void createDynPage(HttpServletResponse response, String message) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Εισαγωγή στοιχείων φοιτητή</title></head>");
		out.println("<body bgcolor='gray'>");
		out.println("<p>" + message + "</p>");
		out.println("<a href=\"insert.html\">Πίσω</a>");
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

}