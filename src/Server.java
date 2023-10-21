import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.lang.annotation.Repeatable;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyPair;
import java.util.*;

public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket ss = new ServerSocket(1234);

        Socket sc = ss.accept();

        ObjectInputStream input = new ObjectInputStream(
                sc.getInputStream()
        );

        ObjectOutputStream output = new ObjectOutputStream(
                sc.getOutputStream()
        );

        Request requestFromClient = (Request) input.readObject();

        List<Response> responseList = getCharacterFrequency(requestFromClient.getInput());

        output.writeObject(responseList);

        sc.close();

    }

    private static List<Response> getCharacterFrequency(String message) {
        Map<Character, Integer> frequencies = new HashMap<>();
        for (char ch : message.toCharArray()) {
            if (Character.isAlphabetic(ch)) {
                if (frequencies.containsKey(ch))
                    frequencies.put(ch, frequencies.get(ch) + 1);
                else
                    frequencies.put(ch, 1);
            }
        }

        Integer[] values = frequencies.values().toArray(new Integer[0]);
        Arrays.sort(values);

        int maxCount = values[values.length - 1];

        List<Response> responseList = new ArrayList<>();

        for (Map.Entry<Character, Integer> entries : frequencies.entrySet()) {
            if (entries.getValue() == maxCount)
                responseList.add(
                    new Response(entries.getKey(), entries.getValue())
            );
        }

        return responseList;

    }

}
