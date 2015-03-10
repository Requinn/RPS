import java.util.*;
import java.io.*;
public class Pattern implements Serializable{
	private char[] patternValue;
	public Pattern(String pattern){
		this.patternValue = pattern.toCharArray();
	}
	
	public char[] getPattern(){
		return patternValue;
	}
	
	public int hashCode(){
		return Arrays.hashCode(patternValue);
	}
	
	public boolean equals(Object o){
		if(o==this){
			return true;
		}
		if(!(o instanceof Pattern)){
			return false;
		}
		Pattern pa = (Pattern) o; 
		return Arrays.equals(patternValue, pa.patternValue);
	}
	
	
}
