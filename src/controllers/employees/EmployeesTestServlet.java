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

        // findメソッドでEmployeeクラスからidが1と2の物を取得
        Employee employee1 = em.find(Employee.class, 1);
        Employee employee2 = em.find(Employee.class, 2);

        // RelationShipのインスタンスを生成
        RelationShip r = new RelationShip();

        // rのプロパティにデータを代入
        r.setEmployeeFollowing(employee1);
        r.setEmployeeFollower(employee2);

        // データベースに保存
        em.persist(r);
        em.getTransaction().commit();
        em.close();

        // 自動採番されたIDの値を表示
        response.getWriter().append(Integer.valueOf(r.getId()).toString());
    }
}

