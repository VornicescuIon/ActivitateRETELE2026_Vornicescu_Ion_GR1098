package ro.ase.cts.reader;

import ro.ase.cts.clase.Aplicant;

import java.io.FileNotFoundException;
import java.util.List;

public interface AplicantiReader {
    List<Aplicant> readAplicant() throws FileNotFoundException;
}