package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PropertyLoader {

    private static final String PROPERTIES_FILENAME = "application.properties";

    private static Properties cache;

    public HashMap<String, String> getPropertiesAsMap() {
        Properties properties = getProperties();

        HashMap<String, String> hashMap = new HashMap<String, String>();

        for (Entry<Object, Object> each : properties.entrySet()) {
            String value = each.getValue().toString();
            hashMap.put(each.getKey().toString(), replaceSystemVariables(value));
        }

        return hashMap;
    }

    public String getProperty(String key) {
        String value = getProperties().getProperty(key);
        if (value == null) {
            throw new IllegalStateException("no such property: " + key);
        }
        return value;
    }

    public Properties getProperties() {
        if (cache != null) {
            return cache;
        }

        Properties properties = new Properties();
        try (InputStream is = PropertyLoader.class.getClassLoader()
                .getResourceAsStream(PROPERTIES_FILENAME)) {

            if (is == null) {
                throw new IllegalStateException("can't load file: " + PROPERTIES_FILENAME);
            }

            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        cache = properties;

        return properties;
    }

    private String replaceSystemVariables(String value) {
        Pattern pattern = Pattern.compile("\\$\\{([^}]+)\\}");
        Matcher matcher = pattern.matcher(value);
        StringBuffer buf = new StringBuffer();
        while (matcher.find()) {
        	String v = System.getProperty(matcher.group(1));
        	v = v.replace("\\", "\\\\");
        	matcher.appendReplacement(buf, v);
        }
        matcher.appendTail(buf);

        return buf.toString();
    }
}
