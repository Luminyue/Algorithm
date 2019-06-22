package net.lzzy.algorithm;

import android.content.Intent;

import java.io.OptionalDataException;
import java.math.BigDecimal;


    /**
     * Created by lzzy_gxy on 2019/6/15.
     * Description:
     */
    public class InsertSort <T extends Comparable<?super T>>extends BaseSort<T> {
        private T[] items;

        public InsertSort(T[] items) {
            super(items);
        }
//    private long duration;//运行时间
//    private int compareCount;//对比次数
//    private int swapCount;//移动次数
//    private int moveStep;//交换次数
//    //endregi

        @Override
        public void sort() {
            long start = System.currentTimeMillis();
            for (int i = 1; i < items.length; i++) {
                int j = i-1;
                if (bigger(items[i],items[j])) {
                    continue;
                }
                T tmp=items[i];
                while (j>=0&& bigger(items[j],tmp)){
                    items[j+1]=items[j];
                    j--;
                }
                items[j+1]=tmp;
            }
            duration = System.currentTimeMillis() - start;
        }

    }