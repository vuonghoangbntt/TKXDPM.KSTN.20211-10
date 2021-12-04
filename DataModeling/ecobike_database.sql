-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 04, 2021 lúc 06:43 PM
-- Phiên bản máy phục vụ: 10.4.20-MariaDB
-- Phiên bản PHP: 8.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `ecobike_database`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bike`
--

CREATE TABLE `bike` (
  `bikeCode` int(11) NOT NULL,
  `licensePlate` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `motor` varchar(45) NOT NULL,
  `status` int(11) NOT NULL,
  `numOfPedal` int(11) NOT NULL,
  `valueOfBike` int(11) NOT NULL,
  `numOfSaddle` int(11) NOT NULL,
  `maxTime` int(11) NOT NULL,
  `remainBattery` int(11) NOT NULL,
  `numOfSeat` int(11) NOT NULL,
  `dockID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `card`
--

CREATE TABLE `card` (
  `id` int(11) NOT NULL,
  `cardCode` varchar(15) NOT NULL,
  `owner` varchar(45) NOT NULL,
  `cvvCode` varchar(3) NOT NULL,
  `dateExpired` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dock`
--

CREATE TABLE `dock` (
  `dockID` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `numOfBikes` int(11) NOT NULL,
  `address` varchar(45) NOT NULL,
  `area` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `invoice`
--

CREATE TABLE `invoice` (
  `id` int(11) NOT NULL,
  `totalAmount` int(11) NOT NULL,
  `rentID` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `paymenttransaction`
--

CREATE TABLE `paymenttransaction` (
  `id` int(11) NOT NULL,
  `createAt` datetime NOT NULL,
  `content` varchar(45) NOT NULL,
  `method` varchar(45) DEFAULT NULL,
  `cardId` int(11) NOT NULL,
  `invoiceId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `renttransaction`
--

CREATE TABLE `renttransaction` (
  `rentalCode` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `owner` varchar(45) NOT NULL,
  `rentTime` varchar(45) NOT NULL,
  `depositeCost` int(11) NOT NULL,
  `returnTime` varchar(45) NOT NULL,
  `bikeCode` int(11) NOT NULL,
  `rentCost` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `bike`
--
ALTER TABLE `bike`
  ADD PRIMARY KEY (`bikeCode`),
  ADD KEY `bikeCode` (`bikeCode`),
  ADD KEY `dockID` (`dockID`);

--
-- Chỉ mục cho bảng `card`
--
ALTER TABLE `card`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `dock`
--
ALTER TABLE `dock`
  ADD PRIMARY KEY (`dockID`),
  ADD KEY `dockID` (`dockID`);

--
-- Chỉ mục cho bảng `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`id`),
  ADD KEY `rentID` (`rentID`);

--
-- Chỉ mục cho bảng `paymenttransaction`
--
ALTER TABLE `paymenttransaction`
  ADD PRIMARY KEY (`id`,`cardId`,`invoiceId`),
  ADD KEY `invoiceId` (`invoiceId`),
  ADD KEY `cardId` (`cardId`);

--
-- Chỉ mục cho bảng `renttransaction`
--
ALTER TABLE `renttransaction`
  ADD PRIMARY KEY (`rentalCode`),
  ADD KEY `rentalCode` (`rentalCode`),
  ADD KEY `bikeCode` (`bikeCode`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `card`
--
ALTER TABLE `card`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `dock`
--
ALTER TABLE `dock`
  MODIFY `dockID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `invoice`
--
ALTER TABLE `invoice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `bike`
--
ALTER TABLE `bike`
  ADD CONSTRAINT `bike_ibfk_1` FOREIGN KEY (`dockID`) REFERENCES `dock` (`dockID`);

--
-- Các ràng buộc cho bảng `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`rentID`) REFERENCES `renttransaction` (`rentalCode`);

--
-- Các ràng buộc cho bảng `paymenttransaction`
--
ALTER TABLE `paymenttransaction`
  ADD CONSTRAINT `paymenttransaction_ibfk_1` FOREIGN KEY (`invoiceId`) REFERENCES `invoice` (`id`),
  ADD CONSTRAINT `paymenttransaction_ibfk_2` FOREIGN KEY (`cardId`) REFERENCES `card` (`id`);

--
-- Các ràng buộc cho bảng `renttransaction`
--
ALTER TABLE `renttransaction`
  ADD CONSTRAINT `renttransaction_ibfk_1` FOREIGN KEY (`bikeCode`) REFERENCES `bike` (`bikeCode`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
