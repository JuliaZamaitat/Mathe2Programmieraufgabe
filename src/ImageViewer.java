import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class ImageViewer
{
    private Cube c;

    private JFrame frame;
    private ImagePanel imagePanel;
    private Image2D currentImage;
    private Thread animator;
    private boolean animationStop;
    
    public ImageViewer()
    {
        c = new Cube();
        makeFrame();
        showNew();
        animationStop = true;
    }

    public void showNew()
    {
        Image2D newImage = new Image2D(imagePanel.getWidth(), imagePanel.getHeight());
        currentImage = newImage;
        c=new Cube();
        currentImage.drawCube(c);
        imagePanel.setImage(currentImage);
        imagePanel.requestFocus();
    }

    private void makeFrame()
    {
        frame = new JFrame("ImageViewer");
        JPanel contentPane = (JPanel)frame.getContentPane();
        imagePanel = new ImagePanel();
        contentPane.add(imagePanel, BorderLayout.CENTER);
        imagePanel.requestFocus();

        imagePanel.addKeyListener(new KeyListener() {
            public void keyReleased(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {
                if  (e.getKeyCode()==32) {
                    animationStop = !animationStop;
                    if (animator==null) {
                        animator = new Thread(new Runnable() {
                            public void run() {
                                int i = 0;
                                double alpha = 2.0*Math.PI/360.0;
                                while (!animationStop) {
                                    c.rotateCube(alpha);
                                    currentImage.drawCube(c);
                                    imagePanel.setImage(currentImage);
                                    try {
                                        Thread.sleep(20);
                                    }
                                    catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                animator = null;
                            }
                        });
                        animator.start();
                    }
                }
            }
            public void keyTyped(KeyEvent e) {}
        });
        
        imagePanel.addMouseListener(
            new MouseListener() {
                public void mouseClicked (MouseEvent e) {imagePanel.requestFocus();}
                public void mouseEntered (MouseEvent e) {}
                public void mouseExited (MouseEvent e) {}
                public void mousePressed (MouseEvent e) {}
                public void mouseReleased (MouseEvent e) {showNew();}
            }
        );
        
        frame.pack();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
        frame.setVisible(true);
    }
}//class
