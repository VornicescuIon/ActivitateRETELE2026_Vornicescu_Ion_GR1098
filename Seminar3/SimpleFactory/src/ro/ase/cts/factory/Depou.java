package ro.ase.cts.factory;

import ro.ase.cts.clase.Autobuz;
import ro.ase.cts.clase.MijlocTransport;
import ro.ase.cts.clase.Tramvai;
import ro.ase.cts.clase.Troleibuz;

public class Depou {
    public MijlocTransport getMijlocTransport(Tipuri tip, int numarRoti, String numarInmatriculare){
        if(tip == Tipuri.AUTOBUZ){
            return new Autobuz(numarRoti, numarInmatriculare);
        }
        if(tip == Tipuri.TRAMVAI){
            return new Tramvai(numarRoti, numarInmatriculare);
        }
        if(tip == Tipuri.TROLEIBUZ){
            return new Troleibuz(numarRoti, numarInmatriculare);
        }

        return null;
    }
}
