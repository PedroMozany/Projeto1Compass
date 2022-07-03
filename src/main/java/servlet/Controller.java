package servlet;

import controller.IAcao;
import org.xml.sax.SAXException;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(urlPatterns = "/Entrada")
public class Controller implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        System.out.println("Controler");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        String paramAcao = request.getParameter("acao");
        String caminho = "controller." + paramAcao;
        String nome;

        try{
            Class classe = Class.forName(caminho);
            IAcao instance = (IAcao) classe.newInstance();
            nome = instance.acao(request, response);
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException e){
            throw  new ServletException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

        String[] redirecionamento = nome.split(":");

        if (redirecionamento[0].equals("forward")) {
            request.getRequestDispatcher("WEB-INF/views/" + redirecionamento[1]).forward(request, response);
        } else {
            response.sendRedirect(redirecionamento[1]);
        }
    }
}
