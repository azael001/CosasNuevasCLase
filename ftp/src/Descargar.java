import java.io.*;
import java.util.Scanner;
import org.apache.commons.net.ftp.*;

public class Descargar {
    final static String SITE = "ftp.nluug.nl";
    final static String USER = "anonymous";

    public static void main(String[] args) {
        FTPClient ftpclient = new FTPClient();
        Scanner scanner = new Scanner(System.in);

        try {
            ftpclient.connect(SITE);

            if (FTPReply.isPositiveCompletion(ftpclient.getReplyCode())) {
                System.out.println(ftpclient.getReplyString());
                if (ftpclient.login(USER, "")) {
                    ftpclient.setFileType(FTPClient.BINARY_FILE_TYPE);
                    ftpclient.enterLocalPassiveMode();
                    File downloadDir = new File("./archivos/");
                    if (!downloadDir.exists()) {
                        downloadDir.mkdirs();
                    }
                    ftpclient.changeWorkingDirectory("/");
                    ListFiles(ftpclient);

//                    System.out.println("Directorio inicial: " + ftpclient.printWorkingDirectory())
//                    System.out.println("Directorio actual: " + ftpclient.printWorkingDirectory());


                    System.out.print("¿Deseas descargar de forma recursiva? (s/n): ");
                    String opcion = scanner.nextLine().trim().toLowerCase();

                    if (opcion.equals("s")) {
                        DescargaRecursiva(ftpclient, "/vol/4/xcontrib/window_managers/gwm/patches/", "./archivos");
                    } else {
                        Descarga(ftpclient);
                    }
                } else {
                    System.out.println("Usuario incorrecto");
                }
            } else {
                System.out.println("Error de acceso, desconectamos");
                ftpclient.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error durante la conexión o transferencia: " + e.getMessage());
        } finally {
            try {
                if (ftpclient.isConnected()) {
                    ftpclient.logout();
                    ftpclient.disconnect();
                }
                scanner.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar la conexión FTP: " + e.getMessage());
            }
        }
    }

    private static void Descarga(FTPClient ftpclient) throws IOException {
        BufferedOutputStream bo;
        FTPFile[] remotefiles = ftpclient.listFiles();
        for (FTPFile f : remotefiles) {
            if (f.isFile()) {
                String safeFileName = f.getName().replaceAll("[^a-zA-Z0-9\\.\\-_]", "_");
                bo = new BufferedOutputStream(new FileOutputStream("./archivos/" + safeFileName));
                if (ftpclient.retrieveFile(f.getName(), bo)) {
                    System.out.println("Descarga de " + f.getName() + " correcta");
                } else {
                    System.err.println("Descarga de " + f.getName() + " incorrecta");
                }
                bo.close();
            }
        }
    }

    private static void DescargaRecursiva(FTPClient ftpClient, String remotePath, String localPath) throws IOException {
        FTPFile[] remoteFiles = ftpClient.listFiles(remotePath);
        if (remoteFiles != null) {
            for (FTPFile file : remoteFiles) {
                String remoteFilePath = remotePath + "/" + file.getName();
                String localFilePath = localPath + File.separator + file.getName();

                if (file.isDirectory()) {
                    File newLocalDir = new File(localFilePath);
                    if (!newLocalDir.exists()) {
                        newLocalDir.mkdirs();
                    }
                    ftpClient.changeWorkingDirectory(remoteFilePath);
                    DescargaRecursiva(ftpClient, remoteFilePath, localFilePath);
                    ftpClient.changeWorkingDirectory("..");
                } else {
                    try (BufferedOutputStream bo = new BufferedOutputStream(new FileOutputStream(localFilePath))) {
                        if (ftpClient.retrieveFile(remoteFilePath, bo)) {
                            System.out.println("Descarga de " + remoteFilePath + " correcta");
                        } else {
                            System.err.println("Error al descargar " + remoteFilePath);
                        }
                    }
                }
            }
        }
    }

    private static void ListFiles(FTPClient ftpclient) throws IOException {
        FTPFile[] remotefiles = ftpclient.listFiles();
        if (remotefiles.length == 0) {
            System.out.println("No se encontraron ficheros/directorios en el directorio actual.");
        } else {
            System.out.println("Se han encontrado " + remotefiles.length + " ficheros/directorios:");
            for (FTPFile f : remotefiles) {
                if (f.isFile()) {
                    System.out.println("Archivo: " + f.getName());
                } else if (f.isDirectory()) {
                    System.out.println("Directorio: " + f.getName());
                }
            }
        }
    }
}
