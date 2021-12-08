package com.teamcode.creamy.Services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.teamcode.creamy.Flavor;
import com.teamcode.creamy.Interfaces.IObserver;
import com.teamcode.creamy.Interfaces.Observable;
import com.teamcode.creamy.Models.IceCream;
import com.teamcode.creamy.Models.User;

import java.util.ArrayList;

public class AuthService extends Observable implements IObserver {
    User currentUser;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    public AuthService() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void getCurrentUser() {
        user = firebaseAuth.getCurrentUser();
        currentUser = new User();
        currentUser.setId(user.getUid());

        ShoppingCartService cartService = new ShoppingCartService();
        cartService.suscribe(this);
        cartService.getCart(currentUser.getId());
    }

    @Override
    public void update(Object value) {
        if(value instanceof java.util.ArrayList) {
            if(((ArrayList<?>)value).size() == 0) {
                this.currentUser.setCarrito(new ArrayList<>());
                notifySuscribers(this.currentUser);
            }
            else if(((ArrayList<?>)value).get(0) instanceof com.teamcode.creamy.Models.IceCream) {
                this.currentUser.setCarrito((ArrayList<IceCream>) value);
                notifySuscribers(this.currentUser);
            }
        }
    }
}
