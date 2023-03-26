


package jp.co.sss.sys.entity;





import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * エンティティクラス
 * @author Inoue Nami
 *
 */
@Entity
@Table(name = "employee")
public class Employee {

	/**
	 * 社員番号
	 */
	@Id
	private String empId;

	/**
	 * 社員名
	 */
	@Column(name = "emp_name")
	private String empName;

	/**
	 * パスワード
	 */
	@Column(name = "password")
	private String password;

	/**
	 * 生年月日
	 */
	@Column(name = "birthday")
	private Date birthday;

	/**
	 * 性別
	 */
	@Column(name = "gender")
	private Integer gender;

	/**
	 * 削除フラグ
	 */
	@Column(name = "delete_at")
	private Boolean deleteat;

	//ゲッターセッター
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return  birthday;
	}

	public void setBithday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

}
