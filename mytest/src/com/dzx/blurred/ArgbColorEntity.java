package com.dzx.blurred;

public class ArgbColorEntity {
    public int colorA;
    public int colorR;
    public int colorG;
    public int colorB;

    public ArgbColorEntity(int colorA, int colorR, int colorG, int colorB) {
        this.colorA = colorA;
        this.colorR = colorR;
        this.colorG = colorG;
        this.colorB = colorB;
    }

    public int getColor() {
        return ColorFilterUtil.argb(colorA, colorR, colorG, colorB);
    }
}
