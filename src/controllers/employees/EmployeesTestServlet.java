package controllers.employees;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.RelationShip;
import utils.DBUtil;

/**
 * Servlet implementation class EmployeesTestServlet
 */
@WebServlet("/employees/test")
public class EmployeesTestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();


        // セッションスコープからログインしている従業員のデータを取得
        Employee employee_following = (Employee)request.getSession().getAttribute("login_employee");

        // JSPファイルからリクエストパラメータを取得
        Employee employee_follower = em.find(Employee.class, Integer.parseInt(request.getParameter("id")));

        // RelationShipのインスタンスを生成
        RelationShip r = new RelationShip();

        // rのプロパティにデータを代入
        r.setEmployeeFollowing(employee_following);
        r.setEmployeeFollower(employee_follower);

        // データベースに保存
        em.persist(r);
        em.getTransaction().commit();
        em.close();

        // インデックスページへリダイレクト
        request.getSession().setAttribute("flush", "フォローしました");
        response.sendRedirect(request.getContextPath() + "/employees/index");
    }
}

