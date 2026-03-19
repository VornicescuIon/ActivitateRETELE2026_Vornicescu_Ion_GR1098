package ro.ase.cts.clase;

public class Troleibuz extends MijlocTransport{
    public Troleibuz(int numarRoti, String numarInmatriculare) {
        super(numarRoti, numarInmatriculare);
    }

    @Override
    public void afiseazaDescriere() {
        StringBuilder sb = new StringBuilder();
        sb.append("Numar roti: ").append(super.numarRoti);
        sb.append("Numar inmatriculare: ").append(super.numarInmatriculare);
    }

    }
