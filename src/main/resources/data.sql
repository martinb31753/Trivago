INSERT INTO hotels (hotel_code, name, destination, room_type, price_per_night, date_from, date_to, is_reserved, is_active) VALUES
('CH-0002', 'Cataratas Hotel', 'Puerto Iguazú', 'Double', 6300, '2025-02-10', '2025-03-20', false, true),
('CH-0003', 'Cataratas Hotel 2', 'Puerto Iguazú', 'Triple', 8200, '2025-02-10', '2025-03-23', false, true),
('HB-0001', 'Hotel Bristol', 'Buenos Aires', 'Single', 5435, '2025-02-10', '2025-03-19', false, true),
('BH-0002', 'Hotel Bristol 2', 'Buenos Aires', 'Double', 7200, '2025-02-12', '2025-04-17', false, true),
('SH-0002', 'Sheraton', 'Tucumán', 'Double', 5790, '2025-04-17', '2025-05-23', false, true),
('SH-0001', 'Sheraton 2', 'Tucumán', 'Single', 4150, '2025-01-02', '2025-02-19', false, true),
('SE-0001', 'Selina', 'Bogotá', 'Single', 3900, '2025-01-23', '2025-11-23', false, true),
('SE-0002', 'Selina 2', 'Bogotá', 'Double', 5840, '2025-01-23', '2025-10-15', false, true),
('EC-0003', 'El Campín', 'Bogotá', 'Triple', 7020, '2025-02-15', '2025-03-27', false, true),
('CP-0004', 'Central Plaza', 'Medellín', 'Multiple', 8600, '2025-03-01', '2025-04-17', false, true),
('CP-0002', 'Central Plaza 2', 'Medellín', 'Double', 6400, '2025-02-10', '2025-03-20', false, true),
('BG-0004', 'Bocagrande', 'Cartagena', 'Multiple', 9370, '2025-04-17', '2025-06-12', false, true);

INSERT INTO flights (flight_number, origin, destination, seat_type, price_per_person, date_from, date_to, is_active) VALUES
('BAPI-1235', 'Buenos Aires', 'Puerto Iguazú', 'Economy', 6500, '2025-02-10', '2025-02-15', true),
('PIBA-1420', 'Puerto Iguazú', 'Bogotá', 'Business', 43200, '2025-02-10', '2025-02-20', true),
('PIBA-1420', 'Puerto Iguazú', 'Bogotá', 'Economy', 25735.89, '2025-02-10', '2025-02-20', true),
('BATU-5536', 'Buenos Aires', 'Tucumán', 'Economy', 7320, '2025-02-10', '2025-02-17', true),
('TUPI-3369', 'Tucumán', 'Puerto Iguazú', 'Business', 12530, '2025-02-12', '2025-02-23', true),
('TUPI-3369', 'Tucumán', 'Puerto Iguazú', 'Economy', 5400, '2025-02-12', '2025-02-23', true),
('BOCA-4213', 'Bogotá', 'Cartagena', 'Economy', 8000, '2025-01-23', '2025-02-05', true),
('CAME-0321', 'Cartagena', 'Medellín', 'Economy', 7800, '2025-01-23', '2025-01-31', true),
('BOBA-6567', 'Bogotá', 'Buenos Aires', 'Business', 57000, '2025-02-15', '2025-02-28', true),
('BOBA-6567', 'Bogotá', 'Buenos Aires', 'Economy', 39860, '2025-02-15', '2025-02-28', true),
('BOME-4442', 'Bogotá', 'Medellín', 'Economy', 11000, '2025-02-10', '2025-02-24', true),
('MEPI-9986', 'Medellín', 'Puerto Iguazú', 'Business', 41640, '2025-04-17', '2025-05-02', true);

INSERT INTO flights (flight_number, origin, destination, seat_type, price_per_person, date_from, date_to) VALUES
('BAPI-1235', 'Buenos Aires', 'Puerto Iguazú', 'Economy', 6500, '2025-02-10', '2025-02-15'),
('PIBA-1420', 'Puerto Iguazú', 'Bogotá', 'Business', 43200, '2025-02-10', '2025-02-20'),
('PIBA-1420', 'Puerto Iguazú', 'Bogotá', 'Economy', 25735.89, '2025-02-10', '2025-02-20'),
('BATU-5536', 'Buenos Aires', 'Tucumán', 'Economy', 7320, '2025-02-10', '2025-02-17'),
('TUPI-3369', 'Tucumán', 'Puerto Iguazú', 'Business', 12530, '2025-02-12', '2025-02-23'),
('TUPI-3369', 'Tucumán', 'Puerto Iguazú', 'Economy', 5400, '2025-02-12', '2025-02-23'),
('BOCA-4213', 'Bogotá', 'Cartagena', 'Economy', 8000, '2025-01-23', '2025-02-05'),
('CAME-0321', 'Cartagena', 'Medellín', 'Economy', 7800, '2025-01-23', '2025-01-31'),
('BOBA-6567', 'Bogotá', 'Buenos Aires', 'Business', 57000, '2025-02-15', '2025-02-28'),
('BOBA-6567', 'Bogotá', 'Buenos Aires', 'Economy', 39860, '2025-02-15', '2025-02-28'),
('BOME-4442', 'Bogotá', 'Medellín', 'Economy', 11000, '2025-02-10', '2025-02-24'),
('MEPI-9986', 'Medellín', 'Puerto Iguazú', 'Business', 41640, '2025-04-17', '2025-05-02');

--################################--