package model;

import java.util.ArrayList;

public class teste {
    public static void main(String[] args) {
        GameVO game1 = new GameVO("1", "Celeste", "https://assets.nintendo.com/image/upload/f_auto/q_auto/dpr_1.5/f_auto/q_auto/dpr_1.5/f_auto/q_auto/dpr_1.5/f_auto/q_auto/dpr_1.5/c_scale,w_400/ncom/pt_BR/games/switch/c/celeste-switch/description-image", "https://assets.nintendo.com/image/upload/c_fill,w_1200/q_auto:best/f_auto/dpr_2.0/ncom/software/switch/70010000006442/691ba3e0801180a9864cc8a7694b6f98097f9d9799bc7e3dc6db92f086759252", 0, 2500, 100, 50, 3.5);
        GameVO game2 = new GameVO("1", "Celeste", "https://assets.nintendo.com/image/upload/f_auto/q_auto/dpr_1.5/f_auto/q_auto/dpr_1.5/f_auto/q_auto/dpr_1.5/f_auto/q_auto/dpr_1.5/c_scale,w_400/ncom/pt_BR/games/switch/c/celeste-switch/description-image", "https://assets.nintendo.com/image/upload/c_fill,w_1200/q_auto:best/f_auto/dpr_2.0/ncom/software/switch/70010000006442/691ba3e0801180a9864cc8a7694b6f98097f9d9799bc7e3dc6db92f086759252", 0, 250, 100, 50, 5);
        ArrayList<GameVO> games = new ArrayList<>();
        games.add(game1);
        games.add(game2);

        for (GameVO game : games) {
            System.out.println(game.getName());
        }
    }
}
