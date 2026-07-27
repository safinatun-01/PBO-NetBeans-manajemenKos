package config; 
import javax.swing.*; // Mengambil library Swing untuk komponen UI (seperti JPanel)
import java.awt.*; // Mengambil library AWT untuk grafis dan warna


public class RoundedPanel extends JPanel { // Membuat class baru yang mewarisi sifat JPanel
    private int cornerRadius = 20; // Variabel untuk menentukan seberapa lengkung sudutnya (50 pixel)

 public RoundedPanel() { // Method yang otomatis jalan saat objek dibuat
    super(); // Menjalankan instruksi dari induk (JPanel)
    setOpaque(false); // Membuat background asli JPanel jadi transparan agar sudut lengkung terlihat
    setBackground(java.awt.Color.WHITE); // Mengatur warna default panel menjadi putih
}

    @Override // Menandakan kita memodifikasi method bawaan dari JPanel
    protected void paintComponent(Graphics g) { // Method khusus untuk menggambar komponen secara manual
        super.paintComponent(g); // Menjalankan proses gambar dasar dari induk
        Dimension arcs = new Dimension(cornerRadius, cornerRadius); // Menentukan diameter lengkungan (lebar & tinggi)
        int width = getWidth(); // Mengambil lebar panel saat ini
        int height = getHeight(); // Mengambil tinggi panel saat ini
        Graphics2D graphics = (Graphics2D) g; // Mengubah Graphics biasa ke Graphics2D agar fitur gambar lebih lengkap
        
        // Mengaktifkan Antialiasing agar garis lengkung terlihat halus dan tidak kasar/patah-patah
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Mengatur warna yang akan digunakan untuk mengisi panel sesuai background yang diset
        graphics.setColor(getBackground());
        
        // Menggambar persegi dengan sudut melengkung dan mengisinya dengan warna
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
        
        // Bagian di bawah ini dimatikan (komentar), gunanya jika ingin menggambar garis tepi (border)
        // graphics.setColor(getForeground());
        // graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
    }
}