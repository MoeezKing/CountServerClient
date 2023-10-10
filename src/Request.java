import java.io.Serializable;

public class Request implements Serializable {
    private String input;

    public Request(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }
}
