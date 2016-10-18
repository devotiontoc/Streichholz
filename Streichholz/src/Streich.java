/**
 * 
 * @author Marcel Janzer
 *
 */
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Streich implements A01_Matches {
	
	private String copy;
	private int counter = 0;
	private String fill;
	private boolean checked = false;
	/**
	 * 
	 */
	@Override
	public String getCorrectEquation(String wrongEquation) throws Exception {
		if(wrongEquation == "" || wrongEquation.length() <= 2){
			throw new Exception();
		}
		
		for(int x= 0; x<wrongEquation.length(); x++){
			this.counter = 0;
			if(moveEquation(x,wrongEquation))
				return copy;
			}
		for(int i= 0; i<wrongEquation.length(); i++){
			for(int j= 0; j<wrongEquation.length(); j++)
			{
				if(i==j)
					continue;
				this.counter = 0;
				if(alterEquation(i,j,wrongEquation))
					return copy;
				}
		}
	return wrongEquation+" ...No solution to ths equation :(";
	}
	/**
	 * switch case for different possibilities to add a matchstick
	 * @param c
	 * @return
	 */
	public String getAddNumber(char c){
		switch(c){
        case '0': return "8";
        case '1': return "7";
        case '3': return "9";
        case '5': return "69";
        case '6': return "8";
        case '9': return "8";
        case '-': return "+=";
        default: return "No";
		}
		
	}
	/**
	 * switch case for different possibilities to move a matchstick
	 * @param c
	 * @return
	 */
	public String getMoveNumber(char c){
		switch(c){
        case '0': return "69";
        case '2': return "3";
        case '3': return "25";
        case '5': return "3";
        case '6': return "09";
        case '9': return "06";
        default: return "No";
		}
	}
	
	/**
	 * 
	 * @param equation
	 * @return returns the value of the solved equation(without '=')
	 */
	public int calculateEquation(String equation){
		int res = 0;
		boolean nextPlus = true;
		List<String> s =  new ArrayList<String>();
		Pattern pattern = Pattern.compile("[0-9]+|[-+]");  //Matches for Numbers between 0 and 9, interrupted by '+' or '-'               
        Matcher matcher = pattern.matcher(equation);
        /**
         * Iterating over matcher and safe results in ListArray
         */
        while(matcher.find()){
        	s.add(matcher.group(0));
        	}
        /**
         * Adding the String content to result (parsing)
         */
        for (int i = 0; i < s.size(); i++) {
        	
			if(s.get(i).contains("+")){
				nextPlus = true;
			}
			else if(s.get(i).contains("-")){
				nextPlus = false;
			}
			else{
				
				if(nextPlus){
					res = res+Integer.parseInt(s.get(i));}
				else{
					res-= Integer.parseInt(s.get(i));
				}
			}
		}
		return res;
	}
	
	/**
	 * 
	 * @param wrongEquation
	 * @return returns boolean true:if given Equation is correct
	 * splits equation into 2 parts(before and after '=') and calls calculateEquation twice. 
	 */
	public boolean checkEquation(String wrongEquation){
		int equals = 0;
		for(int i= 0; i<wrongEquation.length();i++){
			if(wrongEquation.charAt(i) == '=')
				equals++;
		}
		if(equals>=2||equals<1)
			return false;
		
		String[] str = wrongEquation.split("=");
		if(calculateEquation(str[0]) == calculateEquation(str[1]))
			return true;
		return false;
	}
	/**
	 * creates new string with the given changes (remove and add), 
	 * and checks whether it's true or recursively trying to find different solution
	 * @param c
	 * @param i
	 * @param j
	 * @param wrongEquation
	 * @return 
	 */
	public void addString(char c, int i, int j, String wrongEquation){
		fill = getAddNumber(wrongEquation.charAt(j));
		if(i<j)
			copy = wrongEquation.substring(0,i)+c+wrongEquation.substring(i+1,j)+fill.charAt(this.counter)+wrongEquation.substring(j+1,wrongEquation.length());
		else
			copy = wrongEquation.substring(0,j)+fill.charAt(this.counter)+wrongEquation.substring(j+1,i)+c+wrongEquation.substring(i+1,wrongEquation.length());
		if(checkEquation(copy)){
			checked = true;
			return;
		}
		if(fill == "69" && this.counter == 0){
			this.counter++;
			addString('5',i,j,wrongEquation);
		}
		else if(fill == "+=" && this.counter == 0){
			this.counter++;
			addString('-',i,j,wrongEquation);
		}
		}
	/**
	 * creates new string with the given changes (move itself),
	 * and checks whether it's true or recursively trying to find different solution
	 * @param i
	 * @param wrongEquation
	 */
	public void moveString(int i, String wrongEquation){
		fill = getMoveNumber(wrongEquation.charAt(i));
		copy = wrongEquation.substring(0,i)+fill.charAt(this.counter)+wrongEquation.substring(i+1,wrongEquation.length());
		if(checkEquation(copy)){
			checked = true;
			return;
			}
		else if(fill != "3" && this.counter == 0){
			this.counter++;
			moveString(i,wrongEquation);
		}
	}
	
	/**
	 * goes through all the possibilities to remove and a char(actually int) and get the new value for it
	 * @param i
	 * @param j
	 * @param wrongEquation
	 * @return 
	 */
	public boolean alterEquation(int i,int j,String wrongEquation){
		if(wrongEquation.charAt(i) == '0' || wrongEquation.charAt(i) == '1' || wrongEquation.charAt(i) == '2' || wrongEquation.charAt(i) == '3' || wrongEquation.charAt(i) == '4' || wrongEquation.charAt(i) == '5')
			return false;
		if(wrongEquation.charAt(i) == '6'){
				addString('5',i,j,wrongEquation);
				if(checked)
					return true;
		}
		else if(wrongEquation.charAt(i) == '7'){
			addString('1',i,j,wrongEquation);
			if(checked)
				return true;
		}
		else if(wrongEquation.charAt(i) == '8'){
			addString('0',i,j,wrongEquation);
			if(checked)
				return true;
			addString('6',i,j,wrongEquation);
			if(checked)
				return true;
			addString('9',i,j,wrongEquation);
			if(checked)
				return true;
		}
		else if(wrongEquation.charAt(i) == '9'){
			addString('3',i,j,wrongEquation);
			if(checked)
				return true;
			addString('5',i,j,wrongEquation);
			if(checked)
				return true;
		}
		else if(wrongEquation.charAt(i) == '+'){
			addString('-',i,j,wrongEquation);
			if(checked)
				return true;
		}
		else if(wrongEquation.charAt(i) == '='){
			addString('-',i,j,wrongEquation);
			if(checked)
				return true;
		}
		return false;
	}
	/**
	 * basically calls moveString to get a possible solution 
	 * @param i
	 * @param wrongEquation
	 * @return
	 */
	public boolean moveEquation(int i,String wrongEquation){
		if(wrongEquation.charAt(i) == '1' || wrongEquation.charAt(i) == '4' || wrongEquation.charAt(i) == '7' || wrongEquation.charAt(i) == '8' || wrongEquation.charAt(i) == '=' || wrongEquation.charAt(i) == '+' || wrongEquation.charAt(i) == '-'){
			return false;
		}
		moveString(i,wrongEquation);
		if(checked){
			
			return true;
		}
		return false;
	}
}
	

