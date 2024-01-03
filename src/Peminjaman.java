import java.util.Date;

interface Peminjaman {
    void setTanggalPeminjaman(Date tanggalPeminjaman);
    void setTanggalPengembalian(Date tanggalPengembalian);
    void setLunas(boolean lunas);
    boolean isLunas();
}