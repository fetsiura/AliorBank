package pl.alior.bank.common;

import java.math.BigDecimal;

public class KonstantyKredytowe {
    public static final int OGRANICZENIE_GORNE_OKRESU_KREDYTOWANIA = 100;
    public static final BigDecimal MAKSYMALNA_KWOTA_KREDYTU = new BigDecimal(150_000);
    public static final BigDecimal MAKSYMALNE_ZAANGAZOWANIE = new BigDecimal(200_000);
    public static final int MINIMALNY_OKRES_KREDYTOWANIA = 6;
    public static final BigDecimal MINIMALNA_KWOTA_KREDYTU = new BigDecimal(5_000);

    private KonstantyKredytowe() {
    }
}
