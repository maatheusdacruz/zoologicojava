package br.com.zoo.controller.commander.actions.impl;

import br.com.zoo.controller.commander.actions.ICommanderAction;
import br.com.zoo.controller.commander.actions.impl.view.CallViewAnimalPageAction;
import br.com.zoo.model.Animal;
import br.com.zoo.model.dao.impl.AnimalDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CallDeleteAnimalAction implements ICommanderAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id-animal");

        if(idStr != null){
            int id = Integer.parseInt(idStr);
            AnimalDAO ad = new AnimalDAO();
            Animal a = ad.getById(id);
            try {
                ad.delete(a);
            } catch (Exception e) {
                req.setAttribute("msg","Falha ao deletar");
                RequestDispatcher rd = req.getRequestDispatcher("template.jsp?page=Register");
                rd.forward(req,resp);
                return;
            }

            ad.close();

            new CallViewAnimalPageAction().execute(req,resp);
        }
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}
