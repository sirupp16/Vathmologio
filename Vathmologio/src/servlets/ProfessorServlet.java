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

@WebServlet("/ProfessorServlet")
public class ProfessorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			
			String pa = (String) session.getAttribute("password");
			

			try {

				String button = request.getParameter("button1");

				if ("button1".equals(button)) {

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

						out.println("<th>AM</th>");
						out.println("<th>MA8HMA</th>");
						out.println("<th>VATHMOS</th>");
						out.println("</tr>");

						ResultSet rs = stmt.executeQuery(
								"Select grades.id_s,grades.title, grades.grade from courses inner join grades on courses.title= grades.title and courses.sem=grades.course_sem where courses.id_prof='"
										+decrypt(pa,"Blowfish")+ "'and grades.grade is not null");
						while (rs.next()) {

							String foit = rs.getString("id_s");
							String titl = rs.getString("title");
							int vathmos = rs.getInt("grade");

							String htmlRow = createHTMLRow(foit, titl, vathmos);

							out.println(htmlRow);

						}
						rs.close();

						con.close();
					} catch (Exception e) {

						out.println("Database connection problem");
					}
					out.println("<a href=\"index3.html\">Back</a>");
					out.println("</body>");
					out.println("</html>");

				} else if ("button2".equals(button)) {
					response.setContentType("text/html; charset=UTF-8");
					request.setCharacterEncoding("UTF-8");
					response.setCharacterEncoding("UTF-8");
					String MATH = request.getParameter("Mat");
					String AM = request.getParameter("id");
					String VA = request.getParameter("gr");
					String c = null;
					int s = 0;
					int idx1 = 0;
					int idx2 = 0;

					if ((MATH == "") || (AM == "") || (VA == "")) {
						createDynPage(response, "Invalid request type");

					} else {

						try {
							idx1 = Integer.parseInt(AM);
							idx2 = Integer.parseInt(VA);

							Connection con = DBConnection.getMysqlConnection();
							Statement stmt = con.createStatement();
							Statement stmt1 = con.createStatement();
							ResultSet rs = stmt.executeQuery(
									"Select courses.sem,courses.course_dept from courses,professors,students where courses.id_prof='"
											+decrypt(pa,"Blowfish")+ "'and courses.title='" + MATH + "' and students.id_s=" + idx1
											+ " and students.department=courses.course_dept and courses.id_prof=professors.afm");
							while (rs.next()) {

								c = rs.getString("course_dept");
								s = rs.getInt("sem");

							}
							String insertStmt1 = "INSERT INTO grades(id_s,title,course_dept,course_sem,grade)"
									+ " VALUES (";
							insertStmt1 += idx1 + ",";
							insertStmt1 += "'" + MATH + "',";
							insertStmt1 += "'" + c + "',";
							insertStmt1 += +s + ",";
							insertStmt1 += +idx2 + ")";

							stmt1.executeUpdate(insertStmt1);

							createDynPage(response, "Επιτυχής ανάθεση Βαθμού!");
							stmt1.close();

							con.close();

						} catch (PSQLException sqle) {
							createDynPage(response, "Invalid request type");

						}

					}

				} else if ("button3".equals(button)) {

					request.getSession().invalidate();
					response.sendRedirect(request.getContextPath());

					response.setHeader("Pragma", "No-cache");
					response.setHeader("Cache-Control", "no-cache");
					response.setDateHeader("Expires", 1);

				}
			} catch (Exception x) {

			}
		} catch (NullPointerException e) {
			response.sendRedirect("index.html");
		}
	}

	private String createHTMLRow(String AM, String MA8HMA, int vath) {
		String row = "<tr>";

		row += "<td>" + AM + "</td>";
		row += "<td>" + MA8HMA + "</td>";
		row += "<td>" + vath + "</td>";
		row += "</tr>";
		return row;

	}

	private void createDynPage(HttpServletResponse response, String message) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>OPA!</title></head>");
		out.println("<body bgcolor='gray'>");
		out.println("<p>" + message + "</p>");
		out.println("<a href=\"index3.html\">Πίσω</a>");
		out.println("</body></html>");
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