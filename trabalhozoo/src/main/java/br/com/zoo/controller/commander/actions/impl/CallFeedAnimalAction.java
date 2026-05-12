package br.com.zoo.controller.commander.actions.impl;

import br.com.zoo.controller.commander.actions.ICommanderAction;
import br.com.zoo.model.Animal;
import br.com.zoo.model.Food;
import br.com.zoo.model.Worker;
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
import java.time.LocalDateTime;
import java.util.List;

public class CallFeedAnimalAction implements ICommanderAction{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("cpId");
        String idWorkerStr = req.getParameter("cpId-Worker");
        String typeFood = req.getParameter("cpTypeFood");

        if(idStr != null  && idWorkerStr != null){
            int idanimal =  Integer.parseInt(idStr);
            Animal a = new AnimalDAO().getById(idanimal);
            int idworker = Integer.parseInt(idWorkerStr);
            Worker w = new WorkerDAO().getById(idworker);
            Food fd = new Food(0, LocalDateTime.now(), typeFood, a, w);
            FoodDAO fdDAO = new FoodDAO();
            try {
                fdDAO.post(fd);
            } catch (Exception e) {
                req.setAttribute("msg","Falha ao alimentar");
                RequestDispatcher rd = req.getRequestDispatcher("template.jsp?page=Food");
                rd.forward(req,resp);
            }
        }
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}
