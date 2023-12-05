CREATE TABLE `band` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `specialInstruments` tinyint(1) NOT NULL
);
ALTER TABLE `band`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `band`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

-----------------------------------------------------

CREATE TABLE `manager` (
  `id` int(11) NOT NULL,
  `hourlyRate` int(11) NOT NULL,
  `name` text NOT NULL,
  `adressStreet` text NOT NULL,
  `adressNumber` int(11) NOT NULL,
  `addressZip` text NOT NULL,
  `addressCity` text NOT NULL,
  `addressCountry` text NOT NULL
);
ALTER TABLE `manager`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `manager`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

-----------------------------------------------------

CREATE TABLE `supplier` (
  `id` int(11) NOT NULL,
  `averageDeliveryTime` int(11) NOT NULL,
  `name` text NOT NULL
);
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `supplier`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

-----------------------------------------------------

CREATE TABLE `song` (
  `id` int(11) NOT NULL,
  `title` text NOT NULL,
  `year` year(4) NOT NULL,
  `duration` int(11) NOT NULL,
  `filePath` text NOT NULL,
  `bandId` int(11) NOT NULL,
  `managerId` int(11) NOT NULL,
  `supplierId` int(11) NOT NULL
);
ALTER TABLE `song`
  ADD PRIMARY KEY (`id`),
  ADD KEY `bandId` (`bandId`),
  ADD KEY `managerId` (`managerId`),
  ADD KEY `supplierId` (`supplierId`);
ALTER TABLE `song`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
-- Relations
ALTER TABLE `song`
  ADD CONSTRAINT `song_ibfk_1` FOREIGN KEY (`bandId`) REFERENCES `band` (`id`),
  ADD CONSTRAINT `song_ibfk_2` FOREIGN KEY (`managerId`) REFERENCES `manager` (`id`),
  ADD CONSTRAINT `song_ibfk_3` FOREIGN KEY (`supplierId`) REFERENCES `supplier` (`id`);
COMMIT;

-----------------------------------------------------

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` text NOT NULL,
  `password` text NOT NULL
);
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
