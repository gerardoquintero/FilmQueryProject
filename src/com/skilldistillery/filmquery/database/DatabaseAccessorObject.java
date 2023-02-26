package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private static final String user = "student";
	private static final String pass = "student";

	public DatabaseAccessorObject() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override

	public Actor findActorById(int actorId) {
		Actor actor = null;
		// ...
		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";

		try {

			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, actorId);
			ResultSet actorResult = stmt.executeQuery();

			if (actorResult.next()) {
				actor = new Actor(); // Create the object
				// Here is our mapping of query columns to our object fields:
				actor.setId(actorResult.getInt(1));
				actor.setFirstName(actorResult.getString(2));
				actor.setLastName(actorResult.getString(3));
				actor.setFilms(findFilmByActorId(actorId)); // An Actor has Films
			}
			// ...
			return actor;
		} catch (SQLException e) {
		}
		return actor;

	}

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;
//		if( film == null) {
//			System.out.println("No ID exist");
//		}

		String sql = "SELECT id, title, description, release_year,"
				+ "language_id, rental_duration, rental_rate, length, replacement_cost, rating,"
				+ "special_features FROM film WHERE id = ?";

		Connection conn = DriverManager.getConnection(URL, user, pass);
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, filmId);
		ResultSet filmResult = stmt.executeQuery();

		if (filmResult.next()) {
			film = new Film();

			film.setId(filmResult.getInt("id"));
			film.setTitle(filmResult.getString("title"));
			film.setDescription(filmResult.getString("description"));
			film.setReleaseYear(filmResult.getInt("release_year"));
			film.setLanguageId(filmResult.getInt("language_id"));
			film.setRentalDuration(filmResult.getInt("rental_duration"));
			film.setRate(filmResult.getDouble("rental_rate"));
			film.setLength(filmResult.getInt("length"));
			film.setReplacementCost(filmResult.getDouble("replacement_cost"));
			film.setRating(filmResult.getString("rating"));
			film.setSpecialFeatures(filmResult.getString("special_features"));

		}
		return film;
	}

	public String findFilmLanguage(int filmLanguageId) {
		String sql = "SELECT name FROM language WHERE id = ?";

		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, user, pass);

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, filmLanguageId);
			ResultSet filmLanguageResult = stmt.executeQuery();
			if (filmLanguageResult.next()) {
				return filmLanguageResult.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Film> findFilmByActorId(int actorId) {
		List<Film> films = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT id, title, description, release_year, language_id, rental_duration, ";
			sql += " rental_rate, length, replacement_cost, rating, special_features "
					+ " FROM film JOIN film_actor ON film.id = film_actor.film_id " + " WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				Integer releaseYear = rs.getInt("release_year");
				int langId = rs.getInt("language_id");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				Integer length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");
				Film film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features);
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;

	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {

		List<Actor> actor = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT id, title, description release_year, language_id, rental_duration,";
			sql += "rental_rate, length, replacement_cost, rating, special_features,"
					+ "FROM film JOIN film_actor ON film.id = film_actor.film.id" + "WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
//				int id = rs.getInt("id");
//				String title = rs.getString("title");
//				String desc = rs.getString("description");
//				Integer releaseYear = rs.getInt("release_year");
//				int langId = rs.getInt("language_id");
//				int rentDur = rs.getInt("rental_duration");
//				double rate = rs.getDouble("rental_rate");
//				Integer length = rs.getInt("length");
//				double repCost = rs.getDouble("replacement_cost");
//				String rating = rs.getString("rating");
//				String features = rs.getString("special_features");
//				Film film = new Film(id, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
//						features);
//								films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;

	}

}