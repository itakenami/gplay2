package util;

import java.util.HashMap;
import java.util.Map;

import play.data.Form;

public class Binder<T> extends Form<T> {
	
	private final Class<T> tipo;

	public Binder(Class<T> arg0) {
		super(arg0);
		this.tipo = arg0;
	}
	
	public static <T> Binder<T> form(Class<T> clazz) {
        return new Binder<T>(clazz);
    }
	
	public Form<T> bindFromRequestMulti() {

		Map<String, String> newData = new HashMap<String, String>();
		Map<String, String[]> urlFormEncoded = play.mvc.Controller.request()
				.body().asFormUrlEncoded();
		if (urlFormEncoded != null) {
			for (String key : urlFormEncoded.keySet()) {
				String[] value = urlFormEncoded.get(key);
				if (value.length == 1) {
					int pos = key.indexOf(".");
					if (pos > -1) {
						String keyPrefix = key;
						String keyPostfix = "";
						keyPrefix = key.substring(0, pos);
						keyPostfix = key.substring(pos, key.length());
						newData.put(keyPrefix + "[0]" + keyPostfix, value[0]);
					} else {
						newData.put(key, value[0]);
					}

				} else if (value.length > 1) {
					String keyPrefix = key;
					String keyPostfix = "";
					int pos = key.indexOf(".");
					if (pos > -1) {
						keyPrefix = key.substring(0, pos);
						keyPostfix = key.substring(pos, key.length());
					}
					for (int i = 0; i < value.length; i++) {
						System.out.println("COLOCANDO: " + keyPrefix + "[" + i
								+ "]" + keyPostfix + "=" + value[i]);
						newData.put(keyPrefix + "[" + i + "]" + keyPostfix,
								value[i]);
					}
				}
			}
		}

		Form<T> filledForm = new Form<T>(tipo).bind(newData);
		return filledForm;
	}

	public Form<T> bind(Class<T> c) {

		Map<String, String> newData = new HashMap<String, String>();
		Map<String, String[]> urlFormEncoded = play.mvc.Controller.request()
				.body().asFormUrlEncoded();
		if (urlFormEncoded != null) {
			for (String key : urlFormEncoded.keySet()) {
				String[] value = urlFormEncoded.get(key);
				if (value.length == 1) {
					int pos = key.indexOf(".");
					if (pos > -1) {
						String keyPrefix = key;
						String keyPostfix = "";
						keyPrefix = key.substring(0, pos);
						keyPostfix = key.substring(pos, key.length());
						newData.put(keyPrefix + "[0]" + keyPostfix, value[0]);
					} else {
						newData.put(key, value[0]);
					}

				} else if (value.length > 1) {
					String keyPrefix = key;
					String keyPostfix = "";
					int pos = key.indexOf(".");
					if (pos > -1) {
						keyPrefix = key.substring(0, pos);
						keyPostfix = key.substring(pos, key.length());
					}
					for (int i = 0; i < value.length; i++) {
						System.out.println("COLOCANDO: " + keyPrefix + "[" + i
								+ "]" + keyPostfix + "=" + value[i]);
						newData.put(keyPrefix + "[" + i + "]" + keyPostfix,
								value[i]);
					}
				}
			}
		}

		Form<T> filledForm = new Form<T>(c).bind(newData);
		return filledForm;
	}
}
