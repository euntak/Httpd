package euntak.httpd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    // 필수 로그 내용은 시간과 요청페이지, client 정보(본인이 결정)
    public static final Logger logger = LoggerFactory.getLogger(HttpServer.class);

    public void run() throws Exception {
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("서버 실행 클라이언트를 기다리는 중");
        Socket clientSocekt = serverSocket.accept();

        BufferedReader in =
                new BufferedReader(new InputStreamReader(clientSocekt.getInputStream()));
        PrintWriter out =
                new PrintWriter(new OutputStreamWriter(clientSocekt.getOutputStream()));

        String line = null;
        while(((line = in.readLine()) != null)) {
            if("".equals(line)) break;
            out.flush();
            logger.info(line);
        }

        clientSocekt.close();
        serverSocket.close();
    }
}
