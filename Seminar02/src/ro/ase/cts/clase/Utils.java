package ro.ase.cts.clase;

import ro.ase.cts.reader.AplicantiReader;
import ro.ase.cts.reader.AngajatReader;
import ro.ase.cts.reader.ElevReader;
import ro.ase.cts.reader.StudentReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

	public static List<Elev> readPupil(String file) throws FileNotFoundException {
		AplicantiReader reader = new ElevReader("elevi.txt");
		List<Aplicant> aplicanti = reader.readAplicant();
		List<Elev> elevi = new ArrayList<>();
		for(Aplicant a : aplicanti)
			elevi.add((Elev) a);
		return elevi;
	}

	public static List<Student> readStudents(String file) throws FileNotFoundException {
		AplicantiReader reader = new StudentReader("studenti.txt");
		List<Aplicant> aplicanti = reader.readAplicant();
		List<Student> studenti = new ArrayList<>();
		for(Aplicant a : aplicanti)
			studenti.add((Student) a);
		return studenti;
	}

	public static List<Angajat> readAngajati(String file) throws FileNotFoundException {
		AplicantiReader reader = new AngajatReader("angajati.txt");
		List<Aplicant> aplicanti = reader.readAplicant();
		List<Angajat> angajati = new ArrayList<>();
		for(Aplicant a : aplicanti)
			angajati.add((Angajat) a);
		return angajati;
	}

	public static void readAll() {
		List<Elev> listaelevi;
		List<Student> listastudenti;
		List<Angajat> listaangajati;

		try {
			listaelevi = Utils.readPupil("elevi.txt");
			for (int i = 0; i < listaelevi.size(); i++)
				System.out.println(listaelevi.get(i).toString());

			listastudenti = Utils.readStudents("studenti.txt");
			for (int i = 0; i < listastudenti.size(); i++)
				System.out.println(listastudenti.get(i).toString());

			listaangajati = Utils.readAngajati("angajati.txt");
			for (int i = 0; i < listaangajati.size(); i++)
				System.out.println(listaangajati.get(i).toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}