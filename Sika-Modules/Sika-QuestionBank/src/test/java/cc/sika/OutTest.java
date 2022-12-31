package cc.sika;

import org.junit.jupiter.api.Test;

/**
 * @author 吴畅
 * @创建时间 2022/12/27 - 12:41
 */
public class OutTest {
    @Test
    void name() {
        String recipe = "双花15克连翘10克元参15克射干10克板蓝根15克淡豆豉10克黄芩10克荆芥穗8克桔梗10克牛蒡子10克白芷10克淡竹叶10克防风10克薄荷5克（后下）甘草5克（3剂水煎服，每日1剂早晚分服）";
        String[] strs = recipe.split("克");
        for (String str : strs) {
            System.out.println(str + "克");
        }
    }
}
