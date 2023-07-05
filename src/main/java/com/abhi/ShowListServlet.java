package com.abhi;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;

@WebServlet("/list")
public class ShowListServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abhijsp10", "root", "Abhi12@x");
			Statement st = cn.createStatement();
			ResultSet rst = st.executeQuery("select*from productinfo");
			pw.println("<html>");
			pw.println("<body>");
			pw.println(
					"<table style='margin:auto;font-size:20px;background-color:#68e375;border-collapse:collapse;width:55%;margin-top:20px;'border='1'>");
			pw.println(
					"<tr style='color:blue;'><th>Product id</th><th>Product name</th><th>Product brand</th><th>Product Price</th></tr>");
			while (rst.next()) {
				pw.println("<tr>");
				pw.println("<td>" + rst.getString(1) + "</td>");
				pw.println("<td>" + rst.getString(2) + "</td>");
				pw.println("<td>" + rst.getString(3) + "</td>");
				pw.println("<td>" + rst.getString(4) + "</td>");
				pw.println("</tr");
			}
			pw.println("</table>");
			pw.println("<div style='text-align:center;margin-top:30px'>");
			pw.println("<br><br><a href='/product-management/navbar.html'>Home</a>");
			pw.println("</div>");
			pw.println("</body>");
			pw.println("</html>");
		} catch (Exception ex) {

			System.out.println(ex);
		}
	}
}
