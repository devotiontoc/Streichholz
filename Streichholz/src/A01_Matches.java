/**
 * 
 * @author Marcel Janzer
 *
 */

/**
 * 
 * given Interface:
 *
 */
public interface A01_Matches {
		/**
		* Transforms a wrong equation into a correct equation by moving one match.
		* @param wrongEquation A String containing the equation to be corrected.
		* @return The corrected equation or null (there is no valid transformation)
		* @throws Exception if anything goes wrong
		*/
		public String getCorrectEquation(String wrongEquation) throws Exception;
		
}
