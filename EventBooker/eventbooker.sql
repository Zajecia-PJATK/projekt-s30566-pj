-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Czas generowania: 21 Cze 2024, 18:56
-- Wersja serwera: 8.0.31-23
-- Wersja PHP: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `wflek_eventbooker`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Events`
--

CREATE TABLE `Events` (
  `event_id` int NOT NULL,
  `ticket_price` double NOT NULL,
  `organizer_id` int NOT NULL,
  `location_id` int NOT NULL,
  `venue_id` int NOT NULL,
  `event_name` varchar(255) NOT NULL,
  `seat_number` int NOT NULL,
  `scheduled_date` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL,
  `event_type` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Zrzut danych tabeli `Events`
--

INSERT INTO `Events` (`event_id`, `ticket_price`, `organizer_id`, `location_id`, `venue_id`, `event_name`, `seat_number`, `scheduled_date`, `status`, `event_type`) VALUES
(2, 33, 1, 2, 3, 'Testowy Event', 321, '2024-06-18 12:34:56', 'SCHEDULED', 'CONCERT'),
(4, 33, 1, 2, 3, 'Testowy Event', 321, '2024-06-18 12:34:56', 'SCHEDULED', 'CONCERT'),
(5, 123.79, 9, 2, 1, 'Imprezka', 50, '2024-11-12 12:00:00', 'SCHEDULED', 'FESTIVAL'),
(6, 0, 9, 2, 1, 'Nowe Perspektywy Rozwoju', 50, '2025-03-01 12:00:00', 'SCHEDULED', 'WEBINAR');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Locations`
--

CREATE TABLE `Locations` (
  `location_id` int NOT NULL,
  `location_name` varchar(255) NOT NULL,
  `city` varchar(100) NOT NULL,
  `address` varchar(255) NOT NULL,
  `postal_code` varchar(20) NOT NULL,
  `country` varchar(100) NOT NULL,
  `user_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Zrzut danych tabeli `Locations`
--

INSERT INTO `Locations` (`location_id`, `location_name`, `city`, `address`, `postal_code`, `country`, `user_id`) VALUES
(2, 'Gosciniec nad wisla', 'Tczew', 'Taka i Tamta 12A/9', '83-110', 'POLAND', 9),
(3, '0', '0', '0', '0', 'AUSTRIA', 9),
(4, 'Internet', 'Internet', 'Internet', '', 'POLAND', 9);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Tickets`
--

CREATE TABLE `Tickets` (
  `ticket_id` int NOT NULL,
  `event_id` int NOT NULL,
  `user_id` int NOT NULL,
  `seat_number` int NOT NULL,
  `event_name` varchar(255) NOT NULL,
  `purchase_date` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `ticket_price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Users`
--

CREATE TABLE `Users` (
  `user_id` int NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `permission_level` varchar(255) NOT NULL DEFAULT 'CUSTOMER'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Zrzut danych tabeli `Users`
--

INSERT INTO `Users` (`user_id`, `username`, `password`, `email`, `name`, `surname`, `phone`, `permission_level`) VALUES
(6, 'root', '81dc9bdb52d04dc20036dbd8313ed055', 'admin@maknet.com.pl', 'Root', 'Root', '123456789', 'ADMIN'),
(7, 'wflek', '81dc9bdb52d04dc20036dbd8313ed055', 'daniel@maknet.com.pl', 'Daniel', 'Makowski', '123456789', 'CUSTOMER'),
(9, 'organizator', '81dc9bdb52d04dc20036dbd8313ed055', 'maczek280@gmail.com', 'Janusz', 'Kowalski', '123456789', 'ORGANISER');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Venues`
--

CREATE TABLE `Venues` (
  `venue_id` int NOT NULL,
  `venue_name` varchar(255) NOT NULL,
  `capacity` int NOT NULL,
  `location_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Zrzut danych tabeli `Venues`
--

INSERT INTO `Venues` (`venue_id`, `venue_name`, `capacity`, `location_id`) VALUES
(1, 'Sala 1', 50, 2),
(2, 'nowa sala testowa', 2137, 2),
(3, '', 0, 3),
(4, 'Teams', 2000, 4);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `Events`
--
ALTER TABLE `Events`
  ADD PRIMARY KEY (`event_id`);

--
-- Indeksy dla tabeli `Locations`
--
ALTER TABLE `Locations`
  ADD PRIMARY KEY (`location_id`);

--
-- Indeksy dla tabeli `Tickets`
--
ALTER TABLE `Tickets`
  ADD PRIMARY KEY (`ticket_id`),
  ADD KEY `event_id` (`event_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indeksy dla tabeli `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indeksy dla tabeli `Venues`
--
ALTER TABLE `Venues`
  ADD PRIMARY KEY (`venue_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `Events`
--
ALTER TABLE `Events`
  MODIFY `event_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT dla tabeli `Locations`
--
ALTER TABLE `Locations`
  MODIFY `location_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT dla tabeli `Tickets`
--
ALTER TABLE `Tickets`
  MODIFY `ticket_id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `Users`
--
ALTER TABLE `Users`
  MODIFY `user_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT dla tabeli `Venues`
--
ALTER TABLE `Venues`
  MODIFY `venue_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
