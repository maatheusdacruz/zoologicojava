package br.com.zoo.controller.commander;


import br.com.zoo.controller.commander.actions.ICommanderAction;
import br.com.zoo.controller.commander.actions.impl.*;
import br.com.zoo.controller.commander.actions.impl.view.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "")
public class CommanderController extends HttpServlet {

    private static Map<String, ICommanderAction> comandos = new HashMap<>();

    static {
        comandos.put(null,  new CallViewHomePageAction());
        comandos.put("about",new CallViewAboutPageAction());
        comandos.put("error", new CallViewErrorPageAction());
        comandos.put("login", new CallViewLoginPageAction());
        comandos.put("animal", new CallViewAnimalPageAction());
        comandos.put("signup", new CallViewSignupPageAction());
        comandos.put("food", new CallViewFoodPageAction());
        comandos.put("report", new CallViewReportPageAction());
        comandos.put("vallog", new CallValidateLoginAction());
        comandos.put("logout", new CallLogoutPageAction());
        comandos.put("saveAnimal", new CallSaveAnimalAction());
        comandos.put("valsignup", new CallValidateSignupAction());
        comandos.put("deleteAnimal", new CallDeleteAnimalAction());
        comandos.put("updateAnimal", new CallUpdateAnimalAction());
        comandos.put("filterAnimals", new CallFilterAnimalsAction());
        comandos.put("feed", new CallFeedAnimalAction());
        comandos.put("tasks", new CallMakeTasksPageAction());
        comandos.put("taskOnly", new CallViewTaskOnlyPageAction());
        comandos.put("editView", new CallViewEditWorkerPageAction());
        comandos.put("editWorker", new CallEditWorkerAction());
        comandos.put("visit", new CallViewVisitPageAction());
        comandos.put("visits", new CallCreateVisitPageAction());
    }
    private void doRequest(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        String ac = req.getParameter("ac");

        ICommanderAction act = comandos.get(ac);
        if (act == null){
            req.setAttribute("msg","Comando '"+ac+"' não encontrado");
            comandos.get("error").execute(req,resp);
        }

        if (act.isAuthorized(req)){
            act.execute(req, resp);
        }else{
            req.setAttribute("msg","acesso não autorizado");
            comandos.get("error").execute(req,resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }
}
