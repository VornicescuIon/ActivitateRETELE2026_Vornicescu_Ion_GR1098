package ro.ase.cts.main;

import ro.ase.cts.clase.MijlocTransport;
import ro.ase.cts.factory.Depou;
import ro.ase.cts.factory.Tipuri;

public class Main {
    public static void main(String[] args){
        Depou depou = new Depou();
        MijlocTransport autobuz = depou.getMijlocTransport(Tipuri.AUTOBUZ, 4, "B31CTS");
        MijlocTransport tramvai = depou.getMijlocTransport(Tipuri.TRAMVAI, 4, "B31CTS");


        autobuz.afiseazaDescriere();
        tramvai.afiseazaDescriere();
    }
}
