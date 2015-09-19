/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windu.pos.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author WinduPurnomo
 */
public class NumberHelper {
    public static String thousandSparator(double value) {
      Locale loc = new Locale("de", "DE");
      String pattern = "###,###.###";
      NumberFormat nf = NumberFormat.getNumberInstance(loc);
      DecimalFormat df = (DecimalFormat)nf;
      df.applyPattern(pattern);
      String output = df.format(value);
      return output;
   }
}
