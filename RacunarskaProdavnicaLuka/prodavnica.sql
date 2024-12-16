-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 16, 2024 at 03:47 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `prodavnica`
--

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE `korisnik` (
  `korisnik_id` int(11) NOT NULL,
  `ime_i_prezime` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `e_mail` varchar(45) DEFAULT NULL,
  `datum_rodjenja` varchar(45) DEFAULT NULL,
  `stanje_racuna` int(11) DEFAULT NULL,
  `kolicina_potrosenog_novca` int(11) DEFAULT NULL,
  `korisnikcol` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`korisnik_id`, `ime_i_prezime`, `username`, `password`, `e_mail`, `datum_rodjenja`, `stanje_racuna`, `kolicina_potrosenog_novca`, `korisnikcol`) VALUES
(1, 'Marko Markovic', 'markom', 'marko123', 'marko@gmail.com', '1995-06-12', 500, 15000, 'Opis korisnika 1'),
(2, 'Jovana Jovanovic', 'jovanaj', 'jovana123', 'jovana@gmail.com', '1993-03-25', 3000, 12000, 'Opis korisnika 2'),
(3, 'Petar Petrovic', 'petarp', 'petar123', 'petar@gmail.com', '1990-11-30', 3500, 20000, 'Opis korisnika 3'),
(4, 'Ana Anic', 'anaa', 'ana123', 'ana@gmail.com', '1998-09-15', 2500, 18000, 'Opis korisnika 4'),
(5, 'Milos Milosevic', 'milosm', 'milos123', 'milos@gmail.com', '1992-02-18', 4000, 10000, 'Opis korisnika 5'),
(6, 'Luka Petrovic', 'lukap', 'marko123', 'luka@gmail.com', '1995-06-12', 45500, 5500, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `kupovina`
--

CREATE TABLE `kupovina` (
  `kupovina_id` int(11) NOT NULL,
  `korisnik_id` int(11) NOT NULL,
  `proizvod_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kupovina`
--

INSERT INTO `kupovina` (`kupovina_id`, `korisnik_id`, `proizvod_id`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 4, 1),
(5, 3, 1),
(6, 1, 1),
(7, 6, 1);

-- --------------------------------------------------------

--
-- Table structure for table `podesavanje_pretrage`
--

CREATE TABLE `podesavanje_pretrage` (
  `podesavanje_pretrage_id` int(11) NOT NULL,
  `donja_granica_obima_cene` int(11) DEFAULT NULL,
  `gornja_granica_obima_cene` int(11) DEFAULT NULL,
  `vrsta_opreme` varchar(45) DEFAULT NULL,
  `kljucna_rec` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `podesavanje_pretrage`
--

INSERT INTO `podesavanje_pretrage` (`podesavanje_pretrage_id`, `donja_granica_obima_cene`, `gornja_granica_obima_cene`, `vrsta_opreme`, `kljucna_rec`) VALUES
(1, 1000, 5000, 'Laptop', 'Dell'),
(2, 2000, 10000, 'Telefon', 'Samsung'),
(3, 500, 3000, 'Monitor', 'LG');

-- --------------------------------------------------------

--
-- Table structure for table `pretraga`
--

CREATE TABLE `pretraga` (
  `pretraga_id` int(11) NOT NULL,
  `podesavanje_pretrage_id` int(11) NOT NULL,
  `korisnik_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pretraga`
--

INSERT INTO `pretraga` (`pretraga_id`, `podesavanje_pretrage_id`, `korisnik_id`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3);

-- --------------------------------------------------------

--
-- Table structure for table `proizvod`
--

CREATE TABLE `proizvod` (
  `proizvod_id` int(11) NOT NULL,
  `naziv` varchar(45) DEFAULT NULL,
  `cena` int(11) DEFAULT NULL,
  `vrsta_opreme` varchar(45) DEFAULT NULL,
  `stanje_na_lageru` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `proizvod`
--

INSERT INTO `proizvod` (`proizvod_id`, `naziv`, `cena`, `vrsta_opreme`, `stanje_na_lageru`) VALUES
(1, 'Dell Inspiron 15', 4500, 'Laptop', 7),
(2, 'Samsung Galaxy S21', 9500, 'Telefon', 5),
(3, 'LG UltraWide 29\"', 2500, 'Monitor', 12);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD PRIMARY KEY (`korisnik_id`);

--
-- Indexes for table `kupovina`
--
ALTER TABLE `kupovina`
  ADD PRIMARY KEY (`kupovina_id`),
  ADD KEY `fk_kupovina_korisnik1_idx` (`korisnik_id`),
  ADD KEY `fk_kupovina_proizvod1_idx` (`proizvod_id`);

--
-- Indexes for table `podesavanje_pretrage`
--
ALTER TABLE `podesavanje_pretrage`
  ADD PRIMARY KEY (`podesavanje_pretrage_id`);

--
-- Indexes for table `pretraga`
--
ALTER TABLE `pretraga`
  ADD PRIMARY KEY (`pretraga_id`),
  ADD KEY `fk_pretraga_podesavanje_pretrage_idx` (`podesavanje_pretrage_id`),
  ADD KEY `fk_pretraga_korisnik1_idx` (`korisnik_id`);

--
-- Indexes for table `proizvod`
--
ALTER TABLE `proizvod`
  ADD PRIMARY KEY (`proizvod_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `korisnik`
--
ALTER TABLE `korisnik`
  MODIFY `korisnik_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `kupovina`
--
ALTER TABLE `kupovina`
  MODIFY `kupovina_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `podesavanje_pretrage`
--
ALTER TABLE `podesavanje_pretrage`
  MODIFY `podesavanje_pretrage_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `proizvod`
--
ALTER TABLE `proizvod`
  MODIFY `proizvod_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `kupovina`
--
ALTER TABLE `kupovina`
  ADD CONSTRAINT `fk_kupovina_korisnik1` FOREIGN KEY (`korisnik_id`) REFERENCES `korisnik` (`korisnik_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_kupovina_proizvod1` FOREIGN KEY (`proizvod_id`) REFERENCES `proizvod` (`proizvod_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `pretraga`
--
ALTER TABLE `pretraga`
  ADD CONSTRAINT `fk_pretraga_korisnik1` FOREIGN KEY (`korisnik_id`) REFERENCES `korisnik` (`korisnik_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_pretraga_podesavanje_pretrage` FOREIGN KEY (`podesavanje_pretrage_id`) REFERENCES `podesavanje_pretrage` (`podesavanje_pretrage_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
