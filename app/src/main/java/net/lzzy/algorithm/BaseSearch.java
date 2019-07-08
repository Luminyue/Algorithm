package net.lzzy.algorithm;

import android.widget.VideoView;

import javax.xml.datatype.Duration;

/**
 * Created by lzzy_gxy on 2019/6/22.
 * Description:
 */
public abstract class BaseSearch<T extends Comparable<? super T>>extends BaseSort <T>{
    T[]items;
    long duration;
    private int compareCount;
    private  int swapCout;
    int  moveStep;
    BaseSearch(T[] items) {
        super();
        this.items=items;
        compareCount=0;
        swapCout=0;
        moveStep=0;

    }
    public BaseSearch(){}
    boolean equal(T a, T b) {
        compareCount++;
        return a.compareTo(b)==0;
    }
    int compare(T a,T b){
        compareCount++;
        return a.compareTo(b);
    }
    public abstract int search(T key);

    public long getswapCout(){
        return duration;
    }
    public  void setDuration(long duration){
        this.duration=duration;
    }
    public int getcompareCount(){
        return  compareCount;
    }
    public  void setCompareCount(int compareCount){
        this.compareCount=compareCount;
    }
    public int getSwapCount(){
        return swapCout;
    }
    public void setSwapCout(int swapCout){
        this.swapCout=swapCout;
    }
}