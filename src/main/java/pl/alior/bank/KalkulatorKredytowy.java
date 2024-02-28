package pl.alior.bank;

import pl.alior.bank.model.DaneWejsciowe;
import pl.alior.bank.model.Oferta;
import pl.alior.bank.serwisy.SerwisOfertowy;
import pl.alior.bank.serwisy.SerwisWarunkowKredytowych;

import java.math.BigDecimal;
import java.util.TreeMap;

public class KalkulatorKredytowy {

    public static void main(String[] args) {
        SerwisWarunkowKredytowych serwisWarunkowKredytowych = new SerwisWarunkowKredytowych();
        SerwisOfertowy serwisOfertowy = new SerwisOfertowy(serwisWarunkowKredytowych);

        DaneWejsciowe dane1 = new DaneWejsciowe(6, new BigDecimal(4_000), new BigDecimal(1_000), BigDecimal.ZERO, BigDecimal.ZERO);
        DaneWejsciowe dane2 = new DaneWejsciowe(5, new BigDecimal(4_000), new BigDecimal(1_000), BigDecimal.ZERO, BigDecimal.ZERO);
        DaneWejsciowe dane3 = new DaneWejsciowe(78, new BigDecimal(10_000), new BigDecimal(1_500), new BigDecimal(1_000), new BigDecimal(2_000));
        DaneWejsciowe dane4 = new DaneWejsciowe(200, new BigDecimal(3_000), new BigDecimal(500), new BigDecimal(500), BigDecimal.ZERO);
        DaneWejsciowe dane5 = new DaneWejsciowe(18, new BigDecimal(6_000), new BigDecimal(4_500), new BigDecimal(12_000), new BigDecimal(6_000));

        wyswietlOferty(serwisOfertowy.wyliczOfertyKredytowe(dane1), dane1);
        wyswietlOferty(serwisOfertowy.wyliczOfertyKredytowe(dane2), dane2);
        wyswietlOferty(serwisOfertowy.wyliczOfertyKredytowe(dane3), dane3);
        wyswietlOferty(serwisOfertowy.wyliczOfertyKredytowe(dane4), dane4);
        wyswietlOferty(serwisOfertowy.wyliczOfertyKredytowe(dane5), dane5);
    }

    private static void wyswietlOferty(TreeMap<String, Oferta> ofertyKredytowe, DaneWejsciowe daneWejsciowe) {
        if (ofertyKredytowe.isEmpty()) {
            System.out.println("---------------");
            System.out.println(daneWejsciowe);
            System.out.println("Brak ofert");
            System.out.println("---------------");

        } else {
            System.out.println("---------------");
            System.out.println(daneWejsciowe);
            ofertyKredytowe.forEach((nazwaOferty, szczegoly) -> {
                System.out.println(nazwaOferty);
                System.out.println(szczegoly);
            });
            System.out.println("---------------");
        }
    }

}