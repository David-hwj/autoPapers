package autoSubject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {

//	public static ArrayList<User> RegisteredUsers=new ArrayList();
	
	
	public static void main(String[] args) {
		
		
		//登陆界面
		LoginFrame loginFrame=new LoginFrame();
		loginFrame.setVisible(true);

		//注册界面
//		RegisterFrame registerFrame=new RegisterFrame();
//		registerFrame.setVisible(true);
		
		//生成题目界面
//		User user=new User("1234","123","小学");
//		SubjectFrame subjectFrame=new SubjectFrame(user);
//		subjectFrame.setVisible(true);
		
	}
	
	
//	public static void readRegisteredUsers(ArrayList<User> RegisteredUsers,File file) {
//		BufferedReader br;
//		try {
//			br = new BufferedReader(new FileReader(file));
//			String s = null;
//			while((s = br.readLine())!=null){//使用readLine方法，一次读一行
//			System.out.println(s);
//			}
//			br.close();
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}//构造一个BufferedReader类来读取文件
//		catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	/**
	 * 方便获取随机数的方法
	 * @param min 随机范围内的可出现最小值
	 * @param max 随机范围内的可出现最大值
	 * @return
	 */
	private static int getRand(int min,int max) {
		Random rand = new Random();
		return rand.nextInt(max-min+1)+min;
	}

}
