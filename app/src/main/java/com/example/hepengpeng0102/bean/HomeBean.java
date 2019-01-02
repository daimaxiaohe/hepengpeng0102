package com.example.hepengpeng0102.bean;

import java.util.List;

/**
 * はすてすゃの
 * 2019-01-02.
 */
public class HomeBean {
    public String code;
    public String msg;
    public Data data;
    public static class Data{
        public Miao miaosha;
        public static class Miao{
            public List<Home> list;
            public static class Home{
                public String images;
                public String price;
                public String title;
                public String pid;
            }
        }
    }
}
