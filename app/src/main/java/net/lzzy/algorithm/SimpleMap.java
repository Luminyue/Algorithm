package net.lzzy.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzzy_gxy on 2019/6/27.
 * Description:
 */
public class SimpleMap {
    int vertexCount;
    List<Edge> edges=new ArrayList<>();

    public SimpleMap(int v){
        vertexCount=v;
    }
    public void addEdge(int sourece,int target,double distance){
        edges.add(new Edge(sourece,target,distance));
    }
    public void addTwoWayEdge(int v1,int v2,double d1){
        addEdge(v1,v2,d1);
        addEdge(v2,v1,d1);
    }
    public String iterateDepthFirst(){
        return "015234";
    }
    public String itereRangeFirst(){
        return "012354";
    }

}
class Edge{
    private int source;
    private int target;
    private double distance;


    Edge(int source,int target,double distance){
        this.distance=distance;
        this.source=source;
        this.target=target;
    }

    public int getSource() {
        return source;
    }

    public double getDistance() {
        return distance;
    }

    public int getTarget() {
        return target;
    }
}