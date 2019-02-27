package com.caiw.wyy;

import org.apache.poi.hssf.util.HSSFColor;


import com.xuxueli.poi.excel.annotation.ExcelField;
import com.xuxueli.poi.excel.annotation.ExcelSheet;
/**
 * @ClassName WangYiYun
 * @Author caiwe
 * @CreateTime 2018/9/5  17:26
 **/
@ExcelSheet(name = "网易云音乐民谣分析", headColor = HSSFColor.HSSFColorPredefined.LIGHT_GREEN)
public class WangYiYun {




        @ExcelField(name = "歌曲介绍")
        private String description;

        @ExcelField(name = "歌曲链接地址")
        private String href;

        @ExcelField(name = "歌曲播放次数")
        private int playNums;

        public WangYiYun() {
        }

        public WangYiYun(String description, String href, int playNums) {
            this.description = description;
            this.href = href;
            this.playNums = playNums;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public int getPlayNums() {
            return playNums;
        }

        public void setPlayNums(int playNums) {
            this.playNums = playNums;
        }

        @Override
        public String toString() {
            return "WangYiYun [歌曲介绍=" + description + ", 歌曲链接地址=" + href + ", 歌曲播放次数=" + playNums + "]";
        }

}
