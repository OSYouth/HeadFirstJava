import java.io.*;
import java.net.*;
import java.util.*;

public class VerySimpleChatServer{
	ArrayList clientOutputStreams;

	public static void main(String[] args){
		new VerySimpleChatServer().go();
	}

	public class ClientHandler implements Runnable{
		BufferedReader reader;
		Socket sock;

		public ClientHandler(Socket clientScoket){
			try{
				sock = clientScoket;
				InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
				reader = new BufferedReader(isReader);
			}catch (Exception e){
				e.printStackTrace();
			}
		}

		public void run(){
			String message;
			try{
				while((message = reader.readLine())!= null){
					System.out.println("read: " + message);
					tellEveryone(message);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public void go(){
		clientOutputStreams = new ArrayList();
		try{
			ServerSocket serversock = new ServerSocket(5000);
			while(true){
				Socket clientScoket = serversock.accept();
				PrintWriter writer = new PrintWriter(clientScoket.getOutputStream());
				clientOutputStreams.add(writer);

				Thread t = new Thread(new ClientHandler(clientScoket));
				t.start();
				System.out.println("got a connection");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void tellEveryone(String message){
		Iterator it = clientOutputStreams.iterator();
		while(it.hasNext()){
			try{
				PrintWriter writer = (PrintWriter) it.next();
				writer.println(message);
				writer.flush();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
