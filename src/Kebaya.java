import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public abstract class Kebaya implements Peminjaman {
    private int id;
    private String nama;
    private String ukuran;
    private String warna;
    private double hargaSewa;
    private boolean tersedia;
    private Peminjam peminjam;

    public Kebaya(int id, String nama, String ukuran, String warna, double hargaSewa) {
        this.id = id;
        this.nama = nama;
        this.ukuran = ukuran;
        this.warna = warna;
        this.hargaSewa = hargaSewa;
        this.tersedia = true;
    }

    public Peminjam getPeminjam() {

        return peminjam;
    }

    public void setPeminjam(Peminjam peminjam) {

        this.peminjam = peminjam;
    }

    public int getId() {

        return id;
    }

    public String getNama() {

        return nama;
    }

    public String getUkuran() {


        return ukuran;
    }

    public String getWarna() {

        return warna;
    }

    public double getHargaSewa() {

        return hargaSewa;
    }

    public boolean isTersedia() {

        return tersedia;
    }

    public void setTersedia(boolean tersedia) {

        this.tersedia = tersedia;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Nama: " + nama +
                ", Ukuran: " + ukuran +
                ", Warna: " + warna +
                ", Harga Sewa: " + hargaSewa +
                ", Tersedia: " + (tersedia ? "Ya" : "Tidak");
    }
    @Override
    public void setTanggalPeminjaman(Date tanggalPeminjaman) {
        if (peminjam != null) {
            peminjam.setTanggalPeminjaman(tanggalPeminjaman);
        }
    }

    @Override
    public void setTanggalPengembalian(Date tanggalPengembalian) {
        if (peminjam != null) {
            peminjam.setTanggalPengembalian(tanggalPengembalian);
        }
    }

    @Override
    public void setLunas(boolean lunas) {
        if (peminjam != null) {
            peminjam.setLunas(lunas);
        }
    }

    @Override
    public boolean isLunas() {

        return (peminjam != null) && peminjam.isLunas();
    }
}