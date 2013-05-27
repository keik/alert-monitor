package jp.keik.alertmonitor.presentation;

import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.eclipse.jetty.websocket.WebSocket;

public class AlertWebSocket implements WebSocket.OnTextMessage {

	private static ConcurrentLinkedQueue<AlertWebSocket> sSocketQueue = new ConcurrentLinkedQueue<AlertWebSocket>();

	private Connection mConnection;
	private static final AlertWebSocket instance = new AlertWebSocket();

	private AlertWebSocket() {
	}

	public static synchronized AlertWebSocket getInstance() {
		return instance;
	}

	@Override
	public void onClose(int closeCode, String message) {
		sSocketQueue.remove(this);
	}

	@Override
	public void onOpen(Connection connection) {
		this.mConnection = connection;

		sSocketQueue.add(this);
	}

	@Override
	public void onMessage(String data) {
		for (AlertWebSocket socket : sSocketQueue) {
			try {
				socket.mConnection.sendMessage(data);
			} catch (IOException e) {
				sSocketQueue.remove(socket);
				e.printStackTrace();
			}
		}
	}

}
