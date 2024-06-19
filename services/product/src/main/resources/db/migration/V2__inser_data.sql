-- Insert categories
INSERT INTO category (description, name) VALUES
('Chess Boards', 'Boards'),
('Chess Pieces', 'Pieces'),
('Chess Clocks', 'Clocks'),
('Chess Books', 'Books'),
('Chess Accessories', 'Accessories');

-- Insert products
INSERT INTO product (description, name, available_quantity, price, category_id) VALUES
('A high-quality wooden chess board', 'Wooden Chess Board', 10, 199.99, 1),
('A roll-up chess board made of vinyl', 'Vinyl Chess Board', 50, 9.99, 1),
('A set of plastic chess pieces', 'Plastic Chess Pieces', 100, 19.99, 2),
('A luxury set of wooden chess pieces', 'Luxury Wooden Chess Pieces', 5, 199.99, 2),
('A digital chess clock with multiple time settings', 'Digital Chess Clock', 20, 59.99, 3),
('A classic mechanical chess clock', 'Mechanical Chess Clock', 15, 49.99, 3),
('A book on chess strategies for beginners', 'Chess Strategies for Beginners', 30, 14.99, 4),
('An advanced guide to chess tactics', 'Advanced Chess Tactics', 20, 24.99, 4),
('A chess bag for carrying boards and pieces', 'Chess Bag', 25, 19.99, 5),
('A chess bag for carrying wooden boards and pieces', 'Wooden Chess Bag', 25, 299.99, 5),
('A chess set with folding board and pieces', 'Folding Chess Set', 40, 29.99, 5);
