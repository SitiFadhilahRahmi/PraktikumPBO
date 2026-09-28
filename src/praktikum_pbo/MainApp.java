package praktikum_pbo;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("   REGISTRASI REKENING TABUNGAN BARU     ");
        System.out.println("==========================================");
        System.out.print("Masukkan Nomor Rekening : ");
        String noRek = scanner.nextLine().trim();

        System.out.print("Masukkan Nama Nasabah   : ");
        String nama = scanner.nextLine().trim();

        System.out.print("Setoran Awal (Rp)       : ");
        double saldoAwal = 0.0;
        try {
            saldoAwal = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid, saldo awal diset ke 0.");
        }

        Rekening rekening = new Rekening(noRek, nama, saldoAwal);
        System.out.println("Rekening berhasil dibuat!\n");

        boolean berjalan = true;
        while (berjalan) {
            System.out.println("==========================================");
            System.out.println("        MENU TABUNGAN TERMINAL           ");
            System.out.println("==========================================");
            System.out.println("1. Cek Informasi Rekening & Saldo");
            System.out.println("2. Setor Tunai");
            System.out.println("3. Tarik Tunai");
            System.out.println("4. Cetak Mutasi Transaksi");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi (1-5): ");

            String pilihan = scanner.nextLine().trim();
            switch (pilihan) {
                case "1":
                    System.out.println("\n--- DATA REKENING ---");
                    System.out.println("No. Rekening : " + rekening.getNomorRekening());
                    System.out.println("Nama Pemilik : " + rekening.getNamaPemilik());
                    System.out.printf("Saldo Aktif  : Rp%,.2f\n\n", rekening.getSaldo());
                    break;

                case "2":
                    System.out.print("Masukkan nominal setoran (Rp): ");
                    try {
                        double nominalSetor = Double.parseDouble(scanner.nextLine().trim());
                        rekening.setor(nominalSetor);
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Input harus berupa angka nominal.");
                    }
                    break;

                case "3":
                    System.out.print("Masukkan nominal penarikan (Rp): ");
                    try {
                        double nominalTarik = Double.parseDouble(scanner.nextLine().trim());
                        rekening.tarik(nominalTarik);
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Input harus berupa angka nominal.");
                    }
                    break;

                case "4":
                    rekening.cetakMutasi();
                    break;

                case "5":
                    berjalan = false;
                    System.out.println("\nTerima kasih telah bertransaksi. Sampai jumpa!");
                    break;

                default:
                    System.out.println("❌ Pilihan tidak valid. Silakan pilih 1-5.");
            }
        }

        scanner.close();
    }
}