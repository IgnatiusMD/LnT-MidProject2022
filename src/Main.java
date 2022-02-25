import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Main {
	Scanner sc = new Scanner(System.in);
	Vector<Karyawan> vKaryawan = new Vector<>();
	Random random = new Random();
	int countM=0, countS=0, countA=0;
	
	public void Ascending() {
		for (int i=0; i<vKaryawan.size();i++) {
			for (int j=0; j<vKaryawan.size()-1;j++) {
				
				String name1 = vKaryawan.get(j).getNama();
				String name2 = vKaryawan.get(j+1).getNama();
				
				if (name1.compareTo(name2) > 0) {
					Karyawan temp = vKaryawan.get(j);
					vKaryawan.set(j, vKaryawan.get(j+1));
					vKaryawan.set(j+1, temp);
				}
				
			}
		}
	}
	
//	public void ViewCounts() {
//		System.out.println("=============================");
//		System.out.println("countM -> " + countM);
//		System.out.println("countS -> " + countS);
//		System.out.println("countA -> " + countA);
//		System.out.println("=============================");
//	}
	
	public void MainMenu() {
		int choose=0;
		do {
			System.out.println("1. Insert Data Karyawan");
			System.out.println("2. View Data Karyawan");
			System.out.println("3. Update Data Karyawan");
			System.out.println("4. Delete Data Karyawan");
			System.out.printf(">> ");
			choose = sc.nextInt(); sc.nextLine();
		} while (choose<=0 || choose>=5);
		
		switch (choose) {
		case 1:
			Insert();
			break;
		case 2:
			View();
			System.out.println("Press Enter to Return...");
			sc.nextLine();
			MainMenu();
			break;
		case 3:
			Update();
			break;
		case 4:
			Delete();
			break;
		}
	}
	
	public void Insert() {
		String namaKaryawan="", jenisKelamin="", jabatan="";
		int gaji=0;
		
		String kodeKaryawan="";
		kodeKaryawan += (char) (random.nextInt(26) + 'A');
		kodeKaryawan += (char) (random.nextInt(26) + 'A');
		kodeKaryawan += "-";
		kodeKaryawan += random.nextInt(10);
		kodeKaryawan += random.nextInt(10);
		kodeKaryawan += random.nextInt(10);
		kodeKaryawan += random.nextInt(10);
		
		do {
			System.out.println("Input Nama Karyawan [>= 3]: ");
			namaKaryawan = sc.nextLine();
		} while (namaKaryawan.length()<3);
		
		do {
			System.out.println("Input Jenis Kelamin [Laki-Laki | Perempuan] (Case Sensitive): ");
			jenisKelamin = sc.nextLine();
		} while (!(jenisKelamin.equals("Laki-Laki") || jenisKelamin.equals("Perempuan")));
		
		do {
			System.out.println("Input Jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
			jabatan = sc.nextLine();
		} while(!(jabatan.equals("Manager") || jabatan.equals("Supervisor") || jabatan.equals("Admin")));
		
		if (jabatan.equals("Manager")) {
			gaji = 8000000;
			countM++;
		} else if (jabatan.equals("Supervisor")) {
			gaji = 6000000;
			countS++;
		} else if (jabatan.equals("Admin")) {
			gaji = 4000000;
			countA++;
		}
		
		vKaryawan.add(new Karyawan(kodeKaryawan, namaKaryawan, jenisKelamin, jabatan, gaji));
		
		System.out.println("Berhasil menambah karyawan dengan id " + kodeKaryawan);
		
		int currM=0, currS=0, currA=0;
		
		Vector<String> ManagerGajiBaru = new Vector<>();
		Vector<String> SupervisorGajiBaru = new Vector<>();
		Vector<String> AdminGajiBaru = new Vector<>();
		for (int i=0;i<vKaryawan.size();i++) {
			int gajiBaruManager = (int) (8000000 + (8000000*0.1));
			int gajiBaruSupervisor = (int) (6000000 + (6000000*0.75));
			int gajiBaruAdmin = (int) (4000000 + (4000000*0.05));
			int tempM = countM-1;
			int tempS = countS-1;
			int tempA = countA-1;
			if (vKaryawan.get(i).getJabatan().equals("Manager") && currM!=tempM && countM%3==1 && countM!=1) {
				ManagerGajiBaru.add(vKaryawan.get(i).getKode());
				vKaryawan.get(i).setGaji(gajiBaruManager);
				currM++;
			} else if (currM==tempM && currM!=0) {
				System.out.print("Bonus sebesar 10% telah diberikan kepada karyawan dengan id ");
				for (int j=0;j<ManagerGajiBaru.size();j++) {
					System.out.printf(ManagerGajiBaru.get(j));
					if (j!=ManagerGajiBaru.size()-1) {
						System.out.printf(", ");
					} else {
						System.out.println();
					}
				}
			} else if (vKaryawan.get(i).getJabatan().equals("Supervisor") && currS!=tempS && countS%3==1 && countS!=1) {
				SupervisorGajiBaru.add(vKaryawan.get(i).getKode());
				vKaryawan.get(i).setGaji(gajiBaruSupervisor);
				currS++;
			} else if (currS==tempS && currS!=0) {
				System.out.print("Bonus sebesar 7.5% telah diberikan kepada karyawan dengan id ");
				for (int j=0;j<SupervisorGajiBaru.size();j++) {
					System.out.printf(SupervisorGajiBaru.get(j));
					if (j!=SupervisorGajiBaru.size()-1) {
						System.out.printf(", ");
					} else {
						System.out.println();
					}
				}
			} else if (vKaryawan.get(i).getJabatan().equals("Admin") && currA!=tempA && countA%3==1 && countA!=1) {
				AdminGajiBaru.add(vKaryawan.get(i).getKode());
				vKaryawan.get(i).setGaji(gajiBaruAdmin);
				currA++;
			} else if (currA==tempA && currA!=0) {
				System.out.print("Bonus sebesar 5% telah diberikan kepada karyawan dengan id ");
				for (int j=0;j<AdminGajiBaru.size();j++) {
					System.out.printf(AdminGajiBaru.get(j));
					if (j!=AdminGajiBaru.size()-1) {
						System.out.printf(", ");
					} else {
						System.out.println();
					}
				}
			}
		}
		
		System.out.println("Press Enter to Return...");
		sc.nextLine();
		
		MainMenu();
	}
	
	public void View() {
		if (vKaryawan.isEmpty()) {
			System.out.println("Tidak ada Data!");
			System.out.println("Press Enter to Return...");
			sc.nextLine();
			MainMenu();
		} else {
			Ascending();
			System.out.println("=============================================");
			for (int i=0;i<vKaryawan.size();i++) {
				System.out.println("No. " + (i+1));
				System.out.println("Kode Karyawan	: " + vKaryawan.get(i).getKode());
				System.out.println("Nama		: " + vKaryawan.get(i).getNama());
				System.out.println("Jenis Kelamin	: " + vKaryawan.get(i).getJenisK());
				System.out.println("Jabatan		: " + vKaryawan.get(i).getJabatan());
				System.out.println("Gaji		: Rp. " + vKaryawan.get(i).getGaji());
				System.out.println("=============================================");
//				ViewCounts();
			}
		}
	}
	
	public void Update() {
		View();
		int index=0;
		
		do {
			System.out.println("Input index you want to update");
			System.out.printf(">> ");
			index = sc.nextInt(); sc.nextLine();
		} while (index<1 || index>vKaryawan.size());
		
		String kodekar = vKaryawan.get(index-1).getKode();
		String nama = vKaryawan.get(index-1).getNama();
		String jeniskel = vKaryawan.get(index-1).getJenisK();
		String jabt = vKaryawan.get(index-1).getJabatan();
		int gaji = vKaryawan.get(index-1).getGaji();
		
		do {
			System.out.println("Input Nama Karyawan [>= 3]: ");
			nama = sc.nextLine();
		} while (nama.length()<3);
		
		do {
			System.out.println("Input Jenis Kelamin [Laki-Laki | Perempuan] (Case Sensitive): ");
			jeniskel = sc.nextLine();
		} while (!(jeniskel.equals("Laki-Laki") || jeniskel.equals("Perempuan")));
		
		do {
			System.out.println("Input Jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
			jabt = sc.nextLine();
		} while(!(jabt.equals("Manager") || jabt.equals("Supervisor") || jabt.equals("Admin")));
		
		if (vKaryawan.get(index-1).getJabatan().equals("Manager")) {
			countM--;
		} else if (vKaryawan.get(index-1).getJabatan().equals("Supervisor")) {
			countS--;
		} else if(vKaryawan.get(index-1).getJabatan().equals("Admin")) {
			countA--;
		}
		
		if (jabt.equals("Manager")) {
			gaji = 8000000;
			countM++;
		} else if (jabt.equals("Supervisor")) {
			gaji = 6000000;
			countS++;
		} else if (jabt.equals("Admin")) {
			gaji = 4000000;
			countA++;
		}
		
		vKaryawan.get(index-1).setNama(nama);
		vKaryawan.get(index-1).setJenisK(jeniskel);
		vKaryawan.get(index-1).setJabatan(jabt);
		vKaryawan.get(index-1).setGaji(gaji);
		
		int currM=0, currS=0, currA=0;
		
		for (int i=0;i<vKaryawan.size();i++) {
			if (vKaryawan.get(i).getJabatan().equals("Manager")) {
				vKaryawan.get(i).setGaji(8000000);
			} else if (vKaryawan.get(i).getJabatan().equals("Supervisor")) {
				vKaryawan.get(i).setGaji(6000000);
			} else if (vKaryawan.get(i).getJabatan().equals("Admin")) {
				vKaryawan.get(i).setGaji(4000000);
			}
			int gajiBaruManager = (int) (8000000 + (8000000*0.1));
			int gajiBaruSupervisor = (int) (6000000 + (6000000*0.75));
			int gajiBaruAdmin = (int) (4000000 + (4000000*0.05));
			int tempM = countM-1;
			int tempS = countS-1;
			int tempA = countA-1;
			if (vKaryawan.get(i).getJabatan().equals("Manager") && currM!=tempM && countM%3==1 && countM!=1) {
				vKaryawan.get(i).setGaji(gajiBaruManager);
				currM++;
			} else if (vKaryawan.get(i).getJabatan().equals("Supervisor") && currS!=tempS && countS%3==1 && countS!=1) {
				vKaryawan.get(i).setGaji(gajiBaruSupervisor);
				currS++;
			} else if (vKaryawan.get(i).getJabatan().equals("Admin") && currA!=tempA && countA%3==1 && countA!=1) {
				vKaryawan.get(i).setGaji(gajiBaruAdmin);
				currA++;
			}
		}
		
		System.out.println("Berhasil update karyawan dengan id " + kodekar);
		System.out.println("Press Enter to Return...");
		sc.nextLine();
		MainMenu();
	}
	
	public void Delete() {
		View();
		int index=0;
		String confirmation="";
		
		do {
			System.out.println("Input index you want to delete");
			System.out.printf(">> ");
			index = sc.nextInt(); sc.nextLine();
		} while (index<1 || index>vKaryawan.size());
		
		String kodekar = vKaryawan.get(index-1).getKode();
		String nama = vKaryawan.get(index-1).getNama();
		String jeniskel = vKaryawan.get(index-1).getJenisK();
		String jabt = vKaryawan.get(index-1).getJabatan();
		int gaji = vKaryawan.get(index-1).getGaji();
		
		do {
			System.out.println("Are you sure you want to delete " + kodekar + "? [Yes | No]");
			confirmation = sc.nextLine();
		} while (!(confirmation.equals("Yes")));
		
		if (vKaryawan.get(index-1).getJabatan().equals("Manager")) {
			countM--;
		} else if (vKaryawan.get(index-1).getJabatan().equals("Supervisor")) {
			countS--;
		} else if(vKaryawan.get(index-1).getJabatan().equals("Admin")) {
			countA--;
		}
		
		vKaryawan.remove(index-1);
		System.out.println("Data deleted!");
		System.out.println("Press Enter to Return...");
		sc.nextLine();
		MainMenu();
		
	}
	
	public Main() {
		MainMenu();
	}

	public static void main(String[] args) {
		new Main();
	}

}
