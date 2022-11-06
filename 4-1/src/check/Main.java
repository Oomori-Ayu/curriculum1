package check;
import constants.Constants;

public class Main {

	//フィールド
	private String firstName = "大森";
	private String lastName = "彩由";

	private static void printName(String ftName,String lName) {
		System.out.println("printNameメソッド → "+ftName+lName);
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	Main main = new Main();
	printName(main.firstName,main.lastName);

	Pet pet = new Pet(Constants.CHECK_CLASS_JAVA,Constants.CHECK_CLASS_HOGE);
	pet.introduce();

	RobotPet rbpet = new RobotPet(Constants.CHECK_CLASS_R2D2,Constants.CHECK_CLASS_LUKE);
	rbpet.introduce();

	}



}
