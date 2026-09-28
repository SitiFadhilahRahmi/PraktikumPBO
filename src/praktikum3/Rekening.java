package praktikum3;

import java.util.ArrayList;
import java.util.List;

public class Rekening {
    private final String nomorRekening;
    private final String namaPemilik;
    private double saldo;
    private final List<Transaksi> riwayatTransaksi;
    private int counterTransaksi;

    public Rekening(String nomorRekening, String namaPemilik, double saldoAwal) {
        this.nomorRekening = nomorRekening;
        this.namaPemilik = namaPemilik;
        this.saldo = Math.max(saldoAwal, 0.0);
        this.riwayatTransaksi = new ArrayList<>();
        this.counterTransaksi = 1;

        if (saldoAwal > 0) {
            catatTransaksi("SETOR", saldoAwal);
        }
    }

    public boolean setor(double nominal) {
        if (nominal <= 0) {
            System.out.println("❌ Gagal: Nominal setoran harus lebih besar dari 0.");
            return false;
        }
        this.saldo += nominal;
        catatTransaksi("SETOR", nominal);
        System.out.printf("✅ Setoran sebesar Rp%,.2f berhasil.\n", nominal);
        return true;
    }

    public boolean tarik(double nominal) {
        if (nominal <= 0) {
            System.out.println("❌ Gagal: Nominal penarikan harus lebih besar dari 0.");
            return false;
        }
        if (nominal > this.saldo) {
            System.out.println("❌ Gagal: Saldo tidak mencukupi untuk penarikan.");
            return false;
        }
        this.saldo -= nominal;
        catatTransaksi("TARIK", nominal);
        System.out.printf("✅ Penarikan sebesar Rp%,.2f berhasil.\n", nominal);
        return true;
    }

    private void catatTransaksi(String jenis, double nominal) {
        String idTx = String.format("TX%03d", counterTransaksi++);
        Transaksi tx = new Transaksi(idTx, jenis, nominal, this.saldo);
        riwayatTransaksi.add(tx);
    }

    public void cetakMutasi() {
        System.out.println("\n=========================== RIWAYAT TRANSAKSI ===========================");
        System.out.printf("No. Rekening : %s | Pemilik: %s\n", nomorRekening, namaPemilik);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("| ID Trans   | Waktu               | Tipe   | Nominal       | Saldo Akhir   |");
        System.out.println("-------------------------------------------------------------------------");
        
        if (riwayatTransaksi.isEmpty()) {
            System.out.println("|                      Belum ada mutasi transaksi                       |");
        } else {
            for (Transaksi tx : riwayatTransaksi) {
                tx.tampilkanDetail();
            }
        }
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("Total Saldo Terkini: Rp%,.2f\n\n", saldo);
    }

    public String getNomorRekening() {
        return nomorRekening;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public double getSaldo() {
        return saldo;
    }
}