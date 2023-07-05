package com.abhi;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;

@WebServlet("/delete-record")
public class DeleteServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		String pid = request.getParameter("pid");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abhijsp10", "root", "Abhi12@x");
			PreparedStatement ps = cn.prepareStatement("delete from productinfo where id=?");
			ps.setString(1, pid);
			int num = ps.executeUpdate();
			pw.println("<html>");
			pw.println("<body>");
			pw.println("<div style='text-align:center;margin-top:30px'>");
			if (num >= 1) {
				pw.println("<h2>Product with id " + pid + " has been deleted successfully</h2>");
			} else {
				pw.println("<h2 style='color:red;'>Product with id " + pid + " does not exist</h2>");
			}
			pw.println(
					"<a href='delete-record.html'align='center' style='text-decoration:none;background-color:#f1f5d0;border:2px solid blue;'>Delete more record </a>");
			pw.println(
					"<br><br><a href='/product-management'align='center' style='text-decoration:none;background-color:#f1f5d0;border:2px solid blue;'>Home</a>");
			pw.println("</div>");
			pw.println("</body>");
			pw.println("</html>");

		} catch (Exception ex) {

			System.out.println(ex);
		}
	}
}
