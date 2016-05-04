import java.util.ArrayList;
import java.util.Arrays;

public class Element {
    int x, y, val;

    public Element(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val =  val;
    }

    public int getVal() {
        return val;
    }
    public Integer[] getXY(){
        return new Integer[]{new Integer(x),new Integer(y)};
    }

    public String toString() {
        return String.format("(%d, %d) = %d", x+1, y+1, val);
    }

    public static ArrayList<ArrayList<Integer>> arrToXY(Element[] elements){
        ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
        for (Element n : elements){
            new ArrayList<Integer>(Arrays.asList(n.getXY()));
            arr.add(new ArrayList<Integer>(Arrays.asList(n.getXY())));
        }
        return arr;
    }
}
