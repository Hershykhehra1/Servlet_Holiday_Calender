package cs3220.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.model.HolidayEntry;

/**
 * Servlet implementation class EditEntry
 */
@WebServlet("/UpdateHoliday")
public class UpdateHoliday extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateHoliday() {
		super();
	}

	private HolidayEntry getEntryByID(int id) {
		List<HolidayEntry> entries = (List<HolidayEntry>) getServletContext().getAttribute("entries");
		for (HolidayEntry entry : entries) {
			if (entry.getId() == id) {
				return entry;
			}
		}
		return null;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		HolidayEntry entry = getEntryByID(id);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head>");
		out.println("<title>Update Holiday</title>");
		out.println("<style>");
		out.println("body {");
		out.println("  background-image: linear-gradient(120deg, #2980b9, #8e44ad);");
		out.println("  min-height: 100vh;");
		out.println("  text-align: center;");
		out.println("  margin: auto;");
		out.println("  padding: auto;");
		out.println("}");
		out.println("table {");
		out.println("  margin: auto;");
		out.println("  border-collapse: collapse;");
		out.println("  border: 1px solid black;");
		out.println("  border: none;");
		out.println("}");
		out.println("th, td {");
		out.println("  border: 1px solid black;");
		out.println("  padding: 5px;");
		out.println("}");
		out.println("tr:nth-child(even) {");
		out.println("  background-color: #f2f2f2; /* White */");
		out.println("}");
		out.println("tr:hover {");
		out.println("  background-color: lightblue;");
		out.println("}");
		out.println("</style>");
		out.println("</head><body>");
		out.println("<h2>Update Holiday</h2>");
		out.println("<form id='updateHolidayForm' action='UpdateHoliday' method='post'>");
		out.println("  <label for='day'>Holiday Date:</label>");
		out.println("  <select id='day' name='day'>");
		for (int i = 1; i <= 31; i++) {
			String day = (i < 10) ? "0" + i : String.valueOf(i);
			String selected = (entry.getDate() != null && entry.getDate().length() >= 10
					&& entry.getDate().substring(8, 10).equals(day)) ? "selected" : "";
			out.println("    <option value='" + day + "'" + selected + ">" + i + "</option>");
		}
		out.println("  </select>");
		out.println("  <select id='month' name='month'>");
		String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		for (int i = 1; i <= 12; i++) {
			String month = months[i - 1];
			String selected = (entry.getDate() != null && entry.getDate().length() >= 7
					&& entry.getDate().substring(5, 7).equals(getMonthNumber(month))) ? "selected" : "";
			out.println("    <option value='" + month + "'" + selected + ">" + month + "</option>");
		}
		out.println("  </select>");
		out.println("  <select id='year' name='year'>");
		for (int i = 2024; i <= 2030; i++) {
			String year = String.valueOf(i);
			String selected = (entry.getDate() != null && entry.getDate().length() >= 4
					&& entry.getDate().substring(0, 4).equals(year)) ? "selected" : "";
			out.println("    <option value='" + year + "'" + selected + ">" + i + "</option>");
		}
		out.println("  </select> <br />");
		// Replace single quote with HTML entity &#39;
		String escapedName = entry.getName() != null ? entry.getName().replace("'", "&#39;") : "";
		out.println("  <label for='name'>Holiday Name: </label>");
		out.println("  <input type='text' id='name' name='name' value='" + escapedName + "' required><br/>");
		out.println("<input type='hidden' name='id' value='" + id + "' />");
		out.println("<br />");
		out.println("<button>Update Holiday</button>");
		out.println("</form>");
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int id = Integer.parseInt(request.getParameter("id"));
	    HolidayEntry entry = getEntryByID(id);

	    String year = request.getParameter("year");
	    String month = request.getParameter("month");
	    String day = request.getParameter("day");

	    if (year != null && month != null && day != null) {
	        String monthNumber = getMonthNumber(month);
	        String newDate = year + "-" + monthNumber + "-" + day;

	        // Check if the new date conflicts with an existing one (excluding the current entry)
	        List<HolidayEntry> entries = (List<HolidayEntry>) getServletContext().getAttribute("entries");
	        for (HolidayEntry existingEntry : entries) {
	            if (existingEntry.getId() != id && existingEntry.getDate().equals(newDate)) {
	                // Redirect to HolidayCalendar if there's a conflict
	                response.sendRedirect("HolidayCalender");
	                return;
	            }
	        }

	        // If no conflict, update the date
	        entry.setDate(newDate);
	    }

	    String name = request.getParameter("name");
	    entry.setName(name);

	    // Redirect to HolidayCalendar after successful update
	    response.sendRedirect("HolidayCalender");
	}




	// month name to month number
	private String getMonthNumber(String monthName) {
		switch (monthName) {
		case "January":
			return "01";
		case "February":
			return "02";
		case "March":
			return "03";
		case "April":
			return "04";
		case "May":
			return "05";
		case "June":
			return "06";
		case "July":
			return "07";
		case "August":
			return "08";
		case "September":
			return "09";
		case "October":
			return "10";
		case "November":
			return "11";
		case "December":
			return "12";
		default:
			return null;
		}
	}

}