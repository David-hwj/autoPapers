package autoSubject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
/**
 * 注册界面
 * @author HWJ
 *
 */
public class RegisterFrame extends JFrame{
	String registerFrame_title ="题目生成器-注册";//窗口标题
	int registerFrame_WIDTH=300;
	int registerFrame_HEIGHT=200;
	
	
	RegisterFrame(){
		init_RegisterFrame();
	}
	private void init_RegisterFrame() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(registerFrame_title);
		setSize(registerFrame_WIDTH, registerFrame_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);//居中显示
		setLayout(null);//任意布局
		
		//注册手机号输入
		JLabel tips=new JLabel("注册手机号:");
		tips.setBounds(20, 20, 80, 25);//x,y,w,h
		add(tips);
		JTextField number_input=new JTextField("13600000000");
		number_input.setBounds(100,20,155,25);//x,y,w,h
		add(number_input);
		
		//验证码输入
		JLabel verifyCode=new JLabel("验证码:");
		verifyCode.setBounds(20, 60, 50, 25);//x,y,w,h
		add(verifyCode);
		JTextField verifyCode_input=new JTextField("0000");
		verifyCode_input.setBounds(70,60,50,25);//x,y,w,h
		add(verifyCode_input);
		JButton getVerifyCode=new JButton("点击获取验证码");
		getVerifyCode.setBounds(130, 60, 125, 25);//x,y,w,h
		add(getVerifyCode);
		
		//验证按钮
		JButton verifyButton=new JButton("确定");
		verifyButton.setBounds(85, 110, 120, 25);
		add(verifyButton);
		
		//发送验证码按钮加监听
		getVerifyCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sentVerifyCode();
			}		
		});
		
		//确定按钮加监听
		verifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}			
		});
		
		
	}
	//发送验证码的方法
	public void sentVerifyCode() {
		
	}
	
}
