package net.lzzy.algorithm;

import android.util.SparseArray;

import java.util.List;

/**
 * Created by lzzy_gxy on 2019/7/8.
 * Description:
 */
public class BusMap extends SimpleMap {
    private SparseArray<String> vertexes;
    private double minDistance;
    private int start, target;

    public BusMap(int v) {
        super(v);
        vertexes = new SparseArray<>();
        for (int i = 0; i < v; i++) {
            vertexes.append(i, String.valueOf(i));
        }
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public void rename(int i, String name) {
        vertexes.setValueAt(i, name);
    }
    /* 1.深度优先的方式尝试从当前节点到目的地，记录路径长度*/

    private void tryWay(int curVertex, double distance) {
        if (minDistance > 0 && distance > minDistance) {
            return;
        }
        if (curVertex == target) {
            if (minDistance == 0 || minDistance > distance) {
                minDistance = distance;
            }
            return;
        }
        List<Edge> vEdges = getConnectedEdges(curVertex);
        for (Edge edge : vEdges) {
            if (visited.contains(edge.getTarget())) {
                continue;
            }
            visited.add(edge.getTarget());
            tryWay(edge.getTarget(), distance + edge.getDistance());
            vertexes.remove(edge.getTarget());
        }
    }
    //todo 2.Floyd算法求最短路径（多源最短路径）
    private  double[][] floyd(){
        double[][] distances=new double[vertexCount][vertexCount];
        for (int i=0;i<vertexCount;i++){
            for (int j=0;j<vertexCount;j++){
                distances[i][j]=99999;
            }
        }
        for (Edge edge:edges){
            distances[edge.getSource()][edge.getTarget()]=edge.getDistance();
        }
        for (int k=0;k<vertexCount;k++){
            for (int i=0;i<vertexCount;i++){
                for (int j=0;j<vertexCount;j++){
                    if (distances[i][j]>distances[i][k]+distances[k][j]){
                        distances[i][j]=distances[i][k]+distances[k][j];
                    }
                }
            }
        }
        return  distances;
    }
    //todo 3.Dijkstra算法求最短路径（单源）
    private void dijkstra(int source){
        double[] distances=new double[vertexCount];
        for (int i=0;i<vertexCount;i++){
            distances[i]=99999;
        }
        distances[source]=0;
        List<Edge> vEdges=getConnectedEdges(source);
        for (Edge edge:vEdges){
            distances[edge.getTarget()]=edge.getDistance();
        }
        visited.add(source);
        int shortVertex=source;
        for (int i=1;i<vertexCount;i++){
            double shortDistance=99999;
            for (int j=0;j<vertexCount;j++){
                if (visited.contains(j)||distances[j]>=shortDistance){
                    continue;
                }
                shortDistance=distances[j];
                shortVertex=j;
            }
            visited.add(shortVertex);
            List<Edge> shortVertexEdges=getConnectedEdges(shortVertex);
            for (Edge edge:shortVertexEdges){
                if (distances[edge.getTarget()]>distances[shortVertex]+edge.getDistance());
                distances[edge.getTarget()]=distances[shortVertex]+edge.getDistance();
            }
        }
    }

}


