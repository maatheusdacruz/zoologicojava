package br.com.zoo.controller.commander.actions.impl;

import br.com.zoo.controller.commander.actions.ICommanderAction;
import br.com.zoo.controller.commander.actions.impl.view.CallViewVisitPageAction;
import br.com.zoo.model.ETypeUser;
import br.com.zoo.model.User;
import br.com.zoo.model.Visit;
import br.com.zoo.model.Visitor;
import br.com.zoo.model.dao.impl.VisitDAO;
import br.com.zoo.model.dao.impl.VisitorDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

public class CallCreateVisitPageAction implements ICommanderAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idVisitorStr = req.getParameter("cpId-visitor");
        LocalDate visitDate = LocalDate.parse(req.getParameter("cpVisit-Date"));
        Object visitorSession = req.getSession().getAttribute("user");

        if(idVisitorStr != null){
            int idvisitor = Integer.parseInt(idVisitorStr);
            Visitor w = new VisitorDAO().getById(idvisitor);
            Visit r = new Visit(0, visitDate, w);
            VisitDAO rdao = new VisitDAO();
            try{
                rdao.post(r);
            } catch (Exception e){
                req.setAttribute("msg","Falha ao registrar task");
                RequestDispatcher rd = req.getRequestDispatcher("template.jsp?page=Visit");
                rd.forward(req,resp);
            }
        }
        else{
            if(visitorSession instanceof Visitor){
                Visit r = new Visit(0, visitDate, (Visitor) visitorSession);
                VisitDAO vdao = new VisitDAO();
                try{
                    vdao.post(r);
                } catch (Exception e){
                    req.setAttribute("msg","Falha ao registrar task");
                    RequestDispatcher rd = req.getRequestDispatcher("template.jsp?page=Visit");
                    rd.forward(req,resp);
                }
            }

        }
        new CallViewVisitPageAction().execute(req, resp);
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null){
            return false;
        }else if (!user.getType().equals(ETypeUser.WORKER.toString())){
            return true;
        }
        return false;
    }
}
