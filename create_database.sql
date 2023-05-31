--
-- Tạo CSDL `r2s_shop`
--

DROP DATABASE IF EXISTS `r2s_shop`;
CREATE DATABASE `r2s_shop`;
USE `r2s_shop`;

-- --------------------------------------------------------

--
-- Tạo bảng `category`
--
-- Bảng này lưu trữ thông tin về danh mục sản phẩm.
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `name` nvarchar(256) DEFAULT NULL
);

-- Thêm dữ liệu cho bảng `category`
INSERT INTO `category` (`name`)
VALUES
  ('Điện tử'),
  ('Thời trang'),
  ('Nhà cửa & Đời sống'),
  ('Sách'),
  ('Làm đẹp');

--
-- Tạo bảng `product`
--
-- Bảng này lưu trữ thông tin về sản phẩm.
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `description` nvarchar(512) DEFAULT NULL,
  `name` nvarchar(256) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
);

-- Thêm dữ liệu cho bảng `product`
INSERT INTO `product` (`description`, `name`, `price`, `category_id`)
VALUES
  ('Mô tả sản phẩm 01', 'Sản phẩm 01', 999000, 1),
  ('Mô tả sản phẩm 02', 'Sản phẩm 02', 499000, 2),
  ('Mô tả sản phẩm 03', 'Sản phẩm 03', 1999000, 1),
  ('Mô tả sản phẩm 04', 'Sản phẩm 04', 1499000, 3),
  ('Mô tả sản phẩm 05', 'Sản phẩm 05', 799000, 4),
  ('Mô tả sản phẩm 06', 'Sản phẩm 06', 2999000, 1),
  ('Mô tả sản phẩm 07', 'Sản phẩm 07', 399000, 2),
  ('Mô tả sản phẩm 08', 'Sản phẩm 08', 2499000, 3),
  ('Mô tả sản phẩm 09', 'Sản phẩm 09', 1199000, 5),
  ('Mô tả sản phẩm 10', 'Sản phẩm 10', 699000, 4),
  ('Mô tả sản phẩm 11', 'Sản phẩm 11', 1799000, 1),
  ('Mô tả sản phẩm 12', 'Sản phẩm 12', 899000, 2),
  ('Mô tả sản phẩm 13', 'Sản phẩm 13', 3499000, 3),
  ('Mô tả sản phẩm 14', 'Sản phẩm 14', 999000, 5),
  ('Mô tả sản phẩm 15', 'Sản phẩm 15', 599000, 4),
  ('Mô tả sản phẩm 16', 'Sản phẩm 16', 2299000, 1),
  ('Mô tả sản phẩm 17', 'Sản phẩm 17', 299000, 2),
  ('Mô tả sản phẩm 18', 'Sản phẩm 18', 1999000, 3),
  ('Mô tả sản phẩm 19', 'Sản phẩm 19', 1399000, 5),
  ('Mô tả sản phẩm 20', 'Sản phẩm 20', 799000, 4);

-- 
-- Tạo bảng `variant_product`
--
-- Bảng này lưu trữ thông tin về các biến thể của sản phẩm.
--

DROP TABLE IF EXISTS `variant_product`;
CREATE TABLE IF NOT EXISTS `variant_product` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `material` nvarchar(20) DEFAULT NULL,
  `color` nvarchar(20) DEFAULT NULL,
  `size` nvarchar(5) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
);

-- Thêm dữ liệu cho bảng `variant_product`
INSERT INTO variant_product (material, color, size, price, product_id)
VALUES
  ('Chất liệu 1', 'Đỏ', 'S', 100000.0, 1),
  ('Chất liệu 2', 'Xanh', 'M', 200000.0, 1),
  ('Chất liệu 3', 'Xanh lá cây', 'L', 300000.0, 1),
  ('Chất liệu 4', 'Đen', 'S', 400000.0, 2),
  ('Chất liệu 5', 'Trắng', 'M', 500000.0, 2),
  ('Chất liệu 6', 'Xám', 'L', 600000.0, 2),
  ('Chất liệu 7', 'Đỏ', 'S', 700000.0, 3),
  ('Chất liệu 8', 'Xanh', 'M', 800000.0, 3),
  ('Chất liệu 9', 'Xanh lá cây', 'L', 900000.0, 3),
  ('Chất liệu 10', 'Đen', 'S', 1000000.0, 4),
  ('Chất liệu 11', 'Trắng', 'M', 1100000.0, 4),
  ('Chất liệu 12', 'Xám', 'L', 1200000.0, 4),
  ('Chất liệu 13', 'Đỏ', 'S', 1300000.0, 5),
  ('Chất liệu 14', 'Xanh', 'M', 1400000.0, 5),
  ('Chất liệu 15', 'Xanh lá cây', 'L', 1500000.0, 5),
  ('Chất liệu 16', 'Đen', 'S', 1600000.0, 6),
  ('Chất liệu 17', 'Trắng', 'M', 1700000.0, 6),
  ('Chất liệu 18', 'Xám', 'L', 1800000.0, 6),
  ('Chất liệu 19', 'Đỏ', 'S', 1900000.0, 7),
  ('Chất liệu 20', 'Xanh', 'M', 2000000.0, 7),
  ('Chất liệu 21', 'Xanh lá cây', 'L', 2100000.0, 7),
  ('Chất liệu 22', 'Đen', 'S', 2200000.0, 8),
  ('Chất liệu 23', 'Trắng', 'M', 2300000.0, 8),
  ('Chất liệu 24', 'Xám', 'L', 2400000.0, 8),
  ('Chất liệu 25', 'Đỏ', 'S', 2500000.0, 9),
  ('Chất liệu 26', 'Xanh', 'M', 2600000.0, 9),
  ('Chất liệu 27', 'Xanh lá cây', 'L', 2700000.0, 9),
  ('Chất liệu 28', 'Đen', 'S', 2800000.0, 10),
  ('Chất liệu 29', 'Trắng', 'M', 2900000.0, 10),
  ('Chất liệu 30', 'Xám', 'L', 3000000.0, 10);

--
-- Tạo bảng `role`
--
-- Bảng này lưu trữ thông tin về vai trò của người dùng.
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `name` nvarchar(10) DEFAULT NULL
);

-- Thêm dữ liệu cho bảng `role`
INSERT INTO `role` (`name`)
VALUES
  ('ADMIN'),
  ('USER');

--
-- Tạo bảng `user`
--
-- Bảng này lưu trữ thông tin về người dùng.
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `username` nvarchar(64) UNIQUE DEFAULT NULL,
  `password` nvarchar(256) DEFAULT NULL,
  `gender` nvarchar(16) DEFAULT NULL,
  `email` nvarchar(128) DEFAULT NULL,
  `phone_number` nvarchar(12) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `status` bit DEFAULT 1,
  `role_id` bigint DEFAULT NULL,
  FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
);

-- Thêm dữ liệu cho bảng `user`
INSERT INTO `user` (`username`, `password`, `gender`, `email`, `phone_number`, `date_of_birth`, `status`, `role_id`)
VALUES
  ('admin', '$2a$10$hvVJl2SPA7nkd2c3soDt2uQ6l91wMdvt3nonx69WUDNAirA9o8VX6', 'Nam', 'admin@gmail.com', '0369572542', '2002-11-29', 1, 1),
  ('user', '$2a$10$hvVJl2SPA7nkd2c3soDt2uQ6l91wMdvt3nonx69WUDNAirA9o8VX6', 'Nữ', 'user@gmail.com', '0373134488', '2002-02-02', 1, 2);

--
-- Tạo bảng `address`
--
-- Bảng này lưu trữ thông tin về địa chỉ của người dùng.
--

DROP TABLE IF EXISTS `address`;
CREATE TABLE IF NOT EXISTS `address` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `address` nvarchar(256) DEFAULT NULL,
  `is_deleted` bit DEFAULT 1,
  `user_id` bigint DEFAULT NULL,
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

-- Thêm dữ liệu cho bảng `address`
INSERT INTO `address` (`address`, `is_deleted`, `user_id`)
VALUES
  ('Địa chỉ 1 - User ADMIN', 0, 1),
  ('Địa chỉ 2 - User ADMIN', 0, 1),
  ('Địa chỉ 3 - User ADMIN', 0, 1),
  ('Địa chỉ 4 - User ADMIN', 0, 1),
  ('Địa chỉ 5 - User ADMIN', 0, 1),
  ('Địa chỉ 1 - User USER', 0, 2),
  ('Địa chỉ 2 - User USER', 0, 2),
  ('Địa chỉ 3 - User USER', 0, 2),
  ('Địa chỉ 4 - User USER', 0, 2),
  ('Địa chỉ 5 - User USER', 0, 2);

-- --------------------------------------------------------

--
-- Tạo bảng `cart`
--
-- Bảng này lưu trữ thông tin về giỏ hàng của người dùng.
--

DROP TABLE IF EXISTS `cart`;
CREATE TABLE IF NOT EXISTS `cart` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `status` varchar(64) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

-- Thêm dữ liệu cho bảng `cart`
INSERT INTO `cart` (`status`, `user_id`)
VALUES
  ('new', 1),
  ('new', 2);

--
-- Tạo bảng `cart_line_item`
--
-- Bảng này lưu trữ thông tin về các mặt hàng trong giỏ hàng.
--

DROP TABLE IF EXISTS `cart_line_item`;
CREATE TABLE IF NOT EXISTS `cart_line_item` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `amount` int DEFAULT NULL,
  `price` float DEFAULT NULL,
  `cart_id` bigint DEFAULT NULL,
  `variant_product_id` bigint DEFAULT NULL,
  `is_deleted` tinyint NOT NULL DEFAULT 0,
  FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  FOREIGN KEY (`variant_product_id`) REFERENCES `variant_product` (`id`)
);

-- Thêm dữ liệu cho bảng `cart_line_item`
INSERT INTO `cart_line_item` (`amount`, `price`, `cart_id`, `variant_product_id`)
VALUES
  (1, 50000, 1, 1),
  (2, 30000, 1, 2),
  (3, 150000, 1, 3),
  (1, 200000, 1, 4),
  (2, 100000, 1, 5),
  (2, 25000, 2, 3),
  (1, 120000, 2, 5),
  (3, 80000, 2, 6),
  (2, 35000, 2, 7),
  (1, 60000, 2, 8);

--
-- Tạo bảng `cart_order`
--
-- Bảng này lưu trữ thông tin về đơn hàng từ giỏ hàng.
--

DROP TABLE IF EXISTS `cart_order`;
CREATE TABLE IF NOT EXISTS `cart_order` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `address_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `total_price` float NOT NULL,
  `estimated_arrival_time` date NOT NULL,
  `is_arrived` tinyint NOT NULL DEFAULT 0,
  `cart_id` bigint NOT NULL,
  FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);