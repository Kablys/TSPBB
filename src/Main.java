
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("mano5");
        int numberThreads = 4;
        if (args.length < 2){
            System.out.printf("Using file" + " with %d threads", numberThreads);
            System.out.println("argumens:<number of threads> <filename of cost matrix>");

        } else {
            numberThreads = Integer.parseInt(args[0]);
            path = Paths.get(args[1]);
        }
        BufferedReader br = new BufferedReader(new FileReader(String.valueOf(path)));
        String s;
        int rowNumber = 0;
        Integer numOfCitys = 0;
        Integer [][] costMatrix = {};
        while((s = br.readLine()) != null) {
            String[] row = s.split(" ");
            if (rowNumber ==  0){
                numOfCitys = row.length;
                costMatrix = new Integer[numOfCitys][numOfCitys];
            }else if(numOfCitys != row.length){
                System.err.printf("Incorrect data: number of elements in row should be %d but got %d",
                        numOfCitys, row.length);
            }
            else if(numOfCitys < rowNumber){
                System.err.printf("Incorrect data: number of elements in columb should be %d but got %d",
                        numOfCitys, rowNumber);
            }

            for (int col = 0; col < numOfCitys; col++){
                costMatrix[rowNumber][col] = Integer.parseInt(row[col]);
            }
            rowNumber++;

        }
        TSP tsp = new TSP(costMatrix, numberThreads);
        tsp.solve();

    }
}