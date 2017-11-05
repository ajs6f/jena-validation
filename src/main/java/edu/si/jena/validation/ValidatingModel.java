package edu.si.jena.validation;

import org.apache.jena.graph.Graph;
import org.apache.jena.rdf.model.impl.ModelCom;

import es.weso.rdf.jena.RDFAsJenaModel;
import es.weso.schema.Schema;
import es.weso.schema.ValidationTrigger;

public class ValidatingModel extends ModelCom {
    
    private Schema schema;
    
    private ValidationTrigger trigger = ValidationTrigger.targetDeclarations();

	public ValidatingModel(Graph base, Schema schema) {
        super(base);
        this.schema = schema;
    }
	
    public ValidationTrigger trigger() {
		return trigger;
	}

	public ValidatingModel trigger(ValidationTrigger trigger) {
		this.trigger = trigger;
		return this;
	}

    private RDFAsJenaModel rdfAsJenaModel() {
        return RDFAsJenaModel.apply(this);
    }

    public Result validate() {
        return new Result(schema.validate(rdfAsJenaModel(), trigger));
    }

    public Schema schema() {
        return schema;
    }

    public ValidatingModel schema(Schema schema) {
        this.schema = schema;
        return this;
    }
}
