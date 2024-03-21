-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3307
-- Tempo de geração: 21/03/2024 às 15:17
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `agenda`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `tbl_agenda`
--

CREATE TABLE `tbl_agenda` (
  `id` int(11) NOT NULL,
  `Nome` varchar(100) NOT NULL,
  `DDD` int(2) NOT NULL,
  `Celular` int(9) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Nascimento` date NOT NULL,
  `CPF` varchar(11) NOT NULL,
  `Endereco` varchar(100) NOT NULL,
  `Numero` int(5) NOT NULL,
  `Bairro` varchar(50) NOT NULL,
  `Complemento` varchar(100) NOT NULL,
  `CEP` int(8) NOT NULL,
  `Cidade` varchar(50) NOT NULL,
  `Estado` char(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tbl_agenda`
--

INSERT INTO `tbl_agenda` (`id`, `Nome`, `DDD`, `Celular`, `Email`, `Nascimento`, `CPF`, `Endereco`, `Numero`, `Bairro`, `Complemento`, `CEP`, `Cidade`, `Estado`) VALUES
(1, 'aluno', 11, 986453235, 'aluno@gmail.com', '0000-00-00', '24598709871', 'Rua dos Bobos', 56, 'Barra Funda', 'apt 56 A', 1153040, 'São Paulo', 'SP'),
(3, 'Luis', 11, 987654320, 'luis@gmail.com', '2022-10-23', '12345678902', 'Rua X', 12, 'xd', '12', 9876543, 'SP', 'SP'),
(4, 'Paulo', 11, 98765432, 'paulo@gmail.com', '2000-10-23', '23456789012', 'Rua CX', 12, 'Baixo', '123', 9876543, 'SP', 'SP'),
(5, 'Gabriel', 11, 98765432, 'gabriel@gmail.com', '2000-01-20', '23456712345', 'Rua Primeira', 12, 'Lapa', '12', 9876543, 'RJ', 'RJ'),
(7, 'Maria Luiza', 11, 986422338, 'marialuiza@gmail.com', '1976-10-02', '24576242824', 'Rua Das Cobras', 91, 'Bambu', 'Apt 56', 1182010, 'SP', 'SP');

-- --------------------------------------------------------

--
-- Estrutura para tabela `tbl_credencial`
--

CREATE TABLE `tbl_credencial` (
  `id` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tbl_credencial`
--

INSERT INTO `tbl_credencial` (`id`, `email`, `senha`) VALUES
(1, 'aluno@gmail.com', 'aluno'),
(2, 'menino@gmail.com', 'menino'),
(5, 'admin@gmail.com', 'admin');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `tbl_agenda`
--
ALTER TABLE `tbl_agenda`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `tbl_credencial`
--
ALTER TABLE `tbl_credencial`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `tbl_agenda`
--
ALTER TABLE `tbl_agenda`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de tabela `tbl_credencial`
--
ALTER TABLE `tbl_credencial`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
