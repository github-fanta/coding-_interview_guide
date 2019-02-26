package easy.part9_other;

import java.util.HashMap;

/**
 * 哈希表常见三个操作是put, get 和 containsKey, 而且这三个操作时间复杂度为O(1)。现在想加一个setAll的功能，
 * 就是把所有记录的value都设成统一的值。请设计并实现这种有setAll的功能的哈希表，并且put、get、containsKey
 * 和setAll四个操作的时间复杂度都是O（1）
 */
public class Code02_SetAllHash {
    static class MyValue<V> {
        private V value;
        private long modCount; // 版本号
        public MyValue(V value, long modCount) {
            this.value = value;
            this.modCount = modCount;
        }
    }

    public static class MyHashMap<K, V> {
        private HashMap<K, MyValue<V>> hashMap;
        private long curMod;  // 当前版本号
        private MyValue setAll; // 存储setAll后的值。
        public MyHashMap(){
            this.hashMap = new HashMap<K, MyValue<V>>();
            this.curMod = -1;
            this.setAll = new MyValue(null, curMod);
        }

        public void setAll(V val) {
            this.setAll = new MyValue(val, ++curMod);
        }
        public void put(K key, V val) {
            hashMap.put(key, new MyValue<>(val, ++curMod));
        }

        public V get(K key) {
            MyValue<V> res = null;
            if (hashMap.containsKey(key)) {
                 res  = hashMap.get(key);
                 if (res != null && res.modCount < setAll.modCount) {
                     res = setAll;
                 }
            }
            return res.value;
        }
        public boolean isContains(K key) {
            return hashMap.containsKey(key);
        }
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("1", 1);
        map.put("2", 2);
        Integer i = map.get("2");
        System.out.println(map.isContains("3"));
        map.setAll(9);
        System.out.println(map.get("1"));
        map.put("3", 3);
        System.out.println(map.get("3"));
        System.out.println(map.get("2"));
    }
}
