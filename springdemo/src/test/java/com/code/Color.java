package com.code;

public enum Color {
    RED("红色", 1){
        private int r = 255;
        private int g = 0;
        private int b = 0;
        public void getRGB(){
            System.out.println(String.format("r=%s;g=%s;b=%s",r,g,b));
        }
    }, GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    Color(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // get set 方法
    public String getName() {
        return name;
    }
}
