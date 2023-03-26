package jp.co.sss.sys.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import jp.co.sss.sys.Logger.Logger;


/**
 * フォームクラス
 * @author Omori Ayu
 *
 */
public class UpdateRequestForm{

	/**
	 * 社員番号
	 * チェック内容：空でないこと
	 */
	@NotBlank(message="社員番号は入力必須項目です。")
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
	 * 社員名
	 * チェック内容１：null 半角スペース 空文字の場合エラー発生
	 * チェック内容２：１６文字以内であること
	 *
	 */
	@NotBlank(message="名前は入力必須項目です。")
	@Size(max=16,message="名前は16文字以内で入力してください。")
	private String empName;

	/**
	 * 生年月日
	 * null 半角スペース 空文字の場合エラー発生
	 * "yyyy/MM/d形式で設定してください。"
	 */
	@NotBlank(message="生年月日は入力必須項目です。")
	// @JsonFormat(pattern="yyyy-MM-dd")
	private String birthday;

	/**
	 * 性別
	 */
	private Integer gender;

	//ゲッターセッター
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
		Logger.log(new Throwable(), "empName_set1 = " + this.empName);
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
		Logger.log(new Throwable(), "birthday_set1 = " + this.birthday);
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
		Logger.log(new Throwable(), "gender_set1 = " + this.gender);
	}

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



}
