package net.lzzy.algorithm;

/**
 * Created by lzzy_gxy on 2019/6/15.
 * Description:
 */
public abstract class BaseSort <T extends Comparable<? super T>>{

    T[]items;
    long duration;
    private int compareCount;
    private  int swapCout;
    int  moveStep;
    BaseSort(T[]items){
        this.items=items;
        compareCount=0;
        swapCout=0;
        moveStep=0;
    }

    public BaseSort() {

    }

    boolean bigger(T a, T b){
        compareCount++;
        return a.compareTo(b)>0;
    }
    void swap(int i,int j){
        T tmp=  items[i];
        items[i]=items[j];
        items[j]=tmp;
    }
    public String getResult(){
        String display="";
        for (T i:items){
            display=display.concat(i+",");
        }
        return display.substring(0,display.length()-1);
    }
    public void sortwithTime(){
        long start=System.currentTimeMillis();
        sort();
        duration=System.currentTimeMillis()-start;
    }
    public abstract void sort();
    public long getDuration (){
        return  duration;
    }
    public int getCompareCount (){
        return compareCount;
    }
    public int  getSwapCout(){
        return swapCout;
    }
    public int getMoveStep(){
        return moveStep;
    }
}