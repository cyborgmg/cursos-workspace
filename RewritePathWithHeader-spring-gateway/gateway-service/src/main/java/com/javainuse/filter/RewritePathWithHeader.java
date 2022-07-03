package com.javainuse.filter;


import com.javainuse.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriTemplate;
import org.springframework.http.HttpHeaders;

import java.net.URI;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.addOriginalRequestUrl;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

@Component
@Slf4j
public class RewritePathWithHeader implements GatewayFilterFactory<RewritePathWithHeader.Config>, Ordered {

    @Override
    public RewritePathWithHeader.Config newConfig() {
        return new Config();
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            if (StringUtils.isNotEmpty(config.value)) {

                final ServerHttpRequest request = exchange.getRequest();

                addOriginalRequestUrl(exchange, request.getURI());

                final UriTemplate uriTemplate = new UriTemplate(config.value);

                final Map<String, String> pathParameters = ServerWebExchangeUtils.getUriTemplateVariables(exchange);

                final Map<String, String> variables = new HashMap<>();
                uriTemplate.getVariableNames()
                        .forEach(v -> variables.put(v, getPathOrHeaderValue(v, pathParameters, request.getHeaders())));

                final URI uri = uriTemplate.expand(variables);

                final ServerHttpRequest mutatedRequest = request.mutate().path(uri.getRawPath()).build();

                exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, mutatedRequest.getURI());

                return chain.filter(exchange.mutate().request(mutatedRequest).build());

            }

            return chain.filter(exchange);

        };
    }

    public static class Config {

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("template");
    }

    @Override
    public ShortcutType shortcutType() {
        return ShortcutType.DEFAULT;
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    @Override
    public int getOrder() {
        return 4;
    }

    private String getPathOrHeaderValue(final String parameterName, Map<String, String> pathParameters, HttpHeaders headers) {

        String pathParameterValue = pathParameters.get(parameterName);
        if(pathParameterValue != null) {
            return pathParameterValue;
        }

        final String headerValue = headers.getFirst(parameterName);

        if (StringUtils.isEmpty(headerValue)) {
            throw new BadRequestException(
                    MessageFormat.format(
                            "Header {0} is absent",
                            parameterName
                    )
            );
        }

        return headerValue;
    }

}
