package euntak.httpd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class Request {

    public static final Logger logger = LoggerFactory.getLogger(Request.class);

    public String httpVersion = "HTTP/1.1";
    private Map<String, String> headers;

    private String method;
    private String requestTarget;
    private InputStream bodyInput;
    private InputStream inputStream;

    public Request() {
        this.headers = new HashMap<String, String>();
    }

    public void parseRequestLine(String requestLine) {
        addHeader(requestLine);
    }

    public void addHeader(String header) {
//        System.out.println(header);

        if (header.indexOf(httpVersion) > 0) {
            String[] headers = header.split(" ");
            if(headers.length > 2) {
                setMethod(headers[0].trim());
                setRequestTarget(headers[1].trim());
            } else {
                setMethod(headers[0].trim());
            }

        } else {

            int colon = header.indexOf(":");
            if (colon > 0) {
                String name = header.substring(0, colon).trim();
                String value = header.substring(colon + 1, header.length()).trim();

                if(!"".equals(name) && !"".equals(value)) {
                    headers.put(name, value);
                }
            }
        }
    }

    public String getHeader(String key) {
        return headers.get(key);
    }

    @Override
    public String toString() {
        String headerString = "[";

        //some code
        int i = 0;
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            headerString += entry.getKey() + "=" + entry.getValue();
            if(i++ != headers.entrySet().size() - 1){
                headerString += ", ";
            }
        }
        headerString += "]";

        return "Request[" +
                "method='" + method + '\'' +
                ", requestTarget='" + requestTarget + '\'' +
                ", headers=" + headerString +
                ']';
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequestTarget() {
        return requestTarget;
    }

    public void setRequestTarget(String requestTarget) {
        this.requestTarget = requestTarget;
    }

    public InputStream getBodyInput() {
        return bodyInput;
    }

    public void setBodyInput(InputStream bodyInput) {
        this.bodyInput = bodyInput;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
