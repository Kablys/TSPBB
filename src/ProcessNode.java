public class ProcessNode extends Thread{

    public void run() {
        Node a = null;
        int index = 0;
        while ((a = TSP.queue.poll()) != null && index < 99){
            a.process();
            index++;
            //TODO create two Nodes that put them on queue
        }
        System.out.printf("DONE" + TSP.best);
        System.exit(1);
    }

}
