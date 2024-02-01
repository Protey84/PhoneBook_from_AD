package org.example.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class SwitcherTest {

    @Test
    void switchToDifferentLayout1() {
        Assertions.assertEquals("Nhjgjd", Switcher.switchToDifferentLayout("Тропов"));
    }

    @Test
    void switchToDifferentLayout2() {
        Assertions.assertEquals("Тропов", Switcher.switchToDifferentLayout("Nhjgjd"));
    }

    @Test
    void switchToDifferentLayout3() {
        Assertions.assertEquals("ЭжДюБьТ", Switcher.switchToDifferentLayout("\";L.<mN"));
    }


    @Test
    void switchCharTest1() {
        Assertions.assertEquals("б", Switcher.switchChar(",", Switcher.en, Switcher.ru));
    }
    @Test
    void switchCharTest2() {
        Assertions.assertEquals("Ю", Switcher.switchChar(">", Switcher.en, Switcher.ru));
    }

    @Test
    void switchCharTest3() {
        Assertions.assertEquals("d", Switcher.switchChar("в", Switcher.ru, Switcher.en));
    }
}