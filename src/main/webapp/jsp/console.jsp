<%@ page contentType="text/html;charset=utf-8" %>
<%@page import="java.util.*"
        import="jp.keik.alertmonitor.domain.*" %>

<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="utf-8">
    <title>アラート操作</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/bootstrap-responsive.min.css">
  </head>
  <body>
    <h1>アラート操作</h1>

    <form id="form">
      <label>優先度</label>
      <select name="priority">
        <% for( Map.Entry<Integer, String> e : Constants.PRIORITY.entrySet()) { %>
        <option value="<%= e.getKey() %>"><%= e.getValue() %></option> 
        <% } %>
      </select>

      <label>警報区分</label>
      <select name="spot">
        <% for( Map.Entry<String, String> e : Constants.SPOT.entrySet()) { %>
        <option value="<%= e.getKey() %>"><%= e.getValue() %></option> 
        <% } %>
      </select>

      <label>警報種別</label>
      <select name="type">
        <% for( Map.Entry<String, String> e : Constants.TYPE.entrySet()) { %>
        <option value="<%= e.getKey() %>"><%= e.getValue() %></option> 
        <% } %>
      </select>

      <input id="submit" type="submit" class="btn" style="display: block">
    </form>

    <script type="text/javascript" src="/js/lib/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="/js/lib/bootstrap.min.js"></script>

    <script type="text/javascript">
      $('#submit').click(function() {
        console.log($('#form').serializeArray());
      
        var params = {};
        $($('#form').serializeArray()).each(function(idx, item) {
          params[item.name] = item.value;
        });
        console.log(JSON.stringify(params));
      
        $.ajax({
          type: 'POST',
          contentType: 'application/json',
          url: '/alerts',
          data: JSON.stringify(params),
          dataType: 'json'
        });
        return false;
      });
    </script>
  </body>
</html>
