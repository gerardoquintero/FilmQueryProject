package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	Scanner sc = new Scanner(System.in);
	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
		app.launch();
	}

	private boolean executeMenuSelection(int userMenuSelection) {
		switch (userMenuSelection) {
		case 1:
			findFilmById();
			break;
		case 2:
			searchByFilmKeyword();
			break;
		case 3:
			System.out.println("GoodBye");
			return true;
		default:
			System.out.println("Please enter a valid option!");
			break;
		}
		return false;
	}

	private void launch() {
    Scanner input = new Scanner(System.in);
    boolean exit = false;
    
    do {
    	int userSelection = startUserInterface(input);
    	exit = executeMenuSelection(userSelection);
    } while (!exit);
    
    input.close();
  }

	private int startUserInterface(Scanner input) {
//    menu goes here
		System.out.println(" ------------------Menu------------------");
		System.out.println("| 1. Look up a film by its id.           |");
		System.out.println("| 2. Look up a film by a search keyword. |");
		System.out.println("| 3. Exit the application                |");
		System.out.println("|________________________________________|");
		System.out.print("Select a option, Enter a number: ");
		int userMenuSelection = sc.nextInt();
		sc.nextLine();
		return userMenuSelection;
	}

	public void findFilmById() {
		System.out.print("Please enter a film Id: ");
		int id = sc.nextInt();
		sc.nextLine();
		try {
			Film film = db.findFilmById(id);
			if(film != null) {
				String filmLanguage = db.findFilmLanguage(film.getLanguageId());
				film.setLanguage(filmLanguage);
				System.out.println("|Film release year: " + film.getReleaseYear() + "\n|Film rating: " + film.getRating() + "\n|Description: " + film.getDescription() + "\n|Language: " + film.getLanguage() + "\n|Cast: " + film.getCast());
			} else {
				System.out.println("Film with film ID " + id + " was not found.");
			}
			
		} catch (SQLException e) { 
			e.printStackTrace();
		}
	}
	
	public void searchByFilmKeyword() {
		System.out.println("Please Enter A Keyword: ");
		String keyWord = sc.nextLine();
		List<Film> filmsByKeyword = db.searchByFilmKeyword(keyWord);
		if(filmsByKeyword != null && !filmsByKeyword.isEmpty()) {
			System.out.println("Films matching keyword: " + keyWord + "\n");
			for(Film film : filmsByKeyword) {
				System.out.println("------------");
				System.out.println("|Title:" + film.getTitle() + "\n|ReleaseYear: " + film.getReleaseYear() + "\n|Rating: " + film.getRating() + "\n|Cast: " + film.getCast());
				System.out.println("------------");
			}
			
		} else {
			System.out.println("No films found with the provided keyword.");
		}
		
		
	}
	

}