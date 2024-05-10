import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(8082);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                com.sun.jersey.spi.container.servlet.ServletContainer.class, "/api/*");
        jerseyServlet.setInitOrder(0);
        jerseyServlet.setInitParameter("com.sun.jersey.config.property.packages", "resource");
        jerseyServlet.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true"); // Enable JSON to POJO mapping


        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();  // Print the full stack trace to understand the error
        } finally {
            server.destroy();
        }
    }
}
