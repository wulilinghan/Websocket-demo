package top.b0x0.boot.chatroom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * http://
 *
 * @author ManJiis
 */
@SpringBootApplication
public class SpringbootChatroomApplication {
    private static final Logger log = LoggerFactory.getLogger(SpringBootApplication.class);

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringbootChatroomApplication.class, args);
        ConfigurableEnvironment env = applicationContext.getEnvironment();
        String appName = env.getProperty("spring.application.name");
        String host = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Index: \thttp://127.0.0.1:{}/index.html\n\t" +
                        "----------------------------------------------------------",
                appName,
                port);
    }

}
