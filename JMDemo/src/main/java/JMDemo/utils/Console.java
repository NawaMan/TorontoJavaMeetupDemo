package JMDemo.utils;

import static functionalj.list.FuncList.listOf;

public interface Console {
    
    public static void print(Object ... objs) {
    	System.out.println(listOf(objs).joinToString(" "));
    }
    public static void println() {
    	System.out.println();
    }

}
