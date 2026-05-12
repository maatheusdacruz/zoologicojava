package br.com.zoo.controller.commander.actions.impl;

import br.com.zoo.controller.commander.actions.ICommanderAction;
import br.com.zoo.controller.commander.actions.impl.view.CallViewEditWorkerPageAction;
import br.com.zoo.model.ETypeUser;
import br.com.zoo.model.User;
import br.com.zoo.model.Worker;
import br.com.zoo.model.dao.impl.WorkerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CallEditWorkerAction implements ICommanderAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String btnValue = req.getParameter("btnEdit");
        String cpIdWorker = req.getParameter("cpIdWorker");
        int cpIdWorkerInt = Integer.parseInt(cpIdWorker);

        WorkerDAO workerDAO = new WorkerDAO();
        Worker worker = workerDAO.getById(cpIdWorkerInt);

        try {
            if ("update".equals(btnValue)) {
                String cpNameWorker = req.getParameter("cpNameWorker");
                String cpEmailWorker = req.getParameter("cpEmailWorker");
                String cpPasswordWorker = req.getParameter("cpPasswordWorker");

                if (cpNameWorker != null && cpEmailWorker != null && cpPasswordWorker != null) {
                    worker.setName(cpNameWorker);
                    worker.setLogin(cpEmailWorker);
                    worker.setPassword(cpPasswordWorker);
                    workerDAO.update(worker);
                }
            } else if ("delete".equals(btnValue)) {
                workerDAO.delete(worker);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Erro ao processar ação de edição de trabalhador", e);
        }
        new CallViewEditWorkerPageAction().execute(req, resp);
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null){
            return false;
        }else if (user.getType().equals(ETypeUser.ADMIN.toString())){
            return true;
        }
        return false;
    }
}
