INSERT INTO client(name) VALUES
('John Smith'),
('Emma Brown'),
('Michael Johnson'),
('Olivia Davis'),
('William Wilson'),
('Sophia Taylor'),
('James Moore'),
('Isabella White'),
('Benjamin Harris'),
('Mia Martin');

INSERT INTO planet(id, name) VALUES
('EARTH', 'Earth'),
('MARS', 'Mars'),
('VENUS', 'Venus'),
('JUP', 'Jupiter'),
('SAT', 'Saturn');

INSERT INTO ticket(created_at, client_id, from_planet_id, to_planet_id)
VALUES
(CURRENT_TIMESTAMP, 1, 'EARTH', 'MARS'),
(CURRENT_TIMESTAMP, 2, 'MARS', 'VENUS'),
(CURRENT_TIMESTAMP, 3, 'VENUS', 'EARTH'),
(CURRENT_TIMESTAMP, 4, 'EARTH', 'SAT'),
(CURRENT_TIMESTAMP, 5, 'SAT', 'MARS'),
(CURRENT_TIMESTAMP, 6, 'MARS', 'JUP'),
(CURRENT_TIMESTAMP, 7, 'JUP', 'VENUS'),
(CURRENT_TIMESTAMP, 8, 'VENUS', 'SAT'),
(CURRENT_TIMESTAMP, 9, 'SAT', 'EARTH'),
(CURRENT_TIMESTAMP, 10, 'EARTH', 'JUP');
