import java.io.Serializable;

public class Response implements Serializable {

    private char alpha;
    private int count;

    public Response(char ch, int count) {
        this.alpha = ch;
        this.count = count;
    }

    public char getAlpha() {
        return alpha;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return alpha + " : " + count;
    }
}
