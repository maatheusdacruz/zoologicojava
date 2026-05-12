package br.com.zoo.controller.commander.actions.impl.view;

import br.com.zoo.controller.commander.actions.ICommanderAction;
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
import java.util.List;

public class CallViewReportPageAction implements ICommanderAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDate queryDate;
        String dataParam = req.getParameter("date");
        if (dataParam == null || dataParam.isEmpty()) {
            queryDate = LocalDate.now();
        } else {
            queryDate = LocalDate.parse(dataParam);
        }

        List<Report> reports = new ReportDAO().getReportsperDate(queryDate);
        req.setAttribute("reports", reports);
        req.setAttribute("date", queryDate);
        req.setAttribute("workers", new WorkerDAO().getAll());
        if(req.getSession().getAttribute("userType").equals("WORKER")){
            Object u = req.getSession().getAttribute("user");
            if (u instanceof Worker) {
                Worker worker = (Worker) u;
                int userId = worker.getId();
                List<Report> reportsWorker =
                        new ReportDAO().getReportsperWorker(userId);
                req.setAttribute("reportsWorker", reportsWorker);
            }
        }
        RequestDispatcher rd = req.getRequestDispatcher("template.jsp?page=Report");

        rd.forward(req, resp);
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
