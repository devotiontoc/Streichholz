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

	@Override
	public String getCorrectEquation(String wrongEquation) throws Exception {
		
		
		return null;
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
				continue;
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
		String[] str = new String[2];
		str[0] = "";
		str[1] = "";
		boolean secondPart = false;
		for(int i= 0; i<wrongEquation.length(); i++){
			if(secondPart){
					str[1]+= wrongEquation.charAt(i);
				
				
			}
			else{
				if(wrongEquation.charAt(i) == '='){
					secondPart = true;
					continue;
				}
				
				str[0]+= wrongEquation.charAt(i);
			
			}
			
		}
		
		if(calculateEquation(str[0]) == calculateEquation(str[1]))
			return true;
		return false;
		
		
	}
	
}
