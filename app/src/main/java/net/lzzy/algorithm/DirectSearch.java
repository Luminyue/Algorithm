package net.lzzy.algorithm;

/**
 * Created by lzzy_gxy on 2019/6/22.
 * Description:
 */
public abstract class DirectSearch<T extends Comparable<? super T>>extends BaseSearch<T> {

    DirectSearch(T[] items) {

        super(items);
    }

    @Override
    public int search(T key) {
        long start=System.currentTimeMillis();
        int pos=0;
        for (T item:items){
            if (equal(item,key)){
                setDuration(System.currentTimeMillis()-start);
                return pos;
            }
            pos++;
        }
        setDuration(System.currentTimeMillis()-start);
        return -1;
    }

}