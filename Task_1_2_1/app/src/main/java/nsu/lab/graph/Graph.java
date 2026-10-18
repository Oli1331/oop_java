package nsu.lab.graph;

import java.util.List;

public abstract class Graph<T> implements Igraph<T>{
    public abstract boolean addVertex(T vertex);

    public abstract boolean delVertex(T vertex);

    public abstract boolean addEdge(T vertex1, T vertex2);

    public abstract boolean delEdge(T vertex1, T vertex2);

    public abstract List<T> getNeighbors(T vertex);

    public boolean readFromFile(String path){
        return true;
    }
}
