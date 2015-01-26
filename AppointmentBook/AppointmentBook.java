package AppointmentBook;

import java.util.ArrayList;

public class AppointmentBook
{
	private ArrayList<Appointment> book;
	
	public AppointmentBook()
	{
		book = new ArrayList<Appointment>(0);
	}
	
	public boolean newAppointment(Appointment app)
	{
		int z = 0;
		while(z<book.size() && !(app.getStartDate().after(book.get(z).getStartDate()) && app.getStartDate().before(book.get(z).getEndDate())) && !(app.getEndDate().after(book.get(z).getStartDate()) && app.getEndDate().before(book.get(z).getEndDate())) && !(app.getStartDate().before(book.get(z).getStartDate()) && app.getEndDate().after(book.get(z).getEndDate())) && !(app.getStartDate().after(book.get(z).getStartDate()) && app.getEndDate().before(book.get(z).getEndDate())))
			z++;
		if(z != book.size())
			return false;
		add(app);
		return true;
	}
	
	private void add(Appointment app)
	{
		boolean added = false;
		for(int z = 0; z < book.size();z++)
			if(app.getStartDate().after(book.get(z).getStartDate()))
			{
				book.add(z,app);
				added = true;
			}
		if(!added)
			book.add(app);
		book.trimToSize();
	}
	
	public int numAppointments()
	{
		return book.size();
	}
	
	public Appointment get(int index)
	{
		return book.get(index);
	}
}
