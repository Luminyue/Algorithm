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
    List<Integer> visited;

    List<Edge> getConnectedEdges(int source){
        List<Edge> result=new ArrayList<>();
        for (Edge e:edges){
            if (e.getSource()==source){
                result.add(e);
            }
        }
        return result;
    }
    private void iterateDepth(int v){
        if (!visited.contains(v)){
            visited.add(v);
        }
        if (visited.size()==vertexCount){
            return;
        }
        List<Edge> vEdges=getConnectedEdges(v);
        for (Edge edge:vEdges){
            if (visited.contains(edge.getTarget())){
                continue;
            }
            iterateDepth(edge.getTarget());
        }
    }


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
//        return "015234";
        visited=new ArrayList<>();
        iterateDepth(0);
        StringBuilder result=new StringBuilder();
        for(int v:visited ){
            result.append(v).append(",");
        }
        return result.toString();
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