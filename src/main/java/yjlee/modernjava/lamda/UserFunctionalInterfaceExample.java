package yjlee.modernjava.lamda;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UserFunctionalInterfaceExample {

    public static void main(String[] args){
        createImage(150,100,(x,y) -> x<50 ? Color.BLUE : x <100 ? Color.WHITE : Color.RED);
    }

    static BufferedImage createImage(int width,int height,PixelFuction f){
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        for(int x=0;x<width;x++)
            for(int y=0;y<height;y++){
                Color color = f.apply(x,y);
                image.setRGB(x,y,color.getRGB());
            }
        return image;
    }

}

@FunctionalInterface
interface PixelFuction{
    Color apply(int x , int y);
}
