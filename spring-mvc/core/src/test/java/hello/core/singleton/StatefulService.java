package hello.core.singleton;

public class StatefulService {

//    private int price; //상태를 유지하는 필드

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
//        this.price = price; //여기가 문제!!!
        return price; //공유되는 필드를 사용하지 말고 파라미터 값을 그대로 넘겨버리기
    }

//    public int getPrice() {
//        return price;
//    }
}
