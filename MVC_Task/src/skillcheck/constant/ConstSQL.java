package skillcheck.constant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import skillcheck.bean.EmployeeBean;

/**
 * JDBC、SQL関連の定数
 *
 * @author y.sato
 * @since 2019/01/02
 *
 */
public final class ConstSQL {

    /** ドライバーのクラス名 */
    public static final String JDBC_POSTGRES_DRIVER = "org.postgresql.Driver";
    /** JDMC接続先情報 */
    // MEMO: PostgreSQLを複数バージョン使用している場合は、ポート番号も指定
    // TODO: 接続先のポート番号は個々で異なるため、pgAdminのPostgreSQLより、接続先設定を参照してXXXXへ記述すること。、、、✓
    public static final String JDBC_CONNECTION = "jdbc:postgresql://localhost:5432/lesson_db";
    /** ユーザー名 */
    public static final String JDBC_POSTGRES_USER = "postgres";
    /** パスワード */
    public static final String JDBC_POSTGRES_PASS = "postgres";

    // FIXME Step-2: 以下のSELECT文、INSERT文、UPDATE文を記述しなさい
    // Tips: 更新値や条件値には「?」を使用してください


    /** ・SQL SELECT文 */
    private static final String SQL_SELECT = "SELECT * FROM Employee WHERE EmpId = ? AND Password = ?";


    /** ・SQL UPDATE文 */
    private static final String SQL_UPDATE = "UPDATE Employee SET login_time= ?  WHERE id= ?";

    /** ・SQL INSERT文 */
    private static final String SQL_INSERT = "INSERT INTO Employee(EmpId,Password,Name,Mail,ProgramingLanguage,Comment) "
    		                                    + "VALUES (?,?,?,?,?,?)  WHERE id= ?";



    // FIXME Step-2-1: [SQL-SELECT] 社員情報テーブルより、作成日時、更新日時、削除フラグを除いたカラムを取得するクエリを作成しなさい。

    EmployeeBean employeeDate = null;

    // 送信されたIDとPassWordを元に社員情報を検索するためのメソッド
   public EmployeeBean search(String EmpId, String Password) {
   Connection connection = null;
   Statement statement = null;
   ResultSet resultSet = null;
   PreparedStatement preparedStatement = null;


   try {
    // データベースに接続
   Class.forName(JDBC_POSTGRES_DRIVER);

   //接続先の情報
   connection = DriverManager.getConnection(JDBC_CONNECTION, JDBC_POSTGRES_USER, JDBC_POSTGRES_PASS);
   statement = connection.createStatement();

    // 処理が流れた時間をフォーマットに合わせて生成
   //Calendar cal = Calendar.getInstance();
   //SimpleDateFormat sdFormat = new SimpleDateFormat(TIME_FORMAT);

    // PreparedStatementで使用するため、String型に変換
   //String login_time = sdFormat.format(cal.getTime());


   /*
   * 任意のユーザーのログインタイムを更新できるように、プリペアドステートメントを記述。
   */

    // preparedStatementに実行したいSQLを格納
   //preparedStatement = connection.prepareStatement(SQL_UPDATE);


    // 問④ preparedStatementを使って、一番目のindexに今の時間をセットしてください。2番目のindexにIDをセットしてください。
   //preparedStatement.setString(1,login_time);
   //preparedStatement.setString(2,id);


    // 問⑤ UPDATEを実行する文を記述
   //preparedStatement.executeUpdate() ;
   /*
   * UPDATEが成功したものを即座に表示
   * 任意のユーザーを検索できるように、プリペアドステートメントを記述。
   */
   preparedStatement = connection.prepareStatement(SQL_SELECT);

    //問⑥ 一番目のindexにIDをセットしてください。2番目のindexにPASSWORDをセット。
   preparedStatement.setString(1,EmpId);
   preparedStatement.setString(2,Password);


    // SQLを実行。実行した結果をresultSetに格納。
   resultSet = preparedStatement.executeQuery();

   while (resultSet.next()) {
    // 問⑦ tmpName,tmpComment,tmpLoginTimeに適当な値を入れてください。
	 String tmpEmpId = resultSet.getString("EmpId");
	 String tmpPassword = resultSet.getString("Password");
  	 String tmpName = resultSet.getString("name");
  	 String tmpMail = resultSet.getString("Mail");
   	 String tmpProgramingLanguage = resultSet.getString("ProgramingLanguage");
  	 String tmpComment = resultSet.getString("comment");




    // 問⑧ EmployeeBeanに取得したデータを入れてください。
   employeeDate = new EmployeeBean();
   employeeDate.setEmpId(tmpEmpId);
   employeeDate.setName(tmpName);
   employeeDate.setMail(tmpMail);
   employeeDate.setProgramingLanguage(tmpProgramingLanguage);
   employeeDate.setComment(tmpComment);
   }

    // forName()で例外発生
   } catch (ClassNotFoundException e) {
   e.printStackTrace();

    // getConnection()、createStatement()、executeQuery()で例外発生
   } catch (SQLException e) {
   e.printStackTrace();

   } finally {
   try {

   if (resultSet != null) {
   resultSet.close();
   }
   if (statement != null) {
   statement.close();
   }
   if (connection != null) {
   connection.close();
   }

   } catch (SQLException e) {
   e.printStackTrace();
   }
   }
   return employeeDate;
   }


    /** 社員情報一覧取得用クエリ: 取得カラム + 取得元テーブル */
    public static final String SELECT_BASE = "ここへ記述";
    /** 社員情報一覧取得用クエリ: 削除されていない社員情報を社員番号順に取得 */
    public static final String SELECT_BY_DELETE_FLG_ZERO = " WHERE deleteFlg = '0' ORDER BY empId";
    /** 社員番号を条件とするクエリ: 完全一致 */
    public static final String SELECT_BY_EMPID = " WHERE empId = ? AND deleteFlg = '0'";

    /** プリペアードステートメントで使用するクエリの条件値用プレースホルダー */
    public static final String CONST_PLACEHOLDER_FOR_BIND_PARAM = "?";
}
