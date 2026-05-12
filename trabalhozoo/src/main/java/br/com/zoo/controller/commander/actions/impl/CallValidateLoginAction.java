package br.com.zoo.controller.commander.actions.impl;

import br.com.zoo.controller.commander.actions.ICommanderAction;
import br.com.zoo.model.User;
import br.com.zoo.model.Visitor;
import br.com.zoo.model.Worker;
import br.com.zoo.model.dao.impl.UserDAO;
import br.com.zoo.model.dao.impl.VisitorDAO;
import br.com.zoo.model.dao.impl.WorkerDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CallValidateLoginAction implements ICommanderAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("cpLogin");
        String password = req.getParameter("cpPassword");

        User usu = new UserDAO().getByLoginPassword(login, password);
        if (usu == null){
            usu = (Visitor) new VisitorDAO().getByLoginPassword(login, password);
            if (usu == null){
                usu = (Worker) new WorkerDAO().getByLoginPassword(login, password);
                if (usu == null){
                    req.setAttribute("err","login ou senha incorreta");
                    RequestDispatcher rd = req.getRequestDispatcher("template.jsp?page=Login");
                    rd.forward(req,resp);
                    return;
                }
            }
        }
        req.getSession().setAttribute("user",usu);
        req.getSession().setAttribute("userType",usu.getType());
        req.getSession().setAttribute("Loggedin","1");
        RequestDispatcher rd = req.getRequestDispatcher("template.jsp?page=Home");
        rd.forward(req,resp);
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}
