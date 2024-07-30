## Project Structure

The project is divided into several components:

**Model:**
- **HolidayEntry.java**: This class represents a single holiday entry with an ID, date, and name. The `idSeed` static variable ensures that each entry has a unique ID.

**Servlets:**
- **HolidayCalendar.java**: The main servlet that displays all holiday entries. It initializes with some default entries and provides a user interface to view, add, update, and delete holidays.
- **AddHoliday.java**: This servlet allows users to add a new holiday. It displays a form for the user to enter the date and name of the holiday.
- **UpdateHoliday.java**: This servlet allows users to update an existing holiday. It retrieves the holiday by ID and displays a form for updating the date and name.
- **DeleteHoliday.java**: This servlet handles the deletion of a holiday. It removes the entry from the list based on the ID provided in the request.

**How It Works:**
- **Adding a Holiday**: Users can navigate to the "Add Holiday" page, fill out the form, and submit it to add a new holiday to the calendar.
- **Editing a Holiday**: Each holiday in the calendar has an "Update" link. Clicking this link takes the user to a form where they can update the date and name of the holiday.
- **Deleting a Holiday**: Each holiday also has a "Delete" link. Clicking this link removes the holiday from the calendar.
- **Viewing Holidays**: The main holiday calendar page displays all holidays in a table format, with options to update or delete each entry.

**How to Run:**
- **Setup**: Ensure you have a Java Servlet container (e.g., Apache Tomcat) set up.
- **Deployment**: Download or import the project into your IDE and select "Run on Server."
- **Access**: Navigate to the application URL (e.g., `http://localhost:8080/HolidayCalendar`) to interact with the holiday calendar.
