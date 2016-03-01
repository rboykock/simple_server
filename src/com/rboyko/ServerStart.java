package com.rboyko;

import java.awt.print.Printable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;

public class ServerStart {
	static final int port=4444;
	
	public static void main(String[] args) throws IOException{
		System.out.println("Start server");
		
		MyServerSocket server=null;
		Socket fromclient=null;
		try{
			server=new MyServerSocket(port);
		}catch(IOException e){
			System.out.println("Couldn't listener to port "+port);
			System.exit(-1);
		}
		System.out.println("Listen to port: "+port);
		System.out.println("Waiting for client...");
		
		try{
			fromclient=server.accept();
			System.out.println("Client connected");
		}catch(IOException e){
			System.out.println("Can't accept");
			System.exit(-2);
		}
		
		BufferedReader in=null;
		PrintWriter out=null;

		try {
			 in=new BufferedReader(new InputStreamReader(fromclient.getInputStream()));
			 out=new PrintWriter(fromclient.getOutputStream(),true);
		} catch (IOException e) {
			System.out.println("IO Error");
			System.exit(-3);
		}
		
		String input,output;
		
		
		System.out.println("Wait for message");
		while((input=in.readLine())!=null){
			if(input.equalsIgnoreCase("exit")) 
					break;
			out.println("S ::: "+input);
			System.out.println(input);
		}
		
		out.close();
		in.close();
		
		fromclient.close();
		server.close();
		
	}

}
