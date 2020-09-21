package com.bootx.vo;

/**
 * 扑克牌
 */
public class Card implements Comparable<Card> {
    /**
     * 花色
     */
    private int suit;

    /**
     * 点数
     */
    private int rank;

    public Card(int suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getSuit() {
        return suit;
    }

    public void setSuit(int suit) {
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return App.SUIT[getSuit()]+" "+App.RANK[getRank()];
    }

    @Override
    public int compareTo(Card o) {
        return this.rank-o.rank;
    }
}
