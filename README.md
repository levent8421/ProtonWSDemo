# Startup

- You can run this demo server as a springboot application with main class "com.test.websocket.WebSocketApplication".
- This app will listen port "8080"(WebHttp) and "8000"(Websocket) after app started.
- You need set a url like "ws://ip:8000" into Proton android device(In Proton setting page) if you want connect Proton
  to this server.
- You can see code of websocket server at class "com.test.websocket.server.impl.WebsocketServerImpl";

# Receive message

See class "com.test.websocket.server.hadnler.MyWsHandler"

# Send message

See class "com.test.websocket.controller.WebSocketController"
