package ru.dvfu.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GroupBy {

    PUBLISHER("s.releasedGame.game.publisher"),
    PLATFORM("s.releasedGame.platform"),
    GENRE("s.releasedGame.game.genre");

    private final String value;

}
