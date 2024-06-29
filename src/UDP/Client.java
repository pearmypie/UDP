package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();

        /* send req */
        byte[] reqBuffer = new byte[256]; // BUFFER_SIZE 256
        InetAddress address = InetAddress.getByName("127.0.0.1"); // Loopback address
        DatagramPacket packet = new DatagramPacket(reqBuffer, reqBuffer.length, address, 778);
        socket.send(packet);

        /* get res */
        byte[] resBuffer = new byte[256]; // could have been reused
        packet = new DatagramPacket(resBuffer, resBuffer.length); // reuse variable
        socket.receive(packet);

        /* display response */
        String received = new String(packet.getData());
        System.out.println("Information received from server: " + received);

        /* close socket */
        socket.close();
    }
}
