/**
 * 
 */
package com.indeed.challenges;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author SFargose
 *
 */
public class RobotCircleCheck {
	
	/*static int x=0,y=0;
	static char curDir='N';
	static HashSet<String> visited=new HashSet<String>();
	*/
	
	static String[] doesCircleExist(String[] commands) {
		String[] res=new String[commands.length];
		HashMap<Character, Character> left=new HashMap<Character, Character>();
		left.put('E', 'N');
		left.put('N', 'W');
		left.put('W', 'S');
		left.put('S', 'E');

		HashMap<Character, Character> right=new HashMap<Character, Character>();
		right.put('E', 'S');
		right.put('N', 'E');
		right.put('W', 'N');
		right.put('S', 'W');		

		
		for(int i=0;i<commands.length;i++)
			res[i]=checkForCircle(commands[i], left, right)?"YES":"NO";
		
		return res;
	}

	static boolean checkForCircle(String s,  HashMap<Character, Character> left,
			HashMap<Character, Character> right){
		
		int x=0,y=0;
		char curDir='N';
		

		for(int i=0;i<s.length();i++){
			char curMove=s.charAt(i);

			if(curMove=='L')
				curDir=left.get(curDir);			
			else if(curMove=='R')
				curDir=right.get(curDir);			
			else{
				switch(curDir){
				case 'E':x++; break;
				case 'N':y++; break;
				case 'W':x--; break;
				case 'S':y--; break;

				}
			}
			
		}

		if(curDir=='N' && (x*x+y*y)>0)
			return false;
		else
			return true;


	}


	public static void main(String[] args) {


		assertThat(new RobotCircleCheck().doesCircleExist(new String[]{"GL"}), is(new String[]{"YES"}));
		assertThat(new RobotCircleCheck().doesCircleExist(new String[]{"GRGL"}), is(new String[]{"NO"}));
		assertThat(new RobotCircleCheck().doesCircleExist(new String[]{"GLLG"}), is(new String[]{"YES"}));


		assertThat(new RobotCircleCheck().doesCircleExist(new String[]{"G","L"}), is(new String[]{"NO","YES"}));

		System.out.println("all cases passed");
	}

}
