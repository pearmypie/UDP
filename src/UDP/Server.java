package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    public static void main(String[] args) {
        byte[] reqBuffer;
        byte[] resBuffer;

        try (DatagramSocket socket = new DatagramSocket(778)) {
            // DatagramSocket constructor executes `bind`
            while (true) {
                reqBuffer = new byte[256];

                // receive request
                DatagramPacket packet = new DatagramPacket(reqBuffer, reqBuffer.length);
                socket.receive(packet);

                // compose response
                String resString = "OK";
                resBuffer = resString.getBytes();

                // send response to client at address:port
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(resBuffer, resBuffer.length, address, port);
                socket.send(packet);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

    }
}
