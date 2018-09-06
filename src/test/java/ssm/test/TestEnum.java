package ssm.test;

public class TestEnum {
	//定义枚举类型
	enum Day {
	    MONDAY, TUESDAY, WEDNESDAY,
	    THURSDAY, FRIDAY, SATURDAY, SUNDAY
	}

	public static void main(String[] args) {
		System.out.println(Day.FRIDAY.toString());
	}
	
}
