package AppointmentBook;

import java.util.Date;

public class Appointment
{
	private String name;
	private Date start, end;
	
	public Appointment(String nameOfAppointment, Date startDate, Date endDate)
	{
		if(startDate.after(endDate))
			throw new IllegalArgumentException("Appointment ends before it starts.");
		name = nameOfAppointment;
		start = startDate;
		end = endDate;
	}
	
	public String getName()
	{	
		return name;
	}
	
	public Date getStartDate()
	{
		return start;
	}
	
	public Date getEndDate()
	{
		return end;
	}
	
	public String toString()
	{
		return name + " From: " + start.getTime() + " To: " + end.getTime();
	}
}
