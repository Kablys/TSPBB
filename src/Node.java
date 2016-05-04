import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable{
    List<Element> belong;
    List<Element> notBelong;
    Matrix costMatrix;
    private int lBound;
    public Node(List<Element> belong, List<Element> notBelong, Matrix costMatrix, int lowerBound){

        this.costMatrix = costMatrix;
        this.lBound = lowerBound;
        this.belong = belong;
        this.notBelong = notBelong;

    }
    public void process () {
        if (lBound >= TSP.best){
            return;
        }


        //int lBound = costMatrix.reduce();
        List<Element> vertex = costMatrix.min();
        Element max = new Element(0,0,0); // edge with Maximum LB
        for(Element v : vertex){
            //System.out.println(v);
            if (v.getVal() > max.getVal()){
                max = v;
            }
        }
        System.out.println("Start Bound " + lBound);
        System.out.println(costMatrix);

        //Split into binary tree
        //OUT
        Matrix newCostMatrix = new Matrix(costMatrix);
        int newBound = lBound + newCostMatrix.removeEdge(max);
        notBelong.add(max);
        System.out.println("Bound " + newBound);
        System.out.println(newCostMatrix);
        TSP.queue.add(new Node(belong, notBelong, newCostMatrix, newBound));


        //IN
        newCostMatrix = new Matrix(costMatrix);
        newBound = lBound + newCostMatrix.removeRowCol(max);
        belong.add(max);
        System.out.println("Bound " + newBound);
        System.out.println(newCostMatrix);
        if (newCostMatrix.size == 2){
            if (newBound < TSP.best){
                TSP.best = newBound;//save node
                return;
            }
        }
        TSP.queue.add(new Node(belong, notBelong, newCostMatrix, newBound));



        //System.exit(1);
    }
    public int compareTo (Object obj) {
        if (this == obj) {
            return 0;
        }
        Node other =  (Node) obj;
         if (lBound > other.lBound){
             return 1;
         } else {// Don't know if enough
             return -1;
         }
    }


}
