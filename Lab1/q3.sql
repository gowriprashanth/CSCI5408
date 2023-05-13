SELECT genre FROM lab_1.directors_genres WHERE director_id = (SELECT id FROM lab_1.directors WHERE 
first_name = "Andrew" AND last_name = "Adamson");