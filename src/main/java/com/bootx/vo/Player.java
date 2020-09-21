package com.bootx.vo;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;

    private List<Card> cards = new ArrayList<>();

    private Boolean diZhu;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
        this.diZhu = false;
    }

    public Player(String name, List<Card> cards) {
        this.name = name;
        this.cards = cards;
        this.diZhu = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Boolean getDiZhu() {
        return diZhu;
    }

    public void setDiZhu(Boolean diZhu) {
        this.diZhu = diZhu;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", cards=" + cards +
                ", diZhu=" + diZhu +
                '}';
    }
}
