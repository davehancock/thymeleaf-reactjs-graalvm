package com.djh.example.render;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * @author David Hancock
 */
@Slf4j
@RequiredArgsConstructor
public class TemplateRenderer {

    private static final String DEFAULT_SSR_TEMPLATE_FILE = "ssr-template.html";
    private static final String DEFAULT_RENDER_FUNCTION_NAME = "render";

    // Could move this to a file if desired
    private static final String polyfillContents = "var window = this; " +
            "window.setTimeout = function (){}; " +
            "window.clearTimeout = function (){};";

    private final String componentDir;
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    // The cache on this could be very long - the variable part is the server side state - which could have variable cache times depending on the source.
    public <T> String renderTemplate(String componentName, T serverSideState) {

        String componentSourceName = componentName + ".js";
        String componentFilePath = Paths.get("classpath:", componentDir, componentSourceName).toString();

        // Don't have to read from a file each time... Could cache component and template contents in memory once loaded once.
        Resource componentResource = resourceLoader.getResource(componentFilePath);
        Resource ssrTemplateResource = resourceLoader.getResource(Paths.get("classpath:", DEFAULT_SSR_TEMPLATE_FILE).toString());

        try (BufferedReader componentReader = new BufferedReader(new InputStreamReader(componentResource.getInputStream()));
             BufferedReader templateReader = new BufferedReader(new InputStreamReader(ssrTemplateResource.getInputStream()))) {

            String ssrTemplateContents = templateReader.lines().collect(Collectors.joining("\n"));
            String serializedServerSideState = objectMapper.writeValueAsString(serverSideState);

            Context context = Context.create();
            context.eval("js", polyfillContents);
            context.eval(Source.newBuilder("js", componentReader, componentSourceName).build());

            Value func = context.getBindings("js").getMember(DEFAULT_RENDER_FUNCTION_NAME);
            Value result = func.execute(ssrTemplateContents, componentName, serializedServerSideState);

            return result.asString();

        } catch (IOException e) {
            log.error("Error rendering template with Graal", e);
            throw new RuntimeException("Unable to render template");
        }
    }

}
