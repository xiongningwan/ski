package com.ski.box.bean;

import java.util.List;

/**
 * author Afton
 * date 2020/2/27
 * 双面盘玩法说明
 */
public class ShuoMingDoubleBean {

    /**
     * seriesId : 1
     * description : [{"title":"1.总和单双大小","text":"大小：根据相应单项投注的第一球特 ~ 第五球特开出的球号数字总和值大於或等於23为特码大，小於或等於22为特码小。 \n单双：根据相应单项投注的第一球特 ~ 第五球特开出的球号数字总和值是双数为总和双，数字总和值是单数为总和单"},{"title":"2.龙虎和特殊玩法： 龙 > 虎 > 和 （0为最小，9为最大","text":"龙：开出之号码第一球（万位）的中奖号码大于第五球（个位）的中奖号码，如出和局为打和。如 第一球开出4 第五球开出2；第一球开出9 第五球开出8；第一球开出5 第五球开出1...中奖为龙。\n虎：开出之号码第一球（万位）的中奖号码小于第五球（个位）的中奖号码，如出和局为打和。如 第一球开出7 第五球开出9；第一球开出3 第五球开出5；第一球开出5 第五球开出8...中奖为虎。\n和：开出之号码第一球（万位）的中奖号码等于第五球（个位）的中奖号码，例如开出结果：2***2则投注和局者视为中奖，其它视为不中奖。"}]
     */

    private int seriesId;
    private List<DescriptionBean> description;

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    public List<DescriptionBean> getDescription() {
        return description;
    }

    public void setDescription(List<DescriptionBean> description) {
        this.description = description;
    }

    public static class DescriptionBean {
        /**
         * title : 1.总和单双大小
         * text : 大小：根据相应单项投注的第一球特 ~ 第五球特开出的球号数字总和值大於或等於23为特码大，小於或等於22为特码小。
         * 单双：根据相应单项投注的第一球特 ~ 第五球特开出的球号数字总和值是双数为总和双，数字总和值是单数为总和单
         */

        private String title;
        private String text;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
