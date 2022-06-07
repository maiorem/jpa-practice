package hello.core.singleton;

public class SingletonService {

    //1. staitc 영역에 객체를 딱 1개만 생성한다.
    private static final SingletonService instance = new SingletonService();

    //2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance() {
        return instance;
    }

    //3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    //**이 객체는 getInstance 메서드를 통해서 외에는 인스턴스 생성 불가(new 키워드 사용 불가). 딱 하나의 인스턴스만 생성할 수 있음.
    private SingletonService() {}
    
    public void logic() {
        System.out.println("싱글토 ㄴ객체 로직 호출");
    }
    
}
