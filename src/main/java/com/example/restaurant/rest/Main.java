package com.example.restaurant.rest;

import java.io.IOException;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Main {

  private static URI getBaseURI() {
    return UriBuilder.fromUri("http://localhost/").port(80).build();
  }

  static final URI BASE_URI = getBaseURI();

  static HttpServer startServer() {
    ResourceConfig rc = ResourceConfig.forApplication(new RestaurantApplication());
    return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
  }

  public static void main(String[] args) throws IOException {
    System.out.println("Starting grizzly...");
    HttpServer httpServer = startServer();
    System.out.printf("Jersey app started with WADL available at %sapplication.wadl%n", BASE_URI);
  }
}
