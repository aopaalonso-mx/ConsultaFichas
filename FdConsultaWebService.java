/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fdconsultawebservice;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.xmlrpc.XmlRpcException;

/**
 *
 * @author telecom
 */
public class FdConsultaWebService {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String patternMonto = "([0-9])+";
        String patternFecha = "([0-9]){8}";
        Logger logger = Logger.getLogger("MyLog");
        try {
            FileHandler fh = new FileHandler("logCreacionFichas.log", true);
            fh.setFormatter(new FormatoLog());
            logger.addHandler(fh);
            logger.setUseParentHandlers(false);
            if (args.length == 1) {
               
                  try {
                     HashMap<String, String> parametros = new HashMap();
                     ConsultaFicha ficha = new ConsultaFicha();
                     ficha.setConfig();
                     ficha.setVariables(args[0]);
                     ficha.validaFicha();
                     String error = "";
                     Object[] resultado = ficha.getResultado();
                     boolean estatusFicha = false;

                     for(int i = 0; i < resultado.length; ++i) {
                        Object object = resultado[i];
                        if (i == 0) {
                           estatusFicha = (Boolean)object;
                        }

                        if (estatusFicha && i >= 1) {
                           parametros = (HashMap)object;
                        } else if (!estatusFicha && i >= 1) {
                           error = (String)object;
                        }
                     }

                     if (estatusFicha) {
                        System.out.println("Bandera:" + estatusFicha);
                        if (!parametros.isEmpty()) {
                           Iterator var16 = parametros.entrySet().iterator();

                           while(var16.hasNext()) {
                              Map.Entry<String, String> entry = (Map.Entry)var16.next();
                              String key = (String)entry.getKey();
                              String value = (String)entry.getValue();
                              System.out.println(key + ":" + value);
                           }

                           logger.log(Level.INFO, parametros.toString());
                        }
                     } else {
                        System.out.println("Bandera : " + estatusFicha);
                        System.out.println("Error : " + error);
                        logger.log(Level.WARNING, error);
                     }
                  } catch (XmlRpcException | SecurityException | MalformedURLException var14) {
                     System.out.println("Se suscitó un error al momento de realizar la ficha de deposito. Favor de comunicarse con el área de soporte.");
                     logger.log(Level.SEVERE, var14.getMessage());
                  }
            } else {
               System.out.println("No se ingresaron lo valores adecuados");
               logger.log(Level.WARNING, "No se ingresaron lo valores adecuados");
            }
        } catch (IOException var15) {
            System.out.println("Error:" + var15.getMessage());
        }
    }
    
}
