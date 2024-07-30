package cs3220.servlet;

import cs3220.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/HolidayCalender", loadOnStartup = 1)
public class HolidayCalender extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<HolidayEntry> entries;

	public HolidayCalender() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		List<HolidayEntry> entries = new ArrayList<HolidayEntry>();
		entries.add(new HolidayEntry("2024-01-01", "New Year's Day"));
		entries.add(new HolidayEntry("2024-01-15", "Martin Luther King Jr. Day"));
		entries.add(new HolidayEntry("2024-02-12", "Lincoln's Birthday"));
		entries.add(new HolidayEntry("2024-02-19", "President's Day"));
		entries.add(new HolidayEntry("2024-07-04", "Independence Day"));
		entries.add(new HolidayEntry("2024-09-02", "Labor Day"));
		entries.add(new HolidayEntry("2024-11-28", "Thanksgiving Day"));
		entries.add(new HolidayEntry("2024-12-25", "Christmas Day"));

		getServletContext().setAttribute("entries", entries);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<HolidayEntry> entries = (List<HolidayEntry>) getServletContext().getAttribute("entries");

		if (entries != null) {
			entries.sort(Comparator.comparing(HolidayEntry::getDate));
		}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head>");
		out.println("<title>US Holidays</title>");
		out.println("<style>");
		out.println("body {\n" + "  background-image: linear-gradient(120deg, #2980b9, #8e44ad);\n"
				+ "  min-height: 100vh;\n" 
				+ "  text-align: center;\n" 
				+ "  margin: auto;\n" 
				+ "  padding: auto;\n"
				+ "}");
		out.println("table {" + "" + "margin: auto;" + "border-collapse: collapse" + "}");
		out.println("th, td {" + " border-bottom: 1px solid black; " + "padding: 5px" + "}");
		out.println("tr:nth-child(even) {\n" + "        background-color: #f2f2f2; /* White */\n" + "}");
		out.println("tr:hover{\n" + "  background-color: lightblue;\n" + "}");
		out.println("</style>");
		out.println("</head><body>");
		out.println("<h2>US Holidays</h2>");
		out.println("<table>");
		out.println("<tr><th>Date</th><th>Holiday</th><th>Update | Delete</th>");
		if (entries != null) {
			for (HolidayEntry entry : entries) {
				out.println("<tr>");
				out.println("<td>" + formatDate(entry.getDate()) + "</td>");
				out.println("<td>" + entry.getName() + "</td>");
				out.println("<td><a href='UpdateHoliday?id=" + entry.getId() + "'>Update</a> | "
						+ "<a href='DeleteHoliday?id=" + entry.getId() + "'>Delete</a></td>");
				out.println("</tr>");
			}
		} else {
			out.println("<tr><td colspan='3'>No holidays found</td></tr>");
		}
		out.println("</table>");
		out.println("<br />");
		out.println("<button><a href='AddHoliday'>Add Holiday</a></button>");
		out.println("</body></html>");
	}

	// takes number and converts it into the name
	private String formatDate(String date) {
		String yearNum = date.substring(0, 4);
		String monthNum = date.substring(5, 7);
		String dayNum = date.substring(8, 10);

		int year = Integer.parseInt(yearNum);
		int month = Integer.parseInt(monthNum);
		int day = Integer.parseInt(dayNum);

		String monthName = new DateFormatSymbols().getMonths()[month - 1];

		return String.format("%02d %s %d", day, monthName, year);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String name = request.getParameter("name");

		String date = year + "-" + month + "-" + day;

		HolidayEntry entry = new HolidayEntry(date, name);

		List<HolidayEntry> entries = (List<HolidayEntry>) getServletContext().getAttribute("entries");

		if (entries == null) {
			entries = new ArrayList<>();
		}

		entries.add(entry);

		getServletContext().setAttribute("entries", entries);

		response.sendRedirect("HolidayCalender");
	}

}