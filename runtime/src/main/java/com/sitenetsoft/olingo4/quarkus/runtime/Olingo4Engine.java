package com.sitenetsoft.olingo4.quarkus.runtime;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataHttpHandler;
import org.apache.olingo.server.api.ServiceMetadata;

@ApplicationScoped
public class Olingo4Engine {

    private final ODataHttpHandler handler;

    @Inject
    public Olingo4Engine(Instance<Olingo4Module> modules) {
        // For now: assume a single module. Later we can support multiple.
        Olingo4Module module = modules.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "No Olingo4Module implementation found. " +
                                "You must provide a bean implementing Olingo4Module."));

        OData odata = OData.newInstance();
        ServiceMetadata metadata = odata.createServiceMetadata(
                module.edmProvider(),
                null // references
        );
        this.handler = odata.createHandler(metadata);

        // let the module register its processors
        module.registerProcessors(this.handler);
    }

    public ODataHttpHandler handler() {
        return handler;
    }
}
