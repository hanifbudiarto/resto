-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 29, 2016 at 03:58 PM
-- Server version: 5.6.26
-- PHP Version: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

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
(1, 'PAJAK', '0.1'),
(2, 'SERVIS', '970');

--
-- Triggers `charges`
--
DELIMITER $$
CREATE TRIGGER `uppercase_oninsert_charges` BEFORE INSERT ON `charges`
 FOR EACH ROW BEGIN
    
    SET NEW.nama = UPPER(NEW.nama);

END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `uppercase_onupdate_charges` BEFORE UPDATE ON `charges`
 FOR EACH ROW BEGIN
    
    SET NEW.nama = UPPER(NEW.nama);

END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE IF NOT EXISTS `menu` (
  `menu_id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `harga` bigint(20) NOT NULL,
  `kategori_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`menu_id`, `nama`, `harga`, `kategori_id`) VALUES
(1, 'AIR MINERAL', 5000, 14),
(2, 'AYAM ASAM MANIS', 0, 1),
(3, 'AYAM GORENG 1/EKOR G/B', 55000, 1),
(4, 'AYAM GORENG/BAKAR', 12500, 1),
(5, 'AYAM MENTEGA', 0, 1),
(6, 'AYAM SAOS PADANG', 0, 1),
(7, 'AYAM SAOS TIRAM', 0, 1),
(8, 'BIHUN GORENG ATI AMPELA', 15000, 2),
(9, 'BIHUN GORENG AYAM', 12000, 2),
(10, 'BIHUN GORENG SEAFOOD', 15000, 2),
(11, 'BIHUN GORENG SPESIAL', 12000, 2),
(12, 'BROCOLI AYAM', 20000, 3),
(13, 'BROCOLI CAH BAWANG PUTIH', 15000, 3),
(14, 'BROCOLI SEAFOOD', 25000, 3),
(15, 'BUAVITA', 0, 6),
(16, 'CAP CAY GORENG AYAM', 15000, 4),
(17, 'CAP CAY GORENG SEAFOOD', 25000, 4),
(18, 'CAP CAY KUAH AYAM', 16000, 4),
(19, 'CAP CAY KUAH SEAFOOD', 20000, 4),
(20, 'COCA-COLA/SPRITE/FANTA', 5000, 14),
(21, 'CORNETTO', 0, 6),
(22, 'CUMI ASEM MANIS', 25000, 5),
(23, 'CUMI BAKAR', 23000, 5),
(24, 'CUMI GORENG MENTEGA', 25000, 5),
(25, 'CUMI GORENG TEPUNG', 23000, 5),
(26, 'CUMI LADA HITAM', 25000, 5),
(27, 'CUMI SAOS PADANG', 25000, 5),
(28, 'CUMI SAOS TIRAM', 25000, 5),
(29, 'DUNG-DUNG', 0, 6),
(30, 'ES BUAH', 15000, 14),
(31, 'ES CAPPUCINO', 8000, 14),
(32, 'ES CAPPUCINO CINCAU', 12500, 14),
(33, 'ES TEH MANIS', 4000, 14),
(34, 'ES TEH TAWAR', 3000, 14),
(35, 'FIST', 0, 6),
(36, 'FUYUNG HAY AYAM', 18000, 7),
(37, 'FUYUNG HAY BIASA', 15000, 7),
(38, 'FUYUNG HAY UDANG', 20000, 7),
(39, 'HOT CAPPUCINO', 8000, 14),
(40, 'IKAN AYAM-AYAM BAKAR', 7000, 8),
(41, 'IKAN BARONANG BAKAR', 10000, 8),
(42, 'IKAN BAWAL BAKAR', 10000, 8),
(43, 'IKAN GURAME ASAM MANIS', 13000, 8),
(44, 'IKAN GURAME ASAM MANIS FILLET', 13000, 8),
(45, 'IKAN GURAME BAKAR/GORENG', 12000, 8),
(46, 'IKAN GURAME PECAK', 15000, 8),
(47, 'IKAN GURAME RICA-RICA', 13000, 8),
(48, 'IKAN GURAME SAOS PADANG', 13000, 8),
(49, 'IKAN GURAME SAOS TIRAM', 13000, 8),
(50, 'IKAN KAKAP ASAM MANIS FILLET', 13000, 8),
(51, 'IKAN KAKAP BAKAR', 10000, 8),
(52, 'IKAN KUWE BAKAR', 10000, 8),
(53, 'JERUK PANAS/PERAS', 12000, 14),
(54, 'JUS ALPUKAT', 12500, 9),
(55, 'JUS BELIMBING', 12500, 9),
(56, 'JUS BUAH NAGA', 0, 9),
(57, 'JUS JAMBU', 12500, 9),
(58, 'JUS JERUK/PANAS/NIPIS/LEMON TEA', 12500, 9),
(59, 'JUS MANGGA', 12500, 9),
(60, 'JUS MELON', 15000, 9),
(61, 'JUS NANAS', 0, 9),
(62, 'JUS TIMUN', 0, 9),
(63, 'JUS TOMAT', 12500, 9),
(64, 'JUS WORTEL', 12000, 9),
(65, 'KANGKUNG CAH POLOS', 12000, 22),
(66, 'KANGKUNG CAH SEAFOOD', 18000, 22),
(67, 'KANGKUNG CAH TERASI', 12000, 22),
(68, 'KANGKUNG TAUCO', 12000, 22),
(69, 'KELAPA GELAS', 10000, 14),
(70, 'KELAPA JERUK', 17000, 14),
(71, 'KELAPA MUDA BESAR', 15000, 14),
(72, 'KERANG HIJAU ASAM MANIS', 20000, 10),
(73, 'KERANG HIJAU GORENG MENTEGA', 20000, 10),
(74, 'KERANG HIJAU REBUS', 12000, 10),
(75, 'KERANG HIJAU SAOS PADANG', 17000, 10),
(76, 'KERANG HIJAU SAOS TAUCO', 17000, 10),
(77, 'KERANG HIJAU SAUS TIRAM', 17000, 10),
(78, 'KOPI HITAM', 7000, 14),
(79, 'KOPI SUSU', 0, 14),
(80, 'LALAPAN SAYUR', 5000, 22),
(81, 'LELE ASAM MANIS', 0, 12),
(82, 'LELE GORENG TEPUNG', 0, 12),
(83, 'LELE MENTEGA', 0, 12),
(84, 'LELE SAOS TIRAM', 0, 12),
(85, 'MAGNUM', 0, 6),
(86, 'MIE GORENG ATI AMPELA', 15000, 13),
(87, 'MIE GORENG AYAM', 12000, 13),
(88, 'MIE GORENG SEAFOOD', 20000, 13),
(89, 'NASI BAKUL BESAR', 20000, 15),
(90, 'NASI BAKUL KECIL', 10000, 15),
(91, 'NASI BAKUL SEDANG', 15000, 15),
(92, 'NASI GORENG ATI AMPELA', 15000, 16),
(93, 'NASI GORENG BIASA', 12500, 16),
(94, 'NASI GORENG SEAFOOD', 20000, 16),
(95, 'NASI GORENG SPESIAL', 15000, 16),
(96, 'NASI PORSI', 5000, 15),
(97, 'PADLE POP', 0, 6),
(98, 'PAKET AYAM ASAM MANIS', 20000, 17),
(99, 'PAKET AYAM B/G', 18000, 17),
(100, 'PAKET AYAM KREMES', 18000, 17),
(101, 'PAKET AYAM LADA HITAM', 22000, 17),
(102, 'PAKET AYAM PECAK', 0, 17),
(103, 'PAKET AYAM RICA-RICA', 22000, 17),
(104, 'PAKET AYAM SAOS PADANG', 20000, 17),
(105, 'PAKET AYAM SERUNDENG', 18000, 17),
(106, 'PAKET AYAM TIRAM', 20000, 17),
(107, 'PAKET EKOR AYAM', 0, 17),
(108, 'PAKET IKAN AYAM-AYAM', 100000, 18),
(109, 'PAKET IKAN GURAME', 120000, 18),
(110, 'PAKET LELE ASAM MANIS', 18000, 19),
(111, 'PAKET LELE B/G', 0, 19),
(112, 'PAKET LELE LADA HITAM', 20000, 19),
(113, 'PAKET LELE PECAK', 18000, 19),
(114, 'PAKET LELE RICA-RICA', 18000, 19),
(115, 'PAKET LELE SAOS PADANG', 18000, 19),
(116, 'PAKET LELE SAOS TIRAM', 18000, 19),
(117, 'POPULARE', 0, 6),
(118, 'ROTI BAKAR A.C.K', 15000, 20),
(119, 'ROTI BAKAR ANGGUR', 10000, 20),
(120, 'ROTI BAKAR BLUEBERRY', 10000, 20),
(121, 'ROTI BAKAR C.N.K', 15000, 20),
(122, 'ROTI BAKAR C.P.K', 15000, 20),
(123, 'ROTI BAKAR COKLAT', 10000, 20),
(124, 'ROTI BAKAR K.C.P', 15000, 20),
(125, 'ROTI BAKAR K.N.C', 15000, 20),
(126, 'ROTI BAKAR KACANG', 10000, 20),
(127, 'ROTI BAKAR NANAS', 10000, 20),
(128, 'ROTI BAKAR PIS AG', 12000, 20),
(129, 'ROTI BAKAR PIS KA', 12000, 20),
(130, 'ROTI BAKAR PISANG', 10000, 20),
(131, 'ROTI BAKAR PISKE', 12000, 20),
(132, 'ROTI BAKAR PISNA', 12000, 20),
(133, 'ROTI BAKAR PISTRAW', 12000, 20),
(134, 'ROTI BAKAR STRAWBERRY', 10000, 20),
(135, 'SAMBEL DADAKAN', 5000, 21),
(136, 'SAYUR ASEM', 5000, 22),
(137, 'SHAKI-SHAKE', 0, 6),
(138, 'SODA SUSU', 8000, 14),
(139, 'TEH BOTOL SOSRO', 4000, 14),
(140, 'TEH KOTAK', 0, 14),
(141, 'TEH MANIS HANGAT', 3000, 14),
(142, 'TUBE BESAR (FRUITEA/PULPY)', 0, 14),
(143, 'TUBE MINI', 0, 14),
(144, 'UDANG ASAM MANIS', 25000, 23),
(145, 'UDANG BAKAR/REBUS', 30000, 23),
(146, 'UDANG CAH CABE PETE', 25000, 23),
(147, 'UDANG GORENG MAYONES', 25000, 23),
(148, 'UDANG GORENG MENTEGA', 25000, 23),
(149, 'UDANG GORENG TEPUNG SUPER', 30000, 23),
(150, 'UDANG SAOS PADANG', 25000, 23),
(151, 'UDANG SAOS TIRAM', 25000, 23),
(152, 'UDANG TELOR ASIN', 25000, 23),
(153, 'OTAK-OTAK', 4000, 11),
(154, 'BASO', 12500, 11),
(155, 'SIOMAY', 2500, 11),
(156, 'SIOMAY TELOR', 3500, 11),
(157, 'KENTANG GORENG', 11000, 11),
(158, 'TAHU-TEMPE GORENG', 5000, 11),
(159, 'PETE BAKAR/GORENG', 8000, 11);

--
-- Triggers `menu`
--
DELIMITER $$
CREATE TRIGGER `uppercase_insert_menu` BEFORE INSERT ON `menu`
 FOR EACH ROW BEGIN
    
    SET NEW.nama = UPPER(NEW.nama);

END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `uppercase_update_menu` BEFORE UPDATE ON `menu`
 FOR EACH ROW BEGIN
    
    SET NEW.nama = UPPER(NEW.nama);

END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `menu_kategori`
--

CREATE TABLE IF NOT EXISTS `menu_kategori` (
  `kategori_id` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu_kategori`
--

INSERT INTO `menu_kategori` (`kategori_id`, `nama`) VALUES
(1, 'AYAM'),
(2, 'BIHUN GORENG'),
(3, 'BROCOLI'),
(4, 'CAP CAY'),
(5, 'CUMI'),
(6, 'ES KRIM'),
(7, 'FUYUNG HAY'),
(8, 'IKAN'),
(9, 'JUS'),
(10, 'KERANG HIJAU'),
(11, 'LAIN-LAIN'),
(12, 'LELE'),
(13, 'MIE GORENG'),
(14, 'MINUMAN'),
(15, 'NASI'),
(16, 'NASI GORENG'),
(17, 'PAKET AYAM'),
(18, 'PAKET IKAN'),
(19, 'PAKET LELE'),
(20, 'ROTI BAKAR'),
(21, 'SAMBEL'),
(22, 'SAYUR'),
(23, 'UDANG');

--
-- Triggers `menu_kategori`
--
DELIMITER $$
CREATE TRIGGER `uppercase_insert_kategori` BEFORE INSERT ON `menu_kategori`
 FOR EACH ROW BEGIN
    
    SET NEW.nama = UPPER(NEW.nama);

END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `uppercase_update_kategori` BEFORE UPDATE ON `menu_kategori`
 FOR EACH ROW BEGIN
    
    SET NEW.nama = UPPER(NEW.nama);

END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `pelayan`
--

CREATE TABLE IF NOT EXISTS `pelayan` (
  `pelayan_id` int(11) NOT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `panggilan` varchar(100) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pelayan`
--

INSERT INTO `pelayan` (`pelayan_id`, `nama`, `panggilan`) VALUES
(2, 'MUHAMMAD HANIF BUDIARTO', 'HANIF'),
(3, 'REZA DWI PUTRA', 'ECHA'),
(4, 'IBRAHIM OSWALDO', 'IBAM');

--
-- Triggers `pelayan`
--
DELIMITER $$
CREATE TRIGGER `on_insert_pelayan` BEFORE INSERT ON `pelayan`
 FOR EACH ROW BEGIN
    
    SET NEW.nama = UPPER(NEW.nama);
    SET NEW.panggilan = UPPER(NEW.panggilan);

END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `on_update_pelayan` BEFORE UPDATE ON `pelayan`
 FOR EACH ROW BEGIN
    
    SET NEW.nama = UPPER(NEW.nama);
    SET NEW.panggilan = UPPER(NEW.panggilan);

END
$$
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Triggers `pembelian`
--
DELIMITER $$
CREATE TRIGGER `on_insert_pembelian` BEFORE INSERT ON `pembelian`
 FOR EACH ROW BEGIN

	SET NEW.timestamp = NOW();
    SET NEW.operator = UPPER(NEW.operator);

END
$$
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Triggers `pembelian_detail`
--
DELIMITER $$
CREATE TRIGGER `on_insert_pembeliandet` BEFORE INSERT ON `pembelian_detail`
 FOR EACH ROW BEGIN

	SET NEW.nama_barang = UPPER(NEW.nama_barang);
    SET NEW.satuan = UPPER(NEW.satuan);

END
$$
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`pengguna_id`, `nama`, `username`, `password`, `kategori`) VALUES
(12, 'ADMIN', 'ADMINISTRATOR', 'BAMBURESTO2016', 'ADMIN'),
(15, 'KASIR', 'KASIR01', 'KASIR01', 'KASIR');

--
-- Triggers `pengguna`
--
DELIMITER $$
CREATE TRIGGER `uppercase_insert_pengguna` BEFORE INSERT ON `pengguna`
 FOR EACH ROW BEGIN
    
    SET NEW.nama = UPPER(NEW.nama);
    SET NEW.username = UPPER(NEW.username);
    SET NEW.password = UPPER(NEW.password);
    SET NEW.kategori = UPPER(NEW.kategori);

END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `uppercase_update_pengguna` BEFORE UPDATE ON `pengguna`
 FOR EACH ROW BEGIN
    
    SET NEW.nama = UPPER(NEW.nama);
    SET NEW.username = UPPER(NEW.username);
    SET NEW.password = UPPER(NEW.password);
    SET NEW.kategori = UPPER(NEW.kategori);

END
$$
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Triggers `penjualan`
--
DELIMITER $$
CREATE TRIGGER `on_insert_penjualan` BEFORE INSERT ON `penjualan`
 FOR EACH ROW BEGIN
	SET NEW.timestamp = NOW();
END
$$
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  ADD PRIMARY KEY (`menu_id`),
  ADD UNIQUE KEY `nama` (`nama`),
  ADD KEY `kategori_id` (`kategori_id`);

--
-- Indexes for table `menu_kategori`
--
ALTER TABLE `menu_kategori`
  ADD PRIMARY KEY (`kategori_id`),
  ADD UNIQUE KEY `nama` (`nama`);

--
-- Indexes for table `pelayan`
--
ALTER TABLE `pelayan`
  ADD PRIMARY KEY (`pelayan_id`);

--
-- Indexes for table `pembelian`
--
ALTER TABLE `pembelian`
  ADD PRIMARY KEY (`pembelian_id`),
  ADD KEY `pengguna_id` (`operator`);

--
-- Indexes for table `pembelian_detail`
--
ALTER TABLE `pembelian_detail`
  ADD PRIMARY KEY (`pembelian_detail_id`),
  ADD KEY `pembelian_id` (`pembelian_id`);

--
-- Indexes for table `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`pengguna_id`);

--
-- Indexes for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`penjualan_id`),
  ADD KEY `pengguna_id` (`operator`),
  ADD KEY `penjualan_id` (`penjualan_id`),
  ADD KEY `penjualan_id_2` (`penjualan_id`),
  ADD KEY `penjualan_id_3` (`penjualan_id`),
  ADD KEY `penjualan_id_4` (`penjualan_id`),
  ADD KEY `penjualan_id_5` (`penjualan_id`);

--
-- Indexes for table `penjualan_detail`
--
ALTER TABLE `penjualan_detail`
  ADD PRIMARY KEY (`penjualan_detail_id`),
  ADD KEY `penjualan_id` (`penjualan_id`),
  ADD KEY `menu_id` (`menu`),
  ADD KEY `penjualan_id_2` (`penjualan_id`),
  ADD KEY `penjualan_id_3` (`penjualan_id`),
  ADD KEY `penjualan_id_4` (`penjualan_id`),
  ADD KEY `penjualan_id_5` (`penjualan_id`),
  ADD KEY `penjualan_id_6` (`penjualan_id`),
  ADD KEY `penjualan_id_7` (`penjualan_id`);

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
  MODIFY `menu_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=160;
--
-- AUTO_INCREMENT for table `menu_kategori`
--
ALTER TABLE `menu_kategori`
  MODIFY `kategori_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT for table `pelayan`
--
ALTER TABLE `pelayan`
  MODIFY `pelayan_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
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
  MODIFY `pengguna_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `penjualan`
--
ALTER TABLE `penjualan`
  MODIFY `penjualan_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `penjualan_detail`
--
ALTER TABLE `penjualan_detail`
  MODIFY `penjualan_detail_id` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
