

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

import db.DBConnection;

/**
 * Servlet implementation class StudentsServlet
 */
@WebServlet("/StudentsServlet")
public class StudentsServlet extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	
	
              

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
		response.setContentType("text/html");
		HttpSession session=request.getSession(false);
		String u=(String)session.getAttribute("uname");
		String pa=(String)session.getAttribute("password");
		
		
		
		try {
			
			PrintWriter out = response.getWriter();
					
			String button = request.getParameter("button");

			if ("button1".equals(button)) {

				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				request.setCharacterEncoding("UTF-8");
				

				try {
					Connection con = DBConnection.getMysqlConnection();

					Statement stmt = con.createStatement();
					out.println("<html>");
					out.println("<body bgcolor='gray'>");
					out.println("<table bgcolor='white' bgcolor='white' border=\"1\">");
					out.println("<tr>");

					out.println("<th>Ma8ima</th>");
					out.println("<th>Examino</th>");
					out.println("<th>Vathmos</th>");

					out.println("</tr>");

					ResultSet rs = stmt.executeQuery(
							"Select grades.title,grades.course_sem,grades.grade from grades inner join students on grades.id_s=students.id_s where students.username='"+u+"'and students.id_s='"+decrypt(pa,"Blowfish")+"'");

					while (rs.next()) {

						String titl = rs.getString("title");
						String ex = rs.getString("course_sem");
						String vath = rs.getString("grade");

						String htmlRow = createHTMLRow(titl, ex, vath);

						out.println(htmlRow);

					}
					rs.close();

					con.close();
				} catch (Exception e) {
					out.println("Database connection problem");
				}
				out.println("<a href=\"index2.html\">Back</a>");
				out.println("</body>");
				out.println("</html>");

			}else if ("button2".equals(button)) {

				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				request.setCharacterEncoding("UTF-8");
				

				try {
					Connection con = DBConnection.getMysqlConnection();

					Statement stmt = con.createStatement();
					out.println("<html>");
					out.println("<body bgcolor='gray'>");
					out.println("<table bgcolor='white' border=\"1\">");
					out.println("<tr>");

					
					out.println("<th>Examino</th>");
					out.println("<th>Mo examinou</th>");

					out.println("</tr>");
					
					ResultSet rs = stmt.executeQuery(
							"select course_sem  , sum(grade)/count(grade) from (select course_sem,grade from grades where id_s='"+decrypt(pa,"Blowfish")+"') as mo group by course_sem order by course_sem");
						
					while (rs.next()) {

						
						int ex = rs.getInt(1);
						int vath = rs.getInt(2);
						String vath1 =String.valueOf(vath);
						String ex1 = String.valueOf(ex);
						String htmlRow2 = createHTMLRow2( ex1, vath1);

						out.println(htmlRow2);

					}
					rs.close();

					con.close();
				} catch (Exception e) {
					out.println("Database connection problem");
				}
				out.println("<a href=\"index2.html\">Back</a>");
				out.println("</body>");
				out.println("</html>");


			}else if ("button3".equals(button)) {

				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				request.setCharacterEncoding("UTF-8");
				

				try {
					Connection con = DBConnection.getMysqlConnection();

					Statement stmt = con.createStatement();
					out.println("<html>");
					out.println("<body bgcolor='gray'>");
					out.println("<table bgcolor='white' border=\"1\">");
					out.println("<tr>");

					
					out.println("<th>Mathimata pou exoun do8ei</th>");
					out.println("<th>Synolikos mesos oros </th>");

					out.println("</tr>");
					
					ResultSet rs = stmt.executeQuery(
							"select count(grade), sum(grade)/count(grade) from (select course_sem,grade from grades where id_s='"+decrypt(pa,"Blowfish")+"') as mo where grade is not null");
						
					while (rs.next()) {

						
						int ex = rs.getInt(1);
						int vath = rs.getInt(2);
						String vath1 =String.valueOf(vath);
						String ex1 = String.valueOf(ex);
						String htmlRow3 = createHTMLRow3( ex1, vath1);

						out.println(htmlRow3);

					}
					rs.close();

					con.close();
				} catch (Exception e) {
					out.println("Database connection problem");
				}
				out.println("<a href=\"index2.html\">Back</a>");
				out.println("</body>");
				out.println("</html>");


			}else if ("button4".equals(button)) {

				request.getSession().invalidate();
				response.sendRedirect(request.getContextPath());

				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 1);

			}

		} catch (Exception e) {
			
			createDynPage(response, "Something went wrong!");
		}
		} catch (NullPointerException e) {
			response.sendRedirect("index.html");
		}
	}

	private String createHTMLRow(String title, String examino, String vathmos) {
		
		
		String row = "<tr>";

		row += "<td>" + title + "</td>";
		row += "<td>" + examino+ "</td>";
		row += "<td>" + vathmos + "</td>";
		row += "</tr>";
		return row;
	}

	private String createHTMLRow2(String examino, String vathmos) {
		String row = "<tr>";

		
		row += "<td>" + examino + "</td>";
		row += "<td>" + vathmos + "</td>";
		
		row += "</tr>";
		return row;
	}
	private String createHTMLRow3(String examino, String vathmos) {
		String row = "<tr>";

		
		row += "<td>" + examino + "</td>";
		row += "<td>" + vathmos + "</td>";
		
		row += "</tr>";
		return row;
	}


	private void createDynPage(HttpServletResponse response, String message) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Oups!</title></head>");
		out.println("<body>");
		out.println("<p>" + message + "</p>");
		out.println("<a href=\"index2.html\">Back</a>");
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