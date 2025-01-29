import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class SubirFtp {
	public static void main(String[] args) throws IOException {
		FTPClient cliente = new FTPClient();
		String srvftp = "ftp.nluug.nl";
		String usuario = "anonymous";
		String password = "";
		String directorio = "/";

		try {
			// Nos conectamos
			cliente.connect(srvftp);
			// Login
			boolean login = cliente.login(usuario, password);
			if (login) {
				// System.out.println("El usuario es correcto");
				cliente.changeWorkingDirectory(directorio);
				cliente.setFileType(FTP.BINARY_FILE_TYPE);
				// Stream para subir archivos
				BufferedInputStream bis = new BufferedInputStream(
						new FileInputStream("src/mensajeIRIS.txt"));
				cliente.storeFile("mensajaeIRIS.txt", bis);
				bis.close();
				cliente.logout();
			} else {
				System.out.println("Usuario incorrecto");

			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		cliente.disconnect();
	}

}
