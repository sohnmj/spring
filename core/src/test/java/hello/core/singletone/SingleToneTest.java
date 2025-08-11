package hello.core.singletone;

public class SingleToneTest {
    private static final SingleToneTest instance=new SingleToneTest();
    public static SingleToneTest getInstance(){
        return instance;
    }
    private SingleToneTest(){}
    public void display(){
        System.out.println("hh");
    }

}
