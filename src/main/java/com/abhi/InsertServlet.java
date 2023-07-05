package com.abhi;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;

@WebServlet("/save-record")
public class InsertServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		String v1 = request.getParameter("id");
		String v2 = request.getParameter("name");
		String v3 = request.getParameter("brand");
		String v4 = request.getParameter("price");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abhijsp10", "root", "Abhi12@x");
            PreparedStatement ps=cn.prepareStatement("insert into productinfo values(?,?,?,?)");
            ps.setString(1, v1);
            ps.setString(2, v2);
            ps.setString(3, v3);
            ps.setString(4, v4);
            ps.executeUpdate();
           /* pw.println("<html>");
            pw.println("<body>");
            pw.println("<div >");
            pw.println("<h2 style='color:red;'align='center'>product record has been inserted successfully</h2>");
            pw.println("</dvi>");
            pw.println("<table style='border:2px solid black;background-color:yellow;color:white;border-radius:7px;font-size:22px;' >");
            pw.println("<tr><td ><a href='insert-record.html' align='right'>Insert more record</a></td></tr>");
            pw.println("</table>");
            pw.println("</body>");
            pw.println("</html>");*/
            response.sendRedirect("save-success.html");
		} catch (Exception ex) {

			System.out.println(ex);
		}
	}
}
