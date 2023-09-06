package pe.bazan.luis.plugins.restarthttp;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.eclipse.jetty.server.Server;
import pe.bazan.luis.plugins.restarthttp.controllers.MainControler;

public final class RestartHttp extends JavaPlugin {
    private static RestartHttp instance;
    private Server httpServer;

    @Override
    public void onEnable() {
        instance = this;

        // Plugin startup logic
        saveDefaultConfig();

        getServer().getScheduler().runTaskAsynchronously(this, new Runnable() {
            @Override
            public void run() {
                try {
                    startHttpServer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onDisable() {
        if (httpServer != null && httpServer.isRunning()) {
            try {
                httpServer.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void startHttpServer() throws Exception {
        int httpPort = getConfig().getInt("port", 8090);
        getLogger().info(String.format("Http server starting in port %d.", httpPort));
        httpServer = new Server(httpPort);

        httpServer.setHandler(new MainControler()); // Define el manejador de solicitudes

        httpServer.start();
        httpServer.join();
    }

    public static RestartHttp getInstance() {
        return instance;
    }
}
