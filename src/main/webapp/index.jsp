<%@ page contentType="text/html;charset=utf-8" %>

<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="utf-8">
    <title>故障情報</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/bootstrap-responsive.min.css">
  </head>
  <body>
    <h1>故障情報</h1>

    <table id="alerts" class="table">
      <tr>
        <th>優先度</th>
        <th>発生日時</th>
        <th>警報区分</th>
        <th>警報種別</th>
      </tr>
    </table>

    <script type="text/javascript" src="/js/lib/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="/js/lib/bootstrap.min.js"></script>
    <script type="text/javascript">
      const url = "ws://localhost:8080/alert_web_socket";
      
      var ws = new WebSocket(url);
      
      // メッセージ受信時のコールバック関数
      ws.onmessage = function(event){
        var alert = JSON.parse(event.data);
        $('#alerts tr:first-child').after(
          '<tr><td>' + alert.priority + '</td>' +
          '<td>' + alert.date + '</td>' +
          '<td>' + alert.spot + '</td>' +
          '<td>' + alert.type + '</td></tr>');
      }
      
      window.onunload = function(){
        var code = 4500;
        var reason = "クライアントが閉じられました。";
        ws.close(code, reason);
      }
    </script>
  </body>
</html>
