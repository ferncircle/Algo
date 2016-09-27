package com.chawkalla.algorithms.examples;


public class ReverseString {


	public static void main(String[] args) {

		String str=" ";
		System.out.println(str);
		StringBuffer sb=new StringBuffer(str);

		//reverse characters
		sb=reverseCharacters(sb, 0, sb.length()-1);

		//reverse words
		sb=reverseWords(sb);

		//trim
		sb=trim(sb);
		System.out.println(sb);
		

		char[] s=str.toCharArray();

		s=reverse(str.toCharArray(), 0, str.length()-1);		
		//System.out.println(s);		

		s=reverseWords(s);
		//System.out.println(s);
	}

	public static String reverseString(String s){
		if(s==null)
			return s;
		StringBuffer reverse=reverseCharacters(new StringBuffer(s), 0, s.length()-1);
		return reverse.toString();
	}

	public String reverseWords(String s) {
		if(s==null)
			return s;
		s=s.trim();
		StringBuffer sb=new StringBuffer(s);
		//reverse characters
		sb=reverseCharacters(sb, 0, sb.length()-1);

		//reverse words
		sb=reverseWords(sb);

		//trim
		sb=trim(sb);
		return sb.toString();
	}

	/**
	 * This method swaps starting char with ending in given range
	 * @param s
	 * @param start
	 * @param end
	 * @return
	 */
	public static StringBuffer reverseCharacters(StringBuffer s, int start, int end){

		if(s==null || s.length()==1 || start>=end || end>s.length())
			return s;

		int mid=(end-start)/2;

		for(int i=start;i<=(start+mid);i++){
			int a=i;
			int b=start+(end-i); //adding start offset
			//swap
			char temp=s.charAt(a);
			s.setCharAt(a, s.charAt(b));
			s.setCharAt(b, temp);
		}		

		return s;
	}


	/**
	 * This method reverses each word in sentence
	 * @param s
	 * @return
	 */
	public static  StringBuffer reverseWords(StringBuffer s){
		if(s==null || s.length()==1)
			return s;


		//reverse words
		boolean done=false;
		int cursor=0;
		while(!done){
			//find start of word, make sure not to cross boundaries
			while(cursor<=(s.length()-1) && Character.isWhitespace(s.charAt(cursor)))
				cursor++;
			int startWord=cursor;	

			//find end of word
			while(cursor<=(s.length()-1) && !Character.isWhitespace(s.charAt(cursor)) )
				cursor++;			
			int endWord=cursor-1;

			//now reverse that word in array
			if(startWord<endWord)
				reverseCharacters(s, startWord, endWord);

			if(cursor>=(s.length()-1))
				done=true;
		}

		return s;
	}


	public static StringBuffer trim(StringBuffer s){
		if(s==null || s.length()==1)
			return s;

		//remove whitespaces

		//remove starting and trailing spaces	
		int cursor=0;
		while(cursor<=(s.length()-1) && Character.isWhitespace(s.charAt(cursor)))
			cursor++;
		if(cursor>0)
			s.delete(0, cursor);

		cursor=s.length()-1;
		while(cursor>0 && Character.isWhitespace(s.charAt(cursor))){
			cursor--;
		}
		s.delete(cursor+1, s.length());

		//now remove extra whitespaces in between words
		boolean done=false;
		cursor=0;
		while(!done){
			//find white space
			while(cursor<=(s.length()-1) && !Character.isWhitespace(s.charAt(cursor)))
				cursor++;
			int whiteSpace=cursor;
			//replace whitespace with space
			if(whiteSpace<s.length())
				s.setCharAt(whiteSpace, ' ');

			//now find next word			
			while(cursor<=(s.length()-1) && Character.isWhitespace(s.charAt(cursor)) )
				cursor++;			

			//delete everything from whitespace to next word
			if((whiteSpace+1)<cursor)
				s.delete(whiteSpace+1, cursor);

			if(cursor>=(s.length()-1))
				done=true;
		}


		return s;
	}


	public static char[] reverse(char[] s, int start, int end){

		if(s==null || s.length==1 || start>=end || end>s.length)
			return s;

		int mid=(end-start)/2;

		for(int i=start;i<=(start+mid);i++){
			int a=i;
			int b=start+(end-i); //adding start offset
			//swap
			char temp=s[a];
			s[a]=s[b];
			s[b]=temp;
		}		

		return s;
	}

	public static  char[] reverseWords(char[] s){
		if(s==null || s.length==1)
			return s;

		boolean done=false;
		int cursor=0;
		while(!done){
			//find start of word, make sure not to cross boundaries
			while(cursor<=(s.length-1) && s[cursor]==' ')
				cursor++;
			int startWord=cursor;	

			//find end of word
			while(cursor<=(s.length-1) && s[cursor]!=' ' )
				cursor++;			
			int endWord=cursor-1;

			//now reverse that word in array
			if(startWord<endWord)
				reverse(s, startWord, endWord);

			if(cursor>=(s.length-1))
				done=true;
		}

		String str=new String(s);
		StringBuffer sb=new StringBuffer();
		for(String t:str.split("\\s+")){
			if(t.length()>0)
				sb.append(t+" ");			
		}
		sb.setLength(sb.length()-1);



		return sb.toString().toCharArray();
	}
}
