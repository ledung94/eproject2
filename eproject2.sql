-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th2 09, 2021 lúc 11:00 AM
-- Phiên bản máy phục vụ: 10.4.11-MariaDB
-- Phiên bản PHP: 7.2.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `eproject2`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `currentstocks`
--

CREATE TABLE `currentstocks` (
  `productID` varchar(100) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `currentstocks`
--

INSERT INTO `currentstocks` (`productID`, `quantity`) VALUES
('1', 10),
('2', 24),
('3', 0),
('5', 0),
('6', 5),
('7', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customers`
--

CREATE TABLE `customers` (
  `customerID` int(11) NOT NULL,
  `customerCode` varchar(100) NOT NULL,
  `customerName` varchar(50) NOT NULL,
  `customerAddress` varchar(50) NOT NULL,
  `customerPhone` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `customers`
--

INSERT INTO `customers` (`customerID`, `customerCode`, `customerName`, `customerAddress`, `customerPhone`) VALUES
(2, 'cus3', 'ram', 'ktm', '331');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `productID` int(11) NOT NULL,
  `productName` varchar(200) NOT NULL,
  `productCode` varchar(200) NOT NULL,
  `costprice` float NOT NULL,
  `sellingprice` float NOT NULL,
  `category` varchar(200) NOT NULL,
  `productImage` text DEFAULT NULL,
  `status` varchar(200) NOT NULL,
  `date` varchar(200) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`productID`, `productName`, `productCode`, `costprice`, `sellingprice`, `category`, `productImage`, `status`, `date`) VALUES
(1, 'samsung', 'S21', 100, 1200, 'phone', 'C:\\Users\\ADMIN\\Downloads\\image.png', 'AVAILABLE', '2021-01-31'),
(2, 'iphone', 'I12', 200, 1500, 'phone', 'C:\\Users\\ADMIN\\Downloads\\image.png', 'AVAILABLE', '2021-01-31'),
(3, 'huawei', 'H2', 100, 500, 'phone', NULL, 'AVAILABLE', '2021-02-06'),
(4, 'xiaomi', 'RED1', 200, 400, 'phone', 'C:\\Users\\ADMIN\\Downloads\\image.png', 'AVAILABLE', '2021-02-06'),
(5, 'xiami', 'RED7', 100, 300, 'phone', NULL, 'AVAILABLE', '2021-02-06'),
(6, 'Macbook pro', 'MF2015', 1000, 1799, 'laptop', 'C:\\Users\\ADMIN\\Downloads\\image.png', 'AVAILABLE', '2021-02-09'),
(7, 'Thinkpad', 'HF5K1', 600, 1200, 'laptop', 'C:\\Users\\ADMIN\\Downloads\\image.png', 'AVAILABLE', '2021-02-09');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `recorddetail`
--

CREATE TABLE `recorddetail` (
  `productID` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `recordID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `recorddetail`
--

INSERT INTO `recorddetail` (`productID`, `quantity`, `recordID`) VALUES
(1, 1, 1),
(1, 2, 2),
(1, 5, 3),
(1, 2, 4),
(2, 2, 1),
(2, 2, 2),
(2, 5, 3),
(2, 5, 4),
(2, 10, 5),
(6, 5, 5);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `records`
--

CREATE TABLE `records` (
  `recordID` int(11) NOT NULL,
  `recordCode` varchar(200) NOT NULL,
  `recordType` varchar(200) NOT NULL,
  `supplierID` int(11) NOT NULL,
  `customerID` int(11) NOT NULL,
  `handleBy` int(11) NOT NULL,
  `date` varchar(200) NOT NULL,
  `totalPrice` double NOT NULL,
  `vat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `records`
--

INSERT INTO `records` (`recordID`, `recordCode`, `recordType`, `supplierID`, `customerID`, `handleBy`, `date`, `totalPrice`, `vat`) VALUES
(1, 'RC1', 'DELETED', 68, 0, 0, '05/01/2021', 300, 0),
(2, 'RC2', 'IMPORT', 69, 0, 0, '07/01/2021', 400, 10),
(3, 'RC3', 'IMPORT', 69, 0, 0, '05/01/2021', 1100, 10),
(4, 'RC4', 'IMPORT', 69, 0, 0, '05/01/2021', 770, 10),
(5, 'RC5', 'IMPORT', 143, 0, 58, '09/02/2021', 7700, 10);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `suppliers`
--

CREATE TABLE `suppliers` (
  `supplierID` int(11) NOT NULL,
  `supplierName` varchar(50) NOT NULL,
  `supplierCode` varchar(100) NOT NULL,
  `supplierContact` varchar(10) NOT NULL,
  `supplierLocation` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `suppliers`
--

INSERT INTO `suppliers` (`supplierID`, `supplierName`, `supplierCode`, `supplierContact`, `supplierLocation`) VALUES
(69, 'manish', 'sup5', '4123372', 'ktm'),
(68, 'sia', 'sup4', '11623231', 'US'),
(143, 'Cinnamon', 'cin2', '012345678', '36 hoang cau');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `userID` int(11) NOT NULL,
  `fullname` varchar(50) NOT NULL,
  `location` varchar(50) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(200) NOT NULL,
  `role` varchar(20) NOT NULL,
  `status` varchar(200) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`userID`, `fullname`, `location`, `phone`, `username`, `password`, `role`, `status`) VALUES
(54, 'Sajan Rajbhandari', 'Pokhara', '9849284991', 'user4', 'cc03e747a6afbbcbf8be7668acfebee5', 'ADMINISTRATOR', ''),
(56, 'Ram', 'Kathmandu', '9849284991', 'user5', 'a791842f52a0acfbb3a783378c066b8', 'NORMAL USER', ''),
(57, 'shyam', 'ktm', '98239832', 'user6', 'affec3b64cf90492377a8114c86fc093', 'NORMAL USER', ''),
(58, 'Le Dung', 'HaNoi', '123', 'ledung94', '123', 'ADMINISTRATOR', 'DELETED');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`customerID`);

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`productID`),
  ADD UNIQUE KEY `productCode` (`productCode`);

--
-- Chỉ mục cho bảng `recorddetail`
--
ALTER TABLE `recorddetail`
  ADD PRIMARY KEY (`productID`,`recordID`);

--
-- Chỉ mục cho bảng `records`
--
ALTER TABLE `records`
  ADD PRIMARY KEY (`recordID`),
  ADD UNIQUE KEY `recordCode` (`recordCode`);

--
-- Chỉ mục cho bảng `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`supplierID`),
  ADD UNIQUE KEY `supplierCode` (`supplierCode`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `customers`
--
ALTER TABLE `customers`
  MODIFY `customerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `productID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `records`
--
ALTER TABLE `records`
  MODIFY `recordID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `supplierID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=144;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
