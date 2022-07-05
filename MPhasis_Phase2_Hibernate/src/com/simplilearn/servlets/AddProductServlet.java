package com.simplilearn.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.procedure.ProcedureCall;

import com.simplilearn.entity.EProduct;
import com.simplilearn.util.HibernateUtil;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/add-product")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("add-product.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		Double price = Double.valueOf(request.getParameter("price"));
		
		EProduct eproduct = new EProduct();
		eproduct.setName(name);
		eproduct.setPrice(price);
		
		
		// STEP 1: Get SessionFactory Object
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		
		// STEp 2: Create session object
		Session session = sf.openSession();
		
		// Step 3: Persist object (using transaction)
		Transaction tx = session.beginTransaction();
				session.save(eproduct);
		tx.commit();
		
		PrintWriter pw = response.getWriter();
		pw.println("<html><body>"
				+ "Product successfully saved in DB. Please <a href='list-product'>Click here</a> to view all products"
				+ "</body></html>");
		
		session.close();
	}

}
