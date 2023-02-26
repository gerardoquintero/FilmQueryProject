package com.skilldistillery.filmquery.entities;

import java.util.List;

public class Actor {
	private int id;
	private String firstName;
	private String lastName;
	private List<Film> films;  
//	methods
	public Actor() { }
	
	public Actor(int id, String fname, String lname) {
		this.id = id;
		this.firstName = fname;
		this.lastName = lname;
		
	}
	public Actor(int id, String fname, String lname, List<Film> films) {
		this.id = id;
		this.firstName = fname;
		this.lastName = lname;
		this.films = films;
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Actor [id=").append(id).append(", firstName=").append(firstName).append(", lastName=")
				.append(lastName).append("]");
		return builder.toString();
	}

	
	
	
}
	