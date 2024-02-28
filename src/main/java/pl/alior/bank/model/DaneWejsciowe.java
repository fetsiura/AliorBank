package pl.alior.bank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class DaneWejsciowe {
    private int okresZatrudnienia;
    private BigDecimal dochod;
    private BigDecimal kosztyUtrzymaia;
    private BigDecimal sumaZobowiazanKredytowych;
    private BigDecimal sumaSaldKredytowRatalnych;

    @Override
    public String toString() {
        return "Dla okresu zatrudnienia " + okresZatrudnienia +
                " miesiecy, dochodu " + dochod +
                "PLN, kosztow utrzymania " + kosztyUtrzymaia +
                "PLN, sumy zobowiazan kredytowych " + sumaZobowiazanKredytowych +
                "PLN, sumy sald kredytow ratalnych " + sumaSaldKredytowRatalnych + "PLN";
    }
}
