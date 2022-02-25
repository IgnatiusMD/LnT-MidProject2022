
public class Karyawan {
	private String kode,nama,jenisK,jabatan;
	private int gaji;
	
	public String getKode() {
		return kode;
	}
	public void setKode(String kode) {
		this.kode = kode;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getJenisK() {
		return jenisK;
	}
	public void setJenisK(String jenisK) {
		this.jenisK = jenisK;
	}
	public String getJabatan() {
		return jabatan;
	}
	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}
	public int getGaji() {
		return gaji;
	}
	public void setGaji(int gaji) {
		this.gaji = gaji;
	}
	
	public Karyawan(String kode, String nama, String jenisK, String jabatan, int gaji) {
		this.kode = kode;
		this.nama = nama;
		this.jenisK = jenisK;
		this.jabatan = jabatan;
		this.gaji = gaji;
	}
}
