package br.com.zoo.controller.commander;

import br.com.zoo.model.ETypeUser;
import br.com.zoo.model.User;
import br.com.zoo.util.CriptoUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

@WebServlet(value = "testa/banco")
public class TesteConexaoBanco extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persist_zoo");

        EntityManager em = emf.createEntityManager();

        User u = new User(0, "Ademiro", "admin@email.com", CriptoUtil.getHash("12345"), ETypeUser.ADMIN);



        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        resp.getWriter().println(em.getMetamodel());
        em.close();



    }
}
