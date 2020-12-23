use `bibleapp`;
-- Select TODOS LOS LIBROS 
SELECT idBook,name,testament FROM bible_books ;

-- Select TODOS LOS LIBROS por testamento -O -N
SELECT idBook,name,testament FROM bible_books where testament = 'O';

-- Buscar por nombre de libro
SELECT idBook,name,testament FROM bible_books where name LIKE '%Géne%';

-- SQLite Obtener Capitulos 
DELIMITER //
create Procedure GetAllChapter(Numchapter int)
Begin
SELECT chapter FROM bible_verses where idBook = Numchapter group by chapter order by chapter asc;
END //
DELIMITER ;

call GetAllChapter(1);

-- SQLite Obtener versiculos por capitulo de libro 
SELECT idBook, chapter, verse, text
FROM bible_verses where idBook = '1' and chapter = '5' group by verse order by verse asc;

-- Select Busqueda favorito
SELECT idBook, chapter, verse, text
FROM bible_verses where idBook = '1' and chapter = '5' and verse = '5' group by verse;

select * from bible_bibles
