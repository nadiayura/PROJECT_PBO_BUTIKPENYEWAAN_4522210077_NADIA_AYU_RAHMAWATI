import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
public class RentalManager {
    private List<Kebaya> kebayas;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public RentalManager() {
        kebayas = new ArrayList<>();
        kebayas.add(new StandardKebaya(1, "Kebaya Maria", "S", "Merah", 500000.0));
        kebayas.add(new StandardKebaya(2, "Kebaya Hirari", "M", "Hijau", 500000.0));
        kebayas.add(new PremiumKebaya(61, "Kebaya Rayna", "L", "Biru", 800000.0));
        kebayas.add(new PremiumKebaya(62, "Kebaya Adelle", "XL", "Kuning", 800000.0));
    }

    public void displayAvailableKebayas() {
        System.out.println("Daftar Kebaya Tersedia:");

        List<Kebaya> standardKebayas = new ArrayList<>();
        List<Kebaya> premiumKebayas = new ArrayList<>();

        // Categorize kebayas into Standard and Premium
        for (Kebaya kebaya : kebayas) {
            if (kebaya.isTersedia()) {
                if (kebaya.getId() <= 60) {
                    standardKebayas.add(kebaya);
                } else {
                    premiumKebayas.add(kebaya);
                }
            }
        }

        // Menampilkan Kebaya Standard
        System.out.println("Jenis : Standard");
        for (Kebaya kebaya : standardKebayas) {
            System.out.println(kebaya);
        }

        // Menampilkan Kebayas Premium
        System.out.println("Jenis : Premium");
        for (Kebaya kebaya : premiumKebayas) {
            System.out.println(kebaya);
        }
    }


    public void rentKebaya() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan ID Kebaya yang ingin dipinjam: ");
        int kebayaId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // untuk cek apakah ID kebaya valid
        Kebaya kebaya = findKebayaById(kebayaId);
        if (kebaya == null || !kebaya.isTersedia()) {
            System.out.println("Kebaya tidak tersedia atau ID tidak valid.");
            return;
        }

        System.out.print("Masukkan nama peminjam: ");
        String namaPeminjam = scanner.nextLine();

        System.out.print("Masukkan nomor telepon peminjam: ");
        String nomorTelepon = scanner.nextLine();

        System.out.print("Masukkan tanggal peminjaman (dd/MM/yyyy): ");
        Date tanggalPeminjaman = readDate(scanner);

        System.out.print("Masukkan tanggal pengembalian (dd/MM/yyyy): ");
        Date tanggalPengembalian = readDate(scanner);

        // Validasi tanggal pengembalian
        if (tanggalPengembalian.compareTo(tanggalPeminjaman) <= 0) {
            System.out.println("Tanggal pengembalian tidak valid.");
            return;
        }

        // Set informasi peminjam ke kebaya
        Peminjam peminjam = new Peminjam(namaPeminjam, nomorTelepon);
        peminjam.setTanggalPeminjaman(tanggalPeminjaman);
        peminjam.setTanggalPengembalian(tanggalPengembalian);
        kebaya.setPeminjam(peminjam);
        kebaya.setTersedia(false);

        // Proses pembayaran
        System.out.print("Masukkan jumlah pembayaran: ");
        double pembayaran = scanner.nextDouble();

        double hargaSewa = kebaya.getHargaSewa();

        if (pembayaran >= hargaSewa) {
            System.out.println("Pembayaran berhasil. Kembalian: " + (pembayaran - hargaSewa));
            peminjam.setLunas(true);
        } else {
            System.out.println("Pembayaran tidak mencukupi. Pembayaran dibatalkan.");
            kebaya.setPeminjam(null);
            kebaya.setTersedia(true);
            return;
        }

        System.out.println("Kebaya berhasil dipinjam oleh " + namaPeminjam + ".");
    }


    private Date readDate(Scanner scanner) {
        Date date = null;
        boolean validInput = false;
        while (!validInput) {
            try {
                String dateString = scanner.nextLine();
                date = dateFormat.parse(dateString);
                validInput = true;
            } catch (ParseException e) {
                System.out.print("Format tanggal tidak valid. Masukkan kembali (dd/MM/yyyy): ");
            }
        }
        return date;
    }
    private Kebaya findKebayaById(int id) {
        for (Kebaya kebaya : kebayas) {
            if (kebaya.getId() == id) {
                return kebaya;
            }
        }
        return null;
    }
    public void returnKebaya() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan ID Kebaya yang akan dikembalikan: ");
        int kebayaId = scanner.nextInt();

        Kebaya kebaya = findKebayaById(kebayaId);
        if (kebaya == null || kebaya.isTersedia()) {
            System.out.println("Kebaya tidak sedang dipinjam atau ID tidak valid.");
            return;
        }

        Peminjam peminjam = kebaya.getPeminjam();
        Date tanggalPeminjaman = peminjam.getTanggalPeminjaman();
        Date tanggalPengembalian = peminjam.getTanggalPengembalian();
        double hargaSewa = kebaya.getHargaSewa();
        boolean lunas = peminjam.isLunas();

        System.out.println("Informasi Peminjaman:");
        System.out.println("Nama Peminjam: " + peminjam.getNama());
        System.out.println("Nomor Telepon: " + peminjam.getNomorTelepon());
        System.out.println("Tanggal Peminjaman: " + dateFormat.format(tanggalPeminjaman));
        System.out.println("Tanggal Pengembalian: " + dateFormat.format(tanggalPengembalian));
        System.out.println("Harga Sewa: " + hargaSewa);
        System.out.println("Status Pembayaran: " + (lunas ? "Lunas" : "Belum Lunas"));

        // Logika pembayaran dan pengembalian kebaya
        if (!lunas) {
            System.out.println("Pembayaran belum lunas.");
        } else {
            System.out.println("Pembayaran sudah lunas.");
        }

        // Kembalikan kebaya dan atur status ke tersedia
        kebaya.setPeminjam(null);
        kebaya.setTersedia(true);

        // Set informasi peminjaman ke kebaya
        kebaya.setTanggalPeminjaman(tanggalPeminjaman);
        kebaya.setTanggalPengembalian(tanggalPengembalian);
        kebaya.setLunas(lunas);

        System.out.println("Kebaya berhasil dikembalikan.");
        System.out.println("Terimakasih sudah menyewa di BUTIK ANARA");
    }

    public void searchKebaya() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan ID kebaya yang ingin dicari: ");
        int kebayaId = scanner.nextInt();

        Kebaya kebaya = findKebayaById(kebayaId);

        if (kebaya != null) {
            if (!kebaya.isTersedia()) {
                Peminjam peminjam = kebaya.getPeminjam();
                Date tanggalPeminjaman = peminjam.getTanggalPeminjaman();
                Date tanggalPengembalian = peminjam.getTanggalPengembalian();
                System.out.println("-------------------------------");
                System.out.println("     Kebaya sedang disewakan.");
                System.out.println("===============================");
                System.out.println("       Informasi Peminjam");
                System.out.println("===============================");
                System.out.println("Nama Peminjam: " + peminjam.getNama());
                System.out.println("Nomor Telepon: " + peminjam.getNomorTelepon());
                System.out.println("Tanggal Peminjaman: " + dateFormat.format(tanggalPeminjaman));
                System.out.println("Tanggal Pengembalian: " + dateFormat.format(tanggalPengembalian));
            } else {
                System.out.println("-------------------------------");
                System.out.println("Kebaya tersedia untuk disewakan.");
                System.out.println("===============================");
                System.out.println("       Informasi Kebaya");
                System.out.println("===============================");
                System.out.println(kebaya);
            }
        } else {
            System.out.println("Kebaya dengan ID '" + kebayaId + "' tidak ditemukan.");
        }
    }

    private Kebaya findKebayaByNama(String nama) {
        for (Kebaya kebaya : kebayas) {
            if (kebaya.getNama().equalsIgnoreCase(nama)) {
                return kebaya;
            }
        }
        return null;
    }

}
