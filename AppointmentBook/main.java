package AppointmentBook;

import java.util.Date;

public class main
{
	public static void main(String[] args)
	{
		Date a1,a2,b1,b2,c1,c2,d1,d2,e1,e2,f1,f2,g1,g2;
		a1= new Date(1);
		a2= new Date(3);
		b1= new Date(4);
		b2= new Date(6);
		c1= new Date(8);
		c2= new Date(11);
		d1= new Date(7);
		d2= new Date(9);
		e1= new Date(9);
		e2= new Date(12);
		f1= new Date(7);
		f2= new Date(12);
		g1= new Date(9);
		g2= new Date(10);
		Appointment a,b,c,d,e,f,g;
		a= new Appointment("early", a1,a2);
		b= new Appointment("right after", b1,b2);
		c= new Appointment("wait a little", c1,c2);
		d= new Appointment("start-good, end-not so much", d1,d2);
		e= new Appointment("start-bad, finish-good", e1,e2);
		f= new Appointment("engulf", f1,f2);
		g= new Appointment("be engulfed", g1,g2);
		
		try
		{
			Date h1 = new Date(2);
			Date h2 = new Date(1);
			Appointment h = new Appointment("lol",h1,h2);
			System.out.println("fail");
		}
		catch(IllegalArgumentException E)
		{
			System.out.println("pwn");
		}
		
		AppointmentBook book = new AppointmentBook();
		
		System.out.println(book.newAppointment(a));
		System.out.println(book.newAppointment(b));
		System.out.println(book.newAppointment(c));
		System.out.println(book.newAppointment(d));
		System.out.println(book.newAppointment(e));
		System.out.println(book.newAppointment(f));
		System.out.println(book.newAppointment(g));;
	
	
	
		
		for(int z = 0;z<book.numAppointments(); z++)
			System.out.println(book.get(z).toString());
		
	}
}
