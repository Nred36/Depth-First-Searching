/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package detectcolonies;

/**
 *
 * @author rkaune
 */
 public class DetectColonies2 {

	public static void main(String[] args) {

		Slide culture = new Slide("slide.dat");
		culture.displaySlide();
		culture.displayColonies();                
	}
}
