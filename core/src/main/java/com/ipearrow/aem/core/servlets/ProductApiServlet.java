package com.ipearrow.aem.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.util.EntityUtils;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import org.osgi.service.component.annotations.Component;

import static org.apache.sling.api.servlets.ServletResolverConstants.SLING_SERVLET_PATHS;
import static org.apache.sling.api.servlets.ServletResolverConstants.SLING_SERVLET_METHODS;

@Component(service = Servlet.class, property = {
        SLING_SERVLET_METHODS + "=GET",
        SLING_SERVLET_PATHS + "=/bin/productapi"
})
public class ProductApiServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(
            SlingHttpServletRequest request,
            SlingHttpServletResponse response)
            throws ServletException, IOException {

        String productId = request.getParameter("id");

        String apiUrl = "http://localhost:3000/api/products/" + productId;

        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(apiUrl);

        CloseableHttpResponse apiResponse = client.execute(httpGet);

        String jsonResponse = EntityUtils.toString(apiResponse.getEntity());

        response.setContentType("application/json");

        if (jsonResponse.contains("Interconnect")
                || jsonResponse.contains("Passives")
                || jsonResponse.contains("Electromechanical")) {

            response.getWriter().write(jsonResponse);

        } else {

            response.getWriter().write(
                    "{\"error\":\"Category not allowed for IP&E\"}");
        }

        apiResponse.close();
        client.close();
    }
}