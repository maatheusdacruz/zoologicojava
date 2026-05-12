package br.com.zoo.controller.commander.actions.impl;

import br.com.zoo.controller.commander.actions.ICommanderAction;
import br.com.zoo.model.ETypeUser;
import br.com.zoo.model.User;
import br.com.zoo.model.Visitor;
import br.com.zoo.model.Worker;
import br.com.zoo.model.dao.impl.UserDAO;
import br.com.zoo.util.CriptoUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CallValidateSignupAction implements ICommanderAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name  = req.getParameter("cpName");
        String login = req.getParameter("cpLogin");
        String password = req.getParameter("cpPassword");
        String type = req.getParameter("radio-user");

        User u;

        if(type == null){
            u = new Visitor(0, name, login, CriptoUtil.getHash(password), ETypeUser.VISITOR);
        } else{
            if(type.equals(ETypeUser.ADMIN.toString())){
                u  = new User(0, name, login, CriptoUtil.getHash(password), ETypeUser.ADMIN);
            } else if(type.equals(ETypeUser.WORKER.toString())){
                u = new Worker(0, name, login, CriptoUtil.getHash(password), ETypeUser.WORKER);
            } else {
                u = new Visitor(0, name, login, CriptoUtil.getHash(password), ETypeUser.VISITOR);
            }
        }

        UserDAO ud = new UserDAO();

        try {
            ud.post(u);
        } catch (Exception e) {
            req.setAttribute("err","login já cadastrado");
            RequestDispatcher rd = req.getRequestDispatcher("template.jsp?page=Signup");
            rd.forward(req,resp);
            return;
        }

        ud.close();

        RequestDispatcher rd = req.getRequestDispatcher("template.jsp?page=Home");
        rd.forward(req,resp);
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}
