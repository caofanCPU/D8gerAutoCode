package d8ger;

import com.xyz.caofancpu.d8ger.core.LinkUrlEnum;
import org.junit.Test;

/**
 * This is a Test Demo showing how to execute a test case by junit.
 *
 * @author D8GER
 */
public class D8T {

    @Test
    public void hello() {
        System.out.println(LinkUrlEnum.loadHelpInfo());
    }

}
