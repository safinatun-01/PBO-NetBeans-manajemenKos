package config;
import java.awt.Color;
import java.awt.Font;


public class UIConfig {
    public static void setHeaderTabel(javax.swing.JTable tabel) {
        tabel.getTableHeader().setReorderingAllowed(false);    
        tabel.getTableHeader().setBackground(new Color(90,120,150));
        tabel.getTableHeader().setForeground(new Color(255, 255, 255));
        tabel.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
    }
}