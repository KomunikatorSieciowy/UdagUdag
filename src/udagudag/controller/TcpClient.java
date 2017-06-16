package udagudag.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TcpClient extends Thread {

	private Controller controller;

	private Socket clientSocket;
	private BufferedReader inFromServer;
	private DataOutputStream outToServer;

	private volatile boolean running = true;

	public TcpClient(Controller controller) {
		this.controller = controller;
		this.controller.setTcpClient(this);
		connectToServer();
	}

	private void connectToServer() {
		try {
			controller.tellConnectionIsNotReady();
			clientSocket = new Socket(controller.getIpAddress(), 8086);
			inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			outToServer = new DataOutputStream(clientSocket.getOutputStream());
			controller.tellConnectionIsReady();
		} catch (IOException e) {
			System.out.println("Problem while connecting to SERVER.");
			askAndReconnectToServer();
		}
	}

	private void askAndReconnectToServer() {
		if (controller.askForReconnection() == true) {
			closeConnections();
			connectToServer();
		} else {
			System.exit(0);
		}
	}

	public void sendCommandToServer(String json) {
		try {
			outToServer.writeBytes(json + '\n'); // NullPointerException could happen, but we disable send button when the connection is not set.
		} catch (IOException e) {	// Very unlikely, we do not have to do anything with it, because our listening loop will do.
			System.out.println("Problem while sending a message.");
		}
	}

	private void receiveBackCommandFromServer() {
		try {
			String receivedJson = inFromServer.readLine(); // Returns null if the other end was not closed properly.
			if (receivedJson != null) {
				System.out.println("Received from SERVER: \"" + receivedJson+"\".");
				controller.executeBackCommand(receivedJson);
			} else {
				System.out.println("SERVER has closed.");
				askAndReconnectToServer();
			}
		} catch (IOException e) { // Happens when the other end was not closed properly or the connection was lost or we closed our connections.
			if (running) { // This is when we did not close connections by closing view.
				System.out.println("SERVER has disconnected in wrong way.");
				askAndReconnectToServer();
			} else { // This is when we closed connections by closing View.
				System.out.println("CLIENT has closed connection - could not listen to SERVER.");
			}
		}
	}

	@Override
	public void run() {
		while (running) {
			receiveBackCommandFromServer();
		}
	}

	public void closeConnections() { // If there are any connections and they are opened, close them in opposite order to opening.
		controller.requestRemovingUserFromList();
		if (outToServer != null)
			try {
				outToServer.close(); // Nothing happens if we close it more than once.
			} catch (IOException e) {
				System.out.println("Problem while closing the socket."); // Very unlikely.
			}

		if (inFromServer != null)
			try {
				inFromServer.close(); // Nothing happens if we close it more than once.
			} catch (IOException e) {
				System.out.println("Problem while closing input reader."); // Very unlikely.
			}

		if (clientSocket != null && !clientSocket.isClosed())
			try {
				clientSocket.close(); // Not sure if we can close it more than once, but we can check if it is closed.
			} catch (IOException e) {
				System.out.println("Problem while closing output stream."); // Very unlikely.
			}
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}