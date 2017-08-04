package euntak.httpd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    private int portNumber = 8080;

    public HttpServer(int portNumber) {
        this.portNumber = portNumber;
    }

    public void run() throws Exception {
        ServerSocket serverSocket = new ServerSocket(portNumber);
        System.out.println("서버 실행 클라이언트를 기다리는 중");
        Socket clientSocket = serverSocket.accept();

        RequestHandler requestHandler = new RequestHandler();
        Request request = requestHandler.handle(clientSocket);

        logger.info(request.toString());

        clientSocket.close();
        serverSocket.close();

    }

    public class RequestHandler {

        // 필수 로그 내용은 시간과 요청페이지, client 정보(본인이 결정)
        public Logger logger = LoggerFactory.getLogger(this.getClass());

         public Request handle(Socket clientSocket) throws Exception {
             Request request = null;

            if(clientSocket != null) {
                request = new Request();
                request.setInputStream(clientSocket.getInputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
                String line = null;
                while (!"".equals((line = in.readLine()))) {
                    request.parseRequestLine(line);
                }
            }

            return request;
         }
    }
}
