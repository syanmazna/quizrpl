import java.util.Scanner;

public class john {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Nilai ketetapan
        final int RATE_NORMAL = 15000;
        final int RATE_LEMBUR = 22500; // 1.5 * 15000
        final int BATAS_NORMAL = 40;

        // 1. Input Nilai Variatif (Bebas)
        System.out.print("Masukkan jumlah jam kerja minggu ini : ");
        int jamKerja = input.nextInt();

        System.out.print("Masukkan jumlah pengeluaran (Rp)     : ");
        int pengeluaran = input.nextInt();

        // 2. Hitung Pemasukan (Gaji)
        int gajiNormal = 0;
        int gajiLembur = 0;

        if (jamKerja > BATAS_NORMAL) {
            gajiNormal = BATAS_NORMAL * RATE_NORMAL;
            gajiLembur = (jamKerja - BATAS_NORMAL) * RATE_LEMBUR;
        } else {
            gajiNormal = jamKerja * RATE_NORMAL;
            gajiLembur = 0;
        }

        int pemasukan = gajiNormal + gajiLembur;

        // 3. Output Rincian Gaji
        System.out.println("\n--- Rincian Gaji ---");
        System.out.println("Gaji Normal    : Rp " + gajiNormal);
        System.out.println("Gaji Lembur    : Rp " + gajiLembur);
        System.out.println("Total Gaji     : Rp " + pemasukan);

        // 4. Hitung Tabungan & Status
        System.out.println("\n--- Status Keuangan ---");
        if (pemasukan > pengeluaran) {
            int tabungan = pemasukan - pengeluaran;
            System.out.println("Status         : Bisa menabung");
            System.out.println("Besar tabungan : Rp " + tabungan);
        } else if (pemasukan == pengeluaran) {
            System.out.println("Status         : Tidak bisa menabung");
        } else {
            System.out.println("Status         : Cari tambahan");
        }

        input.close();
    }
}