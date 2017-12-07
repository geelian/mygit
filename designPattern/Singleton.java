class Singleton{
    private static Singleton singleton = new Singleton();
}

class Singleton{
    private static Singleton singleton;
    public static synchronized Singleton getInstance(){
        if(singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }
}

class Singleton{
    private static Singleton singleton;
    public static Singleton getInstance(){
        if(singleton == null){
            synchronized(Singleton.class){
                if(singleton == null){
                    return singleton;
                }
            }
        }
        return singleton;
    }
}

synchronized synchronized synchronized synchronized synchronized synchronized 
