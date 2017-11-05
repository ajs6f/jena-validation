package edu.si.jena.validation;

import java.util.List;
import java.util.Optional;

import es.weso.rdf.nodes.RDFNode;
import es.weso.schema.ErrorInfo;
import es.weso.schema.SchemaLabel;
import es.weso.schema.Solution;
import es.weso.schema.ValidationTrigger;
import io.circe.Json;
import scala.collection.JavaConverters;

/**
 * Wraps a {@link es.weso.schema.Result} in Java types.
 *
 */
public class Result {

    private final es.weso.schema.Result result;

    public Result(es.weso.schema.Result result) {
        this.result = result;
    }

    public int cut() {
        return result.cut();
    }

    @Override
    public boolean equals(Object other) {
        return result.equals(other);
    }

    public List<ErrorInfo> errors() {
        return JavaConverters.seqAsJavaList(result.errors());
    }

    public List<SchemaLabel> hasShapes(RDFNode node) {
        return JavaConverters.seqAsJavaList(result.hasShapes(node));
    }

    @Override
    public int hashCode() {
        return result.hashCode();
    }

    public boolean isValid() {
        return result.isValid();
    }

    public String message() {
        return result.message();
    }

    public boolean noSolutions(List<Solution> sols) {
        return result.noSolutions(JavaConverters.asScalaBuffer(sols));
    }

    public String show() {
        return result.show();
    }

    public Json toJson() {
        return result.toJson();
    }

    @Override
    public String toString() {
        return result.toString();
    }

    public Optional<ValidationTrigger> trigger() {
        return Optional.ofNullable(result.trigger().getOrElse(() -> null));
    }

    public Result addTrigger(ValidationTrigger trigger) {
        result.addTrigger(trigger);
        return this;
    }

    public Optional<Solution> solution() {
        return Optional.ofNullable(result.solution().getOrElse(() -> new Solution(null, null, null)));
    }

    public List<Solution> solutions() {
        return JavaConverters.seqAsJavaList(result.solutions());
    }

}
