package com.rpl.demo; // Pastikan package ini sesuai

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// 1. HALAMAN FORM INPUT (Tampil saat awal dibuka)
	@GetMapping("/")
	public String tampilkanForm() {
		return "<div style='font-family: Arial, sans-serif; padding: 20px; max-width: 400px; border: 1px solid #ccc; border-radius: 10px; margin: 20px auto;'>" +
				"<h2 style='text-align:center;'>Kalkulator Gaji Mr. John</h2>" +
				"<form action='/hitung-gaji' method='GET'>" +
				"  <label>Masukkan Jam Kerja Minggu Ini:</label><br>" +
				"  <input type='number' name='jamKerja' required style='width: 95%; padding: 8px; margin: 8px 0 15px 0;'><br>" +
				"  <label>Masukkan Pengeluaran (Rp):</label><br>" +
				"  <input type='number' name='pengeluaran' required style='width: 95%; padding: 8px; margin: 8px 0 20px 0;'><br>" +
				"  <button type='submit' style='width: 100%; padding: 10px; background-color: #28a745; color: white; border: none; border-radius: 5px; cursor: pointer; font-size: 16px;'>Hitung Gaji & Tabungan</button>" +
				"</form>" +
				"</div>";
	}

	// 2. HALAMAN HASIL PERHITUNGAN (Tampil setelah tombol ditekan)
	@GetMapping("/hitung-gaji")
	public String hitungGaji(
			@RequestParam(defaultValue = "0") int jamKerja,
			@RequestParam(defaultValue = "0") int pengeluaran
	) {
		// Nilai ketetapan
		final int RATE_NORMAL = 15000;
		final int RATE_LEMBUR = 22500;
		final int BATAS_NORMAL = 40;

		int gajiNormal = 0;
		int gajiLembur = 0;

		// Hitung Pemasukan
		if (jamKerja > BATAS_NORMAL) {
			gajiNormal = BATAS_NORMAL * RATE_NORMAL;
			gajiLembur = (jamKerja - BATAS_NORMAL) * RATE_LEMBUR;
		} else {
			gajiNormal = jamKerja * RATE_NORMAL;
		}

		int pemasukan = gajiNormal + gajiLembur;
		String status = "";
		String detailTabungan = "";

		// Hitung Status
		if (pemasukan > pengeluaran) {
			int tabungan = pemasukan - pengeluaran;
			status = "<span style='color: green;'>Bisa menabung</span>";
			detailTabungan = "Besar tabungan : <b>Rp " + tabungan + "</b>";
		} else if (pemasukan == pengeluaran) {
			status = "<span style='color: orange;'>Tidak bisa menabung</span>";
		} else {
			status = "<span style='color: red;'>Cari tambahan</span>";
		}

		// Tampilkan hasil dalam bentuk HTML
		return "<div style='font-family: Arial, sans-serif; padding: 20px; max-width: 400px; border: 1px solid #ccc; border-radius: 10px; margin: 20px auto;'>" +
				"<h2 style='text-align:center;'>Hasil Perhitungan</h2>" +
				"<p>Jam Kerja: " + jamKerja + " jam</p>" +
				"<p>Pengeluaran: Rp " + pengeluaran + "</p>" +
				"<hr>" +
				"<p>Gaji Normal: Rp " + gajiNormal + "</p>" +
				"<p>Gaji Lembur: Rp " + gajiLembur + "</p>" +
				"<p style='font-size: 18px;'><b>Total Pemasukan: Rp " + pemasukan + "</b></p>" +
				"<hr>" +
				"<h3>Status: " + status + "</h3>" +
				"<p>" + detailTabungan + "</p>" +
				"<br>" +
				"<a href='/' style='display: block; text-align: center; padding: 10px; background-color: #007bff; color: white; text-decoration: none; border-radius: 5px;'>Kembali ke Form</a>" +
				"</div>";
	}
}