package br.com.zoo.controller.commander.actions.impl.view;

import br.com.zoo.controller.commander.actions.ICommanderAction;
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
import java.util.List;

public class CallViewVisitPageAction implements ICommanderAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDate dataConsulta;
        String dataParam = req.getParameter("data");
        if (dataParam == null || dataParam.isEmpty()) {
            dataConsulta = LocalDate.now();
        } else {
            dataConsulta = LocalDate.parse(dataParam);
        }

        List<Visit> visits = new VisitDAO().getAll();
        req.setAttribute("visits", visits);
        req.setAttribute("data", dataConsulta);
        req.setAttribute("visitors", new VisitorDAO().getAll());
        if(req.getSession().getAttribute("userType").equals("Visitor")){
            Object u = req.getSession().getAttribute("user");
            if (u instanceof Visitor) {
                Visitor visitor = (Visitor) u;
                int userId = visitor.getId();
                List<Visit> visitsVisitor =
                        new VisitDAO().getVisitsPerVisitor(userId);
                req.setAttribute("VisitsVisitor", visitsVisitor);
            }
        }
        RequestDispatcher rd = req.getRequestDispatcher("template.jsp?page=Visit");

        rd.forward(req, resp);
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
