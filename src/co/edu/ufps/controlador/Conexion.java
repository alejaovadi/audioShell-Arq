package co.edu.ufps.controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Sergio
 */
public class Conexion {

    public static String Login(String params) {
        String rta = "";
        URL url;
        try {
            // Creando un objeto URL
            url = new URL("http://localhost:1337/usuario?usuario=" + params);
            // Realizando la petición GET
            URLConnection con = url.openConnection();
            // Leyendo el resultado
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String linea;
            while ((linea = in.readLine()) != null) {
                rta += linea;
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return rta;
    }

    public static void registrarUsuario(String data) throws IOException {
        URL url = new URL("http://localhost:1337/usuario");
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("usuario", data);

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
                    "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length",
                String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        for (int c = in.read(); c != -1; c = in.read()) {
            System.out.print((char) c);
        }
    }

    public static String listarUsuarios() {
        String rta = "";
        URL url;
        try {
            // Creando un objeto URL
            url = new URL("http://localhost:1337/usuario");
            // Realizando la petición GET
            URLConnection con = url.openConnection();
            // Leyendo el resultado
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String linea;
            while ((linea = in.readLine()) != null) {
                rta += linea;
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return rta;
    }

}
