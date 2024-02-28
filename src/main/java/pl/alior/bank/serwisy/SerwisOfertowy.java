package pl.alior.bank.serwisy;

import lombok.RequiredArgsConstructor;
import pl.alior.bank.common.KonstantyKredytowe;
import pl.alior.bank.model.DaneWejsciowe;
import pl.alior.bank.model.Oferta;
import pl.alior.bank.model.WarunkiKredytowe;

import java.math.BigDecimal;
import java.util.TreeMap;
import java.util.stream.IntStream;

import static pl.alior.bank.common.Formatter.formatujDoDwochLiczbPoPrzecinku;

/**
 * Serwis obsługujący oferty kredytowe.
 */
@RequiredArgsConstructor
public class SerwisOfertowy {
    private final SerwisWarunkowKredytowych serwisWarunkowKredytowych;

    public TreeMap<String, Oferta> wyliczOfertyKredytowe(DaneWejsciowe daneWejsciowe) {
        TreeMap<String, Oferta> wyliczoneOferty = new TreeMap<>();
        int maksymalnyOkresKredytowania = maksymalnyOkresKredytowania(daneWejsciowe);

        if (maksymalnyOkresKredytowania < KonstantyKredytowe.MINIMALNY_OKRES_KREDYTOWANIA) {
            return wyliczoneOferty;
        }

        IntStream.rangeClosed(KonstantyKredytowe.MINIMALNY_OKRES_KREDYTOWANIA, maksymalnyOkresKredytowania)
                .forEach(okresKredytowania -> {
                    dodajOferte(daneWejsciowe, wyliczoneOferty, okresKredytowania);
                });
        return wyliczoneOferty;
    }

    private void dodajOferte(DaneWejsciowe daneWejsciowe, TreeMap<String, Oferta> wyliczoneOferty, int okresKredytowania) {
        WarunkiKredytowe warunkiKredytowe = serwisWarunkowKredytowych.zwrocWarunkiKredytowe(okresKredytowania);
        BigDecimal rataMiesieczna = formatujDoDwochLiczbPoPrzecinku(maksymalnaMiesiecznaRata(daneWejsciowe, warunkiKredytowe));
        BigDecimal kwotaKredytu = formatujDoDwochLiczbPoPrzecinku(maksymalnaKwotaKredytu(daneWejsciowe, warunkiKredytowe, rataMiesieczna, okresKredytowania));
        if (kwotaKredytu.compareTo(KonstantyKredytowe.MINIMALNA_KWOTA_KREDYTU) >= 0) {
            String nazwaOferty = zwrocNazweOferty(warunkiKredytowe);

            wyliczoneOferty.put(nazwaOferty, new Oferta(okresKredytowania, rataMiesieczna, kwotaKredytu));
        }
    }


    private int maksymalnyOkresKredytowania(DaneWejsciowe daneWejsciowe) {
        return Math.min(daneWejsciowe.getOkresZatrudnienia(), KonstantyKredytowe.OGRANICZENIE_GORNE_OKRESU_KREDYTOWANIA);
    }

    private BigDecimal maksymalnaMiesiecznaRata(DaneWejsciowe daneWejsciowe, WarunkiKredytowe warunkiKredytowe) {
        BigDecimal DZ = daneWejsciowe.getDochod();
        BigDecimal KU = daneWejsciowe.getKosztyUtrzymaia();
        BigDecimal ZK = daneWejsciowe.getSumaZobowiazanKredytowych();
        BigDecimal dti = BigDecimal.valueOf(warunkiKredytowe.getDti());
        return (DZ.subtract(KU).subtract(ZK)).min(dti.multiply(DZ).subtract(ZK));
    }

    private BigDecimal maksymalnaKwotaKredytu(DaneWejsciowe daneWejsciowe, WarunkiKredytowe warunkiKredytowe, BigDecimal maksMiesiecznaRata, double okresKredytowania) {
        double MI = warunkiKredytowe.getOprocentowanieWSkaliRoku() / 12.00;

        BigDecimal maksZaangazowanie = KonstantyKredytowe.MAKSYMALNE_ZAANGAZOWANIE.subtract(daneWejsciowe.getSumaSaldKredytowRatalnych());
        BigDecimal kwotaKredytu = BigDecimal.valueOf(maksMiesiecznaRata.doubleValue() * (1 - (Math.pow(1 + MI, -okresKredytowania))) / (MI));

        return maksZaangazowanie.min(KonstantyKredytowe.MAKSYMALNA_KWOTA_KREDYTU).min(kwotaKredytu);
    }

    private String zwrocNazweOferty(WarunkiKredytowe warunkiKredytowe) {
        return switch (warunkiKredytowe) {
            case OKRES_6_12 -> "Oferta 1";
            case OKRES_13_36 -> "Oferta 2";
            case OKRES_37_60 -> "Oferta 3";
            case OKRES_61_100 -> "Oferta 4";
            default -> throw new IllegalArgumentException("Nieprawidłowy okres kredytowania");
        };
    }


}
