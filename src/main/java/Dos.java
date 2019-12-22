import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dos {
    public static void main (String[] args) throws Exception {
        /*
        String arg0= args[0];
        String arg1=args[1];
        String arg2=args[2];
        */
        String arg0= "https://informatica.es.zone/course/dam-programacion-de-servicios-y-procesos/index.php";
        String arg1="span";
        String arg2="25";

        try {
            // Constructor
            URL direccion = new URL(arg0);
            //Utilizamos la clase URLConnection para conectar la pagina.
            URLConnection connection= direccion.openConnection();
            //Usamos el metodo getInpuStream para generar un isr y leer con buffer
            BufferedReader br= new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            ArrayList<String> hRefs=new ArrayList<>();
            int num=0;
            int max= Integer.parseInt(arg2);
            //leemos el documento html
            while((line=br.readLine())!=null && num<max){
                //Creamos un pattern de la clase java regex, este buscara entre dos etiquetas pasadas por parametro
                // ("str(.*?)str") con esta expresion regular se puede capturar substrings que estén
                // contenidos en str (entendemos str por un string cualquiera). En nuestro ejemplo, etiquetas html
                //
                String truquete ="href=3(.*?)3";
                String replace=truquete.replace('3','"');
                Pattern pattern = Pattern.compile(replace);
                //aplicamos el pattern al matcher y ejecutamos el find,

                Matcher matcher = pattern.matcher(line);
                if (matcher.find())
                {
                    try{
                        String s= matcher.group(1);
                        URL url=new URL(s);
                        num++;
                        System.out.println(s);
                        pattern = Pattern.compile("<"+arg1+"(.*?)</"+arg1+">");
                        URLConnection connection1= url.openConnection();
                        BufferedReader bf1= new BufferedReader(new InputStreamReader(connection1.getInputStream()));
                        int contador=0;
                        while ((line=bf1.readLine())!=null){
                            Matcher matcher1=pattern.matcher(line);
                            if(matcher1.find()){
                                contador++;
                            }
                        }
                        System.out.println(contador);

                    }catch (MalformedURLException mue){

                    }catch (IOException ioe){
                        System.out.println("Error en la url");
                    }
                }

            }

        } catch (MalformedURLException e) {
            System.out.println("Error en la construccion de la URL");
        }

    }
}
