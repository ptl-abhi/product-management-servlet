package com.abhi;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;

@WebServlet("/search-record")
public class SearchServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		String pid = request.getParameter("pid");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abhijsp10", "root", "Abhi12@x");
			PreparedStatement ps = cn.prepareStatement("select * from productinfo where id=?");
			ps.setString(1, pid);
			ResultSet rst = ps.executeQuery();
			pw.println("<html>");
			pw.println("<body>");
			pw.println("<div style='text-align:center;margin-top:30px'>");
			if (rst.next()) {
				pw.println(
						"<table style='margin:auto;font-size:20px;background-color:aqua;border-collapse:collapse;width:45%;margin-top:20px' border='1'>");
				pw.println("<tr><th align='left'>Product id</th>");
				pw.println("<td>" + rst.getString(1) + "</td></tr>");
				pw.println("<tr><th align='left'>Product name</th>");
				pw.println("<td>" + rst.getString(2) + "</td></tr>");
				pw.println("<tr><th align='left'>Product brand</th>");
				pw.println("<td>" + rst.getString(3) + "</td></tr>");
				pw.println("<tr><th align='left'>Product price</th>");
				pw.println("<td>" + rst.getString(4) + "</td></tr>");
				pw.println("</table><br><br>");
			} else {
				pw.println("<h2 style='color:red'>Product with id " + pid + " not found</h2>");

			}
			pw.println(
					"<a href='search-record.html'align='center' style='text-decoration:none;background-color:#f1f5d0;border:2px solid blue;'>Search more record </a>");
			pw.println("<br><br><a href='/product-managemenet'>Home</a>");
			pw.println("</div>");
			pw.println("</body>");
			pw.println("</html>");
		} catch (Exception ex) {

			System.out.println(ex);
		}
	}
}
