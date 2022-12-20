package com.soffid.iam.addon.scim2.rest;


import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.StreamingOutput;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONWriter;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.addon.scim.response.SCIMResponseBuilder;
import com.soffid.iam.addon.scim.util.PATCHAnnotation;
import com.soffid.iam.addon.scim.util.PaginationUtil;
import com.soffid.iam.addon.scim2.json.JSONBuilder;
import com.soffid.iam.addon.scim2.json.JSONParser;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.Password;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
public class BaseRest<E> {

	private Class clazz;
	Log log = LogFactory.getLog(getClass());
	public BaseRest (Class<E> cl) {
		this.clazz = cl;
	}
	
	CrudHandler<E> getCrud() throws InternalErrorException, NamingException, CreateException {
		return EJBLocator.getCrudRegistryService().getHandler(clazz);
	}

	@Path("")
	@GET
	public Response list(@QueryParam("filter") @DefaultValue("") String filter, 
			@QueryParam("textFilter") String textFilter,
			@QueryParam("attributes") String atts,
			@QueryParam("excludedAttributes") String excludedAtts,
			@QueryParam("sortBy") String sortBy,
			@QueryParam("sortOrder") String sortOrder,
			@QueryParam("startIndex") @DefaultValue("") String startIndex, 
			@QueryParam("count") @DefaultValue("") String count, 
			@Context HttpServletRequest request)
			throws Throwable {
		JSONBuilder b = new JSONBuilder(request);
		if (atts != null) 
			b.setAttributes((atts+", meta, schemas").split(" *, *"));
		if (excludedAtts != null)
			b.setExcludedAttributes(excludedAtts.split(" *, *"));
		Security.nestedLogin( (SoffidPrincipal) ((HttpServletRequest) request).getUserPrincipal());
		try {
			PaginationUtil p = new PaginationUtil(startIndex, count);
			if (p.getCount() <= 0) p.setCount(100);
			if (p.getCount() > 1000) p.setCount(1000);
			
			if (sortBy != null && !sortBy.trim().isEmpty()) {
				filter += " $orderby ";
				for (String field: sortBy.split("[ ,]+")) {
					filter += " "+field;
					if (sortOrder != null) {
						if ("ascending".equals(sortOrder))
							filter += " asc";
						else if ("descending".equals(sortOrder))
							filter += " desc";
						else
						{
							return 	SCIMResponseBuilder.errorCustom(Status.BAD_REQUEST, new Exception("Wrong value for parameter sortOrder"));

						}
					}
				}
			}
			PagedResult<E> r = getCrud().read(textFilter, filter, p.getStartIndex(), p.getCount());
			
			
			return Response.ok( (StreamingOutput) output -> {
				OutputStreamWriter w = new OutputStreamWriter(output, "UTF-8");
				JSONWriter jsonWriter = new JSONWriter(w);
				jsonWriter.object();
				jsonWriter.key("schemas");
				{
					jsonWriter.array();
					jsonWriter.value("urn:ietf:params:scim:api:messages:2.0:ListResponse");
					jsonWriter.endArray();
				}
				jsonWriter.key("totalResults"); jsonWriter.value(r.getTotalResults());
				jsonWriter.key("startIndex"); jsonWriter.value(r.getStartIndex()+1);
				if (r.getItemsPerPage().intValue() < r.getTotalResults().intValue()) {
					jsonWriter.key("itemsPerPage"); 
					jsonWriter.value(r.getItemsPerPage());
				}
				jsonWriter.key("Resources");
				jsonWriter.array();
				{
					boolean first = true;
					for (E res: r.getResources()) {
						if (first) first = false;
						else w.append(",");
						writeObject(w, b, res);
					}
				}
				jsonWriter.endArray();
				jsonWriter.endObject();
				w.close();
				output.close();
			}).build();
		} catch (Exception e) {
			log.warn("Error processing SCIM Request "+request.getRequestURL(), e);
			return SCIMResponseBuilder.errorGeneric(e);
		} finally {
			Security.nestedLogoff();
		}
		
	}
	

	@Path("/.search")
	@POST
	public Response listPost(@QueryParam("filter") @DefaultValue("") String filter, 
			@QueryParam("textFilter") String textFilter,
			@QueryParam("attributes") String atts,
			@QueryParam("excludedAttributes") String excludedAtts,
			@QueryParam("sortBy") String sortBy,
			@QueryParam("sortOrder") String sortOrder,
			@QueryParam("startIndex") @DefaultValue("") String startIndex, 
			@QueryParam("count") @DefaultValue("") String count, 
			@Context HttpServletRequest request)
			throws Throwable {
		return list(filter, textFilter, atts, excludedAtts, sortBy, sortOrder, startIndex, count, request);
	}

	@Path("")
	@POST
	public Response create(String data, @Context HttpServletRequest request) throws URISyntaxException {
		JSONBuilder b = new JSONBuilder(request);
		Security.nestedLogin( (SoffidPrincipal) ((HttpServletRequest) request).getUserPrincipal());
		try {
			JSONObject o = new JSONObject(data);
			E obj = loadObject(o);
			E newObj = create(o, obj);
			if (newObj != null) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				OutputStreamWriter w = new OutputStreamWriter(out, "UTF-8");
				writeObject(w, b, newObj);
				w.close();

				JSONObject jsonObject = new JSONObject(out.toString("UTF-8"));
				String uri = jsonObject.getJSONObject("meta").getString("location");
				return Response
						.status(201)
						.header("Location", uri)
						.entity(jsonObject.toString())
						.build();
			} else
				return SCIMResponseBuilder.responseOnlyHTTP(Status.NOT_FOUND);
		} catch (Exception e) {
			log.warn("Error processing SCIM Request "+request.getRequestURL(), e);
			return SCIMResponseBuilder.errorGeneric(e);
		} finally {
			Security.nestedLogoff();
		}
	}

	public E create(JSONObject json, E obj) throws Exception, InternalErrorException, NamingException, CreateException {
		checkRequiredAttributes(obj, null);
		E newObj = getCrud().create(obj);
		return newObj;
	}

	public E update(JSONObject json, E obj, E old) throws Exception, InternalErrorException, NamingException, CreateException {
		checkRequiredAttributes(obj, old);
		E newObj = getCrud().update(obj);
		return newObj;
	}

	public void checkRequiredAttributes(E obj, E old) throws InternalErrorException, NamingException, CreateException {
		Map<String,Object> attributes = null;
		Map<String,Object> attributesOld = null;
		try {
			attributes = (Map<String, Object>) obj.getClass().getMethod("getAttributes").invoke(obj);
			attributesOld = old == null ? null:
				(Map<String, Object>) old.getClass().getMethod("getAttributes").invoke(old);
		} catch (Exception e) {
		}
		if (attributes != null) {
			for (DataType att: getMetadata(obj)) {
				if ( Boolean.FALSE.equals( att.getBuiltin())) {
					if (att.getType() == TypeEnumeration.PASSWORD_TYPE)
					{
						Object o = attributes.get(att.getName());
						if (o == null || "******".equals(o))
							attributes.put(att.getName(), attributesOld == null ? null : attributesOld.get(att.getName()));
						else if ( attributesOld == null || ! o.equals(attributesOld.get(att.getName())) )
							attributes.put(att.getName(), new Password(o.toString()).toString());
					}
					if (att.isRequired()) {
						if (attributes.get(att.getName()) == null)
							throw new InternalErrorException("Attribute "+att.getName()+" is required");
					}
				} else {
					if (att.isRequired()) {
						Object value = null;
						try {
							value = PropertyUtils.getProperty(obj, att.getName());
						} catch (Exception e) {
							value = "X";
						}
						if (value == null)
							throw new InternalErrorException("Attribute "+att.getName()+" is required");
					}
				}
			}
		}
	}
	
	public void delete(E obj) throws Exception, InternalErrorException, NamingException, CreateException {
		getCrud().delete(obj);
	}

	protected E loadObject(JSONObject data) throws Exception {
		E result = (E) new JSONParser().load(data, clazz, jsonAttributesToIgnore());
		return result;
	}

	public String[] jsonAttributesToIgnore() {
		return new String[0];
	}
	
	@Path("/{id}")
	@GET
	public Response show(@PathParam("id") long id,
			@QueryParam("attributes") String atts,
			@QueryParam("excludedAttributes") String excludedAtts,
			@Context HttpServletRequest request) {
		JSONBuilder b = new JSONBuilder(request);
		if (atts != null) 
			b.setAttributes((atts+", meta, schemas").split(" *, *"));
		if (excludedAtts != null)
			b.setExcludedAttributes(excludedAtts.split(" *, *"));
		Security.nestedLogin( (SoffidPrincipal) ((HttpServletRequest) request).getUserPrincipal());
		try {
			PagedResult<E> objs = getCrud().read(null, "id eq \""+id+"\"", null, null);
			if ( objs.getResources().isEmpty()) {
				return SCIMResponseBuilder.responseOnlyHTTP(Status.NOT_FOUND);
			} else {
				E obj = objs.getResources().get(0);
				return Response.ok( (StreamingOutput) output -> {
					OutputStreamWriter w = new OutputStreamWriter(output, "UTF-8");
					writeObject(w, b, obj);
					w.close();
					output.close();
				}).build();
			}
		} catch (Exception e) {
			log.warn("Error processing SCIM Request "+request.getRequestURL(), e);
			return SCIMResponseBuilder.errorGeneric(e);
		} finally {
			Security.nestedLogoff();
		}
	}

	@Path("/{id}")
	@DELETE
	public Response delete(@PathParam("id") long id, @Context HttpServletRequest request) {
		Security.nestedLogin( (SoffidPrincipal) ((HttpServletRequest) request).getUserPrincipal());
		try {
			PagedResult<E> objs = getCrud().read(null, "id eq \""+id+"\"", null, null);
			if ( objs.getResources().isEmpty()) {
				return SCIMResponseBuilder.errorCustom(Status.NOT_FOUND, "RoleSvc.roleNotFound", id); //$NON-NLS-1$
			} else {
				E obj = objs.getResources().get(0);
				delete(obj);
				return SCIMResponseBuilder.responseOnlyHTTP(Status.NO_CONTENT);
			}
		} catch (Exception e) {
			log.warn("Error processing SCIM Request "+request.getRequestURL(), e);
			return SCIMResponseBuilder.errorGeneric(e);
		} finally {
			Security.nestedLogoff();
		}
	}

	@Path("/{id}")
	@PUT
	public Response update(@PathParam("id") long id, String data, @Context HttpServletRequest request) {
		Security.nestedLogin( (SoffidPrincipal) ((HttpServletRequest) request).getUserPrincipal());
		try {
			JSONBuilder b = new JSONBuilder(request);
			PagedResult<E> objs = getCrud().read(null, "id eq \""+id+"\"", null, null);
			if ( objs.getResources().isEmpty()) {
				return SCIMResponseBuilder.responseOnlyHTTP(Status.NOT_FOUND);
			} else {
				JSONObject o = new JSONObject(data);
				o.put("id", id);
				E obj = loadObject(o);
				final E obj2 = update (o, obj, objs.getResources().get(0));
				return Response.ok( (StreamingOutput) output -> {
					OutputStreamWriter w = new OutputStreamWriter(output, "UTF-8");
					writeObject(w, b, obj2);
					w.close();
					output.close();
				}).build();
			}
		} catch (Exception e) {
			log.warn("Error processing SCIM Request "+request.getRequestURL(), e);
			return SCIMResponseBuilder.errorGeneric(e);
		} finally {
			Security.nestedLogoff();
		}
	}

	public void writeObject(OutputStreamWriter w, JSONBuilder builder, E obj) {
		try {
			Map<String,Object> attributes = (Map<String, Object>) obj.getClass().getMethod("getAttributes").invoke(obj);
			if (attributes != null) {
				for (DataType att: getMetadata(obj)) {
					if (att.getType() == TypeEnumeration.PASSWORD_TYPE && Boolean.FALSE.equals( att.getBuiltin()))
					{
						Object o = attributes.get(att.getName());
						if ( o != null)
							attributes.put(att.getName(), "******");
					}
				}
			}			
		} catch (Exception e) {
		}
		JSONObject jsonObject = builder.build(obj);
		jsonObject.write(w);
	}

	protected Collection<DataType> getMetadata(E obj) throws InternalErrorException, NamingException, CreateException {
		 return EJBLocator.getAdditionalDataService()
			.findDataTypesByObjectTypeAndName(obj.getClass().getName(), null);
	}

	@Path("/{id}")
	@PATCHAnnotation
	public Response patch(@PathParam("id") long id, String data, @Context HttpServletRequest request) {
		Security.nestedLogin( (SoffidPrincipal) ((HttpServletRequest) request).getUserPrincipal());
		try {
			JSONBuilder b = new JSONBuilder(request);
			PagedResult<E> objs = getCrud().read(null, "id eq \""+id+"\"", null, null);
			if ( objs.getResources().isEmpty()) {
				return SCIMResponseBuilder.responseOnlyHTTP(Status.NOT_FOUND);
			} else {
				JSONObject o = new JSONObject(data);
				E obj = objs.getResources().get(0);
				E objb = getCrud().read(null, "id eq \""+id+"\"", null, null).getResources().get(0);
				JSONObject o2 = b.build(obj);
				if (o.has("Operations")) {
					o2 = newPatch(o2, o);
				} else {
					o2 = oldPatch(o2, o);
				}
				o2.put("id", id);
				obj = loadObject(o2);
				final E obj2 = update(o2, obj, objb);
				return Response.ok( (StreamingOutput) output -> {
					OutputStreamWriter w = new OutputStreamWriter(output, "UTF-8");
					writeObject(w, b, obj2);
					w.close();
					output.close();
				}).build();
			}
		} catch (Exception e) {
			log.warn("Error processing SCIM Request "+request.getRequestURL(), e);
			return SCIMResponseBuilder.errorGeneric(e);
		} finally {
			Security.nestedLogoff();
		}
	}

	private JSONObject oldPatch(JSONObject oldjson, JSONObject newjson) throws Exception {
		merge(oldjson, newjson);
		return oldjson;
	}

	private void merge(JSONObject oldjson, JSONObject newjson) {
		for (String key: newjson.keySet()) {
			Object newsub = newjson.get(key);
			Object oldsub = oldjson.get(key);
			if (newsub instanceof JSONObject && oldsub instanceof JSONObject) {
				merge ( (JSONObject) oldsub, (JSONObject) newsub);
			} else {
				oldjson.put(key, newjson.get("key"));
			}
		}
		
	}

	protected JSONObject newPatch(JSONObject oldjson, JSONObject newjson) throws Exception {
		JSONArray operations = newjson.getJSONArray("Operations");
		for (int i = 0; i < operations.length(); i++) {
			JSONObject operation = operations.getJSONObject(i);
			String op = operation.getString("op");
			String path = operation.optString("path");
			JSONObject old1 = oldjson;
			String last = null;
			if (path != null && ! path.trim().isEmpty()) {
				if (path.contains("[")) throw new Exception("search clause is not supported");
				String[] split = path.split("\\.");
				for (int j = 0; j < split.length - 1; j++) {
					if ( ! old1.has(split[j]))
						old1.put(split[j], new JSONObject());
					old1 = old1.getJSONObject(split[j]);
				}
				last = split [ split.length - 1];
			}
			if ("add".equals(op)) {
				Object value = operation.get("value");
				if (value instanceof JSONArray) {
					if (last == null)
						throw new Exception("Add operation must specify a path");
					addValues(old1, last, value);
				} else if (value instanceof JSONObject) {
					JSONObject valueObj = (JSONObject) value;
					if (last != null)
						old1 = old1.getJSONObject(last);
					for (String key: valueObj.keySet()) {
						addValues(old1, key, valueObj.get(key));
					}
				} else {
					if (path == null)
						throw new Exception("Add operation must specify a path");
					addValues(old1, last, value);
				}
			}
			else if ("remove".equals(op)) {
				if (last == null)
					throw new Exception("Add operation must specify a path");
				old1.remove(last);
			}
			else if ("replace".equals(op)) {
				Object value = operation.get("value");
				if (value instanceof JSONArray) {
					if (last == null)
						throw new Exception("Update operation must specify a path");
					replaceValues(old1, last, value);
				} else if (value instanceof JSONObject) {
					JSONObject valueObj = (JSONObject) value;
					if (last != null)
						old1 = old1.getJSONObject(last);
					for (String key: valueObj.keySet()) {
						replaceValues(old1, key, valueObj.get(key));
					}
				} else {
					if (last == null)
						throw new Exception("Update operation must specify a path");
					old1.put(last, value);
				}
			}
		}
		return oldjson;
	}

	public void replaceValues(JSONObject old1, String last, Object value) {
		old1.put(last, value);
	}

	public void addValues(JSONObject old1, String last, Object value) {
		JSONArray a2 = (JSONArray) value;
		if (old1.has(last) && old1.get(last) instanceof JSONArray) {
			JSONArray a1 = old1.getJSONArray(last);
			for (int k = 0 ; k < a2.length(); k++)
				a1.put ( a2.get(k));
		} else if (old1.has(last) || a2.length() == 1){
			for (int k = 0 ; k < a2.length(); k++)
				old1.put (last, a2.get(k));
		} else {
			JSONArray a1 = new JSONArray();
			for (int k = 0 ; k < a2.length(); k++)
				a1.put ( a2.get(k));
			old1.put (last, a1);
		}
	}

	public String encode(String s) {
		return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\'", "\\\'");
	}

	protected void addReference(JSONBuilder builder, JSONObject jsonObject, String tag, String url) {
		if (builder.isIncluded("meta.navigation")) {
			JSONObject meta = jsonObject.getJSONObject("meta");
			if (meta != null) {
				JSONObject navigation;
				if (meta.has("links")) 
					navigation = meta.getJSONObject("links");
				else {
					navigation = new JSONObject();
					meta.put("links", navigation);
				}
				navigation.put(tag, builder.getServer()+url);
			}
		}
	}
}

