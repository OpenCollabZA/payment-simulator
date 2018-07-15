package za.co.opencollab.simulator.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ImportResource({"classpath:META-INF/cxf/cxf.xml"})
public class CxfConfiguration {

	private static final Log logger = LogFactory.getLog(CxfConfiguration.class);

	@Autowired
	private ApplicationContext ctx;

	@Autowired
	private Bus bus;

	@Bean
	public ServletRegistrationBean cxfServletRegistrationBean() {
		return new ServletRegistrationBean(new CXFServlet(), "/rest/*");
	}

	@Bean
	public Server jaxRsServer() {
		// Find all beans annotated with @Path
		List<Object> serviceBeans = new ArrayList<>(ctx.getBeansWithAnnotation(Path.class).values());
		logger.info("Registering service beans: " + serviceBeans);

		// Find all beans annotated with @Providers
		List<Object> providers = new ArrayList<>(ctx.getBeansWithAnnotation(Provider.class).values());
		logger.info("Registering providers: " + providers);

		JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
		factory.setBus(bus);
		factory.setAddress("/");
		factory.setServiceBeans(serviceBeans);
		factory.setProviders(providers);
		return factory.create();
	}

}
