package com.jepsolucoes.fansmarvel.viewmodel;

import com.jepsolucoes.fansmarvel.model.Characters;

import java.util.ArrayList;
import java.util.List;

public class CharReceiver {

    private List<Characters> listChars;

    public List<Characters> charReceiver() {

        listChars = new ArrayList<>();

        Characters char1 = new Characters();
        char1.setName("Wolverine");
        char1.setDescription("\"Born with super-human senses and the power to heal from almost any wound, Wolverine was captured by a secret Canadian organization and given an unbreakable skeleton and claws. Treated like an animal, it took years for him to control himself. Now, he's a premiere member of both the X-Men and the Avengers.");
        listChars.add(char1);

        Characters char2 = new Characters();
        char2.setName("Wolverine");
        char2.setDescription("\"Born with super-human senses and the power to heal from almost any wound, Wolverine was captured by a secret Canadian organization and given an unbreakable skeleton and claws. Treated like an animal, it took years for him to control himself. Now, he's a premiere member of both the X-Men and the Avengers.");
        listChars.add(char1);


        return listChars;
    }

}
