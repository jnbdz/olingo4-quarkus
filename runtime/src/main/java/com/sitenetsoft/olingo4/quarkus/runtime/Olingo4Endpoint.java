package com.sitenetsoft.olingo4.quarkus.runtime;

import java.io.IOException;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.olingo.server.api.ODataHttpHandler;

/**
 * Simple servlet that delegates all /odata/* requests to the ODataHttpHandler.
 */
@WebServlet(urlPatterns = "/odata/*")
public class Olingo4Endpoint extends HttpServlet {

    @Inject
    Olingo4Engine engine;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Delegate to Olingo handler
        ODataHttpHandler handler = engine.handler();
        handler.process(req, resp);
    }
}
