package autoSubject;

import java.util.Random;

/**
 * 一道题目：题干及其四个选项
 * @author HWJ
 *
 */
public class Subject {

	private String question="";//题干
	private String answer="";//答案 两位小数
	private String[] options={"","","",""};//这题的四个选项
	private int hasSelected=-1;//已选选项：0,1,2,3.-1表示此题为选答案
	
	Subject(String question,String answer){
		this.question=question;this.answer=answer;
		createOptions();
	}
	
	public String getQuestion() {
		return question;
	}
	public String[] getOptions() {
		return options;
	}
	/**
	 * 给出这题的选择
	 * @param s
	 */
	public void setWhichBeSelected(int s) {
		this.hasSelected=s;
	}
	/**
	 * 外部调用判断做题正确否
	 * @param option
	 * @return
	 */
	public boolean isRight(String option) {
		if(option.equals(answer))return true;
		return false;
	}
	public boolean isRight() {
		if(hasSelected==-1)return false;//此题未做
		else if(options[hasSelected].equals(answer))return true;//选对答案
		return false;
	}
	public int gethasSelected() {
		return this.hasSelected;
	}
	/**
	 * 构造器中调用，生成四个选项
	 */
	private void createOptions() {
		double ans=Double.parseDouble(answer);//正确答案
		//随机放入正确答案
		int place=getRand(0,3);
		int size=options.length;
		for(int i=0;i<size;i++) {
			if(i==place) options[i]=answer;
			else options[i]=""+getAOption(ans);
		}
	}
	/**
	 * 根据正确答案生成近似选项
	 * @param answer
	 * @return
	 */
	private double getAOption(double answer) {
		double ans=answer;
		double p=0.00;//生成错误选项的偏移量
		p=((double)getRand(1,199))/100;
		if(getRand(0,1)==1) ans+=p;
		else ans-=p;
		return ans;
	}
	/**
	 * 方便获取随机数的方法
	 * @param min 随机范围内的可出现最小值
	 * @param max 随机范围内的可出现最大值
	 * @return
	 */
	private int getRand(int min,int max) {
		Random rand = new Random();
		return rand.nextInt(max-min+1)+min;
	}
}
