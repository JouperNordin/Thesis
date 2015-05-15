public class BitonicSort{
    private static int[] internalData;
    private final static boolean ASCENDING=true, DESCENDING=false;
       
    public static int[] sort(int[] externalData, int level){
        internalData = externalData;
    	bitonicSort(0, internalData.length, ASCENDING, level);
        return internalData;
    }
    
    public static void bitonicSort(int lo, int n, boolean dir, int level){
        if (n>1){
            int m=n/2;
        	if(level == 0){
                bitonicSort(lo, m, ASCENDING, level);
                bitonicSort(lo+m, m, DESCENDING, level);
                bitonicMerge(lo, n, dir);
        	}
        	else{
        		Thread Thread_a = new Thread(new Sorter(lo, m, ASCENDING, level-1));
        		Thread Thread_b = new Thread(new Sorter(lo+m, m, DESCENDING, level-1));
        		Thread_a.start();
    			Thread_b.start();
    			try {
    				Thread_a.join();
    				Thread_b.join();
    			} catch (InterruptedException ie) {}
    			bitonicMerge(lo, n, dir);
        	}
        	
        }
    }

    private static void bitonicMerge(int lo, int n, boolean dir){
        if (n>1){
            int m=n/2;
            for (int i=lo; i<lo+m; i++)
                compare(i, i+m, dir);
            bitonicMerge(lo, m, dir);
            bitonicMerge(lo+m, m, dir);
        }
    }

    private static void compare(int i, int j, boolean dir){
        if (dir==(internalData[i]>internalData[j]))
            exchange(i, j);
    }

    private static void exchange(int i, int j){
        int t=internalData[i];
        internalData[i]=internalData[j];
        internalData[j]=t;
    }

}
 