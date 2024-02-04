package com.devsuperior.dsmovie.controllers;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static  org.hamcrest.Matchers.*;

public class MovieControllerRA {

	private String movieName;

	@BeforeEach
	void setup() throws JSONException{
		baseURI = "http://localhost:8080";

		movieName = "Matrix Resurrections";
	}

	@Test
	public void findAllShouldReturnOkWhenMovieNoArgumentsGiven() {

		given()
				.get("/movies?page=0")
				.then()
				.statusCode(200)
				.body("content.title", hasItems("The Witcher", "Titanic", "Django Livre"));
	}
	
	@Test
	public void findAllShouldReturnPagedMoviesWhenMovieTitleParamIsNotEmpty() {

		given()
				.get("/movies?title={movieName}", movieName)
				.then()
				.statusCode(200)
				.body("content.id[0]", is(4))
				.body("content.title[0]", equalTo("Matrix Resurrections"))
				.body("content.score[0]", is(0.0F))
				.body("content.count[0]", is(0))
				.body("content.image[0]", equalTo("https://www.themoviedb.org/t/p/w533_and_h300_bestv2/hv7o3VgfsairBoQFAawgaQ4cR1m.jpg"));
	}
	
	@Test
	public void findByIdShouldReturnMovieWhenIdExists() {		
	}
	
	@Test
	public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() {	
	}
	
	@Test
	public void insertShouldReturnUnprocessableEntityWhenAdminLoggedAndBlankTitle() throws JSONException {		
	}
	
	@Test
	public void insertShouldReturnForbiddenWhenClientLogged() throws Exception {
	}
	
	@Test
	public void insertShouldReturnUnauthorizedWhenInvalidToken() throws Exception {
	}
}
