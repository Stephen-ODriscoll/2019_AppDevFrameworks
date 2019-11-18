INSERT INTO customer (customerId, firstName, lastName) VALUES
('1', 'Stephen', 'O Driscoll'),
('2', 'Joe', 'Murphy'),
('3', 'Mary', 'Cronin'),
('4', 'Harry', 'Walsh'),
('5', 'John', 'O Sullivan');

INSERT INTO account (accountId, balance, overdraftLimit) VALUES
('1', '2000.00', '2000.00'),
('2', '1000.00', '3000.00'),
('3', '10001.00', '20000.00'),
('4', '3000.00', '1500.00'),
('5', '20000.00', '1000.00');


INSERT INTO customerAccount (customerId, accountId) VALUES
('1', '1'),
('2', '2'),
('3', '2'),
('4', '3'),
('1', '4'),
('5', '5');

