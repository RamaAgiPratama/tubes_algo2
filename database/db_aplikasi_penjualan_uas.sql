-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 28 Jun 2022 pada 14.21
-- Versi server: 10.4.22-MariaDB
-- Versi PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_aplikasi_penjualan_uas`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `t_barang`
--

CREATE TABLE `t_barang` (
  `kode_barang` varchar(50) NOT NULL,
  `nama_barang` varchar(100) NOT NULL,
  `ukuran` varchar(20) NOT NULL,
  `harga_beli` varchar(100) NOT NULL,
  `harga_jual` varchar(100) NOT NULL,
  `stok` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `t_barang`
--

INSERT INTO `t_barang` (`kode_barang`, `nama_barang`, `ukuran`, `harga_beli`, `harga_jual`, `stok`) VALUES
('KB001', 'Baju Muslim', 'M', '55000', '70000', '100'),
('KB002', 'Celana Gunung', 'L', '75000', '90000', '50'),
('KB003', 'Daster', 'S', '35000', '40000', '55'),
('KB004', 'Lejing', 'L', '25000', '30000', '60');

-- --------------------------------------------------------

--
-- Struktur dari tabel `t_login`
--

CREATE TABLE `t_login` (
  `id` varchar(20) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `t_login`
--

INSERT INTO `t_login` (`id`, `username`, `password`) VALUES
('ID001', 'admin', 'admin'),
('ID002', 'petugas', 'petugas');

-- --------------------------------------------------------

--
-- Struktur dari tabel `t_pelanggan`
--

CREATE TABLE `t_pelanggan` (
  `id_pelanggan` varchar(50) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `no_hp` varchar(20) NOT NULL,
  `alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `t_pelanggan`
--

INSERT INTO `t_pelanggan` (`id_pelanggan`, `nama`, `no_hp`, `alamat`) VALUES
('ID001', 'Rama Agi Pratama', '081223258587', 'Kota Banjar'),
('ID002', 'Fery Juniar', '088123256876', 'Ciamis'),
('ID003', 'Hilman Mutaqin', '087123256782', 'Bandung'),
('ID004', 'Muhammad Fadlullah', '089111987233', 'Cibaduyut'),
('ID005', 'Ismail Marzuki', '085232098171', 'Bekasi'),
('ID006', 'Fedro Rizkiyana', '082009129381', 'Randegan');

-- --------------------------------------------------------

--
-- Struktur dari tabel `t_pembelian`
--

CREATE TABLE `t_pembelian` (
  `no_pembelian` varchar(50) NOT NULL,
  `tanggal_beli` date NOT NULL,
  `kode_barang` varchar(50) NOT NULL,
  `nama_barang` varchar(100) NOT NULL,
  `ukuran` varchar(50) NOT NULL,
  `harga_beli` varchar(100) NOT NULL,
  `jumlah` varchar(100) NOT NULL,
  `total_harga` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `t_pembelian`
--

INSERT INTO `t_pembelian` (`no_pembelian`, `tanggal_beli`, `kode_barang`, `nama_barang`, `ukuran`, `harga_beli`, `jumlah`, `total_harga`) VALUES
('NB001', '2022-06-03', 'KB001', 'Baju Muslim', 'M', '55000', '2', '110000'),
('NB002', '2022-06-04', 'KB003', 'Daster', 'S', '35000', '5', '175000'),
('NB003', '2022-06-10', 'KB002', 'Celana Gunung', 'L', '75000', '1', '55000'),
('NB004', '2022-06-09', 'KB004', 'Lejing', 'L', '25000', '3', '75000'),
('NB005', '2022-06-03', 'KB001', 'Baju Muslim', 'M', '55000', '2', '110000');

-- --------------------------------------------------------

--
-- Struktur dari tabel `t_pendapatan`
--

CREATE TABLE `t_pendapatan` (
  `dari_tanggal` date NOT NULL,
  `sampai_tanggal` date NOT NULL,
  `pengeluaran` int(20) NOT NULL,
  `pemasukan` int(20) NOT NULL,
  `pendapatan` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `t_penjualan`
--

CREATE TABLE `t_penjualan` (
  `no_penjualan` varchar(50) NOT NULL,
  `tanggal_jual` date NOT NULL,
  `pelanggan` varchar(100) NOT NULL,
  `kode_barang` varchar(100) NOT NULL,
  `nama_barang` varchar(100) NOT NULL,
  `ukuran` varchar(20) NOT NULL,
  `harga_jual` varchar(100) NOT NULL,
  `jumlah` varchar(100) NOT NULL,
  `total_harga` varchar(100) NOT NULL,
  `bayar` varchar(100) NOT NULL,
  `kembalian` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `t_penjualan`
--

INSERT INTO `t_penjualan` (`no_penjualan`, `tanggal_jual`, `pelanggan`, `kode_barang`, `nama_barang`, `ukuran`, `harga_jual`, `jumlah`, `total_harga`, `bayar`, `kembalian`) VALUES
('NJ001', '2022-06-04', 'Rama Agi Pratama', 'KB002', 'Celana Gunung', 'L', '75000', '1', '75000', '200000', '125000'),
('NJ002', '2022-06-20', 'Hilman Mutaqin', 'KB004', 'Lejing', 'L', '25000', '1', '35000', '50000', '15000'),
('NJ003', '2022-06-04', 'Muhammad Fadlullah', 'KB003', 'Daster', 'S', '35000', '2', '150000', '170000', '20000');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `t_barang`
--
ALTER TABLE `t_barang`
  ADD PRIMARY KEY (`kode_barang`);

--
-- Indeks untuk tabel `t_login`
--
ALTER TABLE `t_login`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `t_pelanggan`
--
ALTER TABLE `t_pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indeks untuk tabel `t_pembelian`
--
ALTER TABLE `t_pembelian`
  ADD PRIMARY KEY (`no_pembelian`);

--
-- Indeks untuk tabel `t_pendapatan`
--
ALTER TABLE `t_pendapatan`
  ADD PRIMARY KEY (`dari_tanggal`);

--
-- Indeks untuk tabel `t_penjualan`
--
ALTER TABLE `t_penjualan`
  ADD PRIMARY KEY (`no_penjualan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
