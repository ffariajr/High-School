package codeForKevin;

import java.awt.Color;


public class House {

	public static void main(String[] args) {
		House h1 = new House(Color.green, 2, 4, "New York", "New York");
		House h2 = new House(Color.black, 4, 10, "Detroit", "California");
		House h3 = new House(Color.pink, 1, 1, "San Fransisco", "Vermont");
		
		System.out.println(h1.getColor());
		System.out.println(h1.getStories());
		System.out.println(h1.getAge());
		System.out.println(h1.getTown());
		System.out.println(h1.getState());
		System.out.println();
		
		System.out.println(h2.getAge());
		h2.ageOneYear();
		System.out.println(h2.getAge());
		System.out.println();
		
		System.out.println(h3.getColor());
		h3.paintNewColor(Color.cyan);
		System.out.println(h3.getColor());
				
		
	}
	
	private Color color;
	private int stories;
	private int age;
	private String town;
	private String state;
	
	public House(Color initColor, int stories, int initAge, String town, String state) {
		paintNewColor(initColor);
		this.stories = stories;
		age = initAge;
		this.town = town;
		this.state = state;
	}
	
	public void ageOneYear() {
		age++;
	}
	
	public int getAge() {
		return age;
	}

	public Color getColor() {
		return color;
	}

	public void paintNewColor(Color color) {
		this.color = color;
	}

	public int getStories() {
		return stories;
	}
	
	public String getTown() {
		return town;
	}
	
	public String getState() {
		return state;
	}
	
}
