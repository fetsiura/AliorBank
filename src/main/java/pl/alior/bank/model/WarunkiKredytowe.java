package pl.alior.bank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WarunkiKredytowe {
    OKRES_6_12(0.6, 0.02, 6, 12),
    OKRES_13_36(0.6, 0.03, 13, 36),
    OKRES_37_60(0.5, 0.03, 37, 60),
    OKRES_61_100(0.55, 0.03, 61, 100);

    private final double dti;
    private final double oprocentowanieWSkaliRoku;
    private final int minOkres;
    private final int maxOkres;
}
