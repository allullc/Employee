-- Inserts data for Employee table -------------------------------------------------------------------------------------
INSERT INTO employee (id, name, last_name, birth_date, salary)
SELECT 'c41f6764-e05e-407e-8402-2dd4d9abf47e',
       'Leonel',
       'Messi',
       '1968-03-03 00:00:00',
       35.23 WHERE NOT EXISTS(SELECT 1 FROM employee WHERE id = 'c41f6764-e05e-407e-8402-2dd4d9abf47e');

INSERT INTO employee (id, name, last_name, birth_date, salary)
SELECT 'c41f6764-e04e-407e-8402-2dd4d9abf47e',
       'Lautaro',
       'Martinez',
       '1998-11-23 00:00:00',
       2083.52 WHERE NOT EXISTS(SELECT 1 FROM employee WHERE id = 'c41f6764-e04e-407e-8402-2dd4d9abf47e');

INSERT INTO employee (id, name, last_name, birth_date, salary)
SELECT 'c41f6764-e06e-407e-8402-2dd4d9abf47e',
       'Pedro',
       'Gonzales',
       '2014-05-14 00:00:00',
       1023.51 WHERE NOT EXISTS(SELECT 1 FROM employee WHERE id = 'c41f6764-e06e-407e-8402-2dd4d9abf47e');

INSERT INTO employee (id, name, last_name, birth_date, salary)
SELECT 'c41f6764-e07e-407e-8402-2dd4d9abf47e',
       'Maira',
       'Perez',
       '1968-02-16 00:00:00',
       4083.45 WHERE NOT EXISTS(SELECT 1 FROM employee WHERE id = 'c41f6764-e07e-407e-8402-2dd4d9abf47e');

INSERT INTO employee (id, name, last_name, birth_date, salary)
SELECT 'c41f6764-e08e-407e-8402-2dd4d9abf47e',
       'Sergio',
       'Lamoru',
       '2001-08-13 00:00:00',
       2950.34 WHERE NOT EXISTS(SELECT 1 FROM employee WHERE id = 'c41f6764-e08e-407e-8402-2dd4d9abf47e');

INSERT INTO employee (id, name, last_name, birth_date, salary)
SELECT 'c41f6764-e09e-407e-8402-2dd4d9abf47e',
       'Melisa',
       'Hernandez',
       '1994-08-03 00:00:00',
       2012.78 WHERE NOT EXISTS(SELECT 1 FROM employee WHERE id = 'c41f6764-e09e-407e-8402-2dd4d9abf47e');
------------------------------------------------------------------------------------------------------------------------