package com.bootx;

import com.bootx.vo.App;
import com.bootx.vo.Card;
import com.bootx.vo.Player;

import java.util.*;

public class Demo {

    public static void main(String[] args) {
        List<Card> poker = createPoker();
        // 洗牌
        Collections.shuffle(poker);
        Collections.shuffle(poker);

        // 发牌
        List<Player> players = new ArrayList<>();
        players.add(new Player("张三"));
        players.add(new Player("李四"));
        players.add(new Player("王五"));
        initPlayers(poker,players);
        sort(players);
    }

    private static void sort(List<Player> players){

        for (Player player:players) {
            Collections.sort(player.getCards(),new MyComparator());
        }
    }


    private static List<Player> initPlayers(List<Card> poker,List<Player> players) {
        System.out.println(poker.size());
        // 拿三张排出来
        for (int i=0;i<poker.size()-3;){
            for (int j=0;j<players.size();j++){
                players.get(j).getCards().add(poker.get(i++));
            }
        }

        int i = new Random().nextInt(3);
        players.get(i).setDiZhu(true);
        List<Card> cards = poker.subList(poker.size()-3,poker.size());
        players.get(i).getCards().addAll(cards);
        return players;
    }


    public static List<Card> createPoker(){
        List<Card> list = new ArrayList<>();
        for (int i=0;i< App.SUIT.length-1;i++){
            for (int j=0;j<App.RANK.length-2;j++){
                Card card = new Card(i,j);
                list.add(card);
            }
        }

        // 创建小王
        list.add(new Card(App.SUIT_JOKER,App.BLANK_JOKER));
        // 创建大王
        list.add(new Card(App.SUIT_JOKER,App.RED_JOKER));

        return list;
    }
}

class MyComparator implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
        return o1.getRank()-o2.getRank();
    }
}