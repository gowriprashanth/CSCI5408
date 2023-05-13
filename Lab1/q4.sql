SELECT CONCAT(first_name," ",last_name) as directors from lab_1.directors WHERE id IN
(SELECT director_id FROM lab_1.movies_directors WHERE movie_id IN
(SELECT id FROM lab_1.movies WHERE `rank` between 7 and 8));
