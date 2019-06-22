package net.lzzy.algorithm;

/**
 * Created by lzzy_gxy on 2019/6/20.
 * Description:
 */
public class SortFactory {
    public static <T extends Comparable<? super T>> BaseSort<T> getInstance(int key,T[] items){
        BaseSort<T> sort;
        switch (key){
            case 0:
                sort =new DirectSort<>(items);
                break;
            case 1:
                sort=new InsertSort<>(items);
                break;
            case 2:
                sort=new ShellSort<>(items);
                break;
            default:
                return null;
        }
        return sort;
    }
    public static String[]getSortNames(){
        return new String[]{"选择排序","直接选择排序","希尔排序"};
    }
}