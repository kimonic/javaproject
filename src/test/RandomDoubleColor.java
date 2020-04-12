package test;

public class RandomDoubleColor {

    public static void main(String[] args){
        StringBuilder builder=new StringBuilder();

        for (int i = 0; i < 6; i++) {
            builder.append((int)(Math.random()*33+1)).append(",");
        }
        String temp=builder.toString();
        System.out.println(temp.substring(0, temp.length()-1 ));
    }



}
