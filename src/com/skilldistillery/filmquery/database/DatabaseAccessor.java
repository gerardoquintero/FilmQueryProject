package com.skilldistillery.filmquery.database;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public interface DatabaseAccessor {
// finds films by film_id
  public Film findFilmById(int filmId) throws SQLException;
// finds actor by actor_id
  public Actor findActorById(int actorId) throws SQLException;
// finds actors by film_id 
  public List<Actor> findActorsByFilmId(int filmId);
// finds films by actors id
  public List<Film> findFilmByActorId(int filmId);
//  find language by language ID
  public String findFilmLanguage(int filmLanguageId);
  
  

  
}