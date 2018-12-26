public class Cube
{
    public double[][] vertices;
    public int[][] edges;
    
    public Cube()
    {
        vertices = new double[8][3];
        edges = new int[12][2];
        vertices[0][0] =  1.0;
        vertices[0][1] =  1.0;
        vertices[0][2] =  1.0;
        vertices[1][0] = -1.0;
        vertices[1][1] =  1.0;
        vertices[1][2] =  1.0;
        vertices[2][0] = -1.0;
        vertices[2][1] = -1.0;
        vertices[2][2] =  1.0;
        vertices[3][0] =  1.0;
        vertices[3][1] = -1.0;
        vertices[3][2] =  1.0;
        vertices[4][0] =  1.0;
        vertices[4][1] = -1.0;
        vertices[4][2] = -1.0;
        vertices[5][0] =  1.0;
        vertices[5][1] =  1.0;
        vertices[5][2] = -1.0;
        vertices[6][0] = -1.0;
        vertices[6][1] =  1.0;
        vertices[6][2] = -1.0;
        vertices[7][0] = -1.0;
        vertices[7][1] = -1.0;
        vertices[7][2] = -1.0;
        edges[ 0][0] = 0;
        edges[ 0][1] = 1;
        edges[ 1][0] = 1;
        edges[ 1][1] = 2;
        edges[ 2][0] = 2;
        edges[ 2][1] = 3;
        edges[ 3][0] = 3;
        edges[ 3][1] = 0;
        edges[ 4][0] = 3;
        edges[ 4][1] = 4;
        edges[ 5][0] = 4;
        edges[ 5][1] = 5;
        edges[ 6][0] = 5;
        edges[ 6][1] = 0;
        edges[ 7][0] = 5;
        edges[ 7][1] = 6;
        edges[ 8][0] = 6;
        edges[ 8][1] = 1;
        edges[ 9][0] = 4;
        edges[ 9][1] = 7;
        edges[10][0] = 7;
        edges[10][1] = 2;
        edges[11][0] = 6;
        edges[11][1] = 7;
    }

    //Hier kann auch eine andere Drehachse r gewählt werden!
    public void rotateCube(double alpha) {
        double[] r = new double[3];
        double n = 1.0/Math.sqrt(3);
        r[0] = n;
        r[1] = n;
        r[2] = n;
        for (int i=0; i<8; i++) vertices[i] = Transformation.rotate(vertices[i], r, alpha);
    }
}




