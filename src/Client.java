import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket sc = new Socket("localhost", 1234);

        ObjectOutputStream output = new ObjectOutputStream(
                sc.getOutputStream()
        );

        ObjectInputStream input = new ObjectInputStream(
                sc.getInputStream()
        );

        String message = "Hello , Ali! How are you ?";

        Request request = new Request(message);
        output.writeObject(request);

        List<Response> responseList = (List<Response>) input.readObject();

        System.out.println(Arrays.toString(responseList.toArray()));

        sc.close();
    }
}
