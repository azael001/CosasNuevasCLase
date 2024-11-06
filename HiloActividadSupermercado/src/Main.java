public class Main {
    public static void main(String[] args) {
        Producto producto = new Producto(3); // Inicialmente hay 3 unidades de producto

        // Crear y arrancar varios clientes
        for (int i = 1; i <= 5; i++) {
            new Cliente(producto, "Cliente-" + i).start();
        }
        // Crear y arrancar el reponedor
        new Reponedor(producto).start();
    }
}

class Producto {
    private int cantidadStock;
    private final int cantidadMinima = 5;

    public Producto(int cantidadInicial) {
        this.cantidadStock = cantidadInicial;
    }

    public synchronized void comprarProducto() throws InterruptedException {
        while (cantidadStock <= 0) {
            System.out.println(Thread.currentThread().getName() + " - Producto agotado, cliente en espera.");
            wait(); // El cliente espera hasta que haya stock
        }
        cantidadStock--;
        System.out.println(Thread.currentThread().getName() + " - Producto comprado, stock actual: " + cantidadStock);
    }

    public synchronized void reponerStock(int cantidad) {
        cantidadStock += cantidad;
        System.out.println("Reponedor - Stock repuesto, stock actual: " + cantidadStock);
        notifyAll(); // Notifica a todos los clientes en espera
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public int getCantidadMinima() {
        return cantidadMinima;
    }
}

class Cliente extends Thread {
    private final Producto producto;

    public Cliente(Producto producto, String nombre) {
        super(nombre);
        this.producto = producto;
    }

    public void run() {
        try {
            producto.comprarProducto();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Reponedor extends Thread {
    private final Producto producto;

    public Reponedor(Producto producto) {
        this.producto = producto;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(5000); // Verifica el stock cada 5 segundos
                synchronized (producto) {
                    if (producto.getCantidadStock() < producto.getCantidadMinima()) {
                        producto.reponerStock(10); // Reponer 10 unidades
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

