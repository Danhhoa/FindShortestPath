package Server;


import dijkstra.App;
import dijkstra.Vertex;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server {
	private Socket socket = null;
	private ServerSocket server = null;
	BufferedWriter out = null;
	BufferedReader in = null;
	BufferedWriter writer;

	HashMap<String, String> direction = new HashMap<>();
	ArrayList<String> listDir = new ArrayList<String>();
	String from, to, setFrom, setTo;
	String line, next;
	String[] dir;
	String filePath;
	String check_txt = "\\w+\\.txt";

	public boolean checkFile(String name) {
		File file = new File("./src/data/" + name);
		if (file.exists()) {
			return true;
		}
		return false;
	}

	public void createFile(String name) throws IOException {
		File file = new File("./src/data/" + name);
		file.createNewFile();
		writer = new BufferedWriter(new FileWriter(file, false));
//        for (String i : dictionaryData.keySet()) {
//
//            writer.write(i + ";" + dictionaryData.get(i));
//            writer.newLine();
//            writer.flush();
//        }
//        writer.close();
	}

	public Server(int port) {
		try {
			server = new ServerSocket(port);
			System.out.println("Server started");
			System.out.println("Waiting for a client...");
			socket = server.accept();
			System.out.println("connected to client");
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line = "";
			String[] temp;
			boolean flag = true;
			while (true) {

				if ((line = in.readLine()).matches(check_txt)) {
					File file = new File("./src/dataServer/S_" + line);
					file.createNewFile();
					filePath = file.getAbsolutePath();
					writer = new BufferedWriter(new FileWriter(file, false));

					line =  in.readLine();
					while (line != null && flag) {
						System.out.println("data nhận:" + line);
						temp = line.split(";");
						for (int i = 0; i<temp.length; i++) {
							writer.write(temp[i]);
							System.out.println("Ghi file: " + temp[i]);
							writer.newLine();
							writer.flush();
						}
						flag = false;
					
					}
					
				}
				System.out.println(flag);
				if (!flag) {
					System.out.println("filepath: " + filePath);
					App.startDijkstra(filePath);
					List<Vertex> shortestPath = App.path;
					double cost = App.cost;
					System.out.println("shortest path: " + shortestPath + cost);
					if (shortestPath != null) {
						String tmp = "";
						for (int i = 0; i < shortestPath.size(); i++) {
							tmp += shortestPath.get(i).toString() + " ";
						}
						System.out.println(tmp);
						System.out.println(Thread.currentThread());
						out.write(tmp.toString());
						out.newLine();
						out.flush();
						
						System.out.println("chi phí:" + String.valueOf(cost));
						out.write(String.valueOf(cost));
						out.newLine();
						out.flush();
						break;
					} else {
						System.err.println("Không tìm ra đường đi ngắn nhất ");
					}
				}

			}
			stopConnect();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//            try {
//                InputStream is = socket.getInputStream();
//                int bufferSize = socket.getReceiveBufferSize();
//                System.out.println("Buffer size: " + bufferSize);
//                FileOutputStream fos = new FileOutputStream(fileName);
//                BufferedOutputStream bos = new BufferedOutputStream(fos);
//                byte[] bytes = new byte[bufferSize];
//                int count;
//                while ((count = is.read(bytes)) >= 0) {
//                	String s = new String (bytes);
//                	System.out.println("content " +s);
//                   
//                }
//                int line;
//				
//                
//                bos.close();
//                is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            

	public void stopConnect() throws IOException {
		in.close();
		out.close();
		socket.close();
		server.close();
	}

	public static void main(String[] args) {
		Server server = new Server(6655);
	}
}
