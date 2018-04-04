package co.edu.ufps.cliente;

import co.edu.ufps.controlador.Conexion;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Sergio
 */
public class Main {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        System.out.println("¡Bienvenido!");
        System.out.print("Ingrese su usuario : ");
        String usuario = sc.next();
        String remitente = Conexion.Login(usuario);
        if(remitente.equals("[]")) {
            System.out.print("Hola, ");
            Conexion.registrarUsuario(usuario);
        } else {
            System.out.println("Hola, " + remitente);
        }
        System.out.println("Ahora porfavor selecciona uno de los siguientes usuarios para chatear con él : ");
        System.out.println(Conexion.listarUsuarios());
        System.out.print("Digita el ID del usuario con el que quieres chatear : ");
        String destinatario =  sc.next();
    }

}
