USE BAZASEMA;

CREATE TABLE UCENICI (
	UCENIK_ID 		INT NOT NULL AUTO_INCREMENT,
    UCENIK_IME 		VARCHAR(24) NOT NULL,
    UCENIK_PREZIME 	VARCHAR(30) NOT NULL,
    UCENIK_PROSEK 	INT DEFAULT 0,
    UCENIK_DATUMRODJENJA	DATE,
    PRIMARY KEY (UCENIK_ID)
);