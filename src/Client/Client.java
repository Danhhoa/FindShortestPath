package Client;

import java.awt.EventQueue;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;

import com.google.common.graph.ElementOrder.Type;

import GUI.mainGUI;
import dijkstra.Render;

public class Client {
	private Socket socket = null;
	BufferedWriter out = null;
	BufferedReader in = null;
	BufferedReader stdIn = null;

	public Client(String host, int port) throws UnknownHostException, IOException {
		String fileName = mainGUI.filename;
		String fileNeedCreate = mainGUI.fileNeedCreate;
		System.out.println("filepath: " + mainGUI.filename);

		socket = new Socket(host, port);
		System.out.println("Connected");

		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		stdIn = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader readfile = new BufferedReader(new FileReader(fileName));
		String line = "";
		String[] shortestPath = null;

		System.out.println("name: " + fileNeedCreate);
		out.write(fileNeedCreate);
		out.newLine();
		out.flush();

		while (true) {
			String tmp = "";

			line = readfile.readLine();
			while (line != null) {
				System.out.println(line);
				tmp = tmp + line + ";";
				line = readfile.readLine();
			}
			readfile.close();
		
			System.out.println(tmp);
			out.write(tmp);
			out.newLine();
			out.flush();
			
			String dataResponse = in.readLine(); // waiting data from server
			String regex = "\\d+(\\.\\d+)?";
			String cost = null;
			while (dataResponse != null) {
				if (dataResponse.matches(regex)) {
					cost = dataResponse;
					System.out.println("chi phí: " + dataResponse);
				}
				
				if (dataResponse != null && !dataResponse.matches(regex)) {
					System.out.println("data " + dataResponse);
					shortestPath = dataResponse.split(" ");
				} 
				
				dataResponse = in.readLine();
			
			}
			
			
			Render.startDraw(fileName, shortestPath, cost);
			break;
			
			
		}

		stopConnect();
//         String dataResponse = in.readLine();
//         System.out.println(dataResponse);
//         if (dataResponse != null) {
//        	 shortestPath.add(dataResponse);
//         }

	}

//        try {
//            FileInputStream fis = new FileInputStream(FileName);
//            BufferedInputStream bis = new BufferedInputStream(fis);
//            BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());
//            byte[] bytes = new byte[8192];
//            int count;
//            while ((count = bis.read(bytes)) > 0) {
//                out.write(bytes, 0, count);
//              
//
//            }
//            out.close();
//            fis.close();
//            bis.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

	public void stopConnect() throws IOException {
		in.close();
		out.close();
		socket.close();
	}

	public static void main(String[] args) throws UnknownHostException, IOException {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainGUI frame = new mainGUI();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
