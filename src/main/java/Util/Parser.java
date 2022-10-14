package Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static final String DEFAULT_REGEX = "\\w";

    public Map<String, String> map(List<String> strings, String regex) throws Exception {
        strings = strings.stream().filter((x) -> !x.isEmpty()).toList();

        Map<String, String> map = new HashMap<>();

        Pattern pattern = Pattern.compile(regex);
        for (String el : strings) {
            Matcher m = pattern.matcher(String.valueOf(el.charAt(0)));
            if (m.matches()) {
                try {
                    String[] s = el.split("=");
                    if(s.length==2) {
                        map.put(s[0], s[1]);
                    }
                } catch (Exception e) {
                    throw new Exception("Invalid line:\n"+el, e);
                }
            }
        }

        return map;
    }

    public Map<String, String> map(List<String> strings) throws Exception {
        return map(strings, DEFAULT_REGEX);
    }
}
