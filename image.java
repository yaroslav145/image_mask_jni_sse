import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;



class image
{
    static
    {
        System.loadLibrary("maskimg");
    }   

    public static native void maskImg(int data[], int count);

    public static void main( String[] args )
    {
        int i, j, w, h;

        Scanner in = new Scanner(System.in);

        System.out.println("Enter path");

        String input = in.nextLine();

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(input));
        } catch (IOException e) {
        }

        w = img.getWidth();
        h = img.getHeight();
       
        int[] pixels = new int[h*w];



        for(i=0;i<h; i++)
            for(j=0; j<w; j++)
                pixels[i*w + j] = img.getRGB(j, i) & 0xFFFFFF;    


        image imgSse = new image();

        imgSse.maskImg(pixels, h*w);


        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (i = 0; i < h; i++)
                for (j = 0; j < w; j++)
                    bi.setRGB(j, i, pixels[i*w +j]);

        try {
         ImageIO.write(bi, "png", new File("pic2.png"));
        } catch (IOException e) {
        }


        //System.out.println("w: " + w + "\nh: " + h);
    }
}