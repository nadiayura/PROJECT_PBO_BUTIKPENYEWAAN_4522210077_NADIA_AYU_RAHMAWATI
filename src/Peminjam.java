import java.util.Date;
public class Peminjam {
    private String nama;
    private String nomorTelepon;
    private Date tanggalPeminjaman;
    private Date tanggalPengembalian;
    private boolean lunas;

    public Peminjam(String nama, String nomorTelepon) {
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.tanggalPeminjaman = null;
        this.tanggalPengembalian = null;
        this.lunas = false;
    }
    public void setTanggalPeminjaman(Date tanggalPeminjaman) {
        this.tanggalPeminjaman = tanggalPeminjaman;
    }

    public void setTanggalPengembalian(Date tanggalPengembalian) {
        this.tanggalPengembalian = tanggalPengembalian;
    }
    public void setLunas(boolean lunas) {
        this.lunas = lunas;
    }

    public String getNama() {
        return nama;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public Date getTanggalPeminjaman() {
        return tanggalPeminjaman;
    }

    public Date getTanggalPengembalian() {
        return tanggalPengembalian;
    }

    @Override
    public String toString() {
        return "Nama Peminjam: " + nama +
                ", Nomor Telepon: " + nomorTelepon;
    }

    public boolean isLunas() {
        return lunas;
    }
}
