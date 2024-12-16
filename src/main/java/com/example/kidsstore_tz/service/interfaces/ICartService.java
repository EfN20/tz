package com.example.kidsstore_tz.service.interfaces;

import com.example.kidsstore_tz.domain.Cart;

public interface ICartService {

    Cart getCart();

    Cart addCart(Cart cart);

    Cart updateCart(Cart cart);

    boolean removeCart(Cart cart);

    Cart addItemToCart(int productId);

    Cart removeItemFromCart(int productId);

    Cart changeCartItemQuantity(int productId, boolean toIncrease);

    Cart checkOut();

}
