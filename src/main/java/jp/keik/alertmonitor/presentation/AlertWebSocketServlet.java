package jp.keik.alertmonitor.presentation;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;

public class AlertWebSocketServlet extends WebSocketServlet {

	private static final long serialVersionUID = 1L;

	// 接続リクエストを受けたとき、WebSocket通信を処理するWebSocketインターフェイス実装クラスを返す
	@Override
	public WebSocket doWebSocketConnect(HttpServletRequest request,
			String protocol) {
		// return AlertWebSocket.getInstance();
		return AlertWebSocket.getInstance();
	}
}
