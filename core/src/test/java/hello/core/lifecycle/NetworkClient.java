package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient  {
    String url;

    public NetworkClient() {
        System.out.println("생성자 호출 : "+url);

    }

    public void setUrl(String url) {
        this.url = url;
    }
    public void connect(){
        System.out.println("connect = " + url);
    }
    public void call(String message){
        System.out.println("url: "+url+" message = " + message);
    }
    public void disconnect(){
        System.out.println("close = " + url);
    }

    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
