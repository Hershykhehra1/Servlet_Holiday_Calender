package cs3220.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.model.HolidayEntry;

@WebServlet("/AddHoliday")
public class AddHoliday extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head>");
		out.println("<title>Add Holiday</title>");
		out.println("<style>");
		out.println("body {");
		out.println("  background-image: linear-gradient(120deg, #2980b9, #8e44ad);");
		out.println("  min-height: 100vh;");
		out.println("  text-align: center;");
		out.println("  margin: auto;");
		out.println("  padding: auto;");
		out.println("}");
		out.println("</style>");
		out.println("</head><body>");
		out.println("<h2>Add Holiday</h2>");
		out.println("<form id='addHolidayForm' action='AddHoliday' method='post'>");
		out.println("  <label for='year'>Holiday Date:</label>");

		out.println("  <select id='day' name='day'>");
		for (int i = 1; i <= 31; i++) {
			String day = (i < 10) ? "0" + i : String.valueOf(i);
			out.println("    <option value='" + day + "'>" + i + "</option>");
		}
		out.println("  </select>");

		out.println("  <select id='month' name='month'>");
		out.println("    <option value='01'>January</option>");
		out.println("    <option value='02'>February</option>");
		out.println("    <option value='03'>March</option>");
		out.println("    <option value='04'>April</option>");
		out.println("    <option value='05'>May</option>");
		out.println("    <option value='06'>June</option>");
		out.println("    <option value='07'>July</option>");
		out.println("    <option value='08'>August</option>");
		out.println("    <option value='09'>September</option>");
		out.println("    <option value='10'>October</option>");
		out.println("    <option value='11'>November</option>");
		out.println("    <option value='12'>December</option>");
		out.println("  </select>");
		out.println("  <select id='year' name='year'>");
		for (int i = 2024; i <= 2030; i++) {
			out.println("    <option value='" + i + "'>" + i + "</option>");
		}
		out.println("  </select><br />");
		out.println("  <label for='name'>Holiday Name: </label>");
		out.println("  <input type='text' id='name' name='name' required><br/>");
		out.println("  <br />");
		out.println("  <button type='submit'>Add Holiday</button>");
		out.println("</form>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String name = request.getParameter("name");

		String date = year + "-" + month + "-" + day;

		List<HolidayEntry> entries = (List<HolidayEntry>) getServletContext().getAttribute("entries");

		if (entries == null) {
			entries = new ArrayList<>();
			getServletContext().setAttribute("entries", entries);
		}

		// Check if the date already exists in the list of entries
		for (HolidayEntry entry : entries) {
			if (entry.getDate().equals(date)) {
				// If the date already exists, redirect to the HolidayCalender servlet
				response.sendRedirect("HolidayCalender");
				return;
			}
		}

		HolidayEntry newEntry = new HolidayEntry(date, name);
		entries.add(newEntry);

		response.sendRedirect("HolidayCalender");
	}

}
