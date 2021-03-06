import java.io.*;
import java.net.*;

public class FileServer {
	
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(2343);
			Socket socket = server.accept();
			DataInputStream din = new DataInputStream(socket.getInputStream());
			
			String str,size;
			str = (String)din.readUTF();
			System.out.println("File  name " + str);

			size=(String)din.readUTF();
			System.out.println("\n File size " + size);
			
			FileOutputStream fos = new FileOutputStream(str);
			byte [] buffer = new byte[4096];
			
			int filesize = Integer.parseInt(size) *8;
			int read=0;
			int totalRead = 0;
			int remaining = filesize;
			while((read = din.read(buffer,0,Math.min(buffer.length,remaining)))>0){
				totalRead += read;
				remaining -= read;
				System.out.println("read " + totalRead + " bytes.");
				fos.write(buffer,0,read);
			}
			
			
			fos.close();
			din.close();
			server.close();
			socket.close();
			
		}catch(Exception ex) {
			System.out.println(ex);
		}

	}

}
