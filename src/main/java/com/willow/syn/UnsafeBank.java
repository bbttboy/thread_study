package com.willow.syn;

/**
 * 不安全取钱
 * @author MrWillow
 */
public class UnsafeBank {

    public static void main(String[] args) {
        // 共同存了100W
        Account account = new Account(100);

        new Drawing(account, 50, "you").start();
        new Drawing(account, 80, "she").start();
    }
}

class Account {
    /**
     * 余额
     */
    public int balance;

    public Account(int balance) {
        this.balance = balance;
    }
}

class Drawing extends Thread {

    private int drawMoney;
    private final Account account;

    public Drawing(Account account, int drawMoney, String name) {
        super(name);
        this.account = account;
        this.drawMoney = drawMoney;
    }

    @Override
    public void run() {
        synchronized (account) {
            if (account.balance < drawMoney) {
                // 如果继承了Thread this.getName() 等价于 Thread.currentThread().getName();
                System.out.println(this.getName() + "..." + "余额不足");
            } else {
                account.balance = account.balance - drawMoney;
                System.out.println(this.getName() + "取了" + drawMoney + "W");
                System.out.println("剩余" + account.balance + "W");
            }
        }
    }
}
