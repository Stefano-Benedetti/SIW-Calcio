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
-- ARBITRI
-- =========================
INSERT INTO arbitro (id, nome, cognome, codice_arbitrale) VALUES (nextval('arbitro_seq'), 'Orazio', 'Grinzosi', 6769420);
INSERT INTO arbitro (id, nome, cognome, codice_arbitrale) VALUES (nextval('arbitro_seq'), 'Paolo', 'Ferrari', 6769421);
INSERT INTO arbitro (id, nome, cognome, codice_arbitrale) VALUES (nextval('arbitro_seq'), 'Davide', 'Esposito', 6769422);

-- =========================
-- SQUADRE
-- =========================
INSERT INTO squadra (id, nome, fondazione, citta) VALUES (nextval('squadra_seq'), 'Lupi Roma', 1927, 'Roma');
INSERT INTO squadra (id, nome, fondazione, citta) VALUES (nextval('squadra_seq'), 'Aquila Milano', 1908, 'Milano');
INSERT INTO squadra (id, nome, fondazione, citta) VALUES (nextval('squadra_seq'), 'Stella Napoli', 1926, 'Napoli');
INSERT INTO squadra (id, nome, fondazione, citta) VALUES (nextval('squadra_seq'), 'Toro Torino', 1906, 'Torino');

-- =========================
-- GIOCATORI (collegati alla squadra)
-- ruolo: PORTIERE, DIFENSORE, CENTROCAMPISTA, ATTACCANTE
-- altezza in centimetri
-- =========================
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Marco', 'Leoni', '1997-03-12', 'PORTIERE', 189, 1);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Alessio', 'Riva', '1999-07-03', 'DIFENSORE', 184, 1);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Giorgio', 'Neri', '2000-01-21', 'CENTROCAMPISTA', 178, 1);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Tommaso', 'Greco', '1998-11-10', 'ATTACCANTE', 182, 1);

INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Matteo', 'Conti', '1996-05-18', 'PORTIERE', 191, 51);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Riccardo', 'Villa', '1997-09-09', 'DIFENSORE', 186, 51);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Emanuele', 'Lombardi', '2001-02-14', 'CENTROCAMPISTA', 176, 51);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Simone', 'Costa', '1998-12-01', 'ATTACCANTE', 180, 51);

INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Federico', 'Russo', '1995-04-04', 'PORTIERE', 188, 101);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Andrea', 'De Luca', '1999-06-30', 'DIFENSORE', 183, 101);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Lorenzo', 'Marino', '2000-10-22', 'CENTROCAMPISTA', 177, 101);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Nicolo', 'Romano', '1997-08-08', 'ATTACCANTE', 181, 101);

INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Daniele', 'Ferri', '1996-01-15', 'PORTIERE', 190, 151);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Pietro', 'Gallo', '1998-03-27', 'DIFENSORE', 185, 151);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Fabio', 'Serra', '2001-07-19', 'CENTROCAMPISTA', 179, 151);
INSERT INTO giocatore (id, nome, cognome, nascita, ruolo, altezza, squadra_id) VALUES (nextval('giocatore_seq'), 'Michele', 'Parisi', '1999-09-25', 'ATTACCANTE', 183, 151);

-- =========================
-- TORNEI
-- anno (Year) come numerico
-- =========================
INSERT INTO torneo (id, nome, anno, descrizione) VALUES (nextval('torneo_seq'), 'Coppa Primavera', 2026, 'Torneo nazionale giovanile');
INSERT INTO torneo (id, nome, anno, descrizione) VALUES (nextval('torneo_seq'), 'Campionato Estivo', 2026, 'Competizione estiva tra club');

-- =========================
-- SQUADRE ISCRITTE
-- =========================
INSERT INTO squadra_iscritta (id, vittorie, torneo_id, squadra_id) VALUES (nextval('squadra_iscritta_seq'), 1, 1, 1);
INSERT INTO squadra_iscritta (id, vittorie, torneo_id, squadra_id) VALUES (nextval('squadra_iscritta_seq'), 0, 1, 51);
INSERT INTO squadra_iscritta (id, vittorie, torneo_id, squadra_id) VALUES (nextval('squadra_iscritta_seq'), 0, 1, 101);
INSERT INTO squadra_iscritta (id, vittorie, torneo_id, squadra_id) VALUES (nextval('squadra_iscritta_seq'), 0, 1, 151);

INSERT INTO squadra_iscritta (id, vittorie, torneo_id, squadra_id) VALUES (nextval('squadra_iscritta_seq'), 0, 51, 1);
INSERT INTO squadra_iscritta (id, vittorie, torneo_id, squadra_id) VALUES (nextval('squadra_iscritta_seq'), 1, 51, 51);
INSERT INTO squadra_iscritta (id, vittorie, torneo_id, squadra_id) VALUES (nextval('squadra_iscritta_seq'), 0, 51, 101);

-- =========================
-- PARTITE (collegate a torneo, arbitro, squadra_home, squadra_away)
-- =========================
INSERT INTO partita (id, data, luogo, goals_home, goals_away, stato, arbitro_id, squadra_home_id, squadra_away_id, torneo_id) VALUES (nextval('partita_seq'), '2026-05-10T18:00:00', 'Stadio Olimpico', 2, 1, 'PLAYED', 1, 1, 51, 1);

INSERT INTO partita (id, data, luogo, goals_home, goals_away, stato, arbitro_id, squadra_home_id, squadra_away_id, torneo_id) VALUES (nextval('partita_seq'), '2026-06-29T16:45:00', 'Stadio Maradona', 1, 1, 'PLAYED', 51, 101, 151, 1);

INSERT INTO partita (id, data, luogo, goals_home, goals_away, stato, arbitro_id, squadra_home_id, squadra_away_id, torneo_id) VALUES (nextval('partita_seq'), '2026-07-22T21:00:00', 'San Siro', 0, 0, 'SCHEDULED', 101, 151, 1, 1);

INSERT INTO partita (id, data, luogo, goals_home, goals_away, stato, arbitro_id, squadra_home_id, squadra_away_id, torneo_id) VALUES (nextval('partita_seq'), '2026-07-23T18:00:00', 'Stadio del Sium', 0, 0, 'SCHEDULED', 1, 1, 151, 1);

INSERT INTO partita (id, data, luogo, goals_home, goals_away, stato, arbitro_id, squadra_home_id, squadra_away_id, torneo_id) VALUES (nextval('partita_seq'), '2026-07-24T13:30:00', 'Stadio di Altrove', 0, 0, 'SCHEDULED', 51, 101, 1, 51);

INSERT INTO partita (id, data, luogo, goals_home, goals_away, stato, arbitro_id, squadra_home_id, squadra_away_id, torneo_id) VALUES (nextval('partita_seq'), '2026-07-25T09:00:00', 'Casa di Pino', 0, 0, 'SCHEDULED', 101, 1, 101, 51);

INSERT INTO partita (id, data, luogo, goals_home, goals_away, stato, arbitro_id, squadra_home_id, squadra_away_id, torneo_id) VALUES (nextval('partita_seq'), '2026-07-26T10:00:00', 'Circolo Sportivo The Pallon', 0, 0, 'SCHEDULED', 1, 51, 1, 51);

INSERT INTO partita (id, data, luogo, goals_home, goals_away, stato, arbitro_id, squadra_home_id, squadra_away_id, torneo_id) VALUES (nextval('partita_seq'), '2026-06-27T11:30:00', 'Llanfairpwllgwyngyllgogerychwyrndrobwllllantysiliogogogoch', 14, 0, 'PLAYED', 1, 51, 1, 51);

-- =========================
-- COMMENTI (collegati a utente e partita via partita_id)
-- =========================
INSERT INTO commento (id, testo, utente_id, partita_id) VALUES (nextval('commento_seq'), 'Partita bellissima, ritmo altissimo!', 1, 1);
INSERT INTO commento (id, testo, utente_id, partita_id) VALUES (nextval('commento_seq'), 'Arbitraggio molto preciso.', 51, 1);
INSERT INTO commento (id, testo, utente_id, partita_id) VALUES (nextval('commento_seq'), 'Ci aspettiamo tanti gol stasera.', 101, 51);
INSERT INTO commento (id, testo, utente_id, partita_id) VALUES (nextval('commento_seq'), 'ahahah che polli', 101, 51);