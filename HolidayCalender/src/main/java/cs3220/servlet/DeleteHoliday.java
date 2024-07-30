package cs3220.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.model.HolidayEntry;

/**
 * Servlet implementation class DeleteEntry
 */
@WebServlet("/DeleteHoliday")
public class DeleteHoliday extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteHoliday() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		List<HolidayEntry> entries = (List<HolidayEntry>) getServletContext().getAttribute("entries");
		int id = Integer.parseInt(request.getParameter("id"));
		HolidayEntry entry = null;
		for (HolidayEntry temporary : entries) {
			if (temporary.getId() == id) {
				entry = temporary;
				break;
			}
		}

		if (entry != null) {
			entries.remove(entry);
		}

		response.sendRedirect("HolidayCalender");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

	}

}