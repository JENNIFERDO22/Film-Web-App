Project name: Film Web Application

Implement an API query and transform this data into report available via REST API. Create a
Java web application that provides /report service handling PUT, GET and DELETE
requests.

● PUT - generates report and saves it in database table, JSON:
{
“characterPhrase”: “CHARACTER_PHRASE”,
“planetName”: “PLANET_NAME”
}

● DELETE - deletes report data from database.

● GET returns report data as JSON:
{
 “criteria”:
{
“characterPhrase”: “CHARACTER_PHRASE”,
“planetName”: “PLANET_NAME”},
 “result”:
{
“film_id”: “FILM_ID”,
“film_name”: “FILM_NAME”,
“character_id”: “CHARACTER_ID”,
“character_name”: “CHARACTER_NAME”,
“planet_id”: “PLANET_ID”,
“planet_name”: “PLANET_NAME”
}}

The application takes input parameters and queries following services:
● https://swapi.co/api/films/
● https://swapi.co/api/people/
● https://swapi.co/api/planets/
to obtain list of films in which appeared characters who contains given
CHARACTER_PHRASE in their name and whose homeworld planet is PLANET_NAME.
The application queries API with user input and stores transformed result in database report
table. Report table columns film_id, film_name, character_id,
character_name, planet_id, planet_name.

Technical requirements
1. Java 8 or higher.
2. Maven or Gradle for building application.
3. You may use any java library eg.: guava.
4. Hibernate with in memory database.
5. Spring, eg.: DI

Verification criteria
1. Does it run.
2. Unit tests run in building cycle.
3. Error handling.
4. Validity and esthetic of querying the data.
5. Validity and esthetic of writing REST API.
6. Performance.
7. Application of software design patterns.
8. Application of Clean Code SOLID principles.