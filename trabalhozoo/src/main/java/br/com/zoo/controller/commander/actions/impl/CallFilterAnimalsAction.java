package br.com.zoo.controller.commander.actions.impl;

import br.com.zoo.controller.commander.actions.ICommanderAction;
import br.com.zoo.model.dao.impl.AnimalDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CallFilterAnimalsAction implements ICommanderAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("cpName-get");
        req.setAttribute("animals", new AnimalDAO().getByNameView(name));

        RequestDispatcher rd = req.getRequestDispatcher("template.jsp?page=Animal");
        rd.forward(req, resp);
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}
