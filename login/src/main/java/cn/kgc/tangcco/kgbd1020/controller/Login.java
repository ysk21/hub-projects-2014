package cn.kgc.tangcco.kgbd1020.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kgc.tangcco.kgbd1020.common.BaseDBUtils;

/**
 * Servlet implementation class ServletConfig
 */
@WebServlet("/login.action")
public class Login extends HttpServlet {
       
	private static final long serialVersionUID = -9693313869829180L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM person_login WHERE 1 = 1 ");
		sql.append(" AND account = ?  ");
		sql.append(" AND password = ? ");
		Object[] params = { account, password};
		try {
			conn = BaseDBUtils.getConnection();
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			rs = BaseDBUtils.executeQuery(pst, params);
			if (rs.next()) {
				request.getRequestDispatcher("/success.jsp").forward(request,response);
			} else {
				request.getRequestDispatcher("/failed.jsp").forward(request,response);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
