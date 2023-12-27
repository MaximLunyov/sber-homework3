package org.example;

import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<T> implements CountMap<T> {
    private final Map<T, Integer> countMap = new HashMap<>();

    @Override
    public void add(T o) {
        countMap.merge(o, 1, Integer::sum);
    }

    @Override
    public int getCount(T o) {
        return countMap.getOrDefault(o, 0);
    }

    @Override
    public int remove(T o) {
        if (countMap.containsKey(0)) {
            int value = countMap.get(o);
            countMap.remove(o);
            return value;
        } else {
            return 0;
        }
    }

    @Override
    public int size() {
        return countMap.size();
    }

    @Override
    public void addAll(CountMap<T> source) {
        for (T key : source.toMap().keySet()) {
            if (countMap.containsKey(key)) {
                countMap.put(key, countMap.get(key) + 1);
            } else {
                countMap.put(key, 1);
            }
        }
    }

    @Override
    public Map toMap() {
        return countMap;
    }

    @Override
    public void toMap(Map destination) {
        for (T key : countMap.keySet()) {
            Integer count = countMap.get(key);
            destination.put(key, count);
        }
    }
}
