package d8ger;

import com.google.common.collect.Lists;
import com.xyz.caofancpu.d8ger.util.VerbalExpressionUtil;
import org.junit.Test;

import java.util.List;

/**
 * This is a Test Demo showing how to execute a test case by junit.
 *
 * @author D8GER
 */
public class D8T {


    @Test
    public void hello() {

        String nonW = "/myProject/common/src/main/java/com/cy/common/model/Student";
        String nonW1 = "myProject/common/src/main/java/com/cy/common/model/Student";
        String w1 = "D:/myProject/common/src/main/java/com/cy/common/model/Student";
        String w11 = "D://myProject//common//src//main//java//com//cy//common//model//Student";
        String w121 = "D:";
        String w122 = "D:/";
        String w123 = "D://";
        String w124 = "D:\\";
        String w125 = "D:\\\\";
        String w2 = "D:\\myProject\\common\\src\\main\\java\\com\\cy\\common\\model\\Student";
        String w3 = "D:\\\\myProject\\\\common\\\\src\\\\main\\\\java\\\\com\\\\cy\\\\common\\\\model\\\\Student";
        List<String> nonWindowsSourceList = Lists.newArrayList(nonW, nonW1);
        List<String> windowsSourceList = Lists.newArrayList(w1, w11, w121, w122, w123, w124, w125, w2, w3);
        System.out.println("非Windows系统:");
        for (String s : nonWindowsSourceList) {
            try {
                System.out.println("    原串 " + s + " 处理后变为 " + VerbalExpressionUtil.correctUrl(s) + " ");
                System.out.println("    原串 " + s + " 处理后变为 " + VerbalExpressionUtil.convertPathToPackage(s) + " ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Windows系统:");
        VerbalExpressionUtil.CURRENT_OS_IS_WINDOWS = Boolean.TRUE;
        for (String s : windowsSourceList) {
            try {
                System.out.println("    原串 " + s + " 处理后变为 " + VerbalExpressionUtil.correctUrl(s) + " ");
                System.out.println("    原串 " + s + " 处理后变为 " + VerbalExpressionUtil.convertPathToPackage(s) + " ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
