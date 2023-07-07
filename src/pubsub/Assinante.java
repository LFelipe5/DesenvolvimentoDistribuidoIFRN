package pubsub;

import java.util.Properties;
import javax.jms.*;
import javax.naming.InitialContext;
public class Assinante {
    public static void main(String[] args) {
        try {
            
            /*Properties env = new Properties();
            env.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
            env.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
            env.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
            env.setProperty("java.naming.provider.url", "177.89.105.90:2700");
            
            env.setProperty("org.omg.CORBA.ORBInitialHost", "177.89.105.90");
            env.setProperty("org.omg.CORBA.ORBInitialPort", "2700");

            InitialContext ic = new InitialContext(env);*/
            InitialContext ic = new InitialContext();
            
            TopicConnectionFactory f = (TopicConnectionFactory) ic.lookup("FabricaTopicos");
            TopicConnection c = f.createTopicConnection();
            c.start();
            
            Topic t = (Topic) ic.lookup("meuTopico");
            TopicSession s = c.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            TopicSubscriber assinante = s.createSubscriber(t);
            
            OuvinteDeMensagens ouvinte = new OuvinteDeMensagens();
            assinante.setMessageListener(ouvinte);
            System.out.println("Assinante1 esperando por mensagens...");
            System.out.println("Para sair tecle CTRL+C...");
            while (true) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
