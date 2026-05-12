package br.com.zoo.controller.commander.actions.impl.view;

import br.com.zoo.controller.commander.actions.ICommanderAction;
import br.com.zoo.model.ETypeUser;
import br.com.zoo.model.Report;
import br.com.zoo.model.User;
import br.com.zoo.model.dao.impl.ReportDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CallViewTaskOnlyPageAction implements ICommanderAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idReport = req.getParameter("idTask");
        int idR = Integer.parseInt(idReport);
        ReportDAO reportDAO = new ReportDAO();
        Report report = reportDAO.getById(idR);
        req.setAttribute("reportOnly", report);

        RequestDispatcher rd = req.getRequestDispatcher("template.jsp?page=TaskOnly");

        rd.forward(req,resp);
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
