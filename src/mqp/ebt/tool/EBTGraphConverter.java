package mqp.ebt.tool;

import mqp.ebt.MarioXMLManager;
import mqp.gp.command.ButtonPress;
import org.jgap.gp.CommandGene;
import org.jgap.gp.impl.GPProgram;
import org.jgap.gp.impl.ProgramChromosome;
import org.jgrapht.DirectedGraph;
import org.jgrapht.ext.*;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Tool to convert EBTs to DOT files
 * @author Ross Foley and Karl Kuhn
 */
public class EBTGraphConverter {
    public static String runName = "radius2test";
    public static int generation = 100;

    public static ProgramChromosome chromosome;
    public static DirectedGraph<String, DefaultEdge> graph;

    public static void main(String[] args) throws IOException {
        GPProgram gp = (GPProgram) MarioXMLManager.loadEBT(runName, generation);
        graph = new SimpleDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
        chromosome = gp.getChromosome(0);

        addToGraph(0);

        DOTExporter<String, DefaultEdge> exporter = new DOTExporter<String, DefaultEdge>(new IntegerNameProvider<String>(), new EBTNameProvider<String>(), new NoEdgeLabel<DefaultEdge>());
        String fileName = "graph/" + runName + "/generation" + generation + ".dot";
        File file = new File(fileName);
        file.getParentFile().mkdirs();
        exporter.export(new FileWriter(file), graph);
    }

    public static String addToGraph(int index) {
        CommandGene gene = chromosome.getGene(index);
        if (gene instanceof ButtonPress) {
            // ButtonPress is a terminal node, so just add the vertex and return it
            String vertex = "Gene " + index + ": " + gene.toString();
            graph.addVertex(vertex);
            return vertex;
        } else {
            // Add the root vertex
            String vertex = "Gene " + index + ": " + gene.getName();
            graph.addVertex(vertex);

            // Find the two children
            String thenVertex = addToGraph(chromosome.getChild(index, 0));
            String elseVertex = addToGraph(chromosome.getChild(index, 1));

            // Add edges to them
            graph.addEdge(vertex, thenVertex);
            graph.addEdge(vertex, elseVertex);

            // Return the current vertex
            return vertex;
        }
    }

    public static class NoEdgeLabel<E> implements EdgeNameProvider<E> {
        @Override
        public String getEdgeName(E e) {
            return null;
        }
    }

    public static class EBTNameProvider<V> implements VertexNameProvider<V> {
        @Override
        public String getVertexName(V v) {
            String name = v.toString();
            int colon = name.indexOf(":");
            if (colon > 0) {
                name = name.substring(colon + 2);
            }
            return name;
        }
    }
}
