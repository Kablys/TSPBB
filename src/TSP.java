import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class TSP {
    public static Matrix cm;
    public static int size;
    public static PriorityQueue<Node> queue = new PriorityQueue<Node>();
    public static int best = Integer.MAX_VALUE;
    public TSP(Integer[][] costMatrix, int numberThreads) {
        cm = new Matrix(costMatrix);
        size = costMatrix.length;
        for (int i = 0; i < size; i++){
            cm.set(i,i,null);
        }
    }

    public void solve() {
        //Node n = new Node(null, null, cm);
        System.out.println("Start");

        System.out.println(cm);
        int lBound = cm.reduce();

        Node root = new Node(new ArrayList<Element>(), new ArrayList<Element>(), new Matrix(cm), lBound);
        queue.add(root);
        // TODO Thread pull
        ProcessNode pn = new ProcessNode();// threads
        pn.run();
//        System.out.println(cm);
//        System.out.println("Bound " + bound);
//        System.out.println(max.getVal() + bound);
//        System.out.println(cm1);
    }

}
