package ro.ase.cts.clase;

import java.util.Arrays;

public class Student extends Aplicant {
	protected String facultate;
	protected int an_studii;
	private static int val_fin =20;


	public void setFacultate(String facultate) {
		this.facultate = facultate;
	}
	public void setAn_studii(int an_studii) {
		this.an_studii = an_studii;
	}

	public static void setVal_fin(int val_fin) {
		Student.val_fin = val_fin;
	}

	public Student() {
		super();

	}

	@Override
	public void afiseazaFinantare() {
		System.out.println("Studentul "+getNume()+" "+getPrenume()+" primeste "+Student.val_fin +" Euro/zi in proiect.");
	}

	public Student(String nume, String prenume, int varsta, int punctaj, int nr_proiecte, String[] denumireProiect, String facultate, int an_studii) {
		super(nume,prenume,varsta,punctaj,nr_proiecte,denumireProiect);
		this.facultate = facultate;
		this.an_studii = an_studii;
	}
	@Override
	public String toString() {
		return "Student: Nume=" + nume + ", Prenume=" + prenume + ", Varsta=" + varsta + ", Punctaj=" + punctaj + ", Nr_proiecte=" + nr_proiecte +  ", DenumireProiect=" + Arrays.toString(denumireProiect) + ", Facultate=" + facultate + ", An_studii=" + an_studii ;
	}

}