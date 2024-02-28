package pl.alior.bank.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Formatter {
    public static BigDecimal formatujDoDwochLiczbPoPrzecinku(BigDecimal liczba) {
        return liczba.setScale(2, RoundingMode.HALF_UP);
    }
}
