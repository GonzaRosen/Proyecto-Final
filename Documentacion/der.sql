-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:54679
-- Generation Time: Dec 15, 2017 at 03:11 AM
-- Server version: 5.7.9
-- PHP Version: 5.6.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `der`
--

-- --------------------------------------------------------

--
-- Table structure for table `soicitudesdecontacto`
--

CREATE TABLE `soicitudesdecontacto` (
  `IdSoicitudDeContacto` int(11) NOT NULL,
  `IdSolicitante` int(11) NOT NULL,
  `IdSolicitado` int(11) NOT NULL,
  `Fecha` datetime NOT NULL,
  `Estado` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbandas`
--

CREATE TABLE `tbandas` (
  `IdBanda` int(11) NOT NULL,
  `Nombre` text NOT NULL,
  `Descripcion` longtext,
  `UrlImagen` mediumtext,
  `Ubicacion` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbandas`
--

INSERT INTO `tbandas` (`IdBanda`, `Nombre`, `Descripcion`, `UrlImagen`, `Ubicacion`) VALUES
(1, 'Iron Maiden', 'heavy britanico', NULL, 0.4),
(2, 'Metallica', 'panqueques con dulce de leche', 'url', 0.9),
(3, 'Muse', 'sanguche de bondiola', NULL, 0.7);

-- --------------------------------------------------------

--
-- Table structure for table `tbandas_has_tgeneros`
--

CREATE TABLE `tbandas_has_tgeneros` (
  `tBandas_IdBanda` int(11) NOT NULL,
  `tGeneros_IdGenero` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbandas_has_tusuarios`
--

CREATE TABLE `tbandas_has_tusuarios` (
  `tBandas_IdBanda` int(11) NOT NULL,
  `tUsuarios_IdUsuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tinstrumentos`
--

CREATE TABLE `tinstrumentos` (
  `IdInstrumento` int(11) NOT NULL,
  `Nombre` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tinstrumentos`
--

INSERT INTO `tinstrumentos` (`IdInstrumento`, `Nombre`) VALUES
(1, 'Guitarra'),
(2, 'Bajo'),
(3, 'Piano');

-- --------------------------------------------------------

--
-- Table structure for table `tusuarios`
--

CREATE TABLE `tusuarios` (
  `IdUsuario` int(11) NOT NULL,
  `Nombre` text NOT NULL,
  `Apellido` text NOT NULL,
  `Fecha_Nacimiento` text,
  `Instrumentos` longtext NOT NULL,
  `Generos` longtext NOT NULL,
  `Influencias` longtext,
  `Email` text NOT NULL,
  `Password` text NOT NULL,
  `UrlImagen` mediumtext,
  `Descripcion` longtext,
  `Ubicacion` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tusuarios`
--

INSERT INTO `tusuarios` (`IdUsuario`, `Nombre`, `Apellido`, `Fecha_Nacimiento`, `Instrumentos`, `Generos`, `Influencias`, `Email`, `Password`, `UrlImagen`, `Descripcion`, `Ubicacion`) VALUES
(1, 'Ferminaso', 'Blum', '1999-12-07', 'Guitarra      ', 'Rock      ', 'Gonzalo      ', 'ferminblum@hotmail.com', 'fermin', NULL, 'aguante el fasoooxdxd', 'a'),
(2, 'Gonzalo', 'Rosen', '2000-03-14', 'Mandolina Flauta', 'Reggae ', 'Chano', 'gonzirosen@hotmail.com', 'lola123', NULL, 'Me gusta la Coca', 'Villa Crespo'),
(3, 'Santiago', 'Ravaglia', '2000-06-23', 'Violin', 'Cumbia', 'Sol Perez', 'santi23062000@gmail.com', 'tarta123', NULL, 'Tarta de zapallo', 'Villa Crespo'),
(4, 'Ian', 'Rossi', '2000-03-31', 'Trompeta Saxo', 'Tango Balada', 'SOAD', 'ianfrossi@gmail.com', 'ianlol', NULL, 'Islandes loco', 'Caballito'),
(7, 'Alberto', 'Dedinho', '0001-01-01', 'Triangulo electrico', 'bachata', 'Brian Sarmiento', 'albertinho@yahoo.com', 'elalbertmanda', '', '', 'San Telmo'),
(9, 'Mauro', 'Villareal', '0001-01-01', 'Trompeta', 'Bachata', 'Juan Theremin', 'mauritkpo@hotmail.com', '1234', '', 'Aguante el rojo', 'Villa Crespo'),
(10, 'Floyd', 'Mayweather', '2000-01-01', 'Guantes', 'Blues', 'Roger Mayweather', 'mcgregorloser@gmail.com', 'worldsbest', '', 'Caga a piñas a todo lo que ve', 'California'),
(11, 'Diego', 'Pertierra', '2000-12-20', 'Piano Organo', 'EDM', 'Aldo Rico', 'dpertierra@gmail.com', 'manopla22', '', 'El polizonte más gorra de toda la comisaría', 'Flores'),
(12, 'Gonzalito', 'Segundo', '0011-12-10', 'Arpa', 'Jazz ', 'Soda Estereo', 'gonzaprecioso@mailhermoso.com', 'quelindosoy', '', 'Me gusta la coca comun', 'Villa Crespo'),
(13, 'Oliver', 'Gadsdon', '2003-05-09', 'Ukelele Banyo', 'Pop Rock', 'Gonzalo Rosen', 'olivergadsdon@hotmail.com', 'perritomio', '', 'El mejor amigo de Gonza Ro', 'Caballito'),
(14, 'Leonardo', 'Blum', '1972-02-29', 'Piano Ukelele', 'Reggae Rock Ska', 'Bob Marley Gregory ', 'leoblum@hotmail.com', 'leonardo123', NULL, NULL, 'Caballito'),
(15, 'Tiago', 'Sclauzero', '1998-11-16', 'Bateria Guitarra', 'Rock Metal Punk', 'Dave Grohl Joey Jordison', 'tiago@gmail.com', 'tiagobateria', NULL, NULL, 'Caballito'),
(16, 'Gian', 'Barsellini', '1998-09-06', 'Guitarra\nBajo', 'Metal Rock Rap', 'Guthie Govan ', 'gianluca@hotmail.com', 'gianguitarra', NULL, NULL, 'Caballito'),
(17, 'Lucia', 'Blum', '2000-12-20', 'Guitarra\nPiano', 'Pop Rock Reggae', 'Jimi Hendrix Slash', 'luciabl@gmail.com', 'luciatocalaguitarra', NULL, NULL, 'Caballito'),
(18, 'Ricardo', 'Mollo', '1957-08-17', 'Guitarra Bajo', 'Rock Reggae', 'Jimi Hendrix BB King', 'mollodivididos@hotmail.com', 'divididosrock', NULL, NULL, 'Caballito'),
(19, 'Juan Carlos', 'Gutierrez', '1989-05-15', 'Bajo Piano', 'Rock Metal Punk', 'Angus Young Muse', 'juanca@gmail.com', 'juancarlosguti', NULL, NULL, 'Caballito'),
(20, 'Pedro', 'Gomez', '1985-07-23', 'Piano Voz\r\n', 'Reggae Pop', 'Pablo Lescano Fidel Nadal', 'pedriito@hotmail.com', 'pedriito54', NULL, NULL, 'Caballito'),
(21, 'Martina', 'Martinez', '1990-11-11', 'Ukelele Bajo', 'Pop EDM Ska', 'Anitta Lady Gaga', 'martumart@hotmail.com', 'martumartinez85', NULL, NULL, 'Caballito'),
(22, 'Lucas', 'Aperinze', '1979-08-30', 'Guitarra Piano', 'Rap Hip Hop Trap', 'Lil Pump Dr Dre', 'lucasap@gmail.com', 'rap4life', NULL, NULL, 'Caballito'),
(23, 'Gabriel', 'Stentorian', '1988-03-20', 'Bajo', 'Rock', 'Les Claypool', 'stentorianrock@gmail.com', '', 'gabriel123', NULL, 'Villa Crespo'),
(24, 'Martin', 'Dal Lago', '2000-05-03', 'Bajo Piano', 'Rock  Reggae', 'Slash Paz Martinez', 'martindl@hotmail.com', 'martinguitarra', NULL, NULL, 'Villa Crespo'),
(26, 'Agustin', 'Caputo', '2000-02-21', 'Guitarra', 'Rock Punk Pop', 'Green Day Oasis Muse', 'agusc21@hotmail.com', 'agustinc21', NULL, NULL, 'Villa Crespo'),
(27, 'Agustin', 'Oliveto', '2000-05-13', 'Piano', 'Metal', 'Adrian Smith', 'cocooli@gmail.com', 'cocoloco', NULL, NULL, 'Villa Crespo'),
(28, 'Ignacio', 'Zajac', '1999-02-19', 'Bajo', 'Cumbia', 'Pablo Lescano', 'nachoz@gmail.com', 'nachitoxd', NULL, NULL, 'Villa Crespo'),
(29, 'Joaquin', 'Hojman', '1998-10-10', 'Ukelele', 'Trap Cumbia Rap', 'Bad Bunny David Guetta', 'quinopela@hotmail.com', 'yosoyquino', NULL, NULL, 'Caballito'),
(30, 'Facundo', 'Kousian', '1999-06-08', 'Bateria Bajo', 'Rock Rap Metal', NULL, 'facukou@gmail.com', 'facukousi16', NULL, NULL, 'Caballito'),
(31, 'Hernan ', 'Rodriguez', '1999-04-08', 'Bajo Guitarra', 'Rock Metal', 'Slash Dave Grohl', 'hernirod@hotmail.com', 'hernanguitarra', NULL, NULL, 'Caballito'),
(32, 'Sebastian', 'Menendez', '1998-05-09', 'Bajo', 'Rock Cumbia Trap', 'El Bordo La Renga', 'sebamenangel@gmail.com', 'sebabordo', NULL, NULL, 'Caballito'),
(33, 'Juan', 'Rodriguez', '0001-01-01', 'Theremin', 'Jazz', 'Bill Gates', 'juancitoro@gmail.com', 'juanchorodri', '', 'Me gustan los intrumentos alternativos', 'Flores'),
(34, 'admin', 'admin', '0001-01-01', 'admin', 'admin', 'admin', 'admin@admin.com', 'rootroot', '', 'fuck society', 'void'),
(35, 'Juan ', 'Bozzano', '0001-01-01', 'Piano Guitarra', 'Ska', 'Rodrigo', 'juanbz@gmail.com', 'juanchobz', '', 'Tengo muchos rulos', 'Almagro'),
(36, 'administrador', 'administrador', '0001-01-01', 'Banjo', 'Rock', 'Pappo', 'admin1@admin1.com', 'rootroot10', '', 'Me gusta el rock pesado', 'vacio'),
(37, 'admin', 'admin', '0001-01-01', 'null', 'null', 'null', '1@1.1', '1', '', 'null', 'null'),
(38, 'CaAcho', 'Castaña', '0001-01-01', '  Bajo Flauta Theremin', '  Reggaeton Electrónica Salsa', '  Maluma Enrique Iglesias El Cuarteto de Nos', 'cachitoelone@gmail.com', 'marroncito', '', 'Aguante boca', 'Caballito'),
(39, 'Juan', 'Perez', '0001-01-01', '  Teclado Chelo', '  Country Cumbia', '  Patricio Rey Rolling Stones AC/DC', 'juan@perez.com', '12345678', '', 'me gusta eisidisi', 'Almagro'),
(40, 'Leandro', 'Galanterni', '0001-01-01', '  Guitarra Teclado Mandolina', '  Country Reggae', '  Ricky Martin The Beatles', 'lgalant@gmail.com', 'lgalant12', '', 'Programador since 1990', 'Caballito'),
(41, 'Fernando', 'Blum', '0001-01-01', 'Guitarra Bajo ', 'Rock  Pop', 'Jimi Hendrix ', '', '', '', 'Guitarrista desde los 5 años', 'Devoto'),
(42, 'Fernando', 'Blum', '0001-01-01', 'Guitarra Bajo ', 'Rock ', 'Jimi Hendrix ', '', '', '', 'Guitarrista desde los 4 años', 'Villa Crespo'),
(43, 'Alan', 'Frito', '0001-01-01', '  Flauta', '  Pop Country', '  Maluma Katy Perry', 'alan@mail.com', 'alancito1', '', 'Toco la flauta desde los 3', 'Caballito'),
(44, 'Fernando', 'Blum', '0001-01-01', 'Guitarra Bajo ', 'Rock ', 'Jimi Hendrix ', '', '', '', 'Guitarrista desde los 4 años', 'Villa Crespo'),
(45, 'Fernando', 'Blum', '0001-01-01', 'Guitarra Bajo ', 'Rock ', 'Jimi Hendrix ', '', '', '', 'Guitarrista desde los 4 años', 'Villa Crespo'),
(46, 'Fernando', 'Blum', '0001-01-01', 'Guitarra Bajo ', 'Rock ', 'Jimi Hendrix ', '', '', '', 'Guitarrista desde los 4 años', 'Villa Crespo'),
(47, 'Fernando', 'Blum', '0001-01-01', 'Guitarra Bajo ', 'Rock ', 'Jimi Hendrix ', '', '', '', 'Guitarrista desde los 4 años', 'Villa Crespo'),
(48, 'Marta', 'Blum', '0001-01-01', 'Guitarra Bajo ', 'Rock ', 'Jimi Hendrix ', '', '', '', 'Guitarrista desde los 4 años', 'Villa Crespo'),
(49, 'Juan', 'Blum', '0001-01-01', 'Guitarra Bajo ', 'Rock ', 'Jimi Hendrix ', '', '', '', 'Guitarrista desde los 4 años', 'Villa Crespo'),
(50, 'Fernando', 'Blum', '0001-01-01', 'Guitarra Bajo ', 'Rock ', 'Jimi Hendrix ', '', '', '', 'Guitarrista desde los 4 años', 'Villa Crespo'),
(51, 'Alberto', 'Blum', '0001-01-01', 'Guitarra Bajo ', 'Rock ', 'Jimi Hendrix ', '', '', '', 'Guitarrista desde los 4 años', 'Villa Crespo'),
(52, 'Ricardo', 'Blum', '0001-01-01', 'Guitarra Bajo ', 'Rock ', 'Jimi Hendrix ', '', '', '', 'Guitarrista desde los 4 años', 'Villa Crespo'),
(53, 'Ferminaaa', 'Blum', '0001-01-01', 'Guitarra ', 'Rock ', 'Gonzalo ', '', '', '', 'Crack', 'Belgrano'),
(54, 'Julio', 'Blum', '0001-01-01', 'Guitarra ', 'Rock ', 'Gonzalo ', '', '', '', 'Crack', 'Belgrano'),
(55, 'demoprueba', 'demosisi', '0001-01-01', '  Guitarra Bajo ', '  Rock ', '  Maluma Ricky Martin ', 'demo@demo.com', 'demodemo', '', 'demo demo demo', 'Almagro'),
(56, 'demo1', 'demoo12', '0001-01-01', '  Guitarra ', '  Cumbia ', '  Abel Pintos Soda Estereo ', 'demo1@gmail.com', 'demodemo', '', 'demo demo 111', 'Belgrano'),
(57, 'asd', 'asd', '2/Abril/1986', '  Guitarra', '  Pop', '  Abel Pintos', 'asd@mail.com', 'pepepepe', '', 'asdasdasd', 'Caballito'),
(58, '123', '321', '4/5/1996', '  Guitarra', '  Pop', '  Rabalanca', 'dasdas@dsadasas.com', 'lolopepo', '', 'ldashdkasj', 'Villa Crespo'),
(59, 'juan', 'pepasooo', 'android.widget.Spinner{a3c28ee VFED..CL. ........ 0,0-75,18 #7f07004c app:id/dia}/android.widget.Spinner{62a218f VFED..CL. ........ 75,0-150,18 #7f07006e app:id/mes}/android.widget.Spinner{9c40c1c VFED..CL. ........ 150,0-225,18 #7f07002c app:id/año}', '  Guitarra ', '  Rock ', '  Maluma ', 'juanpepasoxd@1.com', 'ahreahre', '', 'pepas de max quality', 'Nuñez'),
(60, 'aaa', 'bbb', '3/4/1991', '  Guitarra   Flauta', '  Cumbia  Cuarteto ', '  El Cuarteto de Nos   Abel Pintos', 'a@b.com', 'aaabbbcc', '', 'aaabbb', 'Floresta');

-- --------------------------------------------------------

--
-- Table structure for table `tusuarios_has_tinstrumentos`
--

CREATE TABLE `tusuarios_has_tinstrumentos` (
  `tUsuarios_IdUsuario` int(11) NOT NULL,
  `tInstrumentos_IdInstrumento` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tusuarios_has_tinstrumentos`
--

INSERT INTO `tusuarios_has_tinstrumentos` (`tUsuarios_IdUsuario`, `tInstrumentos_IdInstrumento`) VALUES
(1, 1),
(3, 1),
(1, 2),
(3, 2),
(2, 3),
(3, 3);

-- --------------------------------------------------------

--
-- Table structure for table `tusuarios_has_tusuarios`
--

CREATE TABLE `tusuarios_has_tusuarios` (
  `tUsuarios_IdUsuario` int(11) NOT NULL,
  `tSeguidos_IdSeguido` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tusuarios_has_tusuarios`
--

INSERT INTO `tusuarios_has_tusuarios` (`tUsuarios_IdUsuario`, `tSeguidos_IdSeguido`) VALUES
(1, 2),
(56, 2),
(1, 4),
(55, 12),
(60, 12),
(55, 24),
(55, 26),
(56, 26),
(55, 27),
(56, 27),
(1, 28),
(56, 28);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `soicitudesdecontacto`
--
ALTER TABLE `soicitudesdecontacto`
  ADD PRIMARY KEY (`IdSoicitudDeContacto`);

--
-- Indexes for table `tbandas`
--
ALTER TABLE `tbandas`
  ADD PRIMARY KEY (`IdBanda`);

--
-- Indexes for table `tbandas_has_tgeneros`
--
ALTER TABLE `tbandas_has_tgeneros`
  ADD PRIMARY KEY (`tBandas_IdBanda`,`tGeneros_IdGenero`),
  ADD KEY `fk_tBandas_has_tGeneros_tGeneros1_idx` (`tGeneros_IdGenero`),
  ADD KEY `fk_tBandas_has_tGeneros_tBandas1_idx` (`tBandas_IdBanda`);

--
-- Indexes for table `tbandas_has_tusuarios`
--
ALTER TABLE `tbandas_has_tusuarios`
  ADD PRIMARY KEY (`tBandas_IdBanda`,`tUsuarios_IdUsuario`),
  ADD KEY `fk_tBandas_has_tUsuarios_tUsuarios1_idx` (`tUsuarios_IdUsuario`),
  ADD KEY `fk_tBandas_has_tUsuarios_tBandas1_idx` (`tBandas_IdBanda`);

--
-- Indexes for table `tinstrumentos`
--
ALTER TABLE `tinstrumentos`
  ADD PRIMARY KEY (`IdInstrumento`);

--
-- Indexes for table `tusuarios`
--
ALTER TABLE `tusuarios`
  ADD PRIMARY KEY (`IdUsuario`);

--
-- Indexes for table `tusuarios_has_tinstrumentos`
--
ALTER TABLE `tusuarios_has_tinstrumentos`
  ADD PRIMARY KEY (`tUsuarios_IdUsuario`,`tInstrumentos_IdInstrumento`),
  ADD KEY `fk_tUsuarios_has_tInstrumentos_tInstrumentos1_idx` (`tInstrumentos_IdInstrumento`),
  ADD KEY `fk_tUsuarios_has_tInstrumentos_tUsuarios1_idx` (`tUsuarios_IdUsuario`);

--
-- Indexes for table `tusuarios_has_tusuarios`
--
ALTER TABLE `tusuarios_has_tusuarios`
  ADD PRIMARY KEY (`tUsuarios_IdUsuario`,`tSeguidos_IdSeguido`),
  ADD KEY `fk_tUsuarios_has_tGeneros_tGeneros1_idx` (`tSeguidos_IdSeguido`),
  ADD KEY `fk_tUsuarios_has_tGeneros_tUsuarios1_idx` (`tUsuarios_IdUsuario`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `soicitudesdecontacto`
--
ALTER TABLE `soicitudesdecontacto`
  MODIFY `IdSoicitudDeContacto` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tbandas`
--
ALTER TABLE `tbandas`
  MODIFY `IdBanda` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `tinstrumentos`
--
ALTER TABLE `tinstrumentos`
  MODIFY `IdInstrumento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `tusuarios`
--
ALTER TABLE `tusuarios`
  MODIFY `IdUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbandas_has_tusuarios`
--
ALTER TABLE `tbandas_has_tusuarios`
  ADD CONSTRAINT `fk_tBandas_has_tUsuarios_tBandas1` FOREIGN KEY (`tBandas_IdBanda`) REFERENCES `tbandas` (`IdBanda`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_tBandas_has_tUsuarios_tUsuarios1` FOREIGN KEY (`tUsuarios_IdUsuario`) REFERENCES `tusuarios` (`IdUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tusuarios_has_tinstrumentos`
--
ALTER TABLE `tusuarios_has_tinstrumentos`
  ADD CONSTRAINT `fk_tUsuarios_has_tInstrumentos_tInstrumentos1` FOREIGN KEY (`tInstrumentos_IdInstrumento`) REFERENCES `tinstrumentos` (`IdInstrumento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_tUsuarios_has_tInstrumentos_tUsuarios1` FOREIGN KEY (`tUsuarios_IdUsuario`) REFERENCES `tusuarios` (`IdUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
