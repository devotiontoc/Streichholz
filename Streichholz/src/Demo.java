/**
 * 
 * @author Marcel Janzer
 *
 */

//Tester
public class Demo {

	public static void main(String[] args){
		Streich s = new Streich();
		try {
			System.out.println(s.getCorrectEquation("93-27-30+16=68"));
		} catch (Exception e) {
			System.out.println("Incorrect Input");
		}
		}
	
}


