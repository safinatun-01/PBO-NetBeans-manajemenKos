package penghuni;

public class Penghuni extends Orang {
    private String jenisKelamin;
    private String nomorKamar;
    private String tanggalMasuk;
    private String status;

    public Penghuni(String nama,
                    String noHp,
                    String jenisKelamin,
                    String nomorKamar,
                    String tanggalMasuk,
                    String status) {

        this.nama = nama;
        this.noHp = noHp;
        this.jenisKelamin = jenisKelamin;
        this.nomorKamar = nomorKamar;
        this.tanggalMasuk = tanggalMasuk;
        this.status = status;
    }
    public String getJenisKelamin()     { return jenisKelamin;  }
    public String getNomorKamar()       { return nomorKamar;    }
    public String getTanggalMasuk()     { return tanggalMasuk;  }
    public String getStatusPenghuni()   { return status;        }
}