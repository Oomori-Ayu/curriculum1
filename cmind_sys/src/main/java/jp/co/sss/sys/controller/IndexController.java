package jp.co.sss.sys.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jp.co.sss.sys.Logger.Logger;
import jp.co.sss.sys.entity.Employee;
import jp.co.sss.sys.form.LoginForm;
import jp.co.sss.sys.form.UpdateRequestForm;
import jp.co.sss.sys.repository.EmployeeRepository;


/**
 * コントローラークラス
 * @author Inoue Nami
 *
 */
@Controller
//セッションに保存する情報は@SessionAttributesアノテーションで指定する
//types属性にHTTPセッションに格納するオブジェクトクラスを指定
@SessionAttributes(types = LoginForm.class)
public class IndexController {

	@Autowired
	EmployeeRepository empRepository;
	Employee emplyee;
	//HttpSession httpSession;
	//LoginForm loginForm;

	private String empId;
	private String password;

	/*
     * オブジェクトをHTTPセッションに追加する
     */
	//コントローラーアクセス時にemplyee(名前)で
	//オブジェクトをセッションに登録。※Formクラスを初期化する
	@ModelAttribute("loginForm")
	  private LoginForm setLoginForm() {
	    return new LoginForm();
	  }

	/**
	 * ログイン画面を表示する(URLを直接入力)
	 * @param loginForm
	 * @return login.html
	 */
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login(@ModelAttribute("loginForm") LoginForm loginForm,HttpServletRequest request,SessionStatus sessionStatus,HttpSession httpSession) {
		Logger.logStart(new Throwable());

		if(httpSession != null) {
			//Form初期化
			httpSession.setAttribute("loginform", setLoginForm());
			loginForm.setEmpName(null);

			//セッション情報を破棄
			httpSession.removeAttribute("loginform");
			httpSession.invalidate();
			httpSession = request.getSession(false);
			sessionStatus.setComplete();
		}

		Logger.logEnd(new Throwable());
		return "login";
	}

	/**
	 * ログイン画面を表示する（ログアウト）
	 * @param loginForm
	 * @return login.html
	 */
	@RequestMapping("/logout")
	public String logout(@ModelAttribute("loginForm") LoginForm loginForm,HttpServletRequest request,SessionStatus sessionStatus,HttpSession httpSession) {
		Logger.logStart(new Throwable());

		if(httpSession != null) {
			//Form初期化
			httpSession.setAttribute("loginform", setLoginForm());
			loginForm.setEmpName(null);

			//セッション情報を破棄
			httpSession.removeAttribute("loginform");
			httpSession.invalidate();
			httpSession = request.getSession(false);
			sessionStatus.setComplete();
		}

		Logger.logEnd(new Throwable());
		return "login";
	}

	/**
	 * 入力された値を元にログイン認証し、トップ画面に遷移する
	 * @param loginForm
	 * @param request
	 * @param response
	 * @return top.html
	 */
	@RequestMapping(path = "/top", method = RequestMethod.POST)
	public String login(@Validated LoginForm loginForm,BindingResult error,Model model, HttpServletRequest request, HttpServletResponse response,HttpSession httpSession){
		Logger.logStart(new Throwable());

		//バリエーションチェックでエラー発生した場合、ログイン画面に遷移する。
		if(error.hasErrors()){
            return "login";
        }


		try {

		//入力された社員番号、パスワードを一時保存する。
		this.empId = loginForm.getEmpId();
		this.password = loginForm.getPassword();

		//入力された条件（社員番号）と一致するデータをDBから取得する。
		Employee emp = empRepository.findByempId(empId);

		//取得したDBと入力したパスワードが完全一致する場合、社員一覧を取得する。
		//パスワードが一致しない場合は、ログイン画面に戻る。
		if(this.password.equals(emp.getPassword())) {

			//ログイン成功後、LoginFormに名前をセットする。
			loginForm.setEmpName(emp.getEmpName());

			//社員一覧を取得する。
			List<Employee> emplist = empRepository.findAll();

			// ①初めてセッションを作成するユーザーからのアクセスの場合、
			// 　内部的にセッションが新たに作成され、ユーザーを識別するセッションIDも、この時に発番されます。
			// ②セッションに値を格納
			httpSession = request.getSession();
			httpSession.setAttribute("loginform", loginForm);

			// ③リクエストに値を格納
			request.setAttribute("emplist", emplist);
		}else {
			model.addAttribute("ErrorMessage", "社員番号またはパスワードが違います。");
			return "login";
		}

		Logger.logEnd(new Throwable());
		return "top";

		}catch(NullPointerException e) {
			model.addAttribute("ErrorMessage", "社員番号またはパスワードが違います。");
			return "login";
		}
	}

	/**
	 * 社員一覧画面を再表示する（リンクより押下）
	 * @return top.html
	 */
	@RequestMapping(path = "/top" ,method = RequestMethod.GET)
	public String top(LoginForm loginForm,HttpServletRequest request, HttpServletResponse response,HttpSession httpSession) {
		Logger.logStart(new Throwable());

		//未ログイン時に直接ブラウザに入力された場合は、ログイン画面に遷移
		Object session = httpSession.getAttribute("loginform");
		Logger.log(new Throwable(), "session = " + session);

		if(session == null) {
			return "login";
		}


		try {
		//社員一覧ボタンが押下した場合、DBから社員情報のデータを再取得する。
		List<Employee> emplist = empRepository.findAll();
		request.setAttribute("emplist", emplist);
		Logger.logEnd(new Throwable());
		return "top";
		}catch(NullPointerException e) {
			return "error";
		}
	}

	/**
	 * マイページ確認・変更画面を表示する
	 * @param loginForm
	 * @return mypage.html
	 */
	@RequestMapping(path = "/mypage", method = RequestMethod.GET)
	public String mypage(UpdateRequestForm updateRequestForm,Model model,HttpServletRequest request,HttpSession httpSession) {
		Logger.logStart(new Throwable());

		//未ログイン時に直接ブラウザに入力された場合は、ログイン画面に遷移
		Object session = httpSession.getAttribute("loginform");
		Logger.log(new Throwable(), "session = " + session);

		if(session == null) {
			return "login";
		}

		//入力された条件（社員番号）と一致するデータをDBから再取得する。
		Employee up_emp = empRepository.findByempId(this.empId);
		request.setAttribute("up_emp", up_emp);

		Logger.logEnd(new Throwable());
		return "mypage";
	}

	 /**
	   * マイページ更新
	   * @param userRequest リクエストデータ
	   * @param model Model
	   * @return top.html
	   */
	  @RequestMapping(value = "/mypage", method = RequestMethod.POST)
	  public String update(@Validated UpdateRequestForm updateRequestForm,BindingResult error,Model model,HttpServletRequest request, HttpServletResponse response) {

		//バリエーションチェックでエラー発生した場合、ログイン画面に遷移する。
			if(error.hasErrors()){
				//入力された条件（社員番号）と一致するデータをDBから再取得する。
				Employee up_emp = empRepository.findByempId(this.empId);
				request.setAttribute("up_emp", up_emp);
	            return "mypage";
	        }

			try {
			//入力された社員番号を一時保存する。
			String empId = updateRequestForm.getEmpId();

			//入力された条件（社員番号）と一致するデータをDBから取得する。
			Employee emp = empRepository.findByempId(empId);

			//取得した社員番号と一致する社員情報がある場合、該当した社員情報を更新する。
			//一致しない場合は、エラー画面に遷移する。

			Logger.log(new Throwable(), "updateRequestForm.getBirthday() = " + updateRequestForm.getBirthday());
				String date_up = updateRequestForm.getBirthday();
				Logger.log(new Throwable(), "updateRequestForm.getBirthday() = " + date_up);

			//String ⇒ Date型に変換
			Date sqldate =  java.sql.Date.valueOf(date_up);

			// ユーザー情報の更新
			emp.setPassword(updateRequestForm.getPassword());
			emp.setEmpName(updateRequestForm.getEmpName());
			emp.setBithday(sqldate);
			emp.setGender(updateRequestForm.getGender());
			empRepository.save(emp);

			Logger.logEnd(new Throwable());
			return "changend";

			}catch(NullPointerException e) {
				return "error";

			}catch(Exception e) {
				return "error";
			}


	  }
	}

