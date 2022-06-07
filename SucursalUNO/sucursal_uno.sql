-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-06-2022 a las 06:40:58
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sucursal_uno`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auxpedido`
--

CREATE TABLE `auxpedido` (
  `id_aux_pedido` int(8) NOT NULL,
  `id_item` int(8) NOT NULL,
  `tipo` varchar(30) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `subtotal` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `auxpedido`
--

INSERT INTO `auxpedido` (`id_aux_pedido`, `id_item`, `tipo`, `cantidad`, `subtotal`) VALUES
(1, 1, 'Postres', 122, 10980),
(17, 2, 'Postres', 23, 230),
(18, 1, 'Comidas', 212, 19080),
(19, 1, 'Comidas', 12, 1080),
(19, 3, 'Postres', 11, 88),
(19, 1, 'Postres', 1, 90),
(19, 1, 'Postres', 1, 90),
(19, 2, 'Comidas', 2, 20),
(19, 6, 'Comidas', 2, 180),
(19, 5, 'Postres', 2, 20),
(19, 4, 'Postres', 22, 2662),
(19, 3, 'Comidas', 25, 200),
(20, 1, 'Comidas', 9, 810),
(20, 1, 'Postres', 9, 810),
(21, 2, 'Comidas', 23, 230),
(22, 2, 'Comidas', 7, 70),
(22, 2, 'Postres', 9, 90),
(23, 1, 'Postres', 2, 180),
(23, 3, 'Comidas', 2, 16),
(23, 3, 'Bebidas', 12, 96);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `id_pedido` int(10) NOT NULL,
  `cliente` varchar(100) NOT NULL,
  `fecha` varchar(20) NOT NULL,
  `Total` double NOT NULL,
  `id_aux_pedido` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `postre`
--

CREATE TABLE `postre` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `precio` double NOT NULL,
  `sabor` varchar(50) NOT NULL,
  `detalles` varchar(120) NOT NULL,
  `cantidad` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `postre`
--

INSERT INTO `postre` (`id`, `nombre`, `precio`, `sabor`, `detalles`, `cantidad`) VALUES
(1, 'Postre1', 90, 'Sabor caca', 'No se', -93),
(2, 'Postre2', 10, 'chocolate', 'sabe bueno', 91),
(3, 'Postre3', 8, 'shisihi', 'adads', 100),
(4, 'Postre4', 121, 'iasdbia', 'bidasbdiasd', 10),
(5, 'Postre5', 10, 'Virgo', 'Se abrio la panza', -98),
(7, 'NO', 23, 'que', 'ria', 12),
(8, 'sa', 1, 'ss', 's', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`id_pedido`);

--
-- Indices de la tabla `postre`
--
ALTER TABLE `postre`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `id_pedido` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de la tabla `postre`
--
ALTER TABLE `postre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
