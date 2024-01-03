import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RentalManager rentalManager = new RentalManager();

        int choice;

        do {
            System.out.println("-----------------------------");
            System.out.println("      Selamat datang di");
            System.out.println("         BUTIK ANARA");
            System.out.println("-----------------------------");
            System.out.println("Menu:");
            System.out.println("1. Daftar Kebaya");
            System.out.println("2. Peminjaman Kebaya");
            System.out.println("3. Pengembalian Kebaya");
            System.out.println("4. Cari Kebaya");
            System.out.println("5. Keluar");
            System.out.println("-----------------------------");
            System.out.print("Pilih menu (1-5): ");
            choice = scanner.nextInt();
            System.out.println("-----------------------------");
            switch (choice) {
                case 1:
                    rentalManager.displayAvailableKebayas();
                    break;
                case 2:
                    rentalManager.rentKebaya();
                    break;
                case 3:
                    rentalManager.returnKebaya();
                    break;
                case 4:
                    rentalManager.searchKebaya();
                    break;
                case 5:
                    System.out.println("Terima kasih. Sampai Jumpa kembali di Butik ANARA.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    break;
            }
        } while (choice != 5);
    }
}
