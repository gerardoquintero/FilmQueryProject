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
//		app.test();
		app.launch();
	}

//	private void test() throws SQLException {
// TEST method below -- for findActorById(4) = 
//	  Actor actor = db.findActorById(4);
//    System.out.println(actor);
// TEST method below -- for findActorById(23) = ANACONDA CONFESSIONS
//    Film film = db.findFilmById(23);
//    System.out.println(film);
// TEST method below -- for findActorsByFilmId(1)
//	 List<Actor> actor = db.findActorsByFilmId(1);
//	  System.out.println(actor);
// TEST method below -- for findFilmByActorId(4)
//		List<Film> film = db.findFilmByActorId(4);
//		System.out.println(film);

//	}

	private boolean executeMenuSelection(int userMenuSelection) {
		switch (userMenuSelection) {
		case 1:
			findFilmById();
			break;
		case 2:
			findFilmBySearchKeyword();
			break;
//		case 3:
//			System.out.println("GoodBye");
//			return false;
//			break;
		default:
			System.out.println("Please enter a valid option!");
			break;
		}
		return false;
	}

	private void findFilmBySearchKeyword() {
		System.out.println("Please Enter A Key Word.");
		sc.next();
		sc.nextLine();
		
	}

	private void launch() {
    Scanner input = new Scanner(System.in);
    boolean run = true;
    
    do {
    	int userSelection = startUserInterface(input);
    	executeMenuSelection(userSelection);
    } while (run);
    
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
				System.out.println("|Film release year: " + film.getReleaseYear() + "\n|Film rating: " + film.getRating() + "\n|Description: " + film.getDescription() + "\n|Language: " + film.getLanguage());
			} else {
				System.out.println("Film with film ID " + id + " was not found.");
			}
			
		} catch (SQLException e) { 
			e.printStackTrace();
		}
	}
	

}