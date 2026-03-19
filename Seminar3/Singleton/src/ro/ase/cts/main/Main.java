package ro.ase.cts.main;

import ro.ase.cts.clase.Hotel;

public class Main {
    static void main(String[] args) {
        Hotel hotel1 = Hotel.getInstance("Intercont", 7, 3);
        Hotel hotel2 = Hotel.getInstance("MegaHotel", 200, 100);

        hotel1.rezervaCamere();
        hotel1.rezervaCamere();

        hotel2.rezervaCamere();
        hotel2.rezervaCamere();

    }
}
