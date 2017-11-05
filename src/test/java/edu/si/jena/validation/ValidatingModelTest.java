package edu.si.jena.validation;

import java.net.URI;

import org.apache.jena.graph.Graph;
import org.apache.jena.sparql.sse.SSE;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.weso.rdf.RDFReader;
import es.weso.rdf.jena.RDFAsJenaModel;
import es.weso.rdf.nodes.IRI;
import es.weso.rdf.nodes.RDFNode;
import es.weso.schema.Schema;
import es.weso.schema.Schemas;
import es.weso.schema.ShapeMapTrigger;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;


public class ValidatingModelTest {
    
    Logger log = LoggerFactory.getLogger(ValidatingModelTest.class);

    @Test
    public void test() {
        RDFReader schemaSource = RDFAsJenaModel.fromURI("https://raw.githubusercontent.com/shexSpec/shexTest/master/schemas/bnode1dot.ttl", "TURTLE", scala.Option.empty()).get();
        Schema schema = Schemas.fromRDF(schemaSource, "test").getOrElse(() -> Schemas.shEx());
        Graph g = SSE.parseGraph("(graph ( <foo> <http://a.example/p1> <bar> ))");
		ValidatingModel model = new ValidatingModel(g, schema);
		Set<RDFNode> nodes = new Set.Set1<RDFNode>(new IRI(URI.create("foo")));
		Map<RDFNode, Set<String>> labels = null;
		new ShapeMapTrigger(labels, nodes);
        Result results = model.validate();
        String show = results.show();
        log.info(show);
    }

}
