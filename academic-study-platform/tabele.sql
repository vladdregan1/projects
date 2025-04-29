
USE platformaStudiu;

CREATE TABLE IF NOT EXISTS Rol (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nume VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS Utilizator (
    id_utilizator INT AUTO_INCREMENT PRIMARY KEY,
    id_rol INT NOT NULL,
    nume_utilizator VARCHAR(45) NOT NULL,
    parola VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_rol) REFERENCES Rol(id_rol)
);


CREATE TABLE IF NOT EXISTS Utilizator_Info (
    id_utilizator INT PRIMARY KEY,
    CNP VARCHAR(15) NOT NULL,
    nume VARCHAR(45) NOT NULL,
    prenume VARCHAR(45) NOT NULL,
    adresa VARCHAR(45),
    numar_telefon VARCHAR(45),
    email VARCHAR(45),
    iban VARCHAR(45),
    nr_contract VARCHAR(45),
    FOREIGN KEY (id_utilizator) REFERENCES Utilizator(id_utilizator)
);

CREATE TABLE IF NOT EXISTS Student (
    id_student INT AUTO_INCREMENT PRIMARY KEY,
    id_utilizator INT NOT NULL UNIQUE,
    an_studiu INT NOT NULL,
    numar_ore INT NOT NULL,
    FOREIGN KEY (id_utilizator) REFERENCES Utilizator(id_utilizator)
);

CREATE TABLE IF NOT EXISTS Departament (
    id_departament INT AUTO_INCREMENT PRIMARY KEY,
    nume VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS Profesor (
    id_profesor INT AUTO_INCREMENT PRIMARY KEY,
    id_departament INT NOT NULL,
    id_utilizator INT NOT NULL UNIQUE,
    nr_minim_ore INT,
    nr_maxim_ore INT,
    FOREIGN KEY (id_departament) REFERENCES Departament(id_departament),
    FOREIGN KEY (id_utilizator) REFERENCES Utilizator(id_utilizator)
);



CREATE TABLE IF NOT EXISTS Disciplina (
    id_disciplina INT AUTO_INCREMENT PRIMARY KEY,
    nume_disciplina VARCHAR(45) NOT NULL,
    descriere VARCHAR(255),
    nr_maxim_studenti INT
);

CREATE TABLE IF NOT EXISTS Profesor_Disciplina (
    id_disciplina INT NOT NULL,
    id_profesor INT NOT NULL,
    nr_studenti_asignati INT NOT NULL,
    PRIMARY KEY (id_disciplina, id_profesor),
    FOREIGN KEY (id_disciplina) REFERENCES Disciplina(id_disciplina),
    FOREIGN KEY (id_profesor) REFERENCES Profesor(id_profesor)
);

CREATE TABLE IF NOT EXISTS Student_Disciplina (
    id_disciplina INT NOT NULL,
    id_student INT NOT NULL,
    PRIMARY KEY (id_disciplina, id_student),
    FOREIGN KEY (id_disciplina) REFERENCES Disciplina(id_disciplina),
    FOREIGN KEY (id_student) REFERENCES Student(id_student)
);

CREATE TABLE IF NOT EXISTS Activitate (
    id_activitate INT AUTO_INCREMENT PRIMARY KEY,
	id_disciplina INT NOT NULL,
    id_profesor INT NOT NULL,
    tip_activitate ENUM('Seminar', 'Laborator', 'Curs'),
    data_inceput DATETIME NOT NULL,
    data_sfarsit DATETIME NOT NULL,
    frecventa VARCHAR(45),
    procent INT,
    FOREIGN KEY (id_disciplina) REFERENCES Disciplina(id_disciplina),
    FOREIGN KEY (id_profesor) REFERENCES Profesor(id_profesor)
);



CREATE TABLE IF NOT EXISTS Catalog (
    id_catalog INT AUTO_INCREMENT PRIMARY KEY,
    id_activitate INT NOT NULL,
    id_student INT NOT NULL,
    data DATETIME NOT NULL,
    nota FLOAT NOT NULL,
    
    FOREIGN KEY (id_activitate) REFERENCES Activitate(id_activitate),
    FOREIGN KEY (id_student) REFERENCES Student(id_student)
);

CREATE TABLE IF NOT EXISTS Calendar (
    id_calendar INT AUTO_INCREMENT PRIMARY KEY,
    id_activitate INT NOT NULL,
    id_profesor INT NOT NULL,
    data_inceput DATETIME NOT NULL,
    data_sfarsit DATETIME NOT NULL,
    nr_maxim_participanti INT,
    descriere VARCHAR(45),
    FOREIGN KEY (id_activitate) REFERENCES Activitate(id_activitate),
    FOREIGN KEY (id_profesor) REFERENCES Profesor(id_profesor)
);

CREATE TABLE IF NOT EXISTS Student_Calendar (
    id_calendar INT NOT NULL,
    id_student INT NOT NULL,
    data_inscriere DATETIME NOT NULL,
    PRIMARY KEY (id_calendar, id_student),
    FOREIGN KEY (id_calendar) REFERENCES Calendar(id_calendar),
    FOREIGN KEY (id_student) REFERENCES Student(id_student)
);

CREATE TABLE IF NOT EXISTS Grup_Studiu (
    id_grup INT AUTO_INCREMENT PRIMARY KEY,
    id_disciplina INT NOT NULL,
    nume_grup VARCHAR(45) NOT NULL,
    FOREIGN KEY (id_disciplina) REFERENCES Disciplina(id_disciplina)
);

CREATE TABLE IF NOT EXISTS Activitate_Grup (
    id_activitate_grup INT AUTO_INCREMENT PRIMARY KEY,
    id_grup INT NOT NULL,
    nume_activitate_grup VARCHAR(45),
    data_creare DATETIME NOT NULL,
    data_inceput DATETIME NOT NULL,
    data_sfarsit DATETIME NOT NULL,
    nr_min_participanti INT,
    data_validarii DATETIME,
    anulat TINYINT,
    FOREIGN KEY (id_grup) REFERENCES Grup_Studiu(id_grup)
);

CREATE TABLE IF NOT EXISTS Activitate_Grup_Student (
    id_activitate_grup INT NOT NULL,
    id_student INT NOT NULL,
    creator TINYINT NOT NULL,
    PRIMARY KEY (id_activitate_grup, id_student),
    FOREIGN KEY (id_activitate_grup) REFERENCES Activitate_Grup(id_activitate_grup),
    FOREIGN KEY (id_student) REFERENCES Student(id_student)
);

CREATE TABLE IF NOT EXISTS Mesaj (
    id_mesaj INT AUTO_INCREMENT PRIMARY KEY,
	id_grup INT NOT NULL,
    id_student INT NOT NULL,
    data_creare DATETIME NOT NULL,
    mesaj TEXT NOT NULL,
    FOREIGN KEY (id_grup) REFERENCES Grup_Studiu(id_grup),
    FOREIGN KEY (id_student) REFERENCES Student(id_student)
);