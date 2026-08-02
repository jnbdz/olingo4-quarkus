package com.sitenetsoft.olingo4.quarkus.runtime;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import org.apache.olingo.server.api.ODataHttpHandler;

import java.io.IOException;

@WebServlet(urlPatterns = "/*") // real mapping done via Quarkus config
public class ODataServlet extends HttpServlet {

    @Inject
    ODataHandlerRegistry registry; // extension-provided service

    // e.g. "default" or derived from path
    private String serviceId = "default";

    private ODataHttpHandler handler;

    @Override
    public void init() throws ServletException {
        super.init();
        this.handler = registry.getHandler(serviceId);
    }

    @Override
    protected void service(jakarta.servlet.http.HttpServletRequest req,
                           jakarta.servlet.http.HttpServletResponse resp) throws IOException {
        handler.process(req, resp);
    }
}
