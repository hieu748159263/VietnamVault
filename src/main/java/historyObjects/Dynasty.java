package historyObjects;

import com.google.gson.annotations.SerializedName;

public class Dynasty {
	
	@SerializedName("Tuổi thọ")
	private String age;
	
	@SerializedName("Thời kỳ")
	private String period;
	
	@SerializedName(" Các vị vua")
	private String kings;
	
	@SerializedName("Tên triều đại")
	private String dynastyName;
	
	@SerializedName("Năm trị vì")
	private String reignTime;

	public String getAge() {
		return age;
	}

	public String getPeriod() {
		return period;
	}

	public String getKings() {
		return kings;
	}

	public String getDynastyName() {
		return dynastyName;
	}

	public String getReignTime() {
		return reignTime;
	}

	@Override
	public String toString() {
		return "DynastyObject{"
				+ "DynastyName='" + dynastyName + '\''
				+ ", kings='" + kings + '\'' 
				+ ", ages='" + age + '\''
				+ ", reignTime='" + reignTime + '\''
				+ '}';
	}
	
}
