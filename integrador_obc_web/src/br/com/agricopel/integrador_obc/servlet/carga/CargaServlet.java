package br.com.agricopel.integrador_obc.servlet.carga;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.agricopel.integrador_obc.servlet.Logica;
import br.com.agricopel.integrador_obc.servlet.carga.logica.MenuCarga;

@WebServlet("/carga")
public class CargaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		try {
			String parametro = req.getParameter("acao");
			String nomeDaClasse = "br.com.agricopel.integrador_obc.servlet.carga.logica." + parametro;

			Logica logica = null;

			try {
				Class<?> classe = Class.forName(nomeDaClasse);
				logica = (Logica) classe.newInstance();
			} catch (ClassNotFoundException e) {

				if (parametro != null && !parametro.isEmpty()) {
					req.setAttribute("erro", "Classe de lógica não encontrada".concat(e.getMessage()));
				}

				logica = new MenuCarga();
			} catch (Exception e) {
				req.setAttribute("erro", e.getMessage());
				logica = new MenuCarga();
			}

			if (logica.getTpRetorno().equals(0)) {
				String pagina = logica.executa(req, res);
	
				req.getRequestDispatcher("/WEB-INF/jsp/carga/".concat(pagina).concat(".jsp")).forward(req, res);
			} else {
				String retorno = logica.executa(req, res);
				res.getWriter().write(retorno);
			}

		} catch (Exception e) {
			throw new ServletException("A lógica de negócios [carga] causou uma exceção", e);
		}
	}

}
