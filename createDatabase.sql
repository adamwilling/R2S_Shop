DROP DATABASE r2s_shop;
CREATE DATABASE r2s_shop;
USE r2s_shop;

CREATE TABLE `category` (
    id INT PRIMARY KEY,
    name NVARCHAR(255)
);

CREATE TABLE `product` (
    id INT PRIMARY KEY,
    name NVARCHAR(255),
    price DECIMAL(10, 2),
    description TEXT,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE `variant_product` (
    id INT PRIMARY KEY,
    name NVARCHAR(255),
    price DECIMAL(10, 2),
    size NVARCHAR(255),
    color NVARCHAR(255),
    material NVARCHAR(255),
    product_id INT,
    FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE `user` (
    id INT PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    fullname NVARCHAR(255),
    email VARCHAR(255),
    phone_number VARCHAR(255),
    date_of_birth DATETIME,
    status BOOLEAN DEFAULT(TRUE)
);

CREATE TABLE `role` (
    id INT PRIMARY KEY,
    name NVARCHAR(255)
);

CREATE TABLE `user_role` (
    user_id INT,
    role_id INT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (role_id) REFERENCES role(id)
);

CREATE TABLE `address` (
    id INT PRIMARY KEY,
    street NVARCHAR(255),
    city NVARCHAR(255),
    state NVARCHAR(255),
    zip_code NVARCHAR(255),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE `cart` (
    id INT PRIMARY KEY,
    user_id INT,
    created_at DATETIME,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE `cart_line_item` (
    id INT PRIMARY KEY,
    cart_id INT,
    variant_product_id INT,
    quantity INT,
    is_deleted BOOLEAN,
    FOREIGN KEY (cart_id) REFERENCES cart(id),
    FOREIGN KEY (variant_product_id) REFERENCES variant_product(id)
);

CREATE TABLE `order` (
    id INT PRIMARY KEY,
    user_id INT,
    total_price DECIMAL(10, 2),
    created_at DATETIME,
    address_id INT,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (address_id) REFERENCES address(id)
);

-- Tạo dữ liệu cho bảng category
INSERT INTO `category` (id, name) VALUES
(1, 'Máy tính xách tay'),
(2, 'Điện thoại di động'),
(3, 'Máy tính để bàn'),
(4, 'Thiết bị lưu trữ'),
(5, 'Phần mềm'),
(6, 'Phụ kiện'),
(7, 'Máy ảnh'),
(8, 'Âm thanh'),
(9, 'Máy in'),
(10, 'Máy scan');

-- Tạo dữ liệu cho bảng product
INSERT INTO `product` (id, name, price, description, category_id) VALUES
(1, 'Laptop Dell XPS 13', 30000000, 'Mô tả laptop Dell XPS 13', 1),
(2, 'Laptop HP Spectre x360', 25000000, 'Mô tả laptop HP Spectre x360', 1),
(3, 'Điện thoại iPhone 12', 20000000, 'Mô tả điện thoại iPhone 12', 2),
(4, 'Điện thoại Samsung Galaxy S21', 18000000, 'Mô tả điện thoại Samsung Galaxy S21', 2),
(5, 'PC Dell OptiPlex 3070', 15000000, 'Mô tả PC Dell OptiPlex 3070', 3),
(6, 'PC HP EliteDesk 800', 17000000, 'Mô tả PC HP EliteDesk 800', 3),
(7, 'Ổ cứng di động WD My Passport', 1500000, 'Mô tả ổ cứng di động WD My Passport', 4),
(8, 'Ổ cứng SSD Samsung 860 EVO', 2500000, 'Mô tả ổ cứng SSD Samsung 860 EVO', 4),
(9, 'Phần mềm Microsoft Office 365', 1500000, 'Mô tả phần mềm Microsoft Office 365', 5),
(10, 'Phần mềm Adobe Photoshop', 5000000, 'Mô tả phần mềm Adobe Photoshop', 5),
(11, 'Chuột không dây Logitech M325', 300000, 'Mô tả chuột không dây Logitech M325', 6),
(12, 'Bàn phím cơ Leopold FC900R', 2000000, 'Mô tả bàn phím cơ Leopold FC900R', 6),
(13, 'Máy ảnh Canon EOS 80D', 15000000, 'Mô tả máy ảnh Canon EOS 80D', 7),
(14, 'Máy ảnh Sony Alpha A7 III', 25000000, 'Mô tả máy ảnh Sony Alpha A7 III', 7),
(15, 'Loa di động JBL Flip 4', 2000000, 'Mô tả loa di động JBL Flip 4', 8),
(16, 'Tai nghe Bluetooth Sony WH-1000XM4', 5000000, 'Mô tả tai nghe Bluetooth Sony WH-1000XM4', 8),
(17, 'Máy in Canon PIXMA TS5370', 2000000, 'Mô tả máy in Canon PIXMA TS5370', 9),
(18, 'Máy in HP Neverstop Laser MFP 1200w', 3000000, 'Mô tả máy in HP Neverstop Laser MFP 1200w', 9),
(19, 'Máy scan Epson Perfection V39', 1500000, 'Mô tả máy scan Epson Perfection V39', 10),
(20, 'Máy scan Canon imageFORMULA DR-C225 II', 5000000, 'Mô tả máy scan Canon imageFORMULA DR-C225 II', 10);

-- Tạo dữ liệu cho bảng variant_product
INSERT INTO `variant_product` (id, name, price, size, color, material, product_id) VALUES
(1, 'Laptop Dell XPS 13 - Variant 1', 30000000, '13 inch', 'Bạc', 'Nhôm', 1),
(2, 'Laptop Dell XPS 13 - Variant 2', 32000000, '13 inch', 'Đen', 'Nhôm', 1),
(3, 'Laptop HP Spectre x360 - Variant 1', 25000000, '15 inch', 'Bạc', 'Nhôm', 2),
(4, 'Laptop HP Spectre x360 - Variant 2', 27000000, '15 inch', 'Vàng', 'Nhôm', 2),
(5, 'Điện thoại iPhone 12 - Variant 1', 20000000, '6.1 inch', 'Trắng', 'Kính và nhôm', 3),
(6, 'Điện thoại iPhone 12 - Variant 2', 22000000, '6.1 inch', 'Đen', 'Kính và nhôm', 3),
(7, 'Điện thoại Samsung Galaxy S21 - Variant 1', 18000000, '6.2 inch', 'Xanh', 'Kính và nhựa', 4),
(8, 'Điện thoại Samsung Galaxy S21 - Variant 2', 19000000, '6.2 inch', 'Đỏ', 'Kính và nhựa', 4),
(9, 'PC Dell OptiPlex 3070 - Variant 1', 15000000, 'Mini Tower', 'Đen', 'Nhựa và thép', 5),
(10, 'PC Dell OptiPlex 3070 - Variant 2', 17000000, 'Mini Tower', 'Đen', 'Nhựa và thép', 5),
(11, 'PC HP EliteDesk 800 - Variant 1', 17000000, 'Tower', 'Đen', 'Nhựa và thép', 6),
(12, 'PC HP EliteDesk 800 - Variant 2', 19000000, 'Tower', 'Đen', 'Nhựa và thép', 6),
(13, 'Ổ cứng di động WD My Passport - Variant 1', 1500000, '2.5 inch', 'Đen', 'Nhựa', 7),
(14, 'Ổ cứng di động WD My Passport - Variant 2', 1700000, '2.5 inch', 'Trắng', 'Nhựa', 7),
(15, 'Ổ cứng SSD Samsung 860 EVO - Variant 1', 2500000, '2.5 inch', 'Đen', 'Nhôm', 8),
(16, 'Ổ cứng SSD Samsung 860 EVO - Variant 2', 2700000, '2.5 inch', 'Bạc', 'Nhôm', 8),
(17, 'Phần mềm Microsoft Office 365 - Variant 1', 1500000, 'Phiên bản Personal', 'Không có', 'Không có', 9),
(18, 'Phần mềm Microsoft Office 365 - Variant 2', 1500000, 'Phiên bản Family', 'Không có', 'Không có', 9),
(19, 'Phần mềm Adobe Photoshop - Variant 1', 5000000, 'Phiên bản Standalone', 'Không có', 'Không có', 10),
(20, 'Phần mềm Adobe Photoshop - Variant 2', 5000000, 'Phiên bản Subscription', 'Không có', 'Không có', 10);

-- Tạo dữ liệu cho bảng user
INSERT INTO `user` (id, username, password, fullname, email, phone_number, date_of_birth, status) VALUES
(1, 'admin', 'admin123', 'Admin', 'admin@example.com', '123456789', '1990-01-01', true),
(2, 'user1', 'user123', 'User 1', 'user1@example.com', '123456780', '1995-02-02', true),
(3, 'user2', 'user123', 'User 2', 'user2@example.com', '123456781', '1996-03-03', true),
(4, 'user3', 'user123', 'User 3', 'user3@example.com', '123456782', '1997-04-04', true),
(5, 'user4', 'user123', 'User 4', 'user4@example.com', '123456783', '1998-05-05', true),
(6, 'user5', 'user123', 'User 5', 'user5@example.com', '123456784', '1999-06-06', true),
(7, 'user6', 'user123', 'User 6', 'user6@example.com', '123456785', '2000-07-07', true),
(8, 'user7', 'user123', 'User 7', 'user7@example.com', '123456786', '2001-08-08', true),
(9, 'user8', 'user123', 'User 8', 'user8@example.com', '123456787', '2002-09-09', true),
(10, 'user9', 'user123', 'User 9', 'user9@example.com', '123456788', '2003-10-10', true);

-- Tạo dữ liệu cho bảng role
INSERT INTO `role` (id, name) VALUES
(1, 'Admin'),
(2, 'User');

-- Tạo dữ liệu cho bảng user_role
INSERT INTO `user_role` (user_id, role_id) VALUES
(1, 1), -- Admin
(2, 2),
(3, 2),
(4, 2),
(5, 2),
(6, 2),
(7, 2),
(8, 2),
(9, 2),
(10, 2);

-- Tạo dữ liệu cho bảng address
INSERT INTO `address` (id, street, city, state, zip_code, user_id) VALUES
(1, 'Đường ABC', 'Thành phố XYZ', 'Tỉnh MNP', '12345', 2),
(2, 'Đường DEF', 'Thành phố UVW', 'Tỉnh QRS', '67890', 3),
(3, 'Đường GHI', 'Thành phố JKL', 'Tỉnh TUV', '11111', 4),
(4, 'Đường MNO', 'Thành phố PQR', 'Tỉnh STU', '22222', 5),
(5, 'Đường VWX', 'Thành phố YZ', 'Tỉnh CD', '33333', 6),
(6, 'Đường EFG', 'Thành phố HIJ', 'Tỉnh KLM', '44444', 7),
(7, 'Đường NOP', 'Thành phố QRS', 'Tỉnh TUV', '55555', 8),
(8, 'Đường UVW', 'Thành phố XYZ', 'Tỉnh MNP', '66666', 9),
(9, 'Đường XYZ', 'Thành phố ABC', 'Tỉnh DEF', '77777', 10);

-- Tạo dữ liệu cho bảng cart
INSERT INTO `cart` (id, user_id, created_at) VALUES
(1, 2, '2023-05-20 10:00:00'),
(2, 3, '2023-05-20 11:00:00'),
(3, 4, '2023-05-20 12:00:00'),
(4, 5, '2023-05-20 13:00:00'),
(5, 6, '2023-05-20 14:00:00'),
(6, 7, '2023-05-20 15:00:00'),
(7, 8, '2023-05-20 16:00:00'),
(8, 9, '2023-05-20 17:00:00'),
(9, 10, '2023-05-20 18:00:00');

-- Tạo dữ liệu cho bảng cart_line_item
INSERT INTO `cart_line_item` (id, cart_id, variant_product_id, quantity, is_deleted) VALUES
(1, 1, 1, 1, false),
(2, 1, 2, 1, false),
(3, 2, 3, 1, false),
(4, 2, 4, 1, false),
(5, 3, 5, 1, false),
(6, 3, 6, 1, false),
(7, 4, 7, 1, false),
(8, 4, 8, 1, false),
(9, 5, 9, 1, false),
(10, 5, 10, 1, false),
(11, 6, 11, 1, false),
(12, 6, 12, 1, false),
(13, 7, 13, 1, false),
(14, 7, 14, 1, false),
(15, 8, 15, 1, false),
(16, 8, 16, 1, false),
(17, 9, 17, 1, false),
(18, 9, 18, 1, false);

-- Tạo dữ liệu cho bảng order
INSERT INTO `order` (id, user_id, total_price, created_at, address_id) VALUES
(1, 2, 60000000, '2023-05-20 10:30:00', 1),
(2, 3, 54000000, '2023-05-20 11:30:00', 2),
(3, 4, 48000000, '2023-05-20 12:30:00', 3),
(4, 5, 42000000, '2023-05-20 13:30:00', 4),
(5, 6, 36000000, '2023-05-20 14:30:00', 5),
(6, 7, 30000000, '2023-05-20 15:30:00', 6),
(7, 8, 24000000, '2023-05-20 16:30:00', 7),
(8, 9, 18000000, '2023-05-20 17:30:00', 8),
(9, 10, 12000000, '2023-05-20 18:30:00', 9);