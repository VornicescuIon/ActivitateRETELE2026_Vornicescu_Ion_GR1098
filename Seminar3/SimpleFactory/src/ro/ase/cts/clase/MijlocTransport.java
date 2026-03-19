package ro.ase.cts.clase;

public abstract class MijlocTransport {
    protected int numarRoti;
    protected String numarInmatriculare;

    public MijlocTransport(int numarRoti, String numarInmatriculare) {
        this.numarRoti = numarRoti;
        this.numarInmatriculare = numarInmatriculare;
    }

    public abstract void afiseazaDescriere();
}
