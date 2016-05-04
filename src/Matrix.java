import java.util.ArrayList;
import java.util.List;

public class Matrix {
    public Integer[][] cm;
    public int size;

    public Matrix(Integer[][] cm) {
        this.cm = cm;
        size = cm.length;
    }
    public Matrix(Matrix c) {
        size = c.getSize();
        cm = new Integer[size][size];
        this.copy(c);
    }
    public int getSize() {
        return size;
    }
    public Integer get (Integer x, Integer y) {
        return cm[x][y];
    }
    public void set (Integer x, Integer y, Integer val) {
        cm[x][y] = val;
    }
    public void sub (Integer x, Integer y, Integer val) {
        cm[x][y] -= val;
    }
    public String toString(){
        String s = "";
        for(Integer[] i : cm){
            for (Integer j : i){
                if(j == null){
                    s = s.concat("x" + " ");
                } else {
                    s = s.concat(j + " ");
                }
            }
            s = s.concat("\n");
        }
        return s;
    }
    public void copy(Matrix c){
        for (int i = 0; i < size ; i++){
            for(int j = 0; j < size ; j++) {
                cm[i][j] = c.get(i,j);
            }
        }
    }

    public int removeRowCol(Element e){
        Integer[] n = e.getXY();
        //ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
        //Matrix newCm = this;
        cm[n[1]][n[0]] = null; // TODO more complex check
        Integer[][] newCm = new Integer[size-1][size-1];
        int indexI = 0;
        for (int i = 0; i < size-1 ; i++){
            if( indexI == n[0]){
                indexI++;
            }
            int indexJ = 0;
            for(int j = 0; j < size-1; j++) {
                if( indexJ == n[1]){
                    indexJ++;
                }
                Integer x = cm[indexI][indexJ];
                newCm[i][j] = x;
                indexJ++;
            }
            indexI++;
        }
        this.cm = newCm;
        this.size = newCm.length;
//        for (int i = 0; i < size ; i++){
//            cm[n[0]][i] = null;
//            cm[i][n[1]] = null;
//        }
        //FIXME don't know if this should be here
        //TODO easy optimization
        return this.reduce();
    }
    public int removeEdge(Element e){
        Integer[] n = e.getXY();
        cm[n[0]][n[1]] = null;
        //FIXME don't know if this should be here
        //TODO easy optimization
        return this.reduce();
    }

    public Integer reduce(){
//        ArrayList<ArrayList<Integer>> test = Element.arrToXY(elm);
//        ArrayList<Integer> x = test.get(0);
//        ArrayList<Integer> y = test.get(1);
        Integer bound = 0;
        for (Integer row = 0; row < size ; row++){
            int min = Integer.MAX_VALUE;
            //find min
            for (Integer col = 0; col < size ; col++) {
                if (cm[row][col] == null) {
                    continue; //TODO optimization to avoid this element in next loop
                }
                if (cm[row][col] < min) {
                    min = cm[row][col];
                }
            }
            //subtract min
            if (min == Integer.MAX_VALUE || min == 0){
                continue;
            }
            for (Integer col = 0; col < size ; col++){
                if (cm[row][col] == null){
                    continue;
                }
                cm[row][col] -= min;
            }
            bound += min;
        }
        for (Integer row = 0; row < size ; row++){
            int min = Integer.MAX_VALUE;
            for (Integer col = 0; col < size ; col++) {
                if (cm[col][row] == null) {
                    continue;
                }
                if (cm[col][row] < min) {
                    min = cm[col][row];
                }
            }
            if (min == Integer.MAX_VALUE || min == 0){
                continue;
            }
            for (Integer col = 0; col < size ; col++){
                if (cm[col][row] == null){
                    continue;
                }
                cm[col][row] -= min;
            }
            bound += min;
        }
        return bound;
    }

    public List<Element> min(){
        List<Element> vertex = new ArrayList<Element>();

        for (int row = 0; row < size ; row++){
            for (int col = 0; col < size ; col++){
                if (cm[row][col]  == null){
                    continue;
                }
                if (cm[row][col] == 0){
                    int rowMin = Integer.MAX_VALUE;
                    int colMin = Integer.MAX_VALUE;
                    for (int row2 = 0; row2 < size ; row2++) {
                        if (cm[row2][col] == null || row2 == row) {
                            continue;
                        }
                        if (cm[row2][col] < rowMin) {
                            rowMin = cm[row2][col] ;
                        }
                    }
                    for (int col2 = 0; col2 < size ; col2++) {
                        if (cm[row][col2] == null || col2 == col ) {
                            continue;
                        }
                        if (cm[row][col2] < colMin) {
                            colMin = cm[row][col2];
                        }
                    }
                    vertex.add(new Element(row, col, rowMin + colMin));
                }
            }
        }
        return vertex;
    }

}
