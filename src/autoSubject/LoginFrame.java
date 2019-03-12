package autoSubject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
 * 登陆界面
 * @author HWJ
 *
 */
public class LoginFrame extends JFrame{
	String loginFrame_title ="题目生成器-登陆";//窗口标题
	int loginFrame_WIDTH=350;
	int loginFrame_HEIGHT=280;
	ImageIcon top_img=new ImageIcon("mysrc/login_top.png");//窗口顶部背景图片
	File file=new File("mysrc/RegisteredUsers.txt");//已注册用户信息文件
	
	LoginFrame(){
		 init_loginFrame();
	}
	
	private void init_loginFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);//关闭窗体即退出程序
		setTitle(loginFrame_title);
		setSize(loginFrame_WIDTH, loginFrame_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null); //居中显示
		setLayout(null);//任意布局
		//加顶部背景图
		JLabel top_bg=new JLabel(top_img);
		top_bg.setBounds(0,0,loginFrame_WIDTH,100);
		add(top_bg);//加上顶部背景
		//加账号输入框
		JLabel account_label=new JLabel("账户/手机号:");
		account_label.setBounds(50,120,80,25);//x,y,w,h
		JTextField account_input=new JTextField("13600000000");
		account_input.setBounds(130,120,150,25);//x,y,w,h
		add(account_label);
		add(account_input);
		
		//加密码输入框
		JLabel password_label=new JLabel("密码:");
		password_label.setBounds(50,160,80,25);//x,y,w,h
		JPasswordField password_input=new JPasswordField();
		password_input.setBounds(130,160,150,25);//x,y,w,h
		add(password_input);
		add(password_label);
		
		//加注册登陆按钮
		JButton registButton=new JButton("注册");
		registButton.setBounds(80, 200, 60, 25);//x,y,w,h
		JButton loginButton=new JButton("登陆");
		loginButton.setBounds(190, 200, 60, 25);//x,y,w,h
		add(registButton);
		add(loginButton);
		
		//给注册按钮加监听
		registButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterFrame registerFrame=new RegisterFrame();
				registerFrame.setVisible(true);
			}
		});
		
		//给登陆按钮加监听
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String acc_input=account_input.getText();
				String pas_input=password_input.getText();
				User user=new User(acc_input,pas_input);
				boolean flag=matchUser(user);
				if(acc_input.equals(""))JOptionPane.showMessageDialog(null, "请输入账号");
				else if(pas_input.equals(""))JOptionPane.showMessageDialog(null, "请输入密码");
				else if(flag && user.getPassword().equals("") )JOptionPane.showMessageDialog(null, "密码错误");
				else if(!flag)JOptionPane.showMessageDialog(null, "该用户未注册");
				else{//密码正常，登录成功
					SubjectFrame subjectFrame=new SubjectFrame(user,getFrame());//把本窗体交由下一个窗口销毁
					subjectFrame.setVisible(true);
				}
			}		
		});
		
		//给密码输入框加监听
		password_input.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String acc_input=account_input.getText();
				String pas_input=password_input.getText();
				User user=new User(acc_input,pas_input);
				boolean flag=matchUser(user);
				if(acc_input.equals(""))JOptionPane.showMessageDialog(null, "请输入账号");
				else if(pas_input.equals(""))JOptionPane.showMessageDialog(null, "请输入密码");
				else if(flag && user.getPassword().equals("") )JOptionPane.showMessageDialog(null, "密码错误");
				else if(!flag)JOptionPane.showMessageDialog(null, "该用户未注册");
				else{//密码正常，登录成功
					SubjectFrame subjectFrame=new SubjectFrame(user,getFrame());//把本窗体交由下一个窗口销毁
					subjectFrame.setVisible(true);
				}
			}		
		});
	}
	/**
	 * 获取本窗体对象
	 * @return
	 */
	private JFrame getFrame() {
		return this;
	}
	/**
	 * 查找用户是否存在
	 * @param user
	 * @return
	 */
	private boolean matchUser(User user) {
		boolean ans=false;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String s = null;
			while((s = br.readLine())!=null){//使用readLine方法，一次读一行
				String[] chrstr = s.split(" ");
				if(user.getName().equals(chrstr[0])) {//此用户已注册
					if(user.getPassword().equals(chrstr[1])) {//密码正确
						user.setAll(chrstr[0], chrstr[1], chrstr[2]);
						ans=true;
						break;
					}
					else {//密码错误
						user.setAll(chrstr[0], "", "");
						ans=true;
						break;
					}
				}	 
			}
			br.close();			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}//构造一个BufferedReader类来读取文件
		catch (IOException e) {
			e.printStackTrace();
		}
		if(ans==false)user.setAll("", "", "");
		return ans;
	}

}
