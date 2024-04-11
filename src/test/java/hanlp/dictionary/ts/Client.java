package hanlp.dictionary.ts;

import com.hankcs.hanlp.HanLP;

/**
 * @author luozhenhong
 * @version 1.0
 * 2024/4/10 9:59
 */
public class Client {

    public static void main(String[] args) {
        System.out.println(HanLP.convertToTraditionalChinese("用笔记本电脑写程序"));
        System.out.println(HanLP.convertToTraditionalChinese("以后等你当上皇后，就能买草莓庆祝了"));
        System.out.println(HanLP.convertToSimplifiedChinese("「以後等妳當上皇后，就能買士多啤梨慶祝了」"));
    }
}
