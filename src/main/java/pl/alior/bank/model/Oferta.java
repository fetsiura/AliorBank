package pl.alior.bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Oferta {
    private int okresKredytowania;
    private BigDecimal rataMiesieczna;
    private BigDecimal kwotaKredytu;

    @Override
    public String toString() {
        return "OkresKredytowania " + okresKredytowania +
                "miesiecy, rata miesieczna " + rataMiesieczna +
                "PLN, kwota kredytu " + kwotaKredytu + "PLN";
    }
}
