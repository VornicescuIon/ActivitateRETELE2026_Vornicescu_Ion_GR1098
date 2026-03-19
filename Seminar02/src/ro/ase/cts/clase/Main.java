package ro.ase.cts.clase;

import ro.ase.cts.reader.AplicantiReader;
import ro.ase.cts.reader.AngajatReader;
import ro.ase.cts.reader.ElevReader;
import ro.ase.cts.reader.StudentReader;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            AplicantiReader elevReader = new ElevReader("elevi.txt");
            List<Aplicant> listaElevi = elevReader.readAplicant();
            for(Aplicant elev : listaElevi) {
                System.out.println(elev.toString());
                elev.afiseazaFinantare();
                elev.afiseazaStatut();
            }

            AplicantiReader studentReader = new StudentReader("studenti.txt");
            List<Aplicant> listaStudenti = studentReader.readAplicant();
            for(Aplicant student : listaStudenti) {
                System.out.println(student.toString());
                student.afiseazaFinantare();
                student.afiseazaStatut();
            }

            AplicantiReader angajatReader = new AngajatReader("angajati.txt");
            List<Aplicant> listaAngajati = angajatReader.readAplicant();
            for(Aplicant angajat : listaAngajati) {
                System.out.println(angajat.toString());
                angajat.afiseazaFinantare();
                angajat.afiseazaStatut();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}