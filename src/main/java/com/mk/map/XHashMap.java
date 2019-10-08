package com.mk.map;

public class XHashMap implements XMap {


    // 默认初始容量为16
    private static final int DEFALUT_CAPACITY = 16;

    // 数据存储结构
    Node[] table = new Node[DEFALUT_CAPACITY];

    // map对象长度
    private int size = 0;


    /**
     * 获取map对象长度
     * @return
     */
    public int size() {
        return this.size;
    }

    /**
     * 判断map对象是否为空
     * @return
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * 通过键key获取value值
     * @param key
     * @return
     */
    public Object get(Object key) {
        int hash = hash(key);
        int i=indexFor(hash,table.length);
        for (Node node = table[i]; node != null; node = node.next){
            if (node.key.equals(key) && hash == node.hash){
                return node.value;
            }
        }
        return null;
    }

    /**
     * 向map对象中存值
     * @param key
     * @param value
     * @return
     */
    public Object put(Object key, Object value) {
        // 获取hash值
        int hash = hash(key);
        // 通过hash值,找到这个key应该放的位置
        int index = indexFor(hash, table.length);
        //index位置已经有数据了,则更新数据
        for(Node node = table[index]; node != null; node = node.next) {
            Object k;
            //且数组中有这个key,覆盖其value
            if (node.hash == hash &&((k=node.key) == key || key.equals(k))){
                Object oldValue = node.value;
                node.value = value;
                //返回oldValue
                return oldValue;
            }
        }

        // 如果index位置没有数据，或index位置有数据但key是新的key,新增节点
        addEntry(key, value, hash, index);
        return null;
    }


    //获取hash值
    public int hash(Object key){
        return key == null ? 0 : key.hashCode();
    }


    //获取插入的位置
    public int indexFor(int hash,int length){
        return hash % length;
    }


    /**
     * 封装节点数据
     * @param key
     * @param value
     * @param hash
     * @param index
     */
    public void addEntry(Object key, Object value, int hash, int index) {

        // 添加数据，size长度加1
        size ++;

        if(size == table.length) { // 如果超过了原数组大小，则进行扩容
            // 扩容一倍
            Node[] newTable = new Node[table.length * 2];
            // 数组复制(将table中数据复制到newTable)
            System.arraycopy(table, 0, newTable, 0, table.length);
            // 将新数组赋值给table数组
            table = newTable;
        }

        // 获取数组index节点数据
        Node node = table[index];
        // 为index节点赋值(如果node节点不为空，则生成单向链表)
        table[index] = new Node(hash, key, value, node);
    }


    static class Node implements XMap.Entry {

        // hash值
        int hash;

        // 存放key值
        Object key;

        // 存放value值
        Object value;

        // 链表下一个节点
        Node next;

        Node(int hash,Object key,Object value,Node next){
            this.hash=hash;
            this.key=key;
            this.value=value;
            this.next=next;
        }

        public Object getkey() {
            return this.key;
        }

        public Object getValue() {
            return this.value;
        }
    }
}
