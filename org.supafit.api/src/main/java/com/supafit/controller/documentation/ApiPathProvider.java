package com.supafit.controller.documentation;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;

import com.mangofactory.swagger.core.SwaggerPathProvider;

public class ApiPathProvider implements SwaggerPathProvider {
    private SwaggerPathProvider defaultSwaggerPathProvider;
    @Autowired
    private ServletContext servletContext;

    private String docsLocation;

    public ApiPathProvider(String docsLocation) {
        this.docsLocation = docsLocation;
    }

    public String getApiResourcePrefix() {
        return defaultSwaggerPathProvider.getApiResourcePrefix();
    }

    public String getAppBasePath() {
        return UriComponentsBuilder
                .fromHttpUrl(docsLocation)
                .path(servletContext.getContextPath())
                .build()
                .toString();
    }

    public String getSwaggerDocumentationBasePath() {
        return UriComponentsBuilder
                .fromHttpUrl(getAppBasePath())
                .pathSegment("api-docs/")
                .build()
                .toString();
    }

    public String getRequestMappingEndpoint(String requestMappingPattern) {
        return defaultSwaggerPathProvider.getRequestMappingEndpoint(requestMappingPattern);
    }

    public void setDefaultSwaggerPathProvider(SwaggerPathProvider defaultSwaggerPathProvider) {
        this.defaultSwaggerPathProvider = defaultSwaggerPathProvider;
    }
}
