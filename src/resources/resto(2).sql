-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 29, 2015 at 08:20 AM
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
-- Table structure for table `charges`
--

CREATE TABLE IF NOT EXISTS `charges` (
`charges_id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `biaya` varchar(100) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `charges`
--

INSERT INTO `charges` (`charges_id`, `nama`, `biaya`) VALUES
(1, 'pajak', '0.1'),
(2, 'servis', '1000');

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE IF NOT EXISTS `menu` (
`menu_id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `harga` bigint(20) NOT NULL,
  `kategori_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`menu_id`, `nama`, `harga`, `kategori_id`) VALUES
(1, 'ES TEH MANIS', 5000, 2),
(2, 'KENTANG GORENG', 10000, 17),
(3, 'MIE AYAM JAKARTA', 20000, 1),
(4, 'NASI GORENG SPECIAL', 17000, 1),
(5, 'JUS MELON', 11000, 16);

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
(16, 'JUS'),
(1, 'MAKANAN'),
(2, 'MINUMAN'),
(17, 'SNACK');

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
-- Table structure for table `pelayan`
--

CREATE TABLE IF NOT EXISTS `pelayan` (
`pelayan_id` int(11) NOT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `panggilan` varchar(100) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pelayan`
--

INSERT INTO `pelayan` (`pelayan_id`, `nama`, `panggilan`) VALUES
(2, 'MUHAMMAD HANIF BUDIARTO', 'HOLONG'),
(3, 'GGG', 'GGG');

--
-- Triggers `pelayan`
--
DELIMITER //
CREATE TRIGGER `on_insert_pelayan` BEFORE INSERT ON `pelayan`
 FOR EACH ROW BEGIN
    
    SET NEW.nama = UPPER(NEW.nama);
    SET NEW.panggilan = UPPER(NEW.panggilan);

END
//
DELIMITER ;
DELIMITER //
CREATE TRIGGER `on_update_pelayan` BEFORE UPDATE ON `pelayan`
 FOR EACH ROW BEGIN
    
    SET NEW.nama = UPPER(NEW.nama);
    SET NEW.panggilan = UPPER(NEW.panggilan);

END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `pembelian`
--

CREATE TABLE IF NOT EXISTS `pembelian` (
`pembelian_id` int(11) NOT NULL,
  `pembelian_tanggal` date DEFAULT NULL,
  `total` bigint(20) DEFAULT '0',
  `operator` varchar(20) DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pembelian`
--

INSERT INTO `pembelian` (`pembelian_id`, `pembelian_tanggal`, `total`, `operator`, `timestamp`) VALUES
(1, '2015-10-28', 257500, 'HANIF', '2015-10-28 04:18:35'),
(2, '2015-10-26', 1280000, 'HANIF', '2015-10-28 04:19:25'),
(3, '2015-10-28', 340000, 'HANIF', '2015-10-28 08:46:21');

--
-- Triggers `pembelian`
--
DELIMITER //
CREATE TRIGGER `on_insert_pembelian` BEFORE INSERT ON `pembelian`
 FOR EACH ROW BEGIN

	SET NEW.timestamp = NOW();
    SET NEW.operator = UPPER(NEW.operator);

END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `pembelian_detail`
--

CREATE TABLE IF NOT EXISTS `pembelian_detail` (
`pembelian_detail_id` int(11) NOT NULL,
  `pembelian_id` int(11) DEFAULT NULL,
  `nama_barang` varchar(100) DEFAULT NULL,
  `satuan` varchar(20) DEFAULT NULL,
  `harga` bigint(20) DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pembelian_detail`
--

INSERT INTO `pembelian_detail` (`pembelian_detail_id`, `pembelian_id`, `nama_barang`, `satuan`, `harga`, `jumlah`) VALUES
(1, 1, 'TEPUNG TERIGU', 'KARUNG', 11500, 5),
(2, 1, 'TELOR', 'BIJI', 2000, 100),
(3, 2, 'MENTEGA', 'PIECE', 15000, 20),
(4, 2, 'ROTI', 'KARDUS', 98000, 10),
(5, 3, 'TELOR', 'KILO', 50000, 3),
(6, 3, 'BAWANG MERAH', 'KILO', 10000, 10),
(7, 3, 'BAWANG PUTIH', 'KILO', 9000, 10);

--
-- Triggers `pembelian_detail`
--
DELIMITER //
CREATE TRIGGER `on_insert_pembeliandet` BEFORE INSERT ON `pembelian_detail`
 FOR EACH ROW BEGIN

	SET NEW.nama_barang = UPPER(NEW.nama_barang);
    SET NEW.satuan = UPPER(NEW.satuan);

END
//
DELIMITER ;

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`pengguna_id`, `nama`, `username`, `password`, `kategori`) VALUES
(11, 'MUHAMMAD HANIF BUDIARTO', 'HANIF', 'BUDIARTO', 'KASIR'),
(12, 'ADMIN', 'ADMIN', 'ADMIN', 'ADMIN'),
(13, 'REZA DWI PUTRA', 'REZA', 'REZA', 'KASIR');

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
  `penjualan_tanggal` date DEFAULT NULL,
  `total` bigint(20) DEFAULT '0',
  `operator` varchar(20) DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  `ispaid` tinyint(1) DEFAULT '0',
  `meja` int(11) DEFAULT NULL,
  `catatan` text,
  `dibayar` bigint(20) DEFAULT NULL,
  `kembalian` bigint(20) DEFAULT NULL,
  `pelayan` varchar(100) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `penjualan`
--

INSERT INTO `penjualan` (`penjualan_id`, `penjualan_tanggal`, `total`, `operator`, `timestamp`, `ispaid`, `meja`, `catatan`, `dibayar`, `kembalian`, `pelayan`) VALUES
(1, '2015-10-28', 83000, 'hanif', '2015-10-28 06:53:46', 1, 1, 'nasi uduknya yang agak kuning yaa, itumah nasi kuning', 0, NULL, NULL),
(2, '2015-10-26', 89000, 'hanif', '2015-10-28 06:54:25', 1, 2, 'catatan kaki.', 0, NULL, NULL),
(3, '2015-10-26', 0, NULL, '2015-10-28 07:13:06', 0, 2, '', 0, NULL, NULL),
(4, '2015-10-26', 83000, 'hanif', '2015-10-28 07:13:58', 1, 1, '', 0, NULL, NULL),
(5, '2015-10-28', 83000, 'hanif', '2015-10-28 08:47:45', 1, 1, 'Jus melon tidak pakai gula', 0, NULL, NULL),
(6, '2015-11-05', 95000, 'hanif', '2015-11-05 03:34:54', 1, 33, 'teh ', 100000, 5000, NULL),
(7, '2015-12-28', 15000, 'hanif', '2015-12-28 02:32:08', 1, 22, 'sdsd', 20000, 5000, NULL),
(8, '2015-12-28', 15000, 'hanif', '2015-12-28 03:07:48', 1, 22, 'dfd', 20000, 5000, NULL),
(9, '2015-12-28', 66000, 'hanif', '2015-12-28 03:19:12', 1, 11, '', 70000, 4000, NULL),
(10, '2015-12-28', 95000, 'hanif', '2015-12-28 03:31:19', 1, 33, 'ss', 100000, 5000, NULL),
(11, '2015-12-28', 105500, 'hanif', '2015-12-28 03:32:30', 1, 3, 'dd', 106000, 500, NULL),
(12, '2015-12-28', 105500, 'hanif', '2015-12-28 03:33:35', 1, 4, '', 110000, 4500, NULL),
(13, '2015-12-28', 105500, 'hanif', '2015-12-28 03:34:42', 1, 4, '', 110000, 4500, NULL),
(14, '2015-12-28', 105500, 'hanif', '2015-12-28 03:35:39', 1, 4, '', 110000, 4500, NULL),
(15, '2015-12-28', 105500, 'hanif', '2015-12-28 03:41:04', 1, 3, '', 106000, 500, NULL),
(16, '2015-12-28', 105500, 'hanif', '2015-12-28 03:44:15', 1, 3, '', 106000, 500, NULL),
(17, '2015-12-28', 105500, 'hanif', '2015-12-28 03:56:34', 1, 4, '', 110000, 4500, NULL),
(18, '2015-12-28', 105500, 'hanif', '2015-12-28 03:58:44', 1, 4, '', 110000, 4500, NULL),
(19, '2015-12-28', 105500, 'hanif', '2015-12-28 04:03:07', 1, 4, '', 110000, 4500, NULL),
(20, '2015-12-28', 105500, 'hanif', '2015-12-28 04:05:03', 1, 3, '', 106000, 500, NULL),
(21, '2015-12-28', 105500, 'hanif', '2015-12-28 04:05:43', 1, 4, '', 110000, 4500, NULL),
(22, '2015-12-28', 0, NULL, '2015-12-28 08:14:05', 0, 44, 'dfdf', NULL, NULL, 'HOLONG'),
(23, '2015-12-28', 0, NULL, '2015-12-28 08:16:41', 0, 1, 'dfd', NULL, NULL, 'HOLONG'),
(24, '2015-12-28', 105500, 'hanif', '2015-12-28 08:18:19', 1, 4, 'ddff', 110000, 4500, 'HOLONG'),
(25, '2015-12-28', 105500, 'hanif', '2015-12-28 08:41:54', 1, 4, 'fff', 110000, 4500, 'GGG'),
(26, '2015-12-29', 105500, 'hanif', '2015-12-29 03:34:42', 1, 3, 'ff', 106000, 500, 'HOLONG'),
(27, '2015-12-29', 105500, 'hanif', '2015-12-29 04:25:31', 1, 3, '', 106000, 500, 'HOLONG'),
(28, '2015-12-29', 105500, 'hanif', '2015-12-29 04:29:21', 1, 4, '', 110000, 4500, 'HOLONG'),
(29, '2015-12-29', 105500, 'hanif', '2015-12-29 05:56:14', 1, 4, '', 110000, 4500, 'HOLONG'),
(30, '2015-12-29', 105500, 'hanif', '2015-12-29 05:58:35', 1, 4, '', 110000, 4500, 'HOLONG');

--
-- Triggers `penjualan`
--
DELIMITER //
CREATE TRIGGER `on_insert_penjualan` BEFORE INSERT ON `penjualan`
 FOR EACH ROW BEGIN
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
  `menu` varchar(100) DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `penjualan_detail`
--

INSERT INTO `penjualan_detail` (`penjualan_detail_id`, `penjualan_id`, `menu`, `jumlah`) VALUES
(1, 1, 'CAKE ES KRIM', 4),
(2, 1, 'NASI BASI', 5),
(3, 1, 'NASI MERAH', 2),
(4, 1, 'JUS TOMAT', 6),
(5, 1, 'NASI UDUK', 1),
(6, 2, 'NASI UDUK', 4),
(7, 2, 'NASI BASI', 5),
(8, 2, 'PISANG RAJA', 3),
(9, 2, 'CAKE ES KRIM', 4),
(10, 3, 'NASI MERAH', 5),
(11, 3, 'PISANG RAJA', 3),
(12, 4, 'NASI KUNING', 4),
(13, 4, 'NASI UDUK', 3),
(14, 5, 'ES TEH MANIS', 3),
(15, 5, 'MIE AYAM JAKARTA', 2),
(16, 5, 'NASI GORENG SPECIAL', 1),
(17, 5, 'JUS MELON', 1),
(18, 6, 'ES TEH MANIS', 2),
(19, 7, 'NASI GORENG SPECIAL', 3),
(20, 7, 'KENTANG GORENG', 3),
(21, 8, 'ES TEH MANIS', 3),
(22, 9, 'ES TEH MANIS', 3),
(23, 9, 'NASI GORENG SPECIAL', 3),
(24, 10, 'ES TEH MANIS', 3),
(25, 10, 'MIE AYAM JAKARTA', 4),
(26, 11, 'ES TEH MANIS', 3),
(27, 12, 'ES TEH MANIS', 3),
(28, 13, 'ES TEH MANIS', 4),
(29, 14, 'ES TEH MANIS', 4),
(30, 15, 'ES TEH MANIS', 3),
(31, 16, 'ES TEH MANIS', 4),
(32, 17, 'ES TEH MANIS', 3),
(33, 18, 'ES TEH MANIS', 4),
(34, 19, 'ES TEH MANIS', 4),
(35, 20, 'ES TEH MANIS', 4),
(36, 21, 'ES TEH MANIS', 3),
(37, 22, 'ES TEH MANIS', 3),
(38, 22, 'MIE AYAM JAKARTA', 4),
(39, 22, 'NASI GORENG SPECIAL', 2),
(40, 23, 'ES TEH MANIS', 4),
(41, 24, 'ES TEH MANIS', 5),
(42, 25, 'ES TEH MANIS', 4),
(43, 25, 'MIE AYAM JAKARTA', 3),
(44, 26, 'ES TEH MANIS', 3),
(45, 27, 'ES TEH MANIS', 3),
(46, 27, 'MIE AYAM JAKARTA', 4),
(47, 28, 'ES TEH MANIS', 5),
(48, 28, 'MIE AYAM JAKARTA', 3),
(49, 29, 'ES TEH MANIS', 3),
(50, 29, 'KENTANG GORENG', 1),
(51, 29, 'KENTANG GORENG', 5),
(52, 30, 'ES TEH MANIS', 3),
(53, 30, 'MIE AYAM JAKARTA', 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `charges`
--
ALTER TABLE `charges`
 ADD PRIMARY KEY (`charges_id`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
 ADD PRIMARY KEY (`menu_id`), ADD UNIQUE KEY `nama` (`nama`), ADD KEY `kategori_id` (`kategori_id`);

--
-- Indexes for table `menu_kategori`
--
ALTER TABLE `menu_kategori`
 ADD PRIMARY KEY (`kategori_id`), ADD UNIQUE KEY `nama` (`nama`);

--
-- Indexes for table `pelayan`
--
ALTER TABLE `pelayan`
 ADD PRIMARY KEY (`pelayan_id`);

--
-- Indexes for table `pembelian`
--
ALTER TABLE `pembelian`
 ADD PRIMARY KEY (`pembelian_id`), ADD KEY `pengguna_id` (`operator`);

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
 ADD PRIMARY KEY (`penjualan_id`), ADD KEY `pengguna_id` (`operator`), ADD KEY `penjualan_id` (`penjualan_id`), ADD KEY `penjualan_id_2` (`penjualan_id`), ADD KEY `penjualan_id_3` (`penjualan_id`), ADD KEY `penjualan_id_4` (`penjualan_id`), ADD KEY `penjualan_id_5` (`penjualan_id`);

--
-- Indexes for table `penjualan_detail`
--
ALTER TABLE `penjualan_detail`
 ADD PRIMARY KEY (`penjualan_detail_id`), ADD KEY `penjualan_id` (`penjualan_id`), ADD KEY `menu_id` (`menu`), ADD KEY `penjualan_id_2` (`penjualan_id`), ADD KEY `penjualan_id_3` (`penjualan_id`), ADD KEY `penjualan_id_4` (`penjualan_id`), ADD KEY `penjualan_id_5` (`penjualan_id`), ADD KEY `penjualan_id_6` (`penjualan_id`), ADD KEY `penjualan_id_7` (`penjualan_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `charges`
--
ALTER TABLE `charges`
MODIFY `charges_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `menu`
--
ALTER TABLE `menu`
MODIFY `menu_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `menu_kategori`
--
ALTER TABLE `menu_kategori`
MODIFY `kategori_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `pelayan`
--
ALTER TABLE `pelayan`
MODIFY `pelayan_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `pembelian`
--
ALTER TABLE `pembelian`
MODIFY `pembelian_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `pembelian_detail`
--
ALTER TABLE `pembelian_detail`
MODIFY `pembelian_detail_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `pengguna`
--
ALTER TABLE `pengguna`
MODIFY `pengguna_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `penjualan`
--
ALTER TABLE `penjualan`
MODIFY `penjualan_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=31;
--
-- AUTO_INCREMENT for table `penjualan_detail`
--
ALTER TABLE `penjualan_detail`
MODIFY `penjualan_detail_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=54;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
