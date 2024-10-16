package com.soffid.iam.json;

import static java.util.Arrays.asList;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.json.JsonReaderFactory;
import javax.json.stream.JsonGeneratorFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.apache.johnzon.jaxrs.JohnzonProvider;
import org.apache.johnzon.mapper.MapperBuilder;
import org.apache.johnzon.mapper.access.AccessMode;
import org.apache.johnzon.mapper.internal.ConverterAdapter;

import es.caib.seycon.ng.comu.AccountType;

@Provider
@Produces({"application/scim+json","application/json"})
@Consumes({"application/scim+json","application/json"})
public class ConfigurableJohnzonProvider<T> implements MessageBodyWriter<T>, MessageBodyReader<T> {
    public ConfigurableJohnzonProvider() {
		super();
		builder.addAdapter(Calendar.class, String.class, new ConverterAdapter<Calendar>(new CalendarConverter()));
		builder.addAdapter(Date.class, String.class, new ConverterAdapter<Date>(new DateConverter()));
		builder.addAdapter(AccountType.class, String.class, new ConverterAdapter<AccountType>(new AccountTypeConverter()));
	}

	// build/configuration
    private MapperBuilder builder = new MapperBuilder();
    private List<String> ignores;

    // runtime
    private JohnzonProvider<T> instance = null;
    
    private JohnzonProvider<T> instance() {
    	if (instance == null)
    		instance = new JohnzonProvider<T>(builder.build(), ignores);
        return instance;
    }

    public boolean isReadable(final Class<?> rawType, final Type genericType,
                              final Annotation[] annotations, final MediaType mediaType) {
        return instance().isReadable(rawType, genericType, annotations, mediaType);
    }

    public T readFrom(final Class<T> rawType, final Type genericType,
                      final Annotation[] annotations, final MediaType mediaType,
                      final MultivaluedMap<String, String> httpHeaders,
                      final InputStream entityStream) throws IOException {
        return instance().readFrom(rawType, genericType, annotations, mediaType, httpHeaders, entityStream);
    }

    public long getSize(final T t, final Class<?> rawType, final Type genericType,
                        final Annotation[] annotations, final MediaType mediaType) {
        return instance().getSize(t, rawType, genericType, annotations, mediaType);
    }

    public boolean isWriteable(final Class<?> rawType, final Type genericType,
                               final Annotation[] annotations, final MediaType mediaType) {
        return instance().isWriteable(rawType, genericType, annotations, mediaType);
    }

    public void writeTo(final T t, final Class<?> rawType, final Type genericType,
                        final Annotation[] annotations, final MediaType mediaType,
                        final MultivaluedMap<String, Object> httpHeaders,
                        final OutputStream entityStream) throws IOException {
        instance().writeTo(t, rawType, genericType, annotations, mediaType, httpHeaders, entityStream);
    }

    // type=a,b,c|type2=d,e
    public void setIgnoreFieldsForType(final String mapping) {
        for (final String config : mapping.split(" *| *")) {
            final String[] parts = config.split(" *= *");
            try {
                final Class<?> type = Thread.currentThread().getContextClassLoader().loadClass(parts[0]);
                if (parts.length == 1) {
                    builder.setIgnoreFieldsForType(type);
                } else {
                    builder.setIgnoreFieldsForType(type, parts[1].split(" *, *"));
                }
            } catch (final ClassNotFoundException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    public void setSupportConstructors(final boolean supportConstructors) {
        builder.setSupportConstructors(supportConstructors);
    }

    public void setPretty(final boolean pretty) {
        builder.setPretty(pretty);
    }

    public void setSupportGetterForCollections(final boolean supportGetterForCollections) {
        builder.setSupportGetterForCollections(supportGetterForCollections);
    }

    public void setSupportsComments(final boolean supportsComments) {
        builder.setSupportsComments(supportsComments);
    }

    public void setIgnores(final String ignores) {
        this.ignores = ignores == null ? null : asList(ignores.split(" *, *"));
    }

    public void setAccessMode(final AccessMode mode) {
        builder.setAccessMode(mode);
    }

    public void setAccessModeName(final String mode) {
        builder.setAccessModeName(mode);
    }

    public void setSupportHiddenAccess(final boolean supportHiddenAccess) {
        builder.setSupportHiddenAccess(supportHiddenAccess);
    }

    public void setAttributeOrder(final Comparator<String> attributeOrder) {
        builder.setAttributeOrder(attributeOrder);
    }

    public void setReaderFactory(final JsonReaderFactory readerFactory) {
        builder.setReaderFactory(readerFactory);
    }

    public void setGeneratorFactory(final JsonGeneratorFactory generatorFactory) {
        builder.setGeneratorFactory(generatorFactory);
    }

    public void setDoCloseOnStreams(final boolean doCloseOnStreams) {
        builder.setDoCloseOnStreams(doCloseOnStreams);
    }

    public void setVersion(final int version) {
        builder.setVersion(version);
    }

    public void setSkipNull(final boolean skipNull) {
        builder.setSkipNull(skipNull);
    }

    public void setSkipEmptyArray(final boolean skipEmptyArray) {
        builder.setSkipEmptyArray(skipEmptyArray);
    }

    public void setBufferSize(final int bufferSize) {
        builder.setBufferSize(bufferSize);
    }

    public void setBufferStrategy(final String bufferStrategy) {
        builder.setBufferStrategy(bufferStrategy);
    }

    public void setMaxSize(final int size) {
        builder.setMaxSize(size);
    }

    public void setTreatByteArrayAsBase64(final boolean treatByteArrayAsBase64) {
        builder.setTreatByteArrayAsBase64(treatByteArrayAsBase64);
    }

    public void setEncoding(final String encoding) {
        builder.setEncoding(encoding);
    }

    public void setReadAttributeBeforeWrite(final boolean rabw) {
        builder.setReadAttributeBeforeWrite(rabw);
    }

    public void setEnforceQuoteString(final boolean val) {
        builder.setEnforceQuoteString(val);
    }

    public void setPrimitiveConverters(final boolean val) {
        builder.setPrimitiveConverters(val);
    }
    
}
