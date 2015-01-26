package ch5_cw;



import java.awt.Color;

import java.awt.Graphics;

import java.awt.Graphics2D;

import java.awt.Rectangle;

import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;

import java.awt.geom.Ellipse2D;



import javax.swing.JComponent;

import javax.swing.JFrame;



public class P5_8 extends JComponent

{

	public void paintComponent(Graphics g)

	{

		Graphics2D g2 = (Graphics2D) g;

		

		Rectangle box = new Rectangle(50,200,400,300);
		g2.draw(box);
		
		box.setBounds(100,300,100,200);
		g2.draw(box);
		
		box.setFrame(250,350,50,50);
		g2.draw(box);
		
		box.setFrame(350,350,50,50);
		g2.draw(box);
		
		Line2D.Double line = new Line2D.Double(50,200,250,50);
		g2.draw(line);
		
		line.setLine(250,50,450,200);
		g2.draw(line);

	}

	

	public static void main(String[] args)

	{

		JFrame frame = new JFrame();

		frame.setSize(500, 550);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(new P5_8());

		frame.setVisible(true);

	}

}

