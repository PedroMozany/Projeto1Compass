package servlet;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "Autorizacao")
public class Autorizacao implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        System.out.println("Autorização");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String paramAcao = request.getParameter("acao");

        HttpSession session = request.getSession();
        boolean usuarioNaoEstaLogado = (session.getAttribute("logado") == null);
        boolean ehUmaAcaoProtegida = !(paramAcao.equals("Login") | paramAcao.equals("MostrarProdutos") | paramAcao.equals("MostrarFormulario")|paramAcao.equals("Cadastro"));

        if(usuarioNaoEstaLogado && ehUmaAcaoProtegida){
            response.sendRedirect("Entrada?acao=Login");
            return;
        }

        chain.doFilter(request, response);
    }
}
