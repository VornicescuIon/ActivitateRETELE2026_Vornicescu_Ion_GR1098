package ro.ase.cts.main;

import ro.ase.cts.clase.Internare;
import ro.ase.cts.clase.InternareBuilder;
import ro.ase.cts.clase.InternareBuilderAlternativ;

public class Main {
    public static void main(String[] args) {
        Internare internare=new Internare(true, false, true, true, "Mihai Popescu");
        Internare internare2=new Internare(false, false, false, false, "Ana Maria");

        InternareBuilder builder=new InternareBuilder("Ana Pop");
        Internare internare3=builder.build();

        Internare internare4=builder.setNume("Andrei Matei").setPapuci(true).setMicDejun(false).setPapuci(true).setHalat(true).build();

        System.out.println(internare3.toString());
        System.out.println(internare4.toString());

        InternareBuilderAlternativ builderAlternativ=new InternareBuilderAlternativ(false, false, false, false);
        builderAlternativ.setMicDejun(true);
        Internare internare101=builderAlternativ.build("Popescu Ion");
        Internare internare102=builderAlternativ.build("Georgescu Maria");

        internare101.setNumePacient("Popescu Ionel");
        System.out.println(internare101.toString());
        System.out.println(internare102.toString());
    }
}
