package com.example.camerademo3;

public class Variable {

        private String desc;
        private int value;

        Variable (){}

        Variable (String desc, int value){
            this.desc = desc;
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

}
