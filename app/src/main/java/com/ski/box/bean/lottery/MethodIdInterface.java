package com.ski.box.bean.lottery;

import java.util.HashMap;

/**
 * @author doms
 * @desc 双面盘, 玩法ID
 * @date on 2019/9/13 10:01
 */
public interface MethodIdInterface {
    HashMap<String, Integer> LHC_IMMUTE_SET = new HashMap<String, Integer>() {
        {
            //二连肖
            put("zhengtemaerlianxiao", 2);
            put("zhengtemaerlianxiaobhb", 2);
            put("zhengtemaerlianxiaohb", 2);
            put("zhengtemaerlianwei", 2);
            put("zhengtemaerlianweibhl", 2);
            put("zhengtemaerlianweihl", 2);

            //三连肖
            put("zhengtemasanlianxiao", 3);
            put("zhengtemasanlianxiaobhb", 3);
            put("zhengtemasanlianxiaohb", 3);
            put("zhengtemasanlianwei", 3);
            put("zhengtemasanlianweibhl", 3);
            put("zhengtemasanlianweihl", 3);

            //四连肖
            put("zhengtemasilianxiao", 4);
            put("zhengtemasilianxiaobhb", 4);
            put("zhengtemasilianxiaohb", 4);
            put("zhengtemasilianwei", 4);
            put("zhengtemasilianweibhl", 4);
            put("zhengtemasilianweihl", 4);

            //五连肖
            put("zhengtemawulianxiao", 5);
            put("zhengtemawulianxiaobhb", 5);
            put("zhengtemawulianxiaohb", 5);
            put("zhengtemawulianwei", 5);
            put("zhengtemawulianweibhl", 5);
            put("zhengtemawulianweihl", 5);
            //合肖
            put("hexiao", 2);
        }
    };
    /************************************** 六合彩 start *******************************************/
    String LE特码 = "tema";
    String PL特码 = "tema";
    String PL特码头尾数 = "touweishu";
    String LE特肖 = "texiao";
    String PL合肖 = "hexiao";
    String PL特肖 = "texiao";
    String PL特肖五行 = "texiaowuxing";
    String PL特码形态 = "temaxingtai";
    String PL特和形态 = "tehexingtai";
    String PL特尾形态 = "teweixingtai";
    String PL特肖形态 = "texiaoxingtai";
    String LE色波 = "sebo";
    String Pl全波 = "quanbo";
    String LE正码 = "ZHENGMA";
    String PL正码1 = "zhengmayi";
    String PL正码2 = "zhengmaer";
    String PL正码3 = "zhengmasan";
    String PL正码4 = "zhengmasi";
    String PL正码5 = "zhengmawu";
    String PL正码6 = "zhengmaliu";
    String LE正肖 = "zhengxiao";
    String LE正波 = "zhengbo";
    String LE正特码 = "zhengtema";
    String GR正特码 = "zhengtema";
    String Pl总分 = "zongfen";
    String Pl平特尾数 = "pingteweishu";
    String Pl自选不中 = "zixuanbuzhong";
    String Pl二连尾 = "erlianwei";
    String Pl三连尾 = "sanlianwei";
    String Pl四连尾 = "silianwei";
    String Pl五连尾 = "wulianwei";
    String Pl二中特 = "erzhongte";
    String Pl特串 = "techuang";
    String LE正特肖 = "zhengtexiao";
    String GR正特肖 = "zhengtexiao";
    String Pl正特一肖 = "zhengteyixiao";
    String Pl二连肖 = "erlianxiao";
    String Pl三连肖 = "sanlianxiao";
    String Pl四连肖 = "silianxiao";
    String Pl五连肖 = "wulianxiao";
    String Pl正特肖数 = "zhnegtexiaoshu";
    String Pl总肖单双 = "zongxiaodanshuang";
    String LE正特波 = "zhengtebo";
    String GR七色波 = "qisebo";
    String 二连尾 = "erlianwei";
    String 三连尾 = "sanlianwei";
    String 四连尾 = "silianwei";
    String 五连尾 = "wulianwei";
    String 七色波 = "qisebo";
    String D自选不中 = "zixuanbuzhong";
    String double_zhengtemazixuanbuzhongww = "zhengtemazixuanbuzhongww";//正特码自选不中5位
    String double_zhengtemazixuanbuzhonglw = "zhengtemazixuanbuzhonglw";//正特码自选不中6位
    String double_zhengtemazixuanbuzhongqw = "zhengtemazixuanbuzhongqw";//正特码自选不中7位
    String double_zhengtemazixuanbuzhongbw = "zhengtemazixuanbuzhongbw";//正特码自选不中8位
    String double_zhengtemazixuanbuzhongjw = "zhengtemazixuanbuzhongjw";//正特码自选不中9位
    String double_zhengtemazixuanbuzhongsw = "zhengtemazixuanbuzhongsw";//正特码自选不中10位
    String double_zhengtemazixuanbuzhongyyw = "zhengtemazixuanbuzhongyyw";//正特码自选不中11位
    /** 六合彩-**/
    /**
     * 特码
     **/
    String S_LHC_TEMA = "tema";
    /**
     * 特码-->两面
     **/
    String S_LHC_TEMA_LIANGMIAN = "liangmian";
    /**
     * 特码大小
     **/
    String S_LHC_TEMA_LIANGMIAN_TEMADAXIAO = "temadaxiao";
    /**
     * 特码单双
     **/
    String S_LHC_TEMA_LIANGMIAN_TEMADANSHUANG = "temadanshuang";
    /**
     * 特码合大小
     **/
    String S_LHC_TEMA_LIANGMIAN_TEMAHEDAXIAO = "temahedaxiao";
    /**
     * 特码和单双
     **/
    String S_LHC_TEMA_LIANGMIAN_TEMAHEDANSHUANG = "temahedanshuang";
    /**
     * 特码天地肖
     **/
    String S_LHC_TEMA_LIANGMIAN_TEMATIANDIXIAO = "tematiandixiao";
    /**
     * 特码前后肖
     **/
    String S_LHC_TEMA_LIANGMIAN_TEMAQIANHOUXIAO = "temaqianhouxiao";
    /**
     * 特码家野肖
     **/
    String S_LHC_TEMA_LIANGMIAN_TEMAJIAYEXIAO = "temajiayexiao";
    /**
     * 特码尾大小
     **/
    String S_LHC_TEMA_LIANGMIAN_TEMAWEIDAXIAO = "temaweidaxiao";
    /**
     * 特码大小单双
     **/
    String S_LHC_TEMA_LIANGMIAN_TEMADAXIAODANSHUANG = "temadaxiaodanshuang";

    /**
     * 号码
     **/
    String S_LHC_TEMA_HAOMA = "haoma";

    /**
     * 色波半波
     **/
    String S_LHC_TEMA_SEBOBANBO = "sebobanbo";
    /**
     * 特码-->波色
     **/
    String S_LHC_TEMA_SEBOBANBO_BOSE = "bose_";
    /**
     * 特码-->半波
     **/
    String S_LHC_TEMA_SEBOBANBO_BANBO = "banbo_";
    /**
     * 特码-->半半波 这个需要放在半波前面
     **/
    String S_LHC_TEMA_SEBOBANBO_BANBANBO = "banbanbo_";

    /**
     * 特码 -->特肖头尾数
     **/
    String S_LHC_TEMA_TEXIAOTOUWEIXIAO = "texiaotouweixiao";

    /**
     * 特码 -->特肖头尾数-->特码特肖非本命年
     **/
    String S_LHC_TEMA_TEXIAOTOUWEIXIAO_TEXIAO_TEMATEXIAOFBMN = "texiao_tematexiaofbmn";
    /**
     * 特码 -->特肖头尾数-->特码特肖本命年
     **/
    String S_LHC_TEMA_TEXIAOTOUWEIXIAO_TEXIAO_TEMATEXIAOBMN = "texiao_tematexiaobmn";
    /**
     * 特码 -->特肖头尾数-->特码选0尾尾数号码
     **/
    String S_LHC_TEMA_TEXIAOTOUWEIXIAO_TOUWEISHU_TEMALINGWEI = "touweishu_weishu_temalingwei";
    /**
     * 特码 -->特肖头尾数-->特码选1-9尾尾数号码
     **/
    String S_LHC_TEMA_TEXIAOTOUWEIXIAO_TOUWEISHU_TEMAYITOJIUWEI = "touweishu_weishu_temayitojiuwei";
    /**
     * 特码 -->特肖头尾数-->特码选0头头数号码
     **/
    String S_LHC_TEMA_TEXIAOTOUWEIXIAO_TOUWEISHU_TEMALINGTOU = "touweishu_toushu_temalingtou";
    /**
     * 特码 -->特肖头尾数-->特码选1-4头头数号码
     **/
    String S_LHC_TEMA_TEXIAOTOUWEIXIAO_TOUWEISHU_TEMAYITOSITOU = "touweishu_toushu_temayitositou";

    /**
     * 特码->特码合肖
     **/
    String S_LHC_TEMA_TEMAHEXIAO = "hexiao";
    /**
     * 特码->五行
     **/
    String S_LHC_TEMA_WUXING = "wuxing";

    /**
     * 六合彩->正码
     **/
    String S_LHC_ZHENGMA = "zhengma";
    /*正码任选一*/
    String S_LHC_ZHENGMA_ZHENGMARENXUANYI = "zhengmarenxuanyi";
    /**
     * 六合彩->正码->正码1-6
     **/
    String S_LHC_ZHENGMA_ZHENGXIAOYIDAOLIU = "zhengxiaoyidaoliu";
    /**
     * 六合彩->正码->正码1-6->正码1
     **/
    String S_LHC_ZHENGMA_ZHENGXIAOYIDAOLIU_ZHENGMAYI = "zhengmayi_";
    /**
     * 六合彩->正码->正码1-6->正码2
     **/
    String S_LHC_ZHENGMA_ZHENGXIAOYIDAOLIU_ZHENGMAER = "zhengmaer_";
    /**
     * 六合彩->正码->正码1-6->正码3
     **/
    String S_LHC_ZHENGMA_ZHENGXIAOYIDAOLIU_ZHENGMASAN = "zhengmasan_";
    /**
     * 六合彩->正码->正码1-6->正码4
     **/
    String S_LHC_ZHENGMA_ZHENGXIAOYIDAOLIU_ZHENGMASI = "zhengmasi_";
    /**
     * 六合彩->正码->正码1-6->正码5
     **/
    String S_LHC_ZHENGMA_ZHENGXIAOYIDAOLIU_ZHENGMAWU = "zhengmawu_";
    /**
     * 六合彩->正码->正码1-6->正码6
     **/
    String S_LHC_ZHENGMA_ZHENGXIAOYIDAOLIU_ZHENGMALIU = "zhengmaliu_";

    /**
     * 六合彩->正码特
     **/
    String S_LHC_ZHENGMATE = "zhengmate";
    /**
     * 六合彩->正码特->正一特
     **/
    String S_LHC_ZHENGMATE_ZHENGYITE = "zhengyite";
    /**
     * 六合彩->正码特->正二特
     **/
    String S_LHC_ZHENGMATE_ZHENGERTE = "zhengerte";
    /**
     * 六合彩->正码特->正三特
     **/
    String S_LHC_ZHENGMATE_ZHENGSANTE = "zhengsante";
    /**
     * 六合彩->正码特->正四特
     **/
    String S_LHC_ZHENGMATE_ZHENGSITE = "zhengsite";
    /**
     * 六合彩->正码特->正五特
     **/
    String S_LHC_ZHENGMATE_ZHENGWUTE = "zhengwute";
    /**
     * 六合彩->正码特->正六特
     **/
    String S_LHC_ZHENGMATE_ZHENGLIUTE = "zhengliute";

    /**
     * 六合彩->正码-》正肖七色波
     **/
    String S_LHC_ZHENGMA_ZHENGXIAOQISEBO = "zhengxiaoqisebo";
    /**
     * 六合彩->正码-》正肖七色波->正码选生肖非本命年
     **/
    String S_LHC_ZHENGMA_ZHENGXIAOQISEBO_ZHENGXIAO_ZHENGXIAOFBM = "zhengxiao_zhengxiaofbm";
    /**
     * 六合彩->正码-》正肖七色波->正码选生肖本命年
     **/
    String S_LHC_ZHENGMA_ZHENGXIAOQISEBO_ZHENGXIAO_ZHENGXIAOBM = "zhengxiao_zhengxiaobm";
    /**
     * 六合彩->正码-》正肖七色波->七色波
     **/
    String S_LHC_ZHENGMA_ZHENGXIAOQISEBO_QISEBO = "qisebo";

    /**
     * 六合彩->连肖连尾
     **/
    String S_LHC_LIANXIAOLIANWEI = "lianxiaolianwei";
    /**
     * 六合彩->连肖连尾->二连肖
     **/
    String S_LHC_LIANXIAOLIANWEI_ERLIANXIAO = "erlianxiao";
    /**
     * 六合彩->连肖连尾->三连肖
     **/
    String S_LHC_LIANXIAOLIANWEI_SANLIANXIAO = "sanlianxiao";
    /**
     * 六合彩->连肖连尾->四连肖
     **/
    String S_LHC_LIANXIAOLIANWEI_SILIANXIAO = "silianxiao";
    /**
     * 六合彩->连肖连尾->五连肖
     **/
    String S_LHC_LIANXIAOLIANWEI_WULIANXIAO = "wulianxiao";

    /**
     * 六合彩->连肖连尾->二连尾
     **/
    String S_LHC_LIANXIAOLIANWEI_ERLIANWEI = "erlianwei";
    /**
     * 六合彩->连肖连尾->三连尾
     **/
    String S_LHC_LIANXIAOLIANWEI_SANLIANWEI = "sanlianwei";
    /**
     * 六合彩->连肖连尾->四连尾
     **/
    String S_LHC_LIANXIAOLIANWEI_SILIANWEI = "silianwei";
    /**
     * 六合彩->连肖连尾->五连尾
     **/
    String S_LHC_LIANXIAOLIANWEI_WULIANWEI = "wulianwei";

    /**
     * 六合彩->连码
     **/
    String S_LHC_LIANMA = "lianma";
    /**
     * 六合彩->连码->三中二
     **/
    String S_LHC_LIANMA_SANZHONGER = "sanzhonger";
    /**
     * 六合彩->连码->三中二->正码三中二
     **/
    String S_LHC_LIANMA_SANZHONGER_ZHENGMASANZHONGER = "zhengmasanzhonger";
    /**
     * 六合彩->连码->三全中
     **/
    String S_LHC_LIANMA_SANQUANZHONG = "sanquanzhong";
    /**
     * 六合彩->连码->正码三全中
     **/
    String S_LHC_LIANMA_ZHENGMA_SANQUANZHONG = "zhengmasanquanzhong";

    /**
     * 六合彩->连码->二全中
     **/
    String S_LHC_LIANMA_ERQUANZHONG = "erquanzhong";
    /**
     * 六合彩->连码->正码二全中
     **/
    String S_LHC_LIANMA_ZHENGMA_ERQUANZHONG = "zhengmaerquanzhong";
    /**
     * 六合彩->连码->二中特
     **/
    String S_LHC_LIANMA_ERZHONGTE = "erzhongte";
    /**
     * 六合彩->连码->正码二中特
     **/
    String S_LHC_LIANMA_ZHENGMA_ERZHONGTE = "zhengtemaerzhongte";
    /**
     * 六合彩->连码->特串
     **/
    String S_LHC_LIANMA_TECHUANG = "techuang";
    /**
     * 六合彩->连码->正码特串
     **/
    String S_LHC_LIANMA_ZHENGMA_TECHUANG = "zhengtematechuang";
    /**
     * 六合彩->连码->四全中
     **/
    String S_LHC_LIANMA_SIQUANZHONG = "siquanzhong";
    /**
     * 六合彩->连码->正码四全中
     **/
    String S_LHC_LIANMA_ZHENGMA_SIQUANZHONG = "zhengmasiquanzhong";

    /**
     * 六合彩->一肖总肖平特尾数
     **/
    String S_LHC_YIXIAOZONGXIAOPINGTEWEISHU = "yixiaozongxiaopingteweishu";
    /**
     * 六合彩->一肖总肖平特尾数->一肖
     **/
    String S_LHC_YIXIAOZONGXIAOPINGTEWEISHU_YIXIAO = "yixiao";
    /**
     * 六合彩->一肖总肖平特尾数->尾数
     **/
    String S_LHC_YIXIAOZONGXIAOPINGTEWEISHU_WEISHU = "weishu";
    /**
     * 六合彩->一肖总肖平特尾数->总肖
     **/
    String S_LHC_YIXIAOZONGXIAOPINGTEWEISHU_ZONGXIAO = "zongxiao";
    /**
     * 六合彩->总和
     **/
    String S_LHC_ZONGHE = "zonghe";
    /**
     * 六合彩->总和->大小
     **/
    String S_LHC_ZONGHE_DAXIAO = "daxiao";
    /**
     * 六合彩->总和->和值单双
     **/
    String S_LHC_ZONGHE_DANSHUANG = "danshuang";
    /**
     * 六合彩->自选不中
     **/
    String S_LHC_ZIXUANBUZHONG = "zixuanbuzhong";
    /**
     * 六合彩->自选不中->正特码自选不中5位
     **/
    String S_LHC_ZIXUANBUZHONG_ZHENGTEMAZIXUANBUZHONGWW = "zhengtemazixuanbuzhongww";

    /**
     * 双面盘冷热遗漏赔率布局
     **/
    /*只有冷热*/
    String S_ColdHot = "S_ColdHot";
    /*只有遗漏*/
    String S_Omit = "S_Omit";
    /*遗漏和赔率*/
    String S_Omit_Odds_One = "S_Omit_Odds_One";
    /*遗漏和两个赔率*/
    String S_Omit_Odds_Two = "S_Omit_Odds_Two";
    /*只有赔率*/
    String S_Odds = "S_Odds_One";
    /*冷热遗漏*/
    String S_ColdHot_Omit = "S_ColdHot_Omit";
    /*冷热遗漏和赔率*/
    String S_ColdHot_Omit_Odds_One = "S_ColdHot_Omit_Odds_One";
    /*冷热遗漏和两个赔率*/
    String S_ColdHot_Omit_Odds_Two = "S_ColdHot_Omit_Odds_Two";
    // 冷热遗漏辅助
    String S_ColdHot_Omit_Assist = "S_ColdHot_Omit_Assist";
    /*不显示*/
    String S_Nothing = "S_Nothing";
    /************************************** 六合彩 end *******************************************/


    /************************************** 快三 start *******************************************/
    String 大小单双 = "daxiaodanshaung";
    String 和值 = "hezhi";
    String 三同号 = "santonghao";
    String 三不同号 = "sanbutonghao";
    String 二同号 = "ertonghao";
    String 二不同号 = "erbutonghao";
    String 猜一个号 = "caiyigeshu";
    String S_K3_ZHENGHE_SANJUNDAXIAO = "sanjundaxiao";
    String S_K3_ZHENGHE_WEITOUQUANTOU = "weitouquantou";
    String S_K3_ZHENGHE_DIANSHU = "dianshu";
    String S_K3_ZHENGHE_CHANGPAI = "changpai";

    /************************************** 快三 end *******************************************/


    /************************************** 快乐彩 start *******************************************/
    String 和值大小单双 = "hezhidaxiaodanshuang";
    String 任选 = "renxuan";
    String 上下盘 = "shangxiapan";
    String 奇偶盘 = "jioupan";
    String 总和 = "zonghe";
    String S_KLC_SHUANGMIAN = "shuangmian";
    String S_KLC_ZHENGMA = "zhengma";
    /************************************** 快乐彩 end *******************************************/


    /************************************** 时时彩 start *******************************************/
    String 定位胆_le = "dingweidan";
    String 趣味_le = "quwei";
    String 任选二_le = "renxuaner";
    String 任选三_le = "renxuansan";
    String 任选四_le = "renxuansi";
    String 前三_le = "qiansan";
    String 中三_le = "zhongsan";
    String 后三_le = "housan";
    String 前二_le = "qianer";
    String 后二_le = "houer";
    String 五星_le = "wuxing";
    String 四星_le = "sixing";
    String 前四直选__gro = "qiansizhixuan";
    String 前四组选__gro = "qiansizuxuan";
    String 后四直选_gro = "housizhixuan";
    String 后四组选_gro = "housizuxuan";
    String 不定胆_le = "budingdan";
    String 三星不定胆_gro = "sanxingbudingdan";
    String 前三一码不定胆_pla = "qiansanyima";
    String 前三二码不定胆_pla = "qiansanliangma";
    String 中三一码不定胆_pla = "zhongsanyima";
    String 中三二码不定胆_pla = "zhongsanliangma";
    String 后三一码不定胆_pla = "housanyima";
    String 后三二码不定胆_pla = "housanliangma";
    String 四星不定胆_gro = "sixingbudingdan";
    String 前四一码不定胆_pla = "qiansiyima";
    String 前四二码不定胆_pla = "qiansiliangma";
    String 前四三码不定胆_pla = "qiansisanma";
    String 后四一码不定胆_pla = "housiyima";
    String 后四二码不定胆_pla = "housiliangma";
    String 后四三码不定胆_pla = "housisanma";
    String 五星不定胆_gro = "wuxingbudingdan";
    String 大小单双_LE = "daxiaodanshuang";
    String 前二大小单双组合_pla = "qianerzuhe";
    String 前三大小单双组合_pla = "qiansanzuhe";
    String 后二大小单双组合_pla = "houerzuhe";
    String 后三大小单双组合_pla = "housanzuhe";
    String 五星和值大小单双_pla = "wuxinghezhi";
    String 三星和值大小单双_pla = "sanxinghezhi";
    String 龙虎_le = "longhu";
    String 龙虎和_pla = "longhuhe";
    String 炸金花_le = "zhajinhua";
    String 梭哈_le = "suoha";
    String 百家乐_le = "baijiale";
    String 牛牛_le = "niuniu";
    String 总和龙虎和 = "zonghelonghuhe";
    /**
     * 双面
     **/
    String S_SSC_SHUANGMIAN = "shuangmian";
    /**
     * 选号
     **/
    String S_SSC_XUANHAO = "xuanhao";
    /**
     * 前中后三
     **/
    String S_SSC_QIANZHONGHOUSAN = "qianzhonghousan";
    /************************************** 时时彩 end *******************************************/


    /************************************** PK10 start *******************************************/
    String 猜名次 = "caimingci";
    String 定位胆 = "dingweidan";
    String 龙虎 = "longhu";
    String PK10_大小单双 = "daxiaodanshuang";
    String 猜冠军 = "caiguanjun";
    String 猜前二 = "caiqianer";
    String 猜前三 = "caiqiansan";
    String 猜前四 = "caiqiansi";
    String 猜前五 = "caiqianwu";
    String 前五定位胆 = "qianwudingweidan";
    String 后五定位胆 = "houwudingweidan";
    String 前五大小单双 = "qianwudaxiaodanshuang";
    String 冠亚和值大小单双 = "guanyahezhi";
    String 冠亚和值 = "guanyahezhi";
    String 前三和值 = "qiansanhezhi";
    String D两面 = "liangmian";
    String D单号 = "danhao";
    String D冠亚和值 = "guanyahezhi";
    /**
     * 双面
     **/
    String S_PK10_LIANGMIAN = "liangmian";
    /**
     * 选号
     **/
    String S_PK10_XUANHAO = "danhao";
    /**
     * 冠亚和值
     **/
    String S_PK10_GUANYAHEZHI = "guanyahezhi";
    /************************************** PK10 end *******************************************/


    /************************************** 3D start *******************************************/
    String 三星 = "sanxing";
    String 二星 = "erxing";
    String 不定位 = "budingwei";
    String 趣味 = "quwei";
    String 前二直选 = "qianerzhixuan";
    String 前二组选 = "qianerzuxuan";
    String 后二直选 = "houerzhixuan";
    String 后二组选 = "houerzuxuan";
    String 龙虎和 = "longhuhe";
    String 双面 = "liangmian";
    String 二字定位 = "liangzidingwei";
    String 三字定位 = "sanzidingwei";
    String 组三 = "zusan";
    String 组六 = "zuliu";
    String 跨度 = "kuadu";
    /**
     * 双面
     **/
    String S_SD_LIANGMIAN = "liangmian";
    /**
     * 一字组合
     **/
    String S_SD_YIZIZUHE = "yizizuhe";
    /**
     * 二字组合
     **/
    String S_SD_LIANGZIZUHE = "liangzizuhe";
    /**
     * 三字组合
     **/
    String S_SD_SANZIZUHE = "sanzizuhe";
    /**
     * 一字定位
     **/
    String S_SD_YIZIDINGWEI = "yizidingwei";
    /**
     * 二字定位
     **/
    String S_SD_LIANGZIDINGWEI = "liangzidingwei";
    /**
     * 二字定位 百十定位
     **/
    String S_SD_LIANGZIDINGWEI_BAISHIDINGWEI = "baishidingwei";
    /**
     * 二字定位 百个定位
     **/
    String S_SD_LIANGZIDINGWEI_BAIGEDINGWEI = "baigedingwei";
    /**
     * 二字定位 十个定位
     **/
    String S_SD_LIANGZIDINGWEI_SHIGEDINGWEI = "shigedingwei";
    /**
     * 三字定位
     **/
    String S_SD_SANZIDINGWEI = "sanzidingwei";
    /**
     * 两字和数
     **/
    String S_SD_LIANGZIHESHU = "liangziheshu";
    /**
     * 两字和数 百十和数
     **/
    String S_SD_LIANGZIHESHU_BAISHIHESHU = "baishi";
    /**
     * 两字和数 百个和数
     **/
    String S_SD_LIANGZIHESHU_BAIGEHESHU = "baige";
    /**
     * 两字和数 十个和数
     **/
    String S_SD_LIANGZIHESHU_SHIGEHESHU = "shige";
    /***三字和数*/
    String S_SD_SANZIHESHU = "sanziheshu";
    /**
     * 组三
     **/
    String S_SD_ZUSAN = "zusan";
    /**
     * 组六
     **/
    String S_SD_ZULIU = "zuliu";
    /**
     * 跨度
     **/
    String S_SD_KUADU = "kuadu";
    /************************************** 3D end *******************************************/


    /************************************** 十一选五 start **************************************/
    String 不定胆 = "budingdan";
    String 前三不定胆 = "qiansanbudingdan";
    String 前三直选 = "qiansanzhixuan";
    String double_renxuanyi = "renxuanyi";   //11x5任选一
    String double_renxuaner = "renxuaner";  //任选二
    String double_renxuansan = "renxuansan";//任选三
    String double_renxuansi = "renxuansi"; //任选四
    String double_renxuanwu = "renxuanwu";//任选五
    String double_renxuanliuzhongwu = "renxuanliuzhongwu";//任选六中五
    String double_renxuanqizhongwu = "renxuanqizhongwu";//任选七中五
    String double_renxuanbazhongwu = "renxuanbazhongwu";//任选八中五
    String double_qianerzuxuan = "qianerzuxuan";//前二组选
    String double_qiansanzuxuan = "qiansanzuxuan";//前三组选

    String double_qianerzhixuan = "qianerzhixuan";  //前二直选
    String double_qiansanzhixuan = "qiansanzhixuan";//前三直选
    String S_SYXW_SHUANGMIAN = "shuangmian";
    /**
     * 总和
     **/
    String S_SYXW_ZONGHE = "zonghe";
    /**
     * 选号
     **/
    String S_SYXW_XUANHAO = "xuanhao";
    /**
     * 任选
     **/
    String S_SYXW_RENXUAN = "renxuan";
    /**
     * 任选 任选一
     **/
    String S_SYXW_RENXUAN_YI = "renxuanyi";
    /**
     * 任选 任选二
     **/
    String S_SYXW_RENXUAN_ER = "renxuaner";
    /**
     * 任选 任选三
     **/
    String S_SYXW_RENXUAN_SAN = "renxuansan";
    /**
     * 任选 任选四
     **/
    String S_SYXW_RENXUAN_SI = "renxuansi";
    /**
     * 任选 任选五
     **/
    String S_SYXW_RENXUAN_WU = "renxuanwu";
    /**
     * 任选 任选六中五
     **/
    String S_SYXW_RENXUAN_LIUZHONGWU = "renxuanliuzhongwu";
    /**
     * 任选 任选七中五
     **/
    String S_SYXW_RENXUAN_QIZHOGNWU = "renxuanqizhongwu";
    /**
     * 任选 任选八中五
     **/
    String S_SYXW_RENXUAN_BAZHOGNWU = "renxuanbazhongwu";
    /**
     * 组选
     **/
    String S_SYXW_ZUXUAN = "zuxuan";

    String S_SYXW_QIANER_ZUXUAN = "qianerzuxuan";
    String S_SYXW_QIANSAN_ZUXUAN = "qiansanzuxuan";

    String S_SYXW_QIANER_ZHIXUAN = "qianerzhixuan";
    String S_SYXW_QIANSAN_ZHIXUAN = "qiansanzhixuan";
    /**
     * 直选
     **/
    String S_SYXW_ZHIXUAN = "zhixuan";
    /************************************** 十一选五 end *******************************************/
}
