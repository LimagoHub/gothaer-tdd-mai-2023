package math;

public class MultipliziererImpl implements Multiplizierer{
    @Override
    public long mult(int a, int b) {
        var result = 0L;

        for(var i = 0; i < a; i ++)
            result += b;

        return result;
    }
}
