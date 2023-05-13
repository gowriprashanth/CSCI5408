SELECT role FROM lab_1.roles WHERE actor_id IN (SELECT id FROM lab_1.actors WHERE first_name="Julliet" AND last_name="Akinyi") 
AND movie_id IN (SELECT id FROM lab_1.movies WHERE name="Lost in Translation");
