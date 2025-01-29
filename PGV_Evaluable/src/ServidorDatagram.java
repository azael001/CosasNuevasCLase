import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Scanner;

public class ServidorDatagram {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Arranca el servidor");
		DatagramSocket datagramSocket = null;
		try {
			datagramSocket = new DatagramSocket(5678);
		} catch (SocketException e) {
			e.printStackTrace();
		}

		while (datagramSocket != null) {
			while(true){
				System.out.println("Escriba 1 si quiere enviar un mensaje al chat");
				System.out.println("Escriba 2 si quiere enviar un mensaje individual");
				int n = sc.nextInt();
				if(n==1){

				}
				else if(n==2){

				}
				else{
					System.out.println("Error número no encontrado");
				}
			}
			try {
				byte[] entrada = new byte[100];
				DatagramPacket datagrama1 = new DatagramPacket(entrada, entrada.length);
				datagramSocket.receive(datagrama1);


				InetAddress dircliente = datagrama1.getAddress();
				int puertocliente = datagrama1.getPort();
				System.out.println("Se ha conectado" + dircliente
						+ "Desde el puerto " + puertocliente);
				System.out.println("Enviando respuesta");

				byte[] salida = .toString().getBytes();
				DatagramPacket datagrama2 = new DatagramPacket(salida,
							salida.length, dircliente, puertocliente);
					datagramSocket.send(datagrama2);
					System.out.println("Mensaje enviado");



			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Fin");
	}
}