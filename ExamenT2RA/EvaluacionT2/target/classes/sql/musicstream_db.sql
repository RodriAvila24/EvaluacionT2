CREATE TABLE IF NOT EXISTS artista (
    id_artista INT PRIMARY KEY AUTO_INCREMENT,
    nombre_artista VARCHAR(100) NOT NULL,
    pais_origen VARCHAR(50) NOT NULL,
    genero_musical VARCHAR(50) NOT NULL,
    fecha_debut DATE
);

CREATE TABLE IF NOT EXISTS cancion (
    id_cancion INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(150) NOT NULL,
    duracion_segundos INT NOT NULL,
    fecha_lanzamiento DATE,
    numero_reproducciones INT NOT NULL DEFAULT 0,
    id_artista INT NOT NULL,
    CONSTRAINT fk_artista FOREIGN KEY (id_artista) REFERENCES artista(id_artista),
    CONSTRAINT unq_titulo_artista UNIQUE (titulo, id_artista)
);