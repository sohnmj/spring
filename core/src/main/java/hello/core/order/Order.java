package hello.core.order;

public class Order {
    private int discountprice;
    private int itemprice;
    private Long memberId;
    private String name;

    public Order(int discountprice, int itemprice, Long memberId, String name) {
        this.discountprice = discountprice;
        this.itemprice = itemprice;
        this.memberId = memberId;
        this.name = name;
    }
    public int calculate(){
        return itemprice-discountprice;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getItemprice() {
        return itemprice;
    }

    public void setItemprice(int itemprice) {
        this.itemprice = itemprice;
    }

    public int getDiscountprice() {
        return discountprice;
    }

    public void setDiscountprice(int discountprice) {
        this.discountprice = discountprice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "discountprice=" + discountprice +
                ", itemprice=" + itemprice +
                ", memberId=" + memberId +
                ", name='" + name + '\'' +
                '}';
    }
}
