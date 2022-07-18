package com.example.demo;

public class Test {
	
	public static void main (String args[]) {
		
		char[] s= {'h','e','l','l','o'};
		
		
		int len=s.length-1;
		char result;
		for(int i=0;i<len;i++) {
			result=s[i];
			s[i]=s[len];
			s[len]=result;
			len--;
			System.out.println(s);
		}
		
	}

}
