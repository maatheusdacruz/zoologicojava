package br.com.zoo.controller.commander.actions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ICommanderAction {
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    public boolean isAuthorized(HttpServletRequest req);
}
