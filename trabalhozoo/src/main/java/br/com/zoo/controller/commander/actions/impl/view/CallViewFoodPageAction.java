package br.com.zoo.controller.commander.actions.impl.view;

import br.com.zoo.controller.commander.actions.ICommanderAction;
import br.com.zoo.model.ETypeUser;
import br.com.zoo.model.User;
import br.com.zoo.model.dao.impl.AnimalDAO;
import br.com.zoo.model.dao.impl.FoodDAO;
import br.com.zoo.model.dao.impl.WorkerDAO;
import br.com.zoo.model.dto.AnimalFoodDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class CallViewFoodPageAction implements ICommanderAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDate queryDate;
        String dataParam = req.getParameter("query-date");
        if (dataParam == null || dataParam.isEmpty()) {
            queryDate = LocalDate.now();
        } else {
            queryDate = LocalDate.parse(dataParam);
        }

        List<AnimalFoodDTO> food = new AnimalDAO().getAnimalFed(queryDate);
        req.setAttribute("FoodReport", food);
        req.setAttribute("queryDate", queryDate);
        req.setAttribute("animals", new AnimalDAO().getAllView());
        req.setAttribute("workers", new WorkerDAO().getAll());
        req.setAttribute("foodPerDay", new FoodDAO().getFoodPerDate(queryDate));
        RequestDispatcher rd = req.getRequestDispatcher("template.jsp?page=Food");

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
