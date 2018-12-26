import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class Image2D extends BufferedImage
{
    public double[] center;
    public double resolution;
    
    public void init(){
        center = new double[2];
        center[0] = 0.0;
        center[1] = 0.0;
        resolution = 40.0;
    }
    
    public Image2D(BufferedImage image)
    {
         super(image.getColorModel(), image.copyData(null), 
               image.isAlphaPremultiplied(), null);
         init();      
    }

    public Image2D(int width, int height)
    {
        super(width, height, TYPE_INT_RGB);
         init();      
    }

    public Point transformToImageCoordinates(double[] a){
        Point p = new Point();
        p.x = (int) Math.round( (a[0] - center[0])*resolution + ((double) getWidth())/2 );
        double y = (center[1] - a[1])*resolution + ((double)getHeight())/2;
        if (y>Integer.MAX_VALUE){
            p.y = Integer.MAX_VALUE;
        } else {
            if (y<Integer.MIN_VALUE){
                p.y = Integer.MIN_VALUE;
            } else {
                p.y = (int) Math.round(y);
            }
        }
        return p;
    }
 
    public double[] projectParallel(double[] v){
        double[] a = new double[2];
        a[0] = v[0];
        a[1] = v[1];
        return a;
    }

    public double[] projectCentral(double[] v, double a, double d){
        double factor = (d-a)/(v[2]-a);
        double[] p = new double[2];
        p[0] = factor*v[0];
        p[1] = factor*v[1];
        return p;
    }
    
    public void drawCube(Cube c) {
        Graphics2D gimage = createGraphics();
        gimage.setColor(Color.white);
        gimage.fillRect(0, 0, getWidth(), getHeight());
        gimage.setColor(Color.blue);
        
        Point p, q;
        for (int i=0; i<c.edges.length; i++) {
            p = transformToImageCoordinates(projectParallel(c.vertices[c.edges[i][0]]));
            q = transformToImageCoordinates(projectParallel(c.vertices[c.edges[i][1]]));
            //p = transformToImageCoordinates(projectCentral(c.vertices[c.edges[i][0]], 10, 2));
            //q = transformToImageCoordinates(projectCentral(c.vertices[c.edges[i][1]], 10, 2));
            gimage.drawLine(p.x, p.y, q.x, q.y);
        }

        gimage.dispose();
    }
}