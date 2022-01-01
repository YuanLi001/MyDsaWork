package com.example.mydsawork;


import java.math.BigDecimal;

public class Calculator {
    static String[] symNum={"+","-","*","/"};
    static int[][] priority={{1,1,0,0,1},{1,1,0,0,1},{1,1,1,1,1},{1,1,1,1,1},{0,0,0,0,1}};


    public static String calculate(String[] strs){
        //首先，判断一下输入的字符串是否合法
        if(!legitimate(strs)){
            return "输入的表达式不合法";
        }
        String[] newStrs = endAdd(strs,"#");
        OpStack opNumStack = new OpStack();
        OpStack opSymStack = new OpStack();
        stackPush(opSymStack,"#");
        int i=0;
        String s=newStrs[0];
        while ( !s.equals("#") || !getTop(opSymStack).equals("#")){
            if( !isSym(s)){
                stackPush(opNumStack,s);
                i++;
                s=newStrs[i];
            }else{
                int p=precede(getTop(opSymStack),s);
                if(p==0){
                    stackPush(opSymStack,s);
                    i++;
                    s=newStrs[i];
                }else{
                    String sym = pop(opSymStack);
                    String num1 = pop(opNumStack);
                    String num2 = pop(opNumStack);
                    double result = operate(num2, num1, sym);
                    stackPush(opNumStack,String.valueOf(result));
                }
            }
        }

        return getTop(opNumStack);
    }

    public static void stackPush(OpStack sk, String s) {
        //如果栈满，则增加空间
        if( (sk.top+1)==sk.size ){
            stcakIncrease(sk);
        }
        sk.stack[sk.top]=s;
        sk.top++;
    }

    public static String pop(OpStack sk){
        String str=sk.stack[sk.top-1];
        sk.top--;
        return str;
    }

    public static String getTop(OpStack sk){
        return sk.stack[ sk.top-1 ];
    }

    public static boolean isSym(String s){
        if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("#")){
            return true;
        }
        return false;
    }

    public static boolean legitimate(String[] strs){
        int len=strs.length;
        //合法表达式长度必为奇数
        if(len%2==0){
            return false;
        }
        //偶数下标必为数字;奇数下标必为运算符,且除号后不能为0，否则不合法
        for (int i = 0; i < len; i++) {
            if(i%2==0){
                String s = strs[i];
                if( isSym(s) ){
                    return false;
                }
            }else{
                String s = strs[i];
                if( !isSym(s)){
                    return false;
                }else{
                    if( s.equals("/")){
                        if( strs[i+1].equals("0")){
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public static String[] endAdd(String[] strs,String s){
        String[] newStrs=new String[strs.length+1];
        for (int i = 0; i < strs.length; i++) {
            newStrs[i]=strs[i];
        }
        newStrs[strs.length]=s;
        return newStrs;
    }
    //获得优先级，获得top对s的优先级
    public static int precede(String top,String s){
        int i=getSymIndex(top);
        int j=getSymIndex(s);
        return priority[i][j];
    }

    public static int getSymIndex(String s){
        int i=0;
        for (; i < symNum.length; i++) {
            if(symNum[i].equals(s)){
                break;
            }
        }
        return i;
    }

    public static double operate(String num1,String num2,String sym){
        BigDecimal n1=new BigDecimal(num1);
        BigDecimal n2=new BigDecimal(num2);
        BigDecimal n3;
        if(sym.equals("+")){
            n3=n1.add(n2);
        }else if(sym.equals("-")){
            n3=n1.subtract(n2);
        }else if(sym.equals("*")){
            n3=n1.multiply(n2);
        }else {
            n3=n1.divide(n2,10,BigDecimal.ROUND_HALF_UP);
        }
        return n3.doubleValue();
    }

    public static void stcakIncrease(OpStack sk){
        String[] oldStack=sk.stack;
        sk.stack=new String[sk.size+10];
        sk.size= sk.size+10;
        for(int i=0;i<oldStack.length;i++){
            sk.stack[i]=oldStack[i];
        }
    }

}
