package pl.alior.bank.serwisy;

import pl.alior.bank.model.WarunkiKredytowe;

import java.util.Arrays;
/**
 * Serwis obsługujący warunki kredytowe.
 */
public class SerwisWarunkowKredytowych {

    public WarunkiKredytowe zwrocWarunkiKredytowe(int maksymalnyOkresKredytowania) {
        return Arrays.stream(WarunkiKredytowe.values())
                .filter(warunki -> maksymalnyOkresKredytowania >= warunki.getMinOkres() && maksymalnyOkresKredytowania <= warunki.getMaxOkres())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Brak oferty dla danego okresu kredytowania"));
    }
}
