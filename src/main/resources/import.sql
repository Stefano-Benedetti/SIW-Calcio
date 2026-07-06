-- =========================
-- UTENTI
-- =========================
INSERT INTO utente (id, username) VALUES (nextval('utente_seq'), 'stefano');
INSERT INTO utente (id, username) VALUES (nextval('utente_seq'), 'simone');
INSERT INTO utente (id, username) VALUES (nextval('utente_seq'), 'admin');

-- =========================
-- CREDENZIALI
-- NB: ruolo è EnumType.STRING -> valori tipo 'USER', 'ADMIN'
-- =========================
INSERT INTO credenziali (id, username, password, ruolo, utente_id) VALUES (nextval('credenziali_seq'), 'stefano', '$2a$12$RmbcqHce93KvHW3DhX46behoglmW.fi2aev6cnJmC0NkEN2t7FzsW', 'USER', 1); -- pwd = 123456
INSERT INTO credenziali (id, username, password, ruolo, utente_id) VALUES (nextval('credenziali_seq'), 'simone', '$2a$12$RmbcqHce93KvHW3DhX46behoglmW.fi2aev6cnJmC0NkEN2t7FzsW', 'USER', 51); -- pwd = 123456
INSERT INTO credenziali (id, username, password, ruolo, utente_id) VALUES (nextval('credenziali_seq'), 'admin', '$2a$12$mrluWy3HIjoXLzGKtlMVCe6oeNLWhZh8eiTtjdREntvpZ96MUNtdy', 'ADMIN', 101);  -- pwd = admin



-- =========================
-- IMPORT DATI SIW CALCIO
-- NB: utenti e credenziali non vengono inseriti
-- NB: gli ID seguono la logica delle sequenze Hibernate del progetto
-- =========================


-- =========================
-- ARBITRI
-- =========================
INSERT INTO arbitro (id, nome, cognome, codice_arbitrale) VALUES (nextval('arbitro_seq'), 'Luca', 'Mariani', 7001001);
INSERT INTO arbitro (id, nome, cognome, codice_arbitrale) VALUES (nextval('arbitro_seq'), 'Gabriele', 'Serafini', 7001002);
INSERT INTO arbitro (id, nome, cognome, codice_arbitrale) VALUES (nextval('arbitro_seq'), 'Davide', 'Moretti', 7001003);
INSERT INTO arbitro (id, nome, cognome, codice_arbitrale) VALUES (nextval('arbitro_seq'), 'Alessandro', 'Fontana', 7001004);
INSERT INTO arbitro (id, nome, cognome, codice_arbitrale) VALUES (nextval('arbitro_seq'), 'Matteo', 'Rinaldi', 7001005);
INSERT INTO arbitro (id, nome, cognome, codice_arbitrale) VALUES (nextval('arbitro_seq'), 'Paolo', 'De Santis', 7001006);
INSERT INTO arbitro (id, nome, cognome, codice_arbitrale) VALUES (nextval('arbitro_seq'), 'Simone', 'Ricci', 7001007);
INSERT INTO arbitro (id, nome, cognome, codice_arbitrale) VALUES (nextval('arbitro_seq'), 'Federico', 'Barbieri', 7001008);


-- =========================
-- SQUADRE
-- =========================
INSERT INTO squadra (id, nome, fondazione, citta) VALUES (nextval('squadra_seq'), 'Lupi Roma', 1998, 'Roma');
INSERT INTO squadra (id, nome, fondazione, citta) VALUES (nextval('squadra_seq'), 'Falchi Milano', 2001, 'Milano');
INSERT INTO squadra (id, nome, fondazione, citta) VALUES (nextval('squadra_seq'), 'Vesuvio Napoli', 1995, 'Napoli');
INSERT INTO squadra (id, nome, fondazione, citta) VALUES (nextval('squadra_seq'), 'Granata Torino', 2003, 'Torino');
INSERT INTO squadra (id, nome, fondazione, citta) VALUES (nextval('squadra_seq'), 'Laguna Venezia', 2007, 'Venezia');
INSERT INTO squadra (id, nome, fondazione, citta) VALUES (nextval('squadra_seq'), 'Delfini Genova', 1999, 'Genova');
INSERT INTO squadra (id, nome, fondazione, citta) VALUES (nextval('squadra_seq'), 'Leoni Firenze', 2005, 'Firenze');
INSERT INTO squadra (id, nome, fondazione, citta) VALUES (nextval('squadra_seq'), 'Etna Catania', 2002, 'Catania');
INSERT INTO squadra (id, nome, fondazione, citta) VALUES (nextval('squadra_seq'), 'Torri Bologna', 1997, 'Bologna');
INSERT INTO squadra (id, nome, fondazione, citta) VALUES (nextval('squadra_seq'), 'Adriatica Bari', 2004, 'Bari');


-- =========================
-- GIOCATORI
-- ruolo: PORTIERE, DIFENSORE, CENTROCAMPISTA, ATTACCANTE
-- altezza in centimetri
--
-- ID squadre attesi:
-- 1   = Lupi Roma
-- 51  = Falchi Milano
-- 101 = Vesuvio Napoli
-- 151 = Granata Torino
-- 201 = Laguna Venezia
-- 251 = Delfini Genova
-- 301 = Leoni Firenze
-- 351 = Etna Catania
-- 401 = Torri Bologna
-- 451 = Adriatica Bari
-- =========================

-- Lupi Roma
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Marco', 'Bellini', '1998-04-12', 'PORTIERE', 188, 1);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Andrea', 'Leoni', '1999-09-21', 'DIFENSORE', 183, 1);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Lorenzo', 'Ferri', '2000-01-18', 'DIFENSORE', 181, 1);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Giacomo', 'Serra', '1997-11-03', 'CENTROCAMPISTA', 176, 1);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Riccardo', 'Costa', '2001-06-27', 'CENTROCAMPISTA', 178, 1);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Tommaso', 'Greco', '1998-12-15', 'ATTACCANTE', 182, 1);

-- Falchi Milano
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Matteo', 'Riva', '1996-02-08', 'PORTIERE', 190, 51);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Davide', 'Lombardi', '1999-05-22', 'DIFENSORE', 184, 51);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Edoardo', 'Villa', '2000-10-11', 'DIFENSORE', 186, 51);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Simone', 'Galli', '1997-07-19', 'CENTROCAMPISTA', 175, 51);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Alessio', 'Conti', '2001-03-30', 'CENTROCAMPISTA', 179, 51);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Nicolò', 'Ferrari', '1998-08-14', 'ATTACCANTE', 181, 51);

-- Vesuvio Napoli
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Federico', 'Esposito', '1995-01-25', 'PORTIERE', 187, 101);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Antonio', 'Russo', '1998-06-09', 'DIFENSORE', 183, 101);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Salvatore', 'Romano', '1999-02-17', 'DIFENSORE', 185, 101);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Michele', 'Marino', '2000-04-04', 'CENTROCAMPISTA', 177, 101);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Giuseppe', 'De Luca', '1997-09-29', 'CENTROCAMPISTA', 174, 101);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Vincenzo', 'Caruso', '1998-12-02', 'ATTACCANTE', 180, 101);

-- Granata Torino
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Daniele', 'Bruno', '1997-05-06', 'PORTIERE', 191, 151);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Pietro', 'Gallo', '1999-08-23', 'DIFENSORE', 184, 151);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Fabio', 'Martini', '1998-03-13', 'DIFENSORE', 182, 151);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Cristian', 'Bianchi', '2001-11-20', 'CENTROCAMPISTA', 178, 151);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Manuel', 'Doria', '1996-07-07', 'CENTROCAMPISTA', 176, 151);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Luca', 'Pellegrini', '1999-12-31', 'ATTACCANTE', 183, 151);

-- Laguna Venezia
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Gabriele', 'Moretti', '1998-01-11', 'PORTIERE', 189, 201);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Leonardo', 'Fabbri', '2000-09-16', 'DIFENSORE', 185, 201);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Filippo', 'Rossi', '1997-02-26', 'DIFENSORE', 181, 201);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Samuele', 'Orlandi', '2001-04-19', 'CENTROCAMPISTA', 175, 201);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Emanuele', 'Vitali', '1999-06-01', 'CENTROCAMPISTA', 179, 201);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Jacopo', 'Mancini', '1998-10-05', 'ATTACCANTE', 182, 201);

-- Delfini Genova
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Stefano', 'Parisi', '1997-03-02', 'PORTIERE', 188, 251);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Mirko', 'Benedetti', '1999-05-15', 'DIFENSORE', 186, 251);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Valerio', 'Sanna', '2000-07-28', 'DIFENSORE', 183, 251);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Nicola', 'Barone', '1998-11-22', 'CENTROCAMPISTA', 177, 251);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Mattia', 'Piras', '2001-01-30', 'CENTROCAMPISTA', 176, 251);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Alberto', 'Cattaneo', '1996-09-12', 'ATTACCANTE', 184, 251);

-- Leoni Firenze
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Francesco', 'Neri', '1996-04-20', 'PORTIERE', 190, 301);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Elia', 'Guidi', '1998-06-18', 'DIFENSORE', 182, 301);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Gianluca', 'Palmieri', '1999-12-07', 'DIFENSORE', 185, 301);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Dario', 'Santoro', '2000-08-03', 'CENTROCAMPISTA', 178, 301);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Massimo', 'Ruggeri', '1997-10-09', 'CENTROCAMPISTA', 174, 301);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Kevin', 'Fiorini', '1998-05-29', 'ATTACCANTE', 181, 301);

-- Etna Catania
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Rosario', 'Messina', '1997-02-13', 'PORTIERE', 189, 351);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Carmelo', 'Grasso', '1999-03-26', 'DIFENSORE', 184, 351);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Sebastiano', 'Vitale', '2000-06-14', 'DIFENSORE', 182, 351);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Domenico', 'Arena', '1998-01-08', 'CENTROCAMPISTA', 176, 351);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Alfio', 'Rizzo', '1996-11-11', 'CENTROCAMPISTA', 179, 351);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Giovanni', 'Spina', '1999-09-02', 'ATTACCANTE', 183, 351);

-- Torri Bologna
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Mauro', 'Bernardi', '1995-07-17', 'PORTIERE', 191, 401);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Enrico', 'Monti', '1998-04-24', 'DIFENSORE', 186, 401);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Ivan', 'Corsi', '2000-12-19', 'DIFENSORE', 181, 401);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Raffaele', 'Donati', '1997-06-05', 'CENTROCAMPISTA', 177, 401);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Milo', 'Venturi', '2001-02-21', 'CENTROCAMPISTA', 175, 401);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Oscar', 'Mazzoni', '1999-08-30', 'ATTACCANTE', 184, 401);

-- Adriatica Bari
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Rocco', 'Longo', '1996-10-10', 'PORTIERE', 188, 451);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Michele', 'Palmisano', '1999-01-14', 'DIFENSORE', 185, 451);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Vito', 'Lorusso', '1998-05-04', 'DIFENSORE', 183, 451);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Sergio', 'Carbone', '2000-03-03', 'CENTROCAMPISTA', 177, 451);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Dario', 'Fiore', '1997-12-27', 'CENTROCAMPISTA', 176, 451);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Luigi', 'De Rosa', '1998-07-01', 'ATTACCANTE', 182, 451);


-- =========================
-- TORNEI
-- =========================
INSERT INTO torneo (id, nome, anno, descrizione) VALUES (nextval('torneo_seq'), 'Coppa SIW', 2026, 'Torneo principale organizzato da SIW Calcio.');
INSERT INTO torneo (id, nome, anno, descrizione) VALUES (nextval('torneo_seq'), 'Trofeo delle Città', 2026, 'Competizione tra squadre provenienti da diverse città italiane.');
INSERT INTO torneo (id, nome, anno, descrizione) VALUES (nextval('torneo_seq'), 'Memorial Estate', 2026, 'Torneo estivo con girone unico e classifica finale.');
INSERT INTO torneo (id, nome, anno, descrizione) VALUES (nextval('torneo_seq'), 'Coppa Autunno', 2026, 'Competizione programmata per la seconda parte della stagione.');


-- =========================
-- SQUADRE ISCRITTE
-- vittorie coerenti con le partite già giocate
-- =========================

-- Coppa SIW: quarti già giocati, semifinali programmate
INSERT INTO squadra_iscritta (id, vittorie, torneo_id, squadra_id) VALUES (nextval('squadra_iscritta_seq'), 1, 1, 1);
INSERT INTO squadra_iscritta (id, vittorie, torneo_id, squadra_id) VALUES (nextval('squadra_iscritta_seq'), 0, 1, 51);
INSERT INTO squadra_iscritta (id, vittorie, torneo_id, squadra_id) VALUES (nextval('squadra_iscritta_seq'), 0, 1, 101);
INSERT INTO squadra_iscritta (id, vittorie, torneo_id, squadra_id) VALUES (nextval('squadra_iscritta_seq'), 1, 1, 151);
INSERT INTO squadra_iscritta (id, vittorie, torneo_id, squadra_id) VALUES (nextval('squadra_iscritta_seq'), 1, 1, 201);
INSERT INTO squadra_iscritta (id, vittorie, torneo_id, squadra_id) VALUES (nextval('squadra_iscritta_seq'), 0, 1, 251);
INSERT INTO squadra_iscritta (id, vittorie, torneo_id, squadra_id) VALUES (nextval('squadra_iscritta_seq'), 0, 1, 301);
INSERT INTO squadra_iscritta (id, vittorie, torneo_id, squadra_id) VALUES (nextval('squadra_iscritta_seq'), 1, 1, 351);

-- Trofeo delle Città: semifinali già giocate, finale programmata
INSERT INTO squadra_iscritta (id, vittorie, torneo_id, squadra_id) VALUES (nextval('squadra_iscritta_seq'), 1, 51, 1);
INSERT INTO squadra_iscritta (id, vittorie, torneo_id, squadra_id) VALUES (nextval('squadra_iscritta_seq'), 0, 51, 51);
INSERT INTO squadra_iscritta (id, vittorie, torneo_id, squadra_id) VALUES (nextval('squadra_iscritta_seq'), 1, 51, 101);
INSERT INTO squadra_iscritta (id, vittorie, torneo_id, squadra_id) VALUES (nextval('squadra_iscritta_seq'), 0, 51, 401);

-- Memorial Estate: turno iniziale già giocato, finale programmata
INSERT INTO squadra_iscritta (id, vittorie, torneo_id, squadra_id) VALUES (nextval('squadra_iscritta_seq'), 1, 101, 201);
INSERT INTO squadra_iscritta (id, vittorie, torneo_id, squadra_id) VALUES (nextval('squadra_iscritta_seq'), 0, 101, 251);
INSERT INTO squadra_iscritta (id, vittorie, torneo_id, squadra_id) VALUES (nextval('squadra_iscritta_seq'), 1, 101, 351);
INSERT INTO squadra_iscritta (id, vittorie, torneo_id, squadra_id) VALUES (nextval('squadra_iscritta_seq'), 0, 101, 451);

-- Coppa Autunno: torneo non ancora iniziato
INSERT INTO squadra_iscritta (id, vittorie, torneo_id, squadra_id) VALUES (nextval('squadra_iscritta_seq'), 0, 151, 1);
INSERT INTO squadra_iscritta (id, vittorie, torneo_id, squadra_id) VALUES (nextval('squadra_iscritta_seq'), 0, 151, 101);
INSERT INTO squadra_iscritta (id, vittorie, torneo_id, squadra_id) VALUES (nextval('squadra_iscritta_seq'), 0, 151, 201);
INSERT INTO squadra_iscritta (id, vittorie, torneo_id, squadra_id) VALUES (nextval('squadra_iscritta_seq'), 0, 151, 301);


-- =========================
-- PARTITE
-- Tornei organizzati a eliminazione diretta
-- Per le partite programmate: goals_home = 0, goals_away = 0
-- =========================

-- =========================
-- COPPA SIW
-- Quarti di finale già giocati
-- Vincitori: Lupi Roma, Granata Torino, Laguna Venezia, Etna Catania
-- Semifinali programmate
-- =========================
INSERT INTO partita (id, data, luogo, goals_home, goals_away, stato, arbitro_id, squadra_home_id, squadra_away_id, torneo_id) VALUES (nextval('partita_seq'), '2026-05-03T18:00:00', 'Centro Sportivo SIW Roma', 2, 1, 'PLAYED', 1, 1, 51, 1);
INSERT INTO partita (id, data, luogo, goals_home, goals_away, stato, arbitro_id, squadra_home_id, squadra_away_id, torneo_id) VALUES (nextval('partita_seq'), '2026-05-04T19:30:00', 'Stadio Comunale Torino', 3, 2, 'PLAYED', 51, 151, 101, 1);
INSERT INTO partita (id, data, luogo, goals_home, goals_away, stato, arbitro_id, squadra_home_id, squadra_away_id, torneo_id) VALUES (nextval('partita_seq'), '2026-05-10T17:00:00', 'Campo Laguna Venezia', 1, 0, 'PLAYED', 101, 201, 251, 1);
INSERT INTO partita (id, data, luogo, goals_home, goals_away, stato, arbitro_id, squadra_home_id, squadra_away_id, torneo_id) VALUES (nextval('partita_seq'), '2026-05-11T20:00:00', 'Campo Sud Catania', 4, 2, 'PLAYED', 151, 351, 301, 1);
INSERT INTO partita (id, data, luogo, goals_home, goals_away, stato, arbitro_id, squadra_home_id, squadra_away_id, torneo_id) VALUES (nextval('partita_seq'), '2026-07-08T18:00:00', 'Centro Sportivo SIW Roma', 0, 0, 'SCHEDULED', 201, 1, 151, 1);
INSERT INTO partita (id, data, luogo, goals_home, goals_away, stato, arbitro_id, squadra_home_id, squadra_away_id, torneo_id) VALUES (nextval('partita_seq'), '2026-07-09T20:30:00', 'Campo Laguna Venezia', 0, 0, 'SCHEDULED', 251, 201, 351, 1);

-- =========================
-- TROFEO DELLE CITTÀ
-- Semifinali già giocate
-- Vincitori: Lupi Roma, Vesuvio Napoli
-- Finale programmata
-- =========================
INSERT INTO partita (id, data, luogo, goals_home, goals_away, stato, arbitro_id, squadra_home_id, squadra_away_id, torneo_id) VALUES (nextval('partita_seq'), '2026-06-01T18:00:00', 'Centro Sportivo SIW Roma', 2, 0, 'PLAYED', 301, 1, 51, 51);
INSERT INTO partita (id, data, luogo, goals_home, goals_away, stato, arbitro_id, squadra_home_id, squadra_away_id, torneo_id) VALUES (nextval('partita_seq'), '2026-06-02T19:30:00', 'Stadio Comunale Bologna', 1, 3, 'PLAYED', 351, 401, 101, 51);
INSERT INTO partita (id, data, luogo, goals_home, goals_away, stato, arbitro_id, squadra_home_id, squadra_away_id, torneo_id) VALUES (nextval('partita_seq'), '2026-07-20T21:00:00', 'Stadio Centrale SIW', 0, 0, 'SCHEDULED', 1, 1, 101, 51);

-- =========================
-- MEMORIAL ESTATE
-- Semifinali già giocate
-- Vincitori: Laguna Venezia, Etna Catania
-- Finale programmata
-- =========================
INSERT INTO partita (id, data, luogo, goals_home, goals_away, stato, arbitro_id, squadra_home_id, squadra_away_id, torneo_id) VALUES (nextval('partita_seq'), '2026-06-15T18:00:00', 'Campo Laguna Venezia', 2, 1, 'PLAYED', 51, 201, 251, 101);
INSERT INTO partita (id, data, luogo, goals_home, goals_away, stato, arbitro_id, squadra_home_id, squadra_away_id, torneo_id) VALUES (nextval('partita_seq'), '2026-06-16T19:30:00', 'Campo Sud Catania', 3, 0, 'PLAYED', 101, 351, 451, 101);
INSERT INTO partita (id, data, luogo, goals_home, goals_away, stato, arbitro_id, squadra_home_id, squadra_away_id, torneo_id) VALUES (nextval('partita_seq'), '2026-07-28T20:30:00', 'Stadio Centrale SIW', 0, 0, 'SCHEDULED', 151, 201, 351, 101);

-- =========================
-- COPPA AUTUNNO
-- Semifinali programmate
-- Torneo non ancora iniziato
-- =========================
INSERT INTO partita (id, data, luogo, goals_home, goals_away, stato, arbitro_id, squadra_home_id, squadra_away_id, torneo_id) VALUES (nextval('partita_seq'), '2026-09-12T17:00:00', 'Centro Sportivo SIW Roma', 0, 0, 'SCHEDULED', 201, 1, 101, 151);
INSERT INTO partita (id, data, luogo, goals_home, goals_away, stato, arbitro_id, squadra_home_id, squadra_away_id, torneo_id) VALUES (nextval('partita_seq'), '2026-09-13T18:30:00', 'Campo Laguna Venezia', 0, 0, 'SCHEDULED', 251, 201, 301, 151);

-- =========================
-- COMMENTI
-- utenti disponibili:
-- 1, 51, 101
-- =========================

INSERT INTO commento (id, testo, utente_id, partita_id) VALUES (nextval('commento_seq'), 'Partita molto equilibrata, decisa dagli episodi.', 1, 1);
INSERT INTO commento (id, testo, utente_id, partita_id) VALUES (nextval('commento_seq'), 'Bella intensità fin dai primi minuti.', 51, 1);
INSERT INTO commento (id, testo, utente_id, partita_id) VALUES (nextval('commento_seq'), 'Risultato giusto per quello che si è visto in campo.', 101, 1);

INSERT INTO commento (id, testo, utente_id, partita_id) VALUES (nextval('commento_seq'), 'La squadra ospite ha giocato davvero bene.', 1, 51);
INSERT INTO commento (id, testo, utente_id, partita_id) VALUES (nextval('commento_seq'), 'Difesa molto ordinata, pochi spazi concessi.', 101, 51);

INSERT INTO commento (id, testo, utente_id, partita_id) VALUES (nextval('commento_seq'), 'Pareggio combattuto fino alla fine.', 51, 101);
INSERT INTO commento (id, testo, utente_id, partita_id) VALUES (nextval('commento_seq'), 'Entrambe le squadre potevano vincerla.', 101, 101);

INSERT INTO commento (id, testo, utente_id, partita_id) VALUES (nextval('commento_seq'), 'Quattro gol e tante occasioni, partita divertente.', 1, 151);
INSERT INTO commento (id, testo, utente_id, partita_id) VALUES (nextval('commento_seq'), 'Grande prova offensiva della squadra di casa.', 51, 151);
INSERT INTO commento (id, testo, utente_id, partita_id) VALUES (nextval('commento_seq'), 'Secondo tempo molto più acceso del primo.', 101, 151);

INSERT INTO commento (id, testo, utente_id, partita_id) VALUES (nextval('commento_seq'), 'Questa sarà una partita interessante da seguire.', 51, 301);
INSERT INTO commento (id, testo, utente_id, partita_id) VALUES (nextval('commento_seq'), 'Mi aspetto una gara molto tattica.', 101, 301);

INSERT INTO commento (id, testo, utente_id, partita_id) VALUES (nextval('commento_seq'), 'Sfida aperta, può succedere di tutto.', 1, 351);

INSERT INTO commento (id, testo, utente_id, partita_id) VALUES (nextval('commento_seq'), 'Bella vittoria, squadra molto concreta.', 1, 501);
INSERT INTO commento (id, testo, utente_id, partita_id) VALUES (nextval('commento_seq'), 'Prestazione solida dall’inizio alla fine.', 51, 501);