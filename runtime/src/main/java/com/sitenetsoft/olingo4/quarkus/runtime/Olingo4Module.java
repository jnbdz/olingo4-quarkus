package com.sitenetsoft.olingo4.quarkus.runtime;

import org.apache.olingo.commons.api.edm.provider.CsdlEdmProvider;
import org.apache.olingo.server.api.ODataHttpHandler;

/**
 * SPI that applications implement to plug their OData EDM and processors
 * into the Olingo4 Quarkus extension.
 */
public interface Olingo4Module {

    /**
     * Provide the EDM (metadata) for this module.
     */
    CsdlEdmProvider edmProvider();

    /**
     * Register OData processors (entity collection, entity, actions, etc.)
     * on the given handler.
     *
     * Default implementation is a no-op to keep things flexible.
     */
    default void registerProcessors(ODataHttpHandler handler) {
        // no-op by default
    }
}
