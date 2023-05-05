package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Servidor {
    
    static int porta = 3000;
    static byte[] mensagem = "RESPOSTA DO SERVIDOR...".getBytes();
    
    public static void main(String[] args) {
        try{
            DatagramSocket soquete = new DatagramSocket(porta);            
            
            byte[] buffer = new byte[65536];            
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            
            System.out.println("Aguardando solicitações...");
            soquete.receive(dp);
            
            System.out.println("Dados: "+new String(dp.getData()).trim());
            System.out.println("Porta de origem: "+dp.getPort());
            System.out.println("Endereço de origem: "+dp.getAddress());
            System.out.println("Endereço de origem: "+dp.getSocketAddress());
            
            dp = new DatagramPacket(mensagem, mensagem.length, dp.getAddress(), dp.getPort());
            soquete.send(dp);
            soquete.close();
            
        }catch(SocketException se){
            se.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}
