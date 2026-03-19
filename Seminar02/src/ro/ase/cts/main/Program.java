package ro.ase.cts.main;

import ro.ase.cts.clase.Angajat;
import ro.ase.cts.clase.Aplicant;
import ro.ase.cts.reader.AplicantiReader;
import ro.ase.cts.reader.AngajatReader;

import java.io.FileNotFoundException;
import java.util.List;


public class Program {

	public static void main(String[] args) {
		Angajat.setValoare_fin(50);
		List<Aplicant> listaAngajati;
		try {
			AplicantiReader angajatReader = new AngajatReader("angajati.txt");
			listaAngajati = angajatReader.readAplicant();
			for(Aplicant angajat:listaAngajati) {
				System.out.println(angajat.toString());
				angajat.afiseazaFinantare();
				angajat.afiseazaStatut();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}