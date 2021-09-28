package ch.zuehlke.fullstack.hackathon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Configuration
public class FrontendConfig implements WebMvcConfigurer {
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uiUrl = "/frontend";
        String resourcePath = "/ui/frontend/";
        registry.addResourceHandler(uiUrl, uiUrl + "/", uiUrl + "/**")
                .addResourceLocations("classpath:" + resourcePath)
                .resourceChain(false)
                .addResolver(new AngularPathResourceResolver(resourcePath));
    }

    private static final class AngularPathResourceResolver extends PathResourceResolver {
        private final String resourceBasePath;

        public AngularPathResourceResolver(String resourceBasePath) {
            this.resourceBasePath = resourceBasePath;
        }

        @Override
        protected Resource getResource(String resourcePath, Resource location) throws IOException {
            Resource resource = super.getResource(resourcePath, location);
            if (resource == null) {
                return new ClassPathResource(resourceBasePath + "index.html");
            } else {
                return resource;
            }
        }
    }
}
