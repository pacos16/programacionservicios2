import java.net.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejemplo_URL {
    public static void main (String[] args) throws Exception {
        String tag;
        tag= args[0];
        try {
            // Constructor
            URL direccion = new URL("https://informatica.es.zone/course/dam-programacion-de-servicios-y-procesos/index.php");
            // Divide las diferentes partes de una URL
            System.out.println("El protocolo utilizado es: " + direccion.getProtocol());
            System.out.println("El host es: " + direccion.getHost());
            System.out.println("El puerto es: " + direccion.getPort());
            System.out.println("El fichero es: " + direccion.getFile());
            System.out.println("User info: " + direccion.getUserInfo());
            System.out.println("Path: " + direccion.getPath());
            System.out.println("Authority: " + direccion.getAuthority());
            System.out.println("Query : " + direccion.getQuery());
            //Utilizamos la clase URLConnection para conectar la pagina.
            URLConnection connection= direccion.openConnection();
            //Usamos el metodo getInpuStream para generar un isr y leer con buffer
            BufferedReader br= new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            //leemos el documento html
            while((line=br.readLine())!=null){

                //Creamos un pattern de la clase java regex, este buscara entre dos etiquetas pasadas por parametro
                // ("str(.*?)str") con esta expresion regular se puede capturar substrings que estén
                // contenidos en str (entendemos str por un string cualquiera). En nuestro ejemplo, etiquetas html
                //
                Pattern pattern = Pattern.compile("<"+tag+"(.*?)</"+tag+">");
                //aplicamos el pattern al matcher y ejecutamos el find,
                //si lo encuentra imprime por pantalla
                Matcher matcher = pattern.matcher(line);
                if (matcher.find())
                {
                    System.out.println(matcher.group(1));
                }

            }
        } catch (MalformedURLException e) {
            System.out.println("Error en la construccion de la URL");
        }

    }
}
