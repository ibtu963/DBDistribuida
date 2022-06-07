-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-06-2022 a las 06:39:01
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
-- Base de datos: `sucursal_dos`
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
(1, 3, 'Postres', 12, 1080),
(1, 1, 'Bebidas', 12, 180),
(10, 1, 'Bebidas', 4, 60),
(11, 1, 'Bebidas', 3, 45),
(12, 3, 'Postres', 12, 1080),
(12, 1, 'Bebidas', 12, 180),
(13, 1, 'Bebidas', 8, 120),
(14, 2, 'Postres', 3, 36),
(14, 3, 'Postres', 3, 270),
(15, 2, 'Postres', 232, 2784),
(15, 3, 'Postres', 222, 19980),
(1, 2, 'Postres', 23, 230),
(17, 1, 'Comidas', 212, 19080),
(18, 1, 'Comidas', 12, 1080),
(18, 3, 'Postres', 11, 88),
(19, 1, 'Postres', 1, 90),
(19, 1, 'Postres', 1, 90),
(19, 2, 'Comidas', 2, 20),
(20, 6, 'Comidas', 2, 180),
(20, 5, 'Postres', 2, 20),
(21, 4, 'Postres', 22, 2662),
(21, 3, 'Comidas', 25, 200),
(22, 1, 'Comidas', 9, 810),
(22, 1, 'Postres', 9, 810),
(23, 2, 'Comidas', 23, 230),
(24, 2, 'Comidas', 7, 70),
(24, 2, 'Postres', 9, 90),
(25, 1, 'Postres', 2, 180),
(25, 3, 'Comidas', 2, 16),
(25, 3, 'Bebidas', 12, 96);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comida`
--

CREATE TABLE `comida` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `precio` double NOT NULL,
  `sabor` varchar(50) NOT NULL,
  `detalles` varchar(120) NOT NULL,
  `cantidad` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `comida`
--

INSERT INTO `comida` (`id`, `nombre`, `precio`, `sabor`, `detalles`, `cantidad`) VALUES
(1, 'Comida1', 90, 'Sabor caca', 'No se', -91),
(2, 'Comida2', 10, 'chocolate', 'sabe bueno', 70),
(3, 'Comida3', 8, 'shisihi', 'adads', -65),
(4, 'Comida4', 121, 'iasdbia', 'bidasbdiasd', 12),
(6, 'Comida5', 90, 'Sabor caca', 'No se', -98);

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

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `comida`
--
ALTER TABLE `comida`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`id_pedido`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `comida`
--
ALTER TABLE `comida`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `id_pedido` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
