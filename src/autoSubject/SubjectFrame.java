package autoSubject;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.script.ScriptException;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


/**
 * 做题界面
 * @author HWJ
 *
 */
public class SubjectFrame extends JFrame {
	String subjectFrame_title="题目生成器";
	int subjectFrame_WIDTH=500;
	int subjectFrame_HEIGHT=410;
	String[] grades= {"小学","初中","高中"};//下拉选择题目可选类型
	String[] nums= {"10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};//可选题目数量 
	ArrayList<Subject> subjects=new ArrayList();//生成的题目，全部存储可供选择上一题
	JPanel doSubject;//做题面板
	JLabel quesLabel;//做题面板上放置题干的标签
	JRadioButton randioButton1;//做题面板上四个单选按钮		
	JRadioButton randioButton2;		
	JRadioButton randioButton3;
	JRadioButton randioButton4;
	ButtonGroup group;//按钮选项组
	
	User user;//登录的用户
	String grade;//要生成题目的年级
	int num;//要生成题目的数量
	boolean isDoSubject=false;//当前状态是否在做题中
	
	int subjectDoing=0;//当前在做/显示的题目
	SubjectFrame(User user,JFrame frame){
		this.user=user;
		frame.dispose();
		initSubjectFrame();
	}
	SubjectFrame(User user,String grade,JFrame frame){
		this.user=user;this.grade=grade;
		frame.dispose();
		initSubjectFrame();
	}
	
	public void initSubjectFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);//关闭窗体即退出程序
		this.setTitle(subjectFrame_title);
		this.setSize(subjectFrame_WIDTH, subjectFrame_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null); //居中显示
		setLayout(null);//任意布局
		this.setBackground(Color.DARK_GRAY);
		
		//题目类型下拉选择框
		JLabel gradeChoose=new JLabel("选择题目类型:");
		gradeChoose.setBounds(40, 20, 120, 25);
		add(gradeChoose);
		JComboBox chooseGrade = new JComboBox(grades);
		chooseGrade.setBounds(140, 20, 60, 25);
		add(chooseGrade);
		
		//选择题目数量
		JLabel numChoose=new JLabel("选择题目数量:");
		numChoose.setBounds(290, 20, 100, 25);
		add(numChoose);
		JComboBox chooseNum = new JComboBox(nums);                                                            //实例化列表框       
		chooseNum.setBounds(390, 20, 60, 25 );
        add(chooseNum);
		
		//开始做题按钮
		JButton beginDoSubject=new JButton("开始做题 / 重新出题");
		beginDoSubject.setBounds(156, 60, 180, 25);
		add(beginDoSubject);
		
		//做题面板
		doSubject=new JPanel();
		doSubject.setBounds(30, 100, subjectFrame_WIDTH-60, 200);
		doSubject.setBackground(Color.WHITE);
		doSubject.setLayout(null);//任意布局
		quesLabel=new JLabel("未生成题目");//做题面板加上题干标签
		quesLabel.setBounds(20, 20, subjectFrame_WIDTH-20, 80);
		quesLabel.setFont(new Font("Dialog", 1, 18));//设置字体和大小
		quesLabel.setForeground(Color.BLACK);//设置字体颜色
		doSubject.add(quesLabel);
		randioButton1=new JRadioButton("A");		//四个选项单选按钮
		randioButton1.setBounds(16, 120, 103, 60);
		randioButton1.setBackground(Color.WHITE);
		randioButton2=new JRadioButton("B");	
		randioButton2.setBounds(119, 120, 103, 60);
		randioButton2.setBackground(Color.WHITE);
		randioButton3=new JRadioButton("C"); 
		randioButton3.setBounds(222, 120, 103, 60);
		randioButton3.setBackground(Color.WHITE);
		randioButton4=new JRadioButton("D");
		randioButton4.setBounds(325, 120, 103, 60);
		randioButton4.setBackground(Color.WHITE);
		doSubject.add(randioButton1);
		doSubject.add(randioButton2);
		doSubject.add(randioButton3);
		doSubject.add(randioButton4);
		group=new ButtonGroup();
		group.add(randioButton1);
		group.add(randioButton2);
		group.add(randioButton3);
		group.add(randioButton4);
		add(doSubject);
		
		
		
		//上一题下一题按钮
		JButton up=new JButton("上一题");
		up.setBounds(100, 310, 100, 25);
		add(up);
		JButton down=new JButton("下一题");
		down.setBounds(300, 310, 100, 25);
		add(down);
		
		//提交按钮
		JButton upPaper=new JButton("提交试卷");
		upPaper.setBounds(150, 345, 200, 25);
		add(upPaper);
		
		//做题按钮加监听
		beginDoSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//判断当前用户状态
				if(isDoSubject) {//用户当前在做题，判断做题情况
					int subjectDone=0;//当前这套题已做的数量，用来检查题目是否做完
					for(int i=0;i<num;i++)
						if(subjects.get(i).gethasSelected()!=-1)subjectDone++;
					if(num>subjectDone) {//未做完题
						//弹出选择框，是否中断做题
						int isContinue=JOptionPane.showConfirmDialog(null, "当前试卷有题目未选择答案，是否继续完成？", "提示", JOptionPane.YES_NO_OPTION);
						if(isContinue==JOptionPane.NO_OPTION) {//选择了中断答题
							int doright=0,dowrong=0,donot=0,score=0;
							for(int i=0;i<num;i++) {
								Subject subject=subjects.get(i);
								if(subject.gethasSelected()==-1) {donot++;}//未做
								else if(subject.isRight()) {doright++;}//做对了
								else {dowrong++;}//做错了
							}
							score=(doright*100)/num;
							String msg="";
							String str="\n请再次点击\"开始做题 / 重新出题\"按钮重新答题。";
							msg="总共"+num+"道题，其中你答对"+doright+"道，答错"+dowrong+"道，还有"+donot+"道未做，所以你本次得分为："+score;
							JOptionPane.showMessageDialog(null, msg+str);
							subjects.clear();//清空当前试卷
							isDoSubject=false;//状态设为空闲
							quesLabel.setText("未生成题目");
							randioButton1.setText("A");
							randioButton2.setText("B");
							randioButton3.setText("C");
							randioButton4.setText("D");
						}
						else {//选择了继续答题
							return;
						}
					}					
				}
				else {//用户没在做题：刚登陆或一提交
					isDoSubject=true;//设置用户状态：在做题
					int gradeNum=chooseGrade.getSelectedIndex();
					grade=grades[gradeNum];
					num=chooseNum.getSelectedIndex()+10;
					//生成题目
					QuestionFactory qf=new QuestionFactory(gradeNum);
					for(int i=0;i<num;i++) {
						try {
							Subject subject=new Subject(qf.GetNextQuestion(),qf.GetAnswer());
							subjects.add(subject);
						} catch (ScriptException e) {e.printStackTrace();}
					}
					//显示第一道题
					displaySubject(0);
				}	
			}		
		});
		
		//"上一题"按钮加监听
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!isDoSubject)return;//不处于做题状态
				subjectDoing--;
				if(subjectDoing<0) {
					subjectDoing=0;JOptionPane.showMessageDialog(null, "已经为第一道题");
				}
				else {	
					displaySubject(subjectDoing);//显示上一题
				}	
			}	
		});
		//"下一题"按钮加监听
		down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!isDoSubject)return;//不处于做题状态
				subjectDoing++;
				if(subjectDoing>=num) {
					subjectDoing=num-1;JOptionPane.showMessageDialog(null, "已经为最后一道题");
				}
				else {	
					displaySubject(subjectDoing);//显示下一题
				}
			}	
		});
		//"提交试卷"按钮加监听
		upPaper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(isDoSubject) {//用户当前在做题，判断做题情况
					int subjectDone=0;//当前这套题已做的数量
					for(int i=0;i<num;i++)
						if(subjects.get(i).gethasSelected()!=-1)subjectDone++;
					if(num>subjectDone) {//未做完题
						//弹出选择框，是否中断做题
						int isContinue=JOptionPane.showConfirmDialog(null, "当前试卷有题目未选择答案，是否继续完成？", "提示", JOptionPane.YES_NO_OPTION);
						if(isContinue==JOptionPane.NO_OPTION) {//选择了中断答题
							int doright=0,dowrong=0,donot=0,score=0;
							for(int i=0;i<num;i++) {
								Subject subject=subjects.get(i);
								if(subject.gethasSelected()==-1) {donot++;}//未做
								else if(subject.isRight()) {doright++;}//做对了
								else {dowrong++;}//做错了
							}
							score=(doright*100)/num;
							String msg="";
							msg="总共"+num+"道题，其中你答对"+doright+"道，答错"+dowrong+"道，\n还有"+donot+"道未做，所以你本次得分为："+score;
							JOptionPane.showMessageDialog(null, msg);
							subjects.clear();//清空当前试卷
							isDoSubject=false;//状态设为空闲
							quesLabel.setText("未生成题目");
							randioButton1.setText("A");
							randioButton2.setText("B");
							randioButton3.setText("C");
							randioButton4.setText("D");
						}
						else {//选择了继续答题
							return;
						}
					}					
				}
				else {//用户没在做题
					JOptionPane.showMessageDialog(null, "没有未提交的试卷");
				}					
			}	
		});
		//四个单选按钮加监听
		randioButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isDoSubject)return;
				subjects.get(subjectDoing).setWhichBeSelected(0);
			}
		});
		randioButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isDoSubject)return;
				subjects.get(subjectDoing).setWhichBeSelected(1);
			}
		});
		randioButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isDoSubject)return;
				subjects.get(subjectDoing).setWhichBeSelected(2);
			}
		});
		randioButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isDoSubject)return;
				subjects.get(subjectDoing).setWhichBeSelected(3);
			}
		});
		
		
		
	}//init结束括号
	
	/**
	 * 显示subjects中下标index的题目
	 * @param index
	 */
	private void displaySubject(int index) {
		Subject subject=subjects.get(index);
		quesLabel.setText("题目"+(index+1)+":    "+subjects.get(index).getQuestion());
		String[] answers=subject.getOptions();
		randioButton1.setText(answers[0]);
		randioButton2.setText(answers[1]);
		randioButton3.setText(answers[2]);
		randioButton4.setText(answers[3]);
		if(subject.gethasSelected()==-1) {
			group.clearSelection();
		}
		else {
			ButtonModel bm=null;
			if(subject.gethasSelected()==0) {bm=randioButton1.getModel();}
			else if(subject.gethasSelected()==1) {bm=randioButton2.getModel();}
			else if(subject.gethasSelected()==2) {bm=randioButton3.getModel();}
			else if(subject.gethasSelected()==3) {bm=randioButton4.getModel();}
			group.setSelected(bm, true);
		}
	}
	
}//类结束括号
