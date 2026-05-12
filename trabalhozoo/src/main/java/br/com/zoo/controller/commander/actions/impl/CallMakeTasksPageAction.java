package br.com.zoo.controller.commander.actions.impl;

import br.com.zoo.controller.commander.actions.ICommanderAction;
import br.com.zoo.controller.commander.actions.impl.view.CallViewReportPageAction;
import br.com.zoo.model.ETypeUser;
import br.com.zoo.model.Report;
import br.com.zoo.model.User;
import br.com.zoo.model.Worker;
import br.com.zoo.model.dao.impl.ReportDAO;
import br.com.zoo.model.dao.impl.WorkerDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

public class CallMakeTasksPageAction implements ICommanderAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idWorkerStr = req.getParameter("cpId-Worker");
        String title = req.getParameter("cpTitle");
        String description = req.getParameter("cpDescription");
        Object workerSession = req.getSession().getAttribute("user");

        if(idWorkerStr != null){
            int idworker = Integer.parseInt(idWorkerStr);
            Worker w = new WorkerDAO().getById(idworker);
            Report r = new Report(0, title, LocalDate.now(), description, w);
            ReportDAO rdao = new ReportDAO();
            try{
                rdao.post(r);
            } catch (Exception e){
                req.setAttribute("msg","Falha ao registrar task");
                RequestDispatcher rd = req.getRequestDispatcher("template.jsp?page=Report");
                rd.forward(req,resp);
            }
        }
        else{
            if(workerSession instanceof Worker){
                Report r = new Report(0, title, LocalDate.now(), description, (Worker) workerSession);
                ReportDAO rdao = new ReportDAO();
                try{
                    rdao.post(r);
                } catch (Exception e){
                    req.setAttribute("msg","Falha ao registrar task");
                    RequestDispatcher rd = req.getRequestDispatcher("template.jsp?page=Report");
                    rd.forward(req,resp);
                }
            }

        }
        new CallViewReportPageAction().execute(req, resp);
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null){
            return false;
        }else if (!user.getType().equals(ETypeUser.VISITOR.toString())){
            return true;
        }
        return false;
    }
}
