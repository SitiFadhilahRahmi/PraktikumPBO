package praktikum3;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaksi {
    private final String idTransaksi;
    private final LocalDateTime tanggal;
    private final String jenis; // "SETOR" atau "TARIK"
    private final double nominal;
    private final double saldoSetelah;

    public Transaksi(String idTransaksi, String jenis, double nominal, double saldoSetelah) {
        this.idTransaksi = idTransaksi;
        this.tanggal = LocalDateTime.now();
        this.jenis = jenis;
        this.nominal = nominal;
        this.saldoSetelah = saldoSetelah;
    }

    public void tampilkanDetail() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.printf("| %-10s | %-19s | %-6s | Rp%,12.2f | Rp%,12.2f |\n",
                idTransaksi, tanggal.format(formatter), jenis, nominal, saldoSetelah);
    }
}