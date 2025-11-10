/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fdconsultawebservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
/**
 *
 * @author telecom
 */
public class ConsultaFicha {
    private final String user = "USUUNTRA";
    private final String pswd = "USUUNTRA";
    private final String service = "consultabancoreferencia.srv";
    private final HashMap<String, String> variables = new HashMap();
   
    private final URL direccion = new URL("http://wscfdspruebas.patronato.unam.mx/wstiendapruebas/srv.php");
    private XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    private XmlRpcClient client = new XmlRpcClient();
    private Object[] resultado;

    public ConsultaFicha() throws MalformedURLException {
    }

    public void setConfig() {
      this.config = new XmlRpcClientConfigImpl();
      this.config.setServerURL(this.direccion);
      this.config.setBasicPassword(this.pswd);
      this.config.setBasicUserName(this.user);
    }

    public void validaFicha() throws XmlRpcException {
      this.client.setConfig(this.config);
      Object[] parametros = new Object[]{this.variables};
      this.resultado = (Object[])((Object[])this.client.execute(this.service, parametros));
    }

    public HashMap<String, String> getVariables() {
      return this.variables;
    }

    public void setVariables(String referencia) {
      this.variables.put("referencia", referencia);
    }

    public XmlRpcClientConfigImpl getConfig() {
      return this.config;
    }

    public void setConfig(XmlRpcClientConfigImpl config) {
      this.config = config;
    }

    public XmlRpcClient getClient() {
      return this.client;
    }

    public void setClient(XmlRpcClient client) {
      this.client = client;
    }

    public Object[] getResultado() {
      return this.resultado;
    }

    public void setResultado(Object[] resultado) {
      this.resultado = resultado;
    }
}
