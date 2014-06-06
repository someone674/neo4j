package com.artlongs.common;

public class Enums {

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

    public enum BooldType{
        A,B,O,Hu,Hd
    }

    public enum BioTitle{

        SOFT(1, "温柔"),
        HANS(2,"士"),
        STRONG(3,"强壮");

        private int key;
        private String name;
        BioTitle(int key,String name){
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

    public enum Finding {
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
