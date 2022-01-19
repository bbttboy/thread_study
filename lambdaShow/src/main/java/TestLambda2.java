/**
 * lambda表达式简化(带参数)
 * @author MrWillow
 */
public class TestLambda2 {

    public static void main(String[] args) {

        ILove love;

        // 1. 带参lambda
        love = (int d) -> {
            System.out.println("i love you " + d);
        };
        love.lambda(520);

        // 2. 简化参数类型声明
        love = (d) -> {
            System.out.println("i love you " + d);
        };
        love.lambda(521);

        // 3. 简化圆括号
        love = d -> {
            System.out.println("i love you " + d);
        };
        love.lambda(522);

        // 4. 简化花括号 (此处只适合单语句)
        love = d -> System.out.println("i love you " + d);
        love.lambda(523);
    }
}

interface ILove {
    /**
     * 接口方法
     * @param degree 程度
     */
    void lambda(int degree);
}