package pruebaDic.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import pruebaDic.DAO.DaoTrain;
import pruebaDic.DAO.DaoUser;
import pruebaDic.model.Train;

/**
 * Servlet implementation class LoginExec
 */
@WebServlet("/LoginExec")
public class LoginExec extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginExec() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		HttpSession userSession = request.getSession();
		String usuario = request.getParameter("nombre");
		String password = request.getParameter("pass");
		
		DaoUser daoUser = new DaoUser();
		
		//compruebo si es valido el usuario.
		
		//compruebo si son nulos y si lo son recupero los atributos de la session
	    if((usuario ==null && password ==null)){	    	
	    	 usuario = (String)userSession.getAttribute("usuario");
		   	 password =(String)userSession.getAttribute("password");
	    	if((usuario ==null||usuario.isEmpty()) && (password ==null||password.isEmpty())){
	    		process(request, response);
	    	}
	    }
		
		if(daoUser.userIsValid(usuario, password)){
            try {
         	userSession.setAttribute("login", "True");
         	userSession.setAttribute("usuario", usuario);
         	userSession.setAttribute("password", password);
         	
            }catch (Exception e){
            	process(request, response);
            }
            
            //declaro que el texto va a ser html/ codificacion UTF-8
    		response.setContentType("text/html;charset=UTF-8");
    		// decimos que en la salida de datos se va a escribir lo siguiente
    		PrintWriter out = response.getWriter();
    	
    		try {
    			out.println("<!DOCTYPE html>\r\n"
    					+ "<html>\r\n"
    					+ "<head>\r\n"
    					+ "<!doctype html>\r\n"
    					+ "<html lang=\"en\">\r\n"
    					+ "<head>\r\n"
    					+ "<!-- Required meta tags -->\r\n"
    					+ "<meta charset=\"utf-8\">\r\n"
    					+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
    					+ "\r\n"
    					+ "<!-- Bootstrap CSS -->\r\n"
    					+ "<link\r\n"
    					+ "	href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\"\r\n"
    					+ "	rel=\"stylesheet\"\r\n"
    					+ "	integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\"\r\n"
    					+ "	crossorigin=\"anonymous\">\r\n"
    					+ "\r\n"
    					+ "<title>Viajes de tren</title>\r\n"
    					+ "</head>\r\n"
    					+ "<body>\r\n"
    					+ "\r\n"
    					+ "	<div class=\"container\">\r\n"
    					+ "		<div class=\"starter-template\">\r\n"
    					+ "			<h1>Viajes disponibles</h1>\r\n"
    					+ "			<!-- Button -->\r\n"
    					+ "			<a href=\"index.jsp\">\r\n"
    					+ "			<div class=\"form-group\">\r\n"
    					+ "				<div class=\"col-md-12\">\r\n"
    					+ "					<button id=\"singlebutton\" name=\"singlebutton\"\r\n"
    					+ "						class=\"btn btn-primary\" style=\"float: right;\">Close session</button>\r\n"
    					+ "				</div>\r\n"
    					+ "			</div>\r\n"
    					+ "			</a>\r\n"
    					+ "			<a href='VerCompras.jsp'>"
    					+ "			<button> Ver Compras"
    					+ "			</button>"
    					+ "			</a>"
    					+ "			<table class=\"table\">\r\n"
    					+ "				<thead>\r\n"
    					+ "					<tr>\r\n"
    					+ "						<th>ID</th>\r\n"
    					+ "						<th>SALIDA</th>\r\n"
    					+ "						<th>DESTINO</th>\r\n"
    					+ "						<th>PRECIO</th>\r\n"
    					+ "						<th>FECHA</th>\r\n"
    					+ "						<th>NUM. ASIENTOS LIBRES</th>\r\n"
    					+ "					</tr>\r\n"
    					+ "				</thead>\r\n"
    					+ "				<tbody>");
    			
    			DaoTrain daoTrain = new DaoTrain();
		  		  ArrayList<Train> listaViajes =daoTrain.getList();
		  		  for (Train train: listaViajes){
		  			  
		  			  out.println("<tr>"
		  			  		+ "	<td>"+train.getId()+"</td>"
		  			  		+ "	<td>"+train.getOrigin()+"</td>"
		  			  		+ "	<td>"+train.getFinalStation()+"</td>"
		  			  		+ " <td>"+train.getPrice()+"</td>"
		  			  		+ "	<td>"+train.getDate()+"</td>"
		  			  		+ "	<td>"+train.getNumbertickets()+"</td>");
		  			  		if(train.getNumbertickets()>0) {	
		  			  		out.println("<td><form action='Compra.jsp' method='POST'>"
		  			  		+ "<button id='Button' name='Comprar' value="+train.getId()+">Comprar</button></form><td>"
		  			  		+ "	</tr>");
		  			  		}else {
		  			  			
		  			  		}
		  		  }
    			
    		
		
		
			
		
		
		
		
		}finally {
    			//cerramos la salida de datos
    			out.close();
    	}
    		
		}else {
    		process(request, response);
    	}
		
	
	
	}
		
		protected void process (HttpServletRequest request, HttpServletResponse response) throws IOException {
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			try {
			out.println("<!DOCTYPE html>"
	   				+ "<html>"
	   				+ "<head>"
	   				+ "<meta charset=\"UTF-8\">"
	   				+ "<title>Error 404</title>"
	   				+ "		<link rel=\"stylesheet\" type=\"text/css\" href=\"css/styleError.css\">"
	   				+ " "
	   				+ "</head>"
	   				+ "<body background=\"images/fondo_movie.jpg\">"
	   				+ "      <a href=\"Index.jsp\"><img src=\"images/logo_movie-removebg.png\" width=\"110px\" height=\"100px\" id=\"logo\"></a>"
	   				+ "            <hr>"
	   				+ "            <div id=\"izq\">"
	   				+ "                "
	   				+ "                <img src=\"images/error_movie.jpg\" id=\"iconoError\">"
	   				+ "            </div>"
	   				+ "            <div id=\"der\">"
	   				+ "                <h1 id=\"TextoGrande\"><FONT color=\"black\">¡Vaya!</FONT></h1>"
	   				+ "                <h3 id=\"TextoChico\"><FONT color=\"black\">Error en el Inicio de sesión.<br><br>Pulsa el icono arriba a la izquierda para volver.</FONT></h3>"
	   				+ "                <h7 id=\"codError\">Codigo de error: 404</h7>"
	   				+ "            </div>"
	   				+ "</body>"
	   				+ "</html>");
	   		
		}finally {
			out.close();
		}
	   		
		}


}
