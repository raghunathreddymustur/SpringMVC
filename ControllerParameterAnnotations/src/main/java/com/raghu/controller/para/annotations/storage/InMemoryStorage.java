package com.raghu.controller.para.annotations.storage;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class InMemoryStorage {
    private Map<String, Byte[]> contentByName = new HashMap<>();

    public void store(String name, byte[] content) {
        contentByName.put(name, ArrayUtils.toObject(content));
    }

    public Set<String> getNames() {
        return contentByName.keySet();
    }

    public byte[] getByName(String name) {
        return ArrayUtils.toPrimitive(contentByName.get(name));
    }
}
