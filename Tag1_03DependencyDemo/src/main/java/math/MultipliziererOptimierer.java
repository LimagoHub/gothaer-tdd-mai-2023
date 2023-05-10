package math;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MultipliziererOptimierer implements Multiplizierer{

    private final Multiplizierer multiplizierer;

    public long mult(final int a, final int b) {
        if(a<b)
            return multiplizierer.mult(a, b);
        else
            return multiplizierer.mult(b, a);
    }
}
