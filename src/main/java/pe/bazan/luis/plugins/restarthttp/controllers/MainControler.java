package pe.bazan.luis.plugins.restarthttp.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bukkit.Bukkit;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import pe.bazan.luis.plugins.restarthttp.RestartHttp;
import pe.bazan.luis.plugins.restarthttp.domain.MessageResponse;
import pe.bazan.luis.plugins.restarthttp.domain.VersionResponse;

import java.io.IOException;

public class MainControler extends AbstractHandler {
    public static String version() {
        return new VersionResponse("1.0", "fldsmdfr").toString();
    }

    public static String reload() {
        Bukkit.getScheduler().runTaskLater(RestartHttp.getInstance(), new Runnable() {
            @Override
            public void run() {
                Bukkit.getServer().reload();
            }
        }, 40);
        return new MessageResponse("OK").toString();
    }

    public static String restart() {
        Bukkit.getScheduler().runTaskLater(RestartHttp.getInstance(), new Runnable() {
            @Override
            public void run() {
                Bukkit.getServer().dispatchCommand(
                        Bukkit.getServer().getConsoleSender(),
                        "restart"
                );
            }
        }, 40);
        return new MessageResponse("OK").toString();
    }

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);

        String jsonResponse = "";

        if ("/reload".equals(target)) {
            jsonResponse = reload();
        } else if ("/restart".equals(target)) {
            jsonResponse = restart();
        } else {
            jsonResponse = version();
        }

        response.getWriter().println(jsonResponse);
    }
}
