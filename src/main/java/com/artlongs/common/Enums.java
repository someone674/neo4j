package com.artlongs.common;

public class Enums {

    /**
     * 婚姻状态
     */
    public enum Marryage {
        SECRECY(0,"保密"),
        SINGLE(1,"未婚"),
        MARRY(2,"已婚"),
        DIVORCE(3,"离婚"),
        WIDOWED(4,"丧偶");

        private int key;
        private String name;
        Marryage(int key,String name){
            this.name = name;
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * 血型
     */
    public enum BooldType{
        A,B,O,Hu,Hd
    }

    /**
     * 男性标签
     */
    public enum ManLable {

        SOFT(11, "温和"),
        GENTLEMAN(12,"绅士"),
        STRONG(13,"强壮");

        private int key;
        private String value;
        ManLable(int key, String value){
            this.value = value;
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
    public enum WomenLable {

        SOFT(21, "温柔"),
        GENTLEMAN(22,"娇小"),
        STRONG(23,"丰满");

        private int key;
        private String value;
        WomenLable(int key, String value){
            this.value = value;
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    /**
     * 交往趋向
     */
    public static enum Finding {
        FRENDS(1, "朋友"),
        PRANER(2,"伴侣"),
        ONENIGHT(3,"一夜情");

        private int key;
        private String name;
        Finding(int key,String name){
            this.name = name;
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
