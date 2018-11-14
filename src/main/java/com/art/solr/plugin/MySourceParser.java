package com.art.solr.plugin;

import org.apache.lucene.queries.function.ValueSource;
import org.apache.solr.common.SolrException;
import org.apache.solr.schema.SchemaField;
import org.apache.solr.search.FunctionQParser;
import org.apache.solr.search.SyntaxError;
import org.apache.solr.search.ValueSourceParser;

public class MySourceParser extends ValueSourceParser {
	public ValueSource parse(FunctionQParser fp) throws SyntaxError {
		String first = fp.parseArg();
		String second = fp.parseArg();
		ValueSource v1 = getValueSource(fp, first);
		return new MySource(v1, second);
	}

	public ValueSource getValueSource(FunctionQParser fp, String arg) {
		if (arg == null)
			return null;
		SchemaField f = fp.getReq().getSchema().getField(arg);
		// if (f.getType().getClass() == DateField.class) {
		// throw new SolrException(SolrException.ErrorCode.BAD_REQUEST, "Can't use ms()
		// function on non-numeric legacy date field " + arg);
		// }
		return f.getType().getValueSource(f, fp);
	}
}
