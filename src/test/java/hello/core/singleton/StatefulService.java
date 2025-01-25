package hello.core.singleton;

public class StatefulService {
    // 스프링 빈은 항상 무상태로 설계해야한다...!!!!!

    // private int price; // 상태를 유지하는 필드

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        // this.price = price; // 여기가 문제!
        return price;
    }

    /*public int getPrice() {
        return price;
    }*/

}