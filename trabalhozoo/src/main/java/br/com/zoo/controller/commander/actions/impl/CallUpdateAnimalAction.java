package br.com.zoo.controller.commander.actions.impl;

import br.com.zoo.controller.commander.actions.ICommanderAction;
import br.com.zoo.controller.commander.actions.impl.view.CallViewAnimalPageAction;
import br.com.zoo.model.Animal;
import br.com.zoo.model.ETypeUser;
import br.com.zoo.model.User;
import br.com.zoo.model.dao.impl.AnimalDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

public class CallUpdateAnimalAction implements ICommanderAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("cpId");
        String name = req.getParameter("cpName-animal");
        String scientificName = req.getParameter("cpSciName-animal");
        LocalDate arrivalDate = LocalDate.parse(req.getParameter("cpArrivalDate-animal"));
        String place = req.getParameter("cpPlace-animal");
        String specie = req.getParameter("cpSpecie-animal");
        String animalHealth = req.getParameter("cpHealth");

        if(idStr != null){
            int id = Integer.parseInt(idStr);
            Animal animal = new AnimalDAO().getById(id);

            animal.setName(name);
            animal.setScientificName(scientificName);
            animal.setArrivalDate(arrivalDate);
            animal.setPlace(place);
            animal.setSpecie(specie);
            animal.setAnimalhealth(animalHealth);
            AnimalDAO animalDAO = new AnimalDAO();
            try {
                animalDAO.update(animal);
            } catch (Exception e) {
                req.setAttribute("msg","Falha ao fazer o update");
                RequestDispatcher rd = req.getRequestDispatcher("template.jsp?page=Register");
                rd.forward(req,resp);
                return;
            }

            animalDAO.close();

            new CallViewAnimalPageAction().execute(req,resp);
        }


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
