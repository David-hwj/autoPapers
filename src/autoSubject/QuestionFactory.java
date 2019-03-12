package autoSubject;
import java.util.*;
import java.math.*;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class QuestionFactory {
	private int question_level;         //棰樼洰绾у埆
	private int curr_question_num = 0;       //褰撳墠鍑洪鏁伴噺
	private Random rand_num = new Random();
	private String questions_all[];
	private String operates[] = {"+","-","*","/"};
	QuestionFactory(int question_level)
	{
		this.question_level = question_level;
		questions_all = new String[50];
	}
	
	private boolean NotHaveSame(String one_question_temp)
	{
		for(int i = 0;i < curr_question_num;i++)
		{
			if(one_question_temp.equals(questions_all[i]))
				return false;
		}
		return true;
	}
	
	private String BasicMake(int num_n)
	{
		String question_temp = "";
		int num_temp;
		int bracket_num = num_n/3;
	    int insert1;        //宸︽嫭鍙风殑鎻掑叆浣嶇疆
	    int insert2;    //鍙虫嫭鍙锋彃鍏ヤ綅缃�
	    if(bracket_num>0)
	    {
	        insert1 = rand_num.nextInt(num_n-1);        //宸︽嫭鍙风殑鎻掑叆浣嶇疆
	        insert2 = insert1 + 1 + rand_num.nextInt(num_n-insert1-1);    //鍙虫嫭鍙锋彃鍏ヤ綅缃�
	        if(insert2 == num_n-1 && insert1 == 0)
	            insert2--;
	    }
	    else
	    {
	        insert1 = -1;
	        insert2 = -1;
	    }
	    for(int i = 0;i < num_n;i++)
	    {
	        if(i==num_n - 1)
	        {
	        	num_temp = rand_num.nextInt(100)+1;
	            question_temp += num_temp;
	            if(i == insert2)
	            	question_temp += ")";
	            question_temp += " = ( )";
	        }
	        else
	        {
	            num_temp = rand_num.nextInt(100)+1;
	            if(i == insert1)
	                question_temp += "(";
	            question_temp += num_temp;
	            if(i == insert2)
	                question_temp += ")";
	            question_temp += operates[rand_num.nextInt(4)];
	        }
	    }
	    return question_temp;
	}
	
	private String NormalMake(int num_n)
	{
		String question_temp = "";
		int num_temp;
		int bracket_num = num_n/3;
	    int insert1;        //宸︽嫭鍙风殑鎻掑叆浣嶇疆
	    int insert2;    //鍙虫嫭鍙锋彃鍏ヤ綅缃�
	    if(bracket_num>0)
	    {
	        insert1 = rand_num.nextInt(num_n-1);        //宸︽嫭鍙风殑鎻掑叆浣嶇疆
	        insert2 = insert1 + 1 + rand_num.nextInt(num_n-insert1-1);    //鍙虫嫭鍙锋彃鍏ヤ綅缃�
	        if(insert2 == num_n-1 && insert1 == 0)
	            insert2--;
	    }
	    else
	    {
	        insert1 = -1;
	        insert2 = -1;
	    }
	    int m_temp = rand_num.nextInt(num_n);
	    int flag[] = new int[num_n];
	    for(int i = 0;i < num_n;i++)
	    {
	       flag[i] = 0;
	    }
	    flag[m_temp] = 1;
	    for(int i = 0;i < num_n;i++)
	    {
	        m_temp = rand_num.nextInt(2);
	        if(flag[i] != 1)
	            flag[i] = m_temp;
	    }
	    for(int i = 0;i < num_n;i++)
	    {
	        if(i==num_n - 1)
	        {
	        	num_temp = rand_num.nextInt(100)+1;
	            question_temp += num_temp;
	            if(flag[i] == 1)
	            {
	                m_temp = rand_num.nextInt(2);
	                if(m_temp == 0)
	                    question_temp += "^0.5";
	                else
	                    question_temp += "^2";
	            }
	            if(i == insert2)
	            	question_temp += ")";
	            question_temp += " = ( )";
	        }
	        else
	        {
	            num_temp = rand_num.nextInt(100)+1;
	            if(i == insert1)
	                question_temp += "(";
	            question_temp += num_temp;
	            if(flag[i] == 1)
	            {
	                m_temp = rand_num.nextInt(2);
	                if(m_temp == 0)
	                    question_temp += "^0.5";
	                else
	                    question_temp += "^2";
	            }
	            if(i == insert2)
	                question_temp += ")";
	            question_temp += operates[rand_num.nextInt(4)];
	        }
	    }
	    return question_temp;
	}
	
	private String HardMake(int num_n)
	{
		String question_temp = "";
		int num_temp;
		int bracket_num = num_n/3;
	    int insert1;        //宸︽嫭鍙风殑鎻掑叆浣嶇疆
	    int insert2;    //鍙虫嫭鍙锋彃鍏ヤ綅缃�
	    if(bracket_num>0)
	    {
	        insert1 = rand_num.nextInt(num_n-1);        //宸︽嫭鍙风殑鎻掑叆浣嶇疆
	        insert2 = insert1 + 1 + rand_num.nextInt(num_n-insert1-1);    //鍙虫嫭鍙锋彃鍏ヤ綅缃�
	        if(insert2 == num_n-1 && insert1 == 0)
	            insert2--;
	    }
	    else
	    {
	        insert1 = -1;
	        insert2 = -1;
	    }
	    int m_temp;
	    int flag_mi[] = new int[num_n];                            //鍐冲畾鏄惁鏈夊箓杩愮畻
	    int flag_tri[] = new int[num_n];                            //鍐冲畾鏄惁鏈変笁瑙掑嚱鏁�
	    for(int i = 0;i < num_n;i++)
	    {
	    	flag_mi[i] = 0;
	        flag_tri[i] = 0;
	    }
	    m_temp  = rand_num.nextInt(num_n);
	    flag_mi[m_temp] = 1;                          //鍏堜繚璇佽嚦灏戞湁涓�涓钩鏂规垨鏍瑰彿
	    for(int i = 0;i < num_n;i++)
	    {
	        m_temp = rand_num.nextInt(2);
	        if(flag_mi[i] != 1)
	            flag_mi[i] = m_temp;
	    }
	    m_temp  = rand_num.nextInt(num_n);
	    flag_tri[m_temp] = 1;                          //鍏堜繚璇佽嚦灏戜竴涓笁瑙掑嚱鏁�
	    for(int i = 0;i < num_n;i++)
	    {
	        m_temp = rand_num.nextInt(2);
	        if(flag_tri[i] != 1)
	            flag_tri[i] = m_temp;
	    }
	    
	    for(int i = 0;i < num_n;i++)
	    {
	        if(i==num_n - 1)
	        {
	        	num_temp = rand_num.nextInt(100)+1;
	        	if(flag_tri[i] == 1)
	            {
	                m_temp = rand_num.nextInt(3);
	                if(m_temp == 0)
	                    question_temp += "sin";
	                else if(m_temp == 1)
	                    question_temp +="cos";
	                else
	                    question_temp += "tan";
	            }
	            question_temp += num_temp;
	            if(flag_mi[i] == 1)
	            {
	                m_temp = rand_num.nextInt(2);
	                if(m_temp == 0)
	                    question_temp += "^0.5";
	                else
	                    question_temp += "^2";
	            }
	            if(i == insert2)
	            	question_temp += ")";
	            question_temp += " = ( )";
	        }
	        else
	        {
	            num_temp = rand_num.nextInt(100)+1;
	            if(i == insert1)
	                question_temp += "(";
	            if(flag_tri[i] == 1)
	            {
	                m_temp = rand_num.nextInt(3);
	                if(m_temp == 0)
	                    question_temp += "sin";
	                else if(m_temp == 1)
	                    question_temp +="cos";
	                else
	                    question_temp += "tan";
	            }
	            question_temp += num_temp;
	            if(flag_mi[i] == 1)
	            {
	                m_temp = rand_num.nextInt(2);
	                if(m_temp == 0)
	                    question_temp += "^0.5";
	                else
	                    question_temp += "^2";
	            }
	            if(i == insert2)
	                question_temp += ")";
	            question_temp += operates[rand_num.nextInt(4)];
	        }
	    }
	    return question_temp;
	}
	
	
	String GetNextQuestion()          //鑾峰彇涓嬩竴棰�
	{
		//curr_question_num++;
		int num_n;                     //鎿嶄綔鏁颁釜鏁�
		String one_question_temp;
		if(question_level == 0)
		{
			while(true)
	        {
	            num_n = rand_num.nextInt(4)+2;
	            one_question_temp = BasicMake(num_n);
	            if(NotHaveSame(one_question_temp))
	            {
	            	questions_all[curr_question_num] = one_question_temp;
	            	curr_question_num++;
	            	return one_question_temp;
	            }
	        }
		}
		else if(question_level == 1)
		{
			while(true)
	        {
	            num_n = rand_num.nextInt(5)+1;
	            one_question_temp = NormalMake(num_n);
	            if(NotHaveSame(one_question_temp))
	            {
	            	questions_all[curr_question_num] = one_question_temp;
	            	curr_question_num++;
	            	return one_question_temp;
	            }
	        }
		}
		else
		{
			while(true)
	        {
	            num_n = rand_num.nextInt(5)+1;
	            one_question_temp = HardMake(num_n);
	            if(NotHaveSame(one_question_temp))
	            {
	            	questions_all[curr_question_num] = one_question_temp;
	            	curr_question_num++;
	            	return one_question_temp;
	            }
	        }
		}
	}
	
	private String CalculateAll(int type,String calculate_temp)  //璁＄畻
	{
		double calculate_num = 0;
		try {
			calculate_num = Double.parseDouble(calculate_temp);
		}catch(NumberFormatException a){
			System.out.println("error:"+calculate_temp);
		}
		
		if(type == 0)
		{
			return String.format("%.2f",Math.pow(calculate_num, 2));
			
		}
		else if(type == 1)
		{
			return String.format("%.2f",Math.pow(calculate_num, 0.5));
		}
		else if(type == 2)
		{
			return String.format("%.2f",Math.sin(Math.toRadians(calculate_num)));
		}
		else if(type == 3)
		{
			return String.format("%.2f",Math.cos(Math.toRadians(calculate_num)));
		}
		else
		{
			return String.format("%.2f",Math.tan(Math.toRadians(calculate_num)));
		}
	}
	
	private String BasicCalculate(String question)throws ScriptException
	{
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		
		 if (engine instanceof Compilable) {
	            Compilable compilable = (Compilable) engine;
	            CompiledScript compiledScript = compilable.compile(question);
	            //compiledScript.eval();
	            }
		 Object result = engine.eval(question);
		String str = ""+result;//String.format("%.2f", result)
		//System.out.println(str);
		return str;
	}
	
	private String DeleteUnuseOperator(String question)
	{
		while(question.indexOf("--") != -1)
			question = question.replace("--", "+");
		while(question.indexOf("+-") != -1)
			question = question.replace("+-", "-");
		while(question.indexOf("-+") != -1)
			question = question.replace("-+", "-");
		question = question.replace(" = ( )", "");
		return question;
	}
	
	String GetAnswer()throws ScriptException
	{
		int position_index_start;
		int position_index_end;
		String calculate_temp;
		String question_temp = questions_all[curr_question_num - 1];
		while(question_temp.indexOf("^2") != -1)
		{
			position_index_start = question_temp.indexOf("^2");
			position_index_end = position_index_start;
			while(position_index_start>0 && question_temp.charAt(position_index_start - 1) >= '0' && question_temp.charAt(position_index_start - 1)<='9')
			{
				position_index_start--;
			}
			calculate_temp = question_temp.substring(position_index_start, position_index_end);
			question_temp = question_temp.replace(question_temp.substring(position_index_start, 
					position_index_end+1),CalculateAll(0,calculate_temp));
		}
		while(question_temp.indexOf("^0.5") != -1)
		{
			position_index_start = question_temp.indexOf("^0.5");
			position_index_end = position_index_start;
			while(position_index_start>0 && question_temp.charAt(position_index_start - 1) >= '0' && question_temp.charAt(position_index_start - 1)<='9')
			{
				position_index_start--;
			}
			calculate_temp = question_temp.substring(position_index_start, position_index_end);
			question_temp = question_temp.replace(question_temp.substring(position_index_start, 
					position_index_end+3),CalculateAll(1,calculate_temp));
		}
		while(question_temp.indexOf("sin") != -1)
		{
			position_index_start = question_temp.indexOf('s')+3;
			position_index_end = position_index_start;
			while((question_temp.charAt(position_index_end) >= '0' && question_temp.charAt(position_index_end)<='9') || question_temp.charAt(position_index_end)=='.')
			{
				position_index_end++;
			}
			calculate_temp = question_temp.substring(position_index_start, position_index_end);
			question_temp = question_temp.replace(question_temp.substring(position_index_start-3, 
					position_index_end),CalculateAll(2,calculate_temp));
		}
		while(question_temp.indexOf("cos") != -1)
		{
			position_index_start = question_temp.indexOf('c')+3;
			position_index_end = position_index_start;
			while((question_temp.charAt(position_index_end) >= '0' && question_temp.charAt(position_index_end)<='9') || question_temp.charAt(position_index_end)=='.')
			{
				position_index_end++;
			}
			calculate_temp = question_temp.substring(position_index_start, position_index_end);
			question_temp = question_temp.replace(question_temp.substring(position_index_start-3, 
					position_index_end),CalculateAll(3,calculate_temp));
		}
		while(question_temp.indexOf("tan") != -1)
		{
			position_index_start = question_temp.indexOf('t')+3;
			position_index_end = position_index_start;
			while((question_temp.charAt(position_index_end) >= '0' && question_temp.charAt(position_index_end)<='9') || question_temp.charAt(position_index_end)=='.')
			{
				position_index_end++;
			}
			calculate_temp = question_temp.substring(position_index_start, position_index_end);
			question_temp = question_temp.replace(question_temp.substring(position_index_start-3, 
					position_index_end),CalculateAll(4,calculate_temp));
		}
		question_temp = DeleteUnuseOperator(question_temp);
		return BasicCalculate(question_temp);
	}
	

}
