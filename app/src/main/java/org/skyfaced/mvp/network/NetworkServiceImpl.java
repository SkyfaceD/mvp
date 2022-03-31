package org.skyfaced.mvp.network;

import java.util.Arrays;
import java.util.List;

public class NetworkServiceImpl implements NetworkService {
    @Override
    public List<String> listOfStrings() {
        return Arrays.asList(
                "Big downtown n' underground",
                "Watch out cuz I’m back",
                "Invisible my miracle",
                "From all kind of blacks",
                "Troublemaker n' peacekeeper",
                "Get moving cuz I’m back",
                "Let’s groove cuz I’m back",
                "I’m back"
        );
    }
}
