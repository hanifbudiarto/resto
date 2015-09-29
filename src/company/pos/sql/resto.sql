-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 29, 2015 at 11:10 AM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `resto`
--

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE IF NOT EXISTS `menu` (
`menu_id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `harga` bigint(20) NOT NULL,
  `kategori_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`menu_id`, `nama`, `harga`, `kategori_id`) VALUES
(1, 'NASI GORENG ISTIMEWA', 20000, 1),
(3, 'Nasi Kebuli', 5000, 1),
(4, 'Air Putih', 2000, 2),
(5, 'ASDAD', 33333, 1),
(6, 'MINUMAN SUSU', 10000, 2),
(7, 'DFGDG', 243243, 1),
(8, 'AAA', 2222, 1),
(9, 'DSD', 1111, 1),
(10, 'ASDAS', 21121, 1),
(11, 'ISTIMEWA', 11111, 1),
(12, 'JJHGHJGHJ', 65654, 1),
(13, 'MGMGMGG', 11112121212, 1),
(14, 'AAA', 19000, 1),
(15, 'DSD', 1111, 1),
(16, 'AAA', 19000, 1),
(17, 'AAA', 19000, 1),
(18, 'AAA', 19000, 1),
(19, 'AAA', 19000, 1),
(20, 'AAA', 19000, 1),
(21, 'AAA', 19000, 1),
(22, 'AAA', 19000, 1),
(23, 'AAA', 19000, 1),
(24, 'AAA', 19000, 1),
(25, 'AAA', 19000, 1),
(26, 'AAA', 19000, 1),
(27, 'AAA', 19000, 1),
(28, 'AAA', 19000, 1),
(29, 'AAA', 19000, 1),
(30, 'AAA', 19000, 1),
(31, 'AAA', 19000, 1);

--
-- Triggers `menu`
--
DELIMITER //
CREATE TRIGGER `uppercase_insert_menu` BEFORE INSERT ON `menu`
 FOR EACH ROW BEGIN
    
    SET NEW.nama = UPPER(NEW.nama);

END
//
DELIMITER ;
DELIMITER //
CREATE TRIGGER `uppercase_update_menu` BEFORE UPDATE ON `menu`
 FOR EACH ROW BEGIN
    
    SET NEW.nama = UPPER(NEW.nama);

END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `menu_kategori`
--

CREATE TABLE IF NOT EXISTS `menu_kategori` (
`kategori_id` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu_kategori`
--

INSERT INTO `menu_kategori` (`kategori_id`, `nama`) VALUES
(1, 'MAKANAN'),
(2, 'MINUMAN'),
(16, 'BEVERAGE'),
(17, 'HANIFF');

--
-- Triggers `menu_kategori`
--
DELIMITER //
CREATE TRIGGER `uppercase_insert_kategori` BEFORE INSERT ON `menu_kategori`
 FOR EACH ROW BEGIN
    
    SET NEW.nama = UPPER(NEW.nama);

END
//
DELIMITER ;
DELIMITER //
CREATE TRIGGER `uppercase_update_kategori` BEFORE UPDATE ON `menu_kategori`
 FOR EACH ROW BEGIN
    
    SET NEW.nama = UPPER(NEW.nama);

END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `pembelian`
--

CREATE TABLE IF NOT EXISTS `pembelian` (
`pembelian_id` int(11) NOT NULL,
  `pembelian_tanggal` date NOT NULL,
  `nama` varchar(100) NOT NULL,
  `total` bigint(20) NOT NULL,
  `pengguna_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pembelian_detail`
--

CREATE TABLE IF NOT EXISTS `pembelian_detail` (
`pembelian_detail_id` int(11) NOT NULL,
  `pembelian_id` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pengguna`
--

CREATE TABLE IF NOT EXISTS `pengguna` (
`pengguna_id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `kategori` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`pengguna_id`, `nama`, `username`, `password`, `kategori`) VALUES
(11, 'MUHAMMAD HANIF BUDIARTO', 'HANIF', 'HANIF', 'KASIR');

--
-- Triggers `pengguna`
--
DELIMITER //
CREATE TRIGGER `uppercase_insert_pengguna` BEFORE INSERT ON `pengguna`
 FOR EACH ROW BEGIN
    
    SET NEW.nama = UPPER(NEW.nama);
    SET NEW.username = UPPER(NEW.username);
    SET NEW.password = UPPER(NEW.password);
    SET NEW.kategori = UPPER(NEW.kategori);

END
//
DELIMITER ;
DELIMITER //
CREATE TRIGGER `uppercase_update_pengguna` BEFORE UPDATE ON `pengguna`
 FOR EACH ROW BEGIN
    
    SET NEW.nama = UPPER(NEW.nama);
    SET NEW.username = UPPER(NEW.username);
    SET NEW.password = UPPER(NEW.password);
    SET NEW.kategori = UPPER(NEW.kategori);

END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE IF NOT EXISTS `penjualan` (
`penjualan_id` int(11) NOT NULL,
  `penjualan_tanggal` date NOT NULL,
  `total` bigint(20) NOT NULL,
  `operator` varchar(20) NOT NULL,
  `timestamp` datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `penjualan`
--

INSERT INTO `penjualan` (`penjualan_id`, `penjualan_tanggal`, `total`, `operator`, `timestamp`) VALUES
(18, '0000-00-00', 1000000, 'hanifbudiarto', '2015-09-29 15:39:59');

--
-- Triggers `penjualan`
--
DELIMITER //
CREATE TRIGGER `oninsert_penjualan` BEFORE INSERT ON `penjualan`
 FOR EACH ROW BEGIN
	SET NEW.penjualan_tanggal = DATE_FORMAT(NEW.penjualan_tanggal, '%Y-%d-%m');
    SET NEW.timestamp = NOW();
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `penjualan_detail`
--

CREATE TABLE IF NOT EXISTS `penjualan_detail` (
`penjualan_detail_id` int(11) NOT NULL,
  `penjualan_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
 ADD PRIMARY KEY (`menu_id`), ADD KEY `kategori_id` (`kategori_id`);

--
-- Indexes for table `menu_kategori`
--
ALTER TABLE `menu_kategori`
 ADD PRIMARY KEY (`kategori_id`);

--
-- Indexes for table `pembelian`
--
ALTER TABLE `pembelian`
 ADD PRIMARY KEY (`pembelian_id`), ADD KEY `pengguna_id` (`pengguna_id`);

--
-- Indexes for table `pembelian_detail`
--
ALTER TABLE `pembelian_detail`
 ADD PRIMARY KEY (`pembelian_detail_id`), ADD KEY `pembelian_id` (`pembelian_id`);

--
-- Indexes for table `pengguna`
--
ALTER TABLE `pengguna`
 ADD PRIMARY KEY (`pengguna_id`);

--
-- Indexes for table `penjualan`
--
ALTER TABLE `penjualan`
 ADD PRIMARY KEY (`penjualan_id`), ADD KEY `pengguna_id` (`operator`);

--
-- Indexes for table `penjualan_detail`
--
ALTER TABLE `penjualan_detail`
 ADD PRIMARY KEY (`penjualan_detail_id`), ADD KEY `penjualan_id` (`penjualan_id`), ADD KEY `menu_id` (`menu_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `menu`
--
ALTER TABLE `menu`
MODIFY `menu_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=32;
--
-- AUTO_INCREMENT for table `menu_kategori`
--
ALTER TABLE `menu_kategori`
MODIFY `kategori_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `pembelian`
--
ALTER TABLE `pembelian`
MODIFY `pembelian_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pembelian_detail`
--
ALTER TABLE `pembelian_detail`
MODIFY `pembelian_detail_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pengguna`
--
ALTER TABLE `pengguna`
MODIFY `pengguna_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `penjualan`
--
ALTER TABLE `penjualan`
MODIFY `penjualan_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `penjualan_detail`
--
ALTER TABLE `penjualan_detail`
MODIFY `penjualan_detail_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `pembelian`
--
ALTER TABLE `pembelian`
ADD CONSTRAINT `pembelian_ibfk_1` FOREIGN KEY (`pengguna_id`) REFERENCES `pengguna` (`pengguna_id`) ON UPDATE CASCADE;

--
-- Constraints for table `pembelian_detail`
--
ALTER TABLE `pembelian_detail`
ADD CONSTRAINT `pembelian_detail_ibfk_1` FOREIGN KEY (`pembelian_id`) REFERENCES `pembelian` (`pembelian_id`) ON UPDATE CASCADE;

--
-- Constraints for table `penjualan_detail`
--
ALTER TABLE `penjualan_detail`
ADD CONSTRAINT `penjualan_detail_ibfk_1` FOREIGN KEY (`penjualan_id`) REFERENCES `penjualan` (`penjualan_id`) ON UPDATE CASCADE,
ADD CONSTRAINT `penjualan_detail_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
