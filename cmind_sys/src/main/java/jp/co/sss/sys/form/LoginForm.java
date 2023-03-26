package jp.co.sss.sys.form;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import jp.co.sss.sys.Logger.Logger;

/**
 * フォームクラス
 * @author Inoue Nami
 *
 */
public class LoginForm {
	/**
	 * 社員番号
	 * チェック内容１：null 半角スペース 空文字の場合エラー発生
	 * チェック内容２：５文字以内であること
	 */
	@NotBlank(message="社員番号は入力必須項目です。")
	@Size(max=5,message="社員番号は5文字以内で入力してください。")
	private String empId;

	/**
	 * パスワード
	 * チェック内容１：null 半角スペース 空文字の場合エラー発生
	 * チェック内容２：１６文字以内であること
	 */
	@NotBlank(message="パスワードは入力必須項目です。")
	@Size(max=16,message="パスワードは16文字以内で入力してください。")
	private String password;

	/**
	 * 名前
	 * 正常にログイン後、名前を格納する
	 */
	private String empName;

	//コンストラクタ
	public LoginForm() {
		this.empId = null;
		this.password = null;
		this.empName = null;
	}

	//ゲッターセッター
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
		Logger.log(new Throwable(), "empId_set1 = " + this.empId);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		Logger.log(new Throwable(), "emppass_set1 = " + this.password);
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

}
