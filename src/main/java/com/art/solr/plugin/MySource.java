package com.art.solr.plugin;

import java.io.IOException;
import java.util.Map;

import org.apache.lucene.index.LeafReaderContext;
import org.apache.lucene.queries.function.FunctionValues;
import org.apache.lucene.queries.function.ValueSource;
import org.apache.lucene.queries.function.docvalues.FloatDocValues;

public class MySource extends ValueSource {

	public String str;
	public ValueSource field;

	public MySource(ValueSource v1, String str) {
		this.field = v1;
		this.str = str;
	}

	@Override
	public FunctionValues getValues(Map context, LeafReaderContext readerContext) throws IOException {
		final FunctionValues fieldVals = field.getValues(context, readerContext);

		return new FloatDocValues(this) {
			@Override
			public float floatVal(int doc) throws IOException {
				String domain = fieldVals.strVal(doc);// 根据domain做不同的权重加权
				float f = 10f;
				return f;
			}

			@Override
			public String toString(int doc) throws IOException {
				return name() + '(' + fieldVals.strVal(doc) + ')';
			}
		};
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub//重要
		return true;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String description() {
		return name();
	}

	public String name() {
		return "mys";
	}

}
