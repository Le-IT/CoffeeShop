package whz.informatik.coffeeshop.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Class for Configuration of the Tomcat Server
 */
@Configuration
public class TomcatConfig {

	@Value("${myserver.port.http}")
    private int httpPort;

    @Value("${server.port}")
    private int httpsPort;

    /**
     * config tomcat: how to root our project, etc.
     */
    @Bean
    public TomcatServletWebServerFactory  servletContainer() {
    	TomcatServletWebServerFactory tomcat =
                new TomcatServletWebServerFactory() {

                    @Override
                    protected void postProcessContext(Context context) {
                        SecurityConstraint securityConstraint = new SecurityConstraint();
                        securityConstraint.setUserConstraint("CONFIDENTIAL");
                        SecurityCollection collection = new SecurityCollection();
                        collection.addPattern("/*");
                        securityConstraint.addCollection(collection);
                        context.addConstraint(securityConstraint);
                    }
                };
        tomcat.addAdditionalTomcatConnectors(createHttpConnector());
        return tomcat;
    }

    /**
     * create bridge from http to https
     */
    private Connector createHttpConnector() {
        Connector connector =
                new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setSecure(false);
        connector.setPort(httpPort);
        connector.setRedirectPort(httpsPort);
        return connector;
    }


}