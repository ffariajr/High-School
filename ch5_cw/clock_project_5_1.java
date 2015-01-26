package ch5_cw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.GregorianCalendar;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class clock_project_5_1 extends JComponent
{
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		final int tx = getWidth()/50;
		final int ty = getHeight()/50;
		
		Ellipse2D.Double circle = new Ellipse2D.Double(tx,ty,getWidth()-tx*2,getHeight()-ty*2);
		g2.draw(circle);
		
		final int cx = getWidth()/2+tx;
		final int cy = getHeight()/2+ty;
		
		Arc2D.Double big = new Arc2D.Double();
		big.setArc(tx, ty, getWidth()-tx*2,getHeight()-ty*2,0,0,2);
		
		Arc2D.Double small = new Arc2D.Double();
		small.setArc(tx*4, ty*4, getWidth()-tx*8,getHeight()-ty*8,0,0,2);
		
		for(int x = 0; x< 12; x++)
		{
			g2.draw(new Line2D.Double(small.getEndPoint(),big.getEndPoint()));
			big.setAngleExtent(big.getAngleExtent()+30);
			small.setAngleExtent(small.getAngleExtent()+30);
		}
		
		small.setArc(tx*1.5, ty*1.5, getWidth()-tx*3,getHeight()-ty*3,0,0,2);
		
		for(int x = 0; x< 60; x++)
		{
			g2.draw(new Line2D.Double(small.getEndPoint(),big.getEndPoint()));
			big.setAngleExtent(big.getAngleExtent()+6);
			small.setAngleExtent(small.getAngleExtent()+6);
		}
		
		
		GregorianCalendar clock = new GregorianCalendar();
			
		Arc2D.Double h = new Arc2D.Double();
		h.setArc(tx*10, ty*10, getWidth()-tx*20, getHeight()-ty*20, -clock.get(clock.HOUR_OF_DAY)*30+90 - (clock.get(clock.MINUTE)/2.0), 0, 2);
		Arc2D.Double m = new Arc2D.Double();
		m.setArc(tx*6, ty*6, getWidth()-tx*12, getHeight()-ty*12, -clock.get(clock.MINUTE)*6+90 - (clock.get(clock.SECOND)/10.0), 0, 2);
		Arc2D.Double s = new Arc2D.Double();
		s.setArc(tx*2, ty*2, getWidth()-tx*4, getHeight()-ty*4, -clock.get(clock.SECOND)*6+90, 0, 2);
		
		
		
		g2.setColor(Color.gray);
		g2.draw(s);
		g2.setColor(Color.red);
		g2.draw(m);
		g2.setColor(Color.blue);
		g2.draw(h);
	}
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new clock_project_5_1());
		frame.setVisible(true);
		while(frame.isVisible())
			frame.repaint();
	}
}
