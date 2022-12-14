package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.EmployeeBean;
import service.EmployeeService;

public class EmployeeController extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	//パラーメータのエンコード方式の設定
        request.setCharacterEncoding("UTF-8");
        try {
        // 問① index.htmlから送信されたIDとPassWordの値を取得できるように修正しましょう。
        String id = request.getParameter("id");
        String password = request.getParameter("pass");

        //html生成
        //エンコード方式の指定
        response.setContentType("text/html; charset=UTF-8");

        /*
        * IDとPassWordと元に、社員情報を検索する関数の呼び出し、結果をJSPに渡す処理
        * ※ EmployeeBeanとEmployeeServiceをimportするのを忘れないでください。
        */

         // 問② EmployeeServiceクラスをインスタンス化する。
        EmployeeService sev =new EmployeeService();

         // 問③ EmployeeBeanに、EmployeeServiceよりsearch関数を呼び出し、返り値を格納する。
        EmployeeBean bean = sev.search(id,password);

         // 問④ nullの部分に適切な引数をセットする。
        request.setAttribute("EmployeeBean", bean);


        } catch (Exception e) {
        	 e.printStackTrace();
        }finally {
        ServletContext context = this.getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
  }
  }
}
